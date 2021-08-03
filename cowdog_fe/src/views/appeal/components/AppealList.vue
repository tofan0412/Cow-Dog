<template>
  <h1>Appeal 게시판</h1>
    <div v-for="article in state.articlelist" :key="article.articleno"> <!-- 왜 key에다가 콜론을 해줘야 하지..? -->
      <router-link :to="{ name: 'AppealDetail', params: { id: article.articleno }}">{{ article.title }}</router-link>
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
    const serverURL = "http://localhost:8081/cowdog/appeal/"
    
    // vue 2에서 data의 역할을 한다.
    const state = reactive({
      articleList: {
        type: Object,
      }
    })

    // 게시글 목록 불러오기
    axios.get(serverURL, { headers: {Authorization:"Bearer "+state.accessToken} })
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