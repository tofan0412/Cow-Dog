<template>
  <h1>Appeal 게시판</h1>
    <div v-for="article in state.articlelist" :key="article.articleno"> <!-- 왜 key에다가 콜론을 해줘야 하지..? -->
      <router-link :to="{ name: 'AppealDetail', params: { id: article.articleno }}">{{ article.title }}</router-link>
      <p>{{ article.regtime }}</p>
    </div>
    
</template>
<script>
import { reactive } from '@vue/reactivity'
import { useStore } from 'vuex' // vuex의 store를 사용하기 위해 사용한다.

export default {
  name: 'BOARDLIST',  
  setup() {
    const store = useStore()
    const state = reactive({
      articleList: {
        type: Object,
      }
    })
    // 게시글 목록 불러오기
    state.articleList = store.dispatch("getArticles")
    
    return {
      state
    }
  },
}
</script>