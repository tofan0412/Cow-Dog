<template>
  <h1>Appeal 게시판</h1>
    <div v-for="article in state.articleList" :key="article.articleno" > <!-- 왜 key에다가 콜론을 해줘야 하지..? -->
      <appealDetail :article="article"/>
      <div>{{ article.title }}</div>
      <p>{{ article.regtime }}</p>      
    </div>
    <button @click="createArticle()">게시글 생성</button>
</template>
<script>
import { reactive } from '@vue/reactivity'
import { useStore } from 'vuex'
import router from '../../../router'
import appealDetail from './AppealDetail.vue'

export default {
  name: 'BOARDLIST',  
  components: {
    appealDetail,
  }, 
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
      // 로그인 여부 확인 후 이동. 근데 목록을 다시 불러와야 함..
      this.state.store.dispatch("getArticles") 
      router.push("/appeal/create")
    },
  }
}
</script>