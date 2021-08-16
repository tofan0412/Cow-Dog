<template>
    <el-menu
      :router="true"
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color="white"
      style="position: sticky; top: 0; z-index: 1;"
    >
      <el-row justify="center" align="middle" style="margin: 10px;">
        <el-col :span="8">
          <el-input 
          class="elinput" 
          placeholder="검색할 태그를 입력하세요." 
          v-model="state.searchKeyword" 
          @keyup.enter="AppealSearch()">
          </el-input>
        </el-col>
      </el-row>
    </el-menu>
  
  <!-- 작성된 모든 게시글 출력 -->
  <div v-if="!state.default">
    <el-row>
      <el-col :span="12" :offset="6"> <!-- offset 설정하면 왼쪽 기준으로 공백 크기 설정 -->
        <!-- 게시글 객체가 존재하지 않을 수도 있다. v-if의 경우 조건에 맞지 않으면 렌더링을 하지 않음 -->
        <div>
          <div v-for="article in state.articles" :key="article.articleno"> <!-- 왜 key에다가 콜론을 해줘야 하지..? -->
            <appealDetail :article="article"/>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>

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

  <!-- 사용자가 검색을 한 경우 -->
  <div v-if="state.default">
    {{ state.lastKeyword }} 검색 결과
    <el-row>
      <el-col :span="12" :offset="6"> <!-- offset 설정하면 왼쪽 기준으로 공백 크기 설정 -->
        <!-- 게시글 객체가 존재하지 않을 수도 있다. v-if의 경우 조건에 맞지 않으면 렌더링을 하지 않음 -->
        <div>
          <div v-for="article in state.searchResults" :key="article.articleno"> <!-- 왜 key에다가 콜론을 해줘야 하지..? -->
            <appealDetail :article="article"/>
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row justify="center">
      <el-col>
        <el-button class="default" plain @click="state.default = false; state.searchKeyword = ''">전체 게시글 보기</el-button>
        
      </el-col>
    </el-row>

  </div>




  <!-- 글 작성 버튼 하단 고정 -->
  <el-row style="margin-top: 50px;">
    <el-button 
    type="danger" plain 
    @click="createArticle()" 
    style="color: black; width: 100%; position: fixed; bottom: 0;">새 게시글 작성하기</el-button>
  </el-row>
</template>

<script>
import { reactive } from '@vue/reactivity'
import { useStore } from 'vuex'
import router from '../../../router'
import appealDetail from './AppealDetail.vue'


export default {
  name: 'AppealList',  
  components: {
    appealDetail,
  }, 
  setup() {
    const store = useStore()
    const state = reactive({
      searchKeyword: '',
      lastKeyword: '',
      store: store,
      articles: store.getters.getArticles,
      searchResults: '',
      default: false,
    })

    if (store.getters.getUserToken === '') {
      alert("로그인 해주세요.")
      router.push("/login")
    }
  
    return {
      state
    }    
  },
  methods: {
    createArticle() {
      router.push("/appeal/create")
    },
    AppealSearch() {
      this.state.store.dispatch("appealSearch", { searchKeyword : this.state.searchKeyword })
      .then(resp => {
        // 태그 전처리       
        for (let i = 0; i < resp.data.length; i++) {
          if (resp.data[i].tags !== null) {
            const tags = resp.data[i].tags.split('#').slice(1)
            resp.data[i].tags = tags
          }
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
  }
}
</script>

<style>
</style>