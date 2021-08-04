<template>
  <h1>게시글 작성</h1>
  <el-form :model="state.articleForm" :rules="state.rules" ref="articleForm">
    <el-form-item prop="title" :label-width="state.formLabelWidth">
      <label for="title">제목</label>
      <el-input class="elinput" v-model="state.articleForm.title" autocomplete="off"  placeholder="제목"></el-input>
    </el-form-item>
    <br>
    <el-form-item prop="content" :label-width="state.formLabelWidth">
      <label for="content">내용</label>
      <el-input class="el-textarea" v-model="state.articleForm.content" autocomplete="off"  placeholder="내용"></el-input>
    </el-form-item>
    <button type="button" @click="create()">작성</button>
  </el-form>
  


  

</template>
<script>
import { useStore } from 'vuex'
import { reactive, ref, onMounted } from 'vue'

export default {
name: 'AppealCreate',
setup() {
  const articleForm = ref(null)
  const store = useStore()
  const state = reactive({
    articleForm: {
      title: '',
      content: '',
    },
    rules: {
      title: [
        { required: true, message: '제목을 입력하세요', trigger: 'blur' }
      ],
      content: [
        { required: true, message: '내용을 입력하세요', trigger: 'blur' }
      ],
    },
    store: store,
  })
  onMounted(() => {
    store.dispatch("checklogin")
  })

  return {
    state,
    articleForm
  }
},
methods: {
  create() {
    console.log(this.state.articleForm)
    if (this.state.articleForm.title == '') {
      alert("제목 입력")
      return
    }
    if (this.state.articleForm.content == '') {
      alert("내용 입력")
      return
    }
    this.state.store.dispatch("createArticle", {title:this.state.article.title, content: this.state.article.content} )
  }
},
}
</script>