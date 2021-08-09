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
        <el-form-item label="Tags" prop="tag" id="tagForm">
          <el-input
          class="elinput"
          v-model="state.articleForm.tagKeyword"
          placeholder="#태그를 입력해 주세요. (최대 10개, 태그당 최대 길이 10자)"
          maxlength="10"
          @keyup.enter="enterTag()"
          />
        </el-form-item>
        <!-- 추가한 태그를 표시하는 곳.. -->
        <el-row style="min-height: 60px;" justify="start" align="top">
          <el-col 
          :span="3"
          v-for="tag in state.articleForm.tags" :key="tag"
          style="color: black; margin: 1px; font-size:13px; padding: 2px; border-radius: 0.2rem;"
          @click="removeTag(tag)"
          >
            {{ tag }}
          </el-col>
        </el-row>

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
  const tagDOM = ref('$tagForm')
  console.log(tagDOM)

  const state = reactive({
    fileList: [],
    store: store,
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

  enterTag() {
    // 태그가 10개를 초과하는 경우 반환
    const tags = this.state.articleForm.tags

    if ( tags.length === 10 ) {
      alert("태그는 최대 10개까지 등록할 수 있습니다.")
      return
    }

    // 입력하지 않고 엔터 누른 경우 금지
    if (this.state.articleForm.tagKeyword.trim() === ''){
      alert("추가할 태그를 입력해 주세요")
      return
    }

    // #을 포함하지 않거나, #이 가장 앞에 오지 않은 경우
    if ( this.state.articleForm.tagKeyword.trim().indexOf('#') === -1 || this.state.articleForm.tagKeyword.trim().indexOf('#') !== 0 ){
      alert("올바른 위치에 # 태그를 사용해주세요.")
    }
    // 조건에 부합하는 경우 tags에 추가한다. 
    else {
      // 이미 리스트에 있는지 검사
      for (let i = 0; i < tags.length; i++) {
        if ( tags[i] === this.state.articleForm.tagKeyword.trim() ) {
          alert("이미 존재하는 태그입니다.")
          this.state.articleForm.tagKeyword = ''
          return
        }
      }

      tags.push(this.state.articleForm.tagKeyword.trim())
      this.state.articleForm.tagKeyword = ''
      console.log(tags)
    }
  },
  removeTag(tag) {
    console.log(tag)
    // 찾아서 없애기
    for (let i = 0; i < this.state.articleForm.tags.length; i++) {
      if (tag === this.state.articleForm.tags[i]) {
        this.state.articleForm.tags.splice(i, 1)
      }
    }
  }

},
}
</script>