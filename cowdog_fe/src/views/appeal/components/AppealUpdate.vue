<template>
  <div class="article-update-background">
    <h1>게시글 수정</h1>
    <div class="article-update-container">
      <el-form class="article-update-form" :model="state.articleForm" :rules="state.rules" ref="articleForm">
        <el-form-item prop="title">
          <label for="title" class="title-label">제목</label>
          <el-input type="text" class="article-title-update" v-model="state.articleForm.title"></el-input>
        </el-form-item>
        <br>
        <el-form-item prop="content">
          <label for="content" class="content-label">내용</label>
          <el-input type="textarea" class="article-content-update" v-model="state.articleForm.content" rows="20"></el-input>
        </el-form-item>
        <div class="article-update-button" @click="update()">수정</div>
      </el-form>
    </div>
  </div>
</template>
<script>
import { useRoute } from 'vue-router'
import { useStore } from 'vuex'
import { reactive, ref } from 'vue'
import router from '../../../router'
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

  if (store.getters.getUserToken === '') {
    Swal.fire('로그인 해주세요.')
    router.push("/login")
  }

  // 대상 게시글 정보를 불러온다. 
  const route = useRoute()
  state.articleForm.articleNo = route.params.articleNo

  store.dispatch("getArticle", { articleNo: state.articleForm.articleNo })
  .then(function (result) {
    console.log("단일 게시글 결과 : ", result.data)
    state.articleForm.title = result.data.title
    state.articleForm.content = result.data.content
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
      Swal.fire("내용 입력")
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

<style>
.article-update-background {
  background: #f0f2f5;
  padding: 2%;
}
.article-update-container {
  width: 60%;
  margin: 0 auto;
  padding: 3%;
  border-radius: 10px;
  background: #fff;
}
.title-label {
  display: block;
  font-weight: bold;
  font-size: 20px;
  text-align: left;
}
.content-label {
  display: block;
  font-weight: bold;
  font-size: 20px;
  text-align: left;
}
.article-update-container .el-input__inner {
  border: 0;
  border-bottom: 1px solid #f0f2f5;
  border-radius: 0;
  padding: 0;
}
.article-update-button {
  width: 90%;
  margin: 0 auto;
  padding: 1%;
  font-weight: bold;
  font-size: 14px;
  border: 2px solid #ff4e7e;
  border-radius: 5px;
}
.article-update-button:hover {
  cursor: pointer;
  background: #ff4e7e;
  color: #fff;
}
</style>