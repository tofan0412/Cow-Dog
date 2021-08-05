<template>
  <h1>Appeal 게시판</h1>
    <div v-for="article in state.articleList" 
        :key="article.articleno" 
        @click="goToDetail(article)"> <!-- 왜 key에다가 콜론을 해줘야 하지..? -->
      <div>{{ article.title }}</div>
      <p>{{ article.regtime }}</p>      
    </div>
    <button @click="createArticle()">게시글 생성</button>
</template>
<script>
import { reactive } from '@vue/reactivity'
import { useStore } from 'vuex'
import router from '../../../router'

export default {
  name: 'BOARDLIST',  
  setup() {
    const store = useStore()
    const state = reactive({
      articleList: {
        type: Array,
      },
      store: store
    })
    store.dispatch("checklogin")
    // vuex의 store의 변수 중에서 게시글 목록을 별도로 저장한다.
    store.dispatch("getArticles")
    
    state.articleList = store.getters.getArticles

    return {
      state
    }
  },
  methods: {
    createArticle() {
      this.state.store.dispatch("checklogin")

      // 로그인 여부 확인 후 이동
      router.push("/appeal/create")
    },
    goToDetail(article) {
      const article_no = article.articleNo
      this.state.store.dispatch("goToDetail", {article_no: article_no})
    }
  }
}
</script>