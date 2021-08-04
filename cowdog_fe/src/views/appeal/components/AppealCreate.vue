<template>
  <h1>게시글 작성 페이지입니다.</h1>
  <label for="title">게시글 제목</label><br>
  <input type="text" name="title" v-model="state.article.title" placeholder="제목을 입력하세요"><br>
  <label for="content">내용</label>
  <br>
  <textarea name="content" id="content" v-model="state.article.content" cols="30" rows="10" placeholder="내용을 입력하세요"></textarea>
  <button @click="createArticle()">작성</button>
</template>
<script>
import { reactive } from 'vue'
import { useStore } from 'vuex'

export default {
name: '',
components: {},
data() {
  return {
    sampleData: ''
  }	
},
setup() {
  const store = useStore()
  console.log(store)
  const state = reactive({
    article: {
      title: "",      
      content: "",
      member_id: "tofan",
    },
    store: store
  })

  // 인증된 사용자에 한해 작성 가능
  state.store.dispatch("checkLogin")

  return {
    state
  }
},
methods: {
  createArticle() {
    if (this.state.article.title.length == 0) {
      alert("제목을 입력해 주세요.")
      return
    }
    else if (this.state.article.content.length == 0) {
      alert("내용을 입력해 주세요.")
      return
    }
    
    console.log(this.state.store)
    this.state.store.dispatch("createArticle", this.state.article)
  }
},
}
</script>