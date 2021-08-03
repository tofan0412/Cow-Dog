<template>
  <h1>게시글 작성 페이지입니다.</h1>
  <label for="title">게시글 제목</label><br>
  <input type="text" name="title" v-model="state.article.title" placeholder="제목을 입력하세요"><br>
  <label for="content">내용</label>
  <br>
  <textarea name="content" id="content" v-model="state.article.content" cols="30" rows="10" placeholder="내용을 입력하세요"></textarea>
  <button @click="articleCreate()">작성</button>
</template>
<script>
import { reactive } from 'vue'
import axios from 'axios'

export default {
name: '',
components: {},
data() {
  return {
    sampleData: ''
  }	
},
setup() {
  const state = reactive({
    article: {
      title: "",      
      content: "",
      member_id: "",
    }
  })

  return {
    state
  }
},
methods: {
  
  articleCreate() {
    // console.log(this.state.article)

    axios.post("http://localhost:8081/cowdog/appeal/create", { 'article' : this.state.article })
    .then(resp => {
      console.log(resp)
    })
    .catch(err => {
      console.log(err)
    })
  }
},
}
</script>