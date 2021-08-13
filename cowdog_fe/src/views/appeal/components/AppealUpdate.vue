<template>
  <h1>게시글 수정</h1>
  <div>사용자: {{ state.articleForm.member_id }}</div>
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
    <button type="button" @click="update()">수정</button>
  </el-form>

</template>
<script>
import { useRoute } from 'vue-router'
import { useStore } from 'vuex'
import { reactive, ref, onMounted } from 'vue'
import Swal from 'sweetalert2'
export default {
name: 'AppealCreate',
setup() {
  const articleForm = ref(null)
  const store = useStore()
  const state = reactive({
    articleForm: {
      articleNo: '',
      title: '',
      content: '',
      member_id: store.getters.getUserId,
      writer: store.getters.getUserInfo.memberid
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

  // 대상 게시글 정보를 불러온다. 
  const route = useRoute()
  state.articleForm.articleNo = route.params.articleNo

  store.dispatch("getArticle", { articleNo: state.articleForm.articleNo })
  .then(function (result) {
    console.log("단일 게시글 결과 : ", result.data)
    state.articleForm.title = result.data.title
    state.articleForm.content = result.data.content
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
  update() {
    if (this.state.articleForm.title == '') {
      Swal.fire("제목 입력")
      return
    }
    if (this.state.articleForm.content == '') {
      Swal.fire("제목 입력")
      return
    }

    this.state.store.dispatch("updateArticle", 
        { articleNo: this.state.articleForm.articleNo,
        title: this.state.articleForm.title, 
        content: this.state.articleForm.content, 
        member_id: this.state.articleForm.member_id ,
        writer: this.state.articleForm.writer})
  }
},
}
</script>