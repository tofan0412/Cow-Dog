<template>
  <h1>이성에게 매력을 어필해 보세요👍</h1>
  <el-row justify="center">
    <el-col :span="12">
      <el-form label-position="top"
      :model="state.articleForm" 
      :rules="state.rules"
      ref="articleForm">
        <el-form-item label="Title" prop="title">
          <el-input
          class="elinput" 
          v-model="state.articleForm.title" 
          maxlength="100"
          show-word-limit
          autocomplete="on" 
          placeholder="제목"
          style="width: 100%;"
          />
        </el-form-item>
        
        <el-form-item label="Content" prop="content">
          <el-input 
          type="textarea" 
          resize="none"
          maxlength="500" 
          show-word-limit
          rows="15"
          v-model="state.articleForm.content" 
          autocomplete="off"
          placeholder="내용" 
          />
        </el-form-item>
        
        <!-- 태그 -->
        <el-form-item label="Tags" prop="tag" >
          <el-input
          class="elinput" id="tagText"
          v-model="state.articleForm.tagKeyword" 
          autocomplete="off" 
          placeholder="#태그를 입력해 주세요.(최대 10개)"
          style="width: 100%;"
          @keyup.enter="enterTag()"
          />
        </el-form-item>
        <!-- 추가한 태그를 표시하는 곳.. -->
        <div style="min-height: 60px;">
          
        </div>

        <!-- 지도 -->


        <!-- 이미지 업로드 -->
        <el-upload
        class="upload-demo"
        action=""
        :on-preview="handlePreview"
        :on-remove="handleRemove"
        :file-list="fileList"
        list-type="picture"
        style="align: start;"
        >
        <el-button size="small" type="primary">Click to upload</el-button>
        <!-- 하단에 표시할 작은 툴팁 메세지 -->
        <template #tip>
          <div class="el-upload__tip">
            jpg/png files with a size less than 500kb
          </div>
        </template>
        </el-upload>

        <div style="margin-top: 20px;"></div>
        <el-button default type="button" @click="create()">작성</el-button>
      </el-form>
    </el-col>
  </el-row>

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
      member_id: store.getters.getUserId,
      writer: store.getters.getUserInfo.memberid,
      tagKeyword: '',
      tags: [],
    },
    rules: {
      title: [
        { required: true, message: '제목을 입력하세요', trigger: 'blur' }
      ],
      content: [
        { required: true, message: '내용을 입력하세요', trigger: 'blur' }
      ],
    },
    // 업로드하는 이미지 리스트
    fileList: [],
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
    if (this.state.articleForm.title == '') {
      alert("제목 입력")
      return
    }
    if (this.state.articleForm.content == '') {
      alert("내용 입력")
      return
    }
    this.state.store.dispatch("createArticle", 
        { title: this.state.articleForm.title, 
        content: this.state.articleForm.content, 
        member_id: this.state.articleForm.member_id ,
        writer: this.state.articleForm.writer} )
  },
  // 파일 업로드 관련 메서드
  handleRemove(file, fileList) {
    console.log(file, fileList)
    

  },
  handlePreview(file) {
    this.state.fileList.push(file)
  },

},
}
</script>