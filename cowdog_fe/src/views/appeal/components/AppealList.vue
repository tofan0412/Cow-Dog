<template>
  <h1>Appeal 게시판</h1>
    <div v-for="article in state.articlelist" :key="article.articleno"> <!-- 왜 key에다가 콜론을 해줘야 하지..? -->
      <router-link :to="{ name: 'AppealDetail', params: { title: article.title }}">{{ article.title }}</router-link>
      <p>{{ article.regtime }}</p>
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
    
    // vue 2에서 data의 역할을 한다.
    const state = reactive({
      articleList: {
        type: Object,
      }
    })

    // 게시글 목록 불러오기
    axios.get(serverURL, { /* parameters */ })
    .then(resp => {
      state.articlelist = resp.data
      // console.log(state.articleList)
    }).catch(err => {
      console.log(err)
    })
    return {
      state
    }
  },
}
</script>