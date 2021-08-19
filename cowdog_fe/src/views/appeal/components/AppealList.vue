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
    <el-row justify="center">
      <el-col :span="12"> <!-- offset 설정하면 왼쪽 기준으로 공백 크기 설정 -->
        <!-- 게시글 객체가 존재하지 않을 수도 있다. v-if의 경우 조건에 맞지 않으면 렌더링을 하지 않음 -->
        <div v-for="article in state.articles" :key="article.articleno"> <!-- 왜 key에다가 콜론을 해줘야 하지..? -->
          <appealDetail :article="article" @send-tag="AppealSearchByClick" @delete-article="afterDeleteArticle" />
        </div>
      </el-col>
    </el-row>

    <el-row  justify="center" style="margin-bottom: 25px;">
      <!-- 이전 페이지 버튼 -->
      <el-col :span="6" v-if="state.store.getters.getCurrentPageNumber > 0">
        <el-button default plain @click="previousPage()">이전 페이지</el-button>
      </el-col>
      <!-- 다음 페이지 버튼 -->
      <el-col :span="6" v-if="state.store.getters.getCurrentPageNumber < state.store.getters.getTotalPageNumber">
        <el-button default plain @click="nextPage()">다음 페이지</el-button>
      </el-col>
    </el-row>
    
  </div>


  <!-- 사용자가 검색을 한 경우 -->
  <div v-if="state.default">
    <div style="margin: 20px;">
      <span style="font-size: 20px; font-weight: bold;">#{{ state.lastKeyword }}</span> 검색 결과
    </div>
    <el-row justify="center">
      <el-col :span="12"> <!-- offset 설정하면 왼쪽 기준으로 공백 크기 설정 -->
        <!-- 게시글 객체가 존재하지 않을 수도 있다. v-if의 경우 조건에 맞지 않으면 렌더링을 하지 않음 -->
        <div v-if="state.searchResults.length !== 0">
          <div v-for="article in state.searchResults" :key="article.articleno"> <!-- 왜 key에다가 콜론을 해줘야 하지..? -->
            <appealDetail :article="article" @send-tag="AppealSearchByClick" @delete-article="afterDeleteArticle" />
          </div>
        </div>

        <div v-else>
          <i class="el-icon-warning-outline" style="font-size: 250px;"></i><br>
          <el-row justify="center" style="margin: 10px;">
            검색결과가 없습니다.
          </el-row>
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

    state.articles = store.getters.getArticles

    console.log("전체 페이지 수는 ", state.store.getters.getTotalPageNumber)

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
    createArticle() {
      router.push("/appeal/create")
    },

    AppealSearch() {
      // 검색어가 없다면 
      if (this.state.searchKeyword === '') {
        Swal.clickConfirm("")
        Swal.fire("경고", "검색어를 입력해 주세요.")
        
        return
      }

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

    nextPage() {
      // 먼저 vuex의 currentPage 변수를 변화한다.
      let currentPageNumber = this.state.store.getters.getCurrentPageNumber
      this.state.store.commit("SET_CURRENT_PAGE_NUMBER", ++currentPageNumber)

      this.state.store.dispatch("nextPage", { nextPage : this.state.store.getters.getCurrentPageNumber })
      .then(resp => {
        console.log("다음 페이지 로딩결과: " , resp)
        const result = resp.data.content

        // 태그 전처리하기 -> 태그 개별 항목 검색 위해...
        for (let i = 0; i < result.length; i++) {
          if (result[i].tags !== null) {
            const tags = result[i].tags.split('#').slice(1)
            result[i].tags = tags
          }
        }

        // 날짜 전처리
        for (let i = 0; i < result.length; i++) {
          const date = new Date(result[i].regtime).toDateString()
          result[i].regtime = date
        }

        // 전체 페이지 수를 중앙 저장소에 저장한다.
        const totalPageNumber = resp.data.totalPages - 1
        this.state.store.commit("SET_TOTAL_PAGE_NUMBER", totalPageNumber)
        this.state.store.commit("SET_ARTICLES", result)
        this.state.articles = this.state.store.getters.getArticles
      })
      .catch(err => {
        console.log(err)
      })      
    },

    previousPage() {
      // 먼저 vuex의 currentPage 변수를 변화한다.
      let currentPageNumber = this.state.store.getters.getCurrentPageNumber
      this.state.store.commit("SET_CURRENT_PAGE_NUMBER", --currentPageNumber)

      this.state.store.dispatch("previousPage", { previousPage : this.state.store.getters.getCurrentPageNumber })
      .then(resp => {
        console.log("이전 페이지 로딩결과: " , resp)
        const result = resp.data.content

        // 태그 전처리하기 -> 태그 개별 항목 검색 위해...
        for (let i = 0; i < result.length; i++) {
          if (result[i].tags !== null) {
            const tags = result[i].tags.split('#').slice(1)
            result[i].tags = tags
          }
        }

        // 날짜 전처리
        for (let i = 0; i < result.length; i++) {
          const date = new Date(result[i].regtime).toDateString()
          result[i].regtime = date
        }

        // 전체 페이지 수를 중앙 저장소에 저장한다.
        const totalPageNumber = resp.data.totalPages - 1
        this.state.store.commit("SET_TOTAL_PAGE_NUMBER", totalPageNumber)
        this.state.store.commit("SET_ARTICLES", result)
        this.state.articles = this.state.store.getters.getArticles // 만약 set_articles에 router.go 조건을 넣으면 필요 없음
      })
      .catch(err => {
        console.log(err)
      })  
    },

    afterDeleteArticle(result) {
      // articles 초기화
      this.state.articles = ''

      // 1. 게시글을 삭제한 후, 현재 페이지에 해당하는 게시글이 존재하지 않는 경우
      if (result === "NODATA_IN_THIS_PAGE") {
        console.log("현재 페이지의 게시글이 없으므로, 앞 페이지 게시글 정보를 불러옵니다.")
        this.previousPage()
        return
      } 
      if (result === "ZERO_DATA_WHOLE_PAGES") {
        this.state.store.commit("SET_ARTICLES_AFTER_DELETE", [])
        this.state.articles = this.state.store.getters.getArticles
        return
      }
      // 2. 현재 페이지에 게시글이 남아 있는 경우...
      else {
        // vuex에 변경사항 저장
        this.state.store.commit("SET_ARTICLES_AFTER_DELETE", result)
        this.state.articles = result
      }
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
  position: sticky;
  margin: 0 auto;
  bottom: 0;
  width: 97%;
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