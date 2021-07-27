<template>
  <h1>Appeal 게시판</h1>
    <div v-for="article in state.articlelist" :key="article.articleno"> <!-- 왜 key에다가 콜론을 해줘야 하지..? -->
      <router-link :to="{ name: 'AppealDetail', query: {article: article.title} }">{{ article.title }}</router-link>
    </div>
    
</template>
<script>
import axios from 'axios'
import { reactive } from '@vue/reactivity'

export default {
  name: 'BOARDLIST',  
  setup() {
    // POSTMAN Mock Server
    const serverURL = "https://5a66a063-6d26-4bf8-ae2c-fe9b30ddbb1b.mock.pstmn.io/appeal"
    // let appealList = [] // 반응형 아님. 반응형 데이터를 만들 때는 ref와 reactive를 사용한다.
    
    const state = reactive({
      articlelist: [],
    })

    // 게시글 목록 불러오기
    axios.get(serverURL, { /* parameters */ })
    .then(resp => {
      state.articlelist = resp.data
    }).catch(err => {
      console.log(err)
    })
    return {
      state
    }
  },
}
</script>