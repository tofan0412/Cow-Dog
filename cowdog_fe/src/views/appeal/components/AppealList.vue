<template>
    <el-menu
      :router="true"
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color=""
      style="position: sticky; top: 0; z-index: 1;"
    >
      <el-row justify="center" align="middle" style="margin: 10px;">
        <el-col :span="8">
          <el-input 
          class="elinput" 
          placeholder="검색할 태그를 입력하세요." 
          v-model="state.searchKeyword" 
          @keyup.enter="AppealSearch()"
          >
          </el-input>
        </el-col>
      </el-row>
    </el-menu>
  
  <!-- 작성된 게시글이 존재하지 않는 경우 -->
  <div v-if="state.articles.length === 0" style="vertical-align: middle;">
    <el-row justify="center" style="margin: 10px;">
      <i class="el-icon-warning-outline" style="font-size: 250px;" />
    </el-row>
    <br>
    <el-row justify="center" style="margin: 10px;">
      작성된 게시글이 아직 없습니다.
    </el-row>
    <br>
    <el-row justify="center" style="margin: 10px;">
      <el-button default plain @click="createArticle()">게시글 작성하기</el-button>
    </el-row>    
  </div>

  
  <!-- 작성된 모든 게시글 출력 -->
  <div v-else-if="!state.default">
    <el-row>
      <el-col :span="12" :offset="6"> <!-- offset 설정하면 왼쪽 기준으로 공백 크기 설정 -->
        <!-- 게시글 객체가 존재하지 않을 수도 있다. v-if의 경우 조건에 맞지 않으면 렌더링을 하지 않음 -->
        <div>
          <div v-for="article in state.articles" :key="article.articleno"> <!-- 왜 key에다가 콜론을 해줘야 하지..? -->
            <appealDetail :article="article" @send-tag="AppealSearchByClick" @delete-article="afterDeleteArticle" />
          </div>
        </div>
      </el-col>
    </el-row>
  </div>


  <!-- 사용자가 검색을 한 경우 -->
  <div v-if="state.default">
    <div style="margin: 20px;">
      <span style="font-size: 20px; font-weight: bold;">#{{ state.lastKeyword }}</span> 검색 결과
    </div>
    <el-row>
      <el-col :span="12" :offset="6"> <!-- offset 설정하면 왼쪽 기준으로 공백 크기 설정 -->
        <!-- 게시글 객체가 존재하지 않을 수도 있다. v-if의 경우 조건에 맞지 않으면 렌더링을 하지 않음 -->
        <div>
          <div v-for="article in state.searchResults" :key="article.articleno"> <!-- 왜 key에다가 콜론을 해줘야 하지..? -->
            <appealDetail :article="article" @send-tag="AppealSearchByClick" @delete-article="afterDeleteArticle" />
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row justify="center" style="margin-bottom: 70px;">
      <el-col>
        <el-button class="default" plain @click="state.default = false; state.searchKeyword = ''">전체 게시글 보기</el-button>
      </el-col>
    </el-row>

  </div>

  <!-- 글 작성 버튼 하단 고정 -->
  <div class="bottom-create-bar" @click="createArticle()">새 게시글 작성하기</div>
</template>

<script>
import { reactive } from '@vue/reactivity'
import { useStore, mapGetters } from 'vuex'
import router from '../../../router'
import appealDetail from './AppealDetail.vue'
import Swal from 'sweetalert2'
import { onMounted } from '@vue/runtime-core'
import axios from 'axios'
axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
const OPENVIDU_SERVER_URL = "https://i5b104.p.ssafy.io:9090";
const OPENVIDU_SERVER_SECRET = "cowdog123";

export default {
  name: 'AppealList',  
  components: {
    appealDetail,
  }, 
  
  setup() {
    const store = useStore()
    const state = reactive({
      store: store,
      searchKeyword: '',
      lastKeyword: '',
      articles: store.getters.getArticles,
      searchResults: '',
      default: false,
    })
    onMounted(() => {
          setTimeout(() => {  
            axios.get(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`, {
              auth: {
                username: 'OPENVIDUAPP',
                password: OPENVIDU_SERVER_SECRET,
              },
            }).then(response=>{
              let content = response.data.content;
              
              for(let i=0;i<content.length;i++){
                if(content[i].id==store.getters.getUserInfo.memberid){
                  Swal.fire({
                      position: 'middle',
                      icon: 'question',
                      title: '누군가가 화상회의를 걸어왔어요!',
                      showConfirmButton: false,
                      timer: 1500
                  }).then(response=>{
                    router.push({name:'VideoChat'});
                    console.log(response);
                    // store.state.centerDialogVisible = true
                    
                  })
                  return;                            
                }
              }
              
            })
            }, 1500);
        })

    if (store.getters.getUserToken === '') {
      Swal.fire('로그인 해주세요.')
      router.push("/login")
    }
    store.dispatch("getNotification",store.getters.getUserId) //알림 뭐 왔나 백엔드에서 가져오는거
  
    return {
      state
    }    
  },
  methods: {
    afterDeleteArticle(result) {
      console.log("afterDeleteArticle: ", result)
      // vuex에 변경사항 저장
      this.state.store.commit("SET_ARTICLES_AFTER_DELETE", { articles: result })
      this.state.articles = result
    },

    createArticle() {
      router.push("/appeal/create")
    },

    AppealSearch() {
      // 초기화
      console.log("검색 시작")
      this.state.searchResults = ''

      this.state.store.dispatch("appealSearch", { searchKeyword : this.state.searchKeyword })
      .then(resp => {
        // 태그 전처리       
        for (let i = 0; i < resp.data.length; i++) {
          if (resp.data[i].tags !== null) {
            const tags = resp.data[i].tags.split('#').slice(1)
            resp.data[i].tags = tags
          }
        }

        // 날짜 전처리
        for (let i = 0; i < resp.data.length; i++) {
          const date = new Date(resp.data[i].regtime).toDateString()
          resp.data[i].regtime = date
        }

        this.state.lastKeyword = this.state.searchKeyword
        console.log("검색 결과는...", resp.data)
        this.state.searchResults = resp.data

        // 보기 방식 전환
        this.state.default = true
      })
      .catch(err => {
        console.log(err)
      })
    },
    AppealSearchByClick(tag) {
      this.state.searchKeyword = tag
      this.AppealSearch()
    },
    computed: {
      ...mapGetters({
        articleList: 'getArticles', // 알림
      })
    }
  }
  
}
</script>

<style>
.bottom-create-bar {
  position: fixed;
  bottom: 0;
  width: 100%;
  padding: 1%;
  border: 1px solid #f0f2f5;
  background: #f0f2f5;
  font-weight: bold;
}

.bottom-create-bar:hover {
  color: white;
  background: #ff4e7e;
  cursor: pointer;
}
</style>