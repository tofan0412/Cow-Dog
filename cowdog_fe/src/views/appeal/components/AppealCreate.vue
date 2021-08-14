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
        <div class="input-name profile-comment">함께 올릴 사진을 선택하세요</div>
          <div class="button-and-img-name">
          <div class="appeal-img-preview-box"><img class="appeal-img-preview" :src="imagePreview"></div>
          <div class="img-upload-btn-box"><label class="img-upload-btn" for="imgfiles"><i class="fas fa-arrow-circle-up"></i>&nbsp;업로드</label></div>
          <input type="file" id="imgfiles" ref="imgfiles" @change="handleFileUpload" multiple style="display:none"/>
        </div>
        <!-- 하단에 표시할 작은 툴팁 메세지 -->
        <template #tip>
          <div class="el-upload__tip">
            jpg/png files with a size less than 500kb
          </div>
        </template>

        <div style="margin-top: 20px;"></div>
        <el-button default type="button" @click="create">작성</el-button>
      </el-form>
    </el-col>
  </el-row>

</template>
<script>
import { useStore } from 'vuex'
import { reactive, ref, onMounted } from 'vue'
import axios from 'axios'
import Swal from 'sweetalert2'

let files=[]
export default {
name: 'AppealCreate',
  data(){
    return{
      imagePreview: "#",
      files:[],
      url:null,
    }
  },
  methods:{
    handleFileUpload(e) {
            const file = e.target.files[0]
            this.imagePreview = URL.createObjectURL(file)
            console.log(this.$refs.imgfiles.files)
            files = this.$refs.imgfiles.files;
            this.url=files[0].name
            console.log(files);
          },
  },
setup() {
  const articleForm = ref(null)
  const store = useStore()
  const state = reactive({
    headerInfo: {
        'Access-Control-Allow-Origin': '*',
        Authorization:"Bearer "+store.getters.getUserToken,
        ContentType: 'multipart/form-data'
    },
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
    
    store: store,
  })
  onMounted(() => {
    store.dispatch("checklogin")
  })
  const ImageUpload=function(){  
        console.log("이미지 업로드~")
        console.log(files)
        console.log(files[0])
        console.log(state.articleForm.member_id)
        for (var i = 0; i < files.length; i++) {
            let formData = new FormData();
            formData.append('title', "profile");
            formData.append('files', files[i]);
            formData.append('userId',state.articleForm.writer)
            axios.post('http://localhost:8080/cowdog/appeal/imageListImgaeUpload',
            
            formData, {
                headers: {
                'Content-Type': 'multipart/form-data',
                Authorization:"Bearer "+store.getters.getUserToken
                },
            }
            )  
            .then(function() {
            console.log('SUCCESS!!');
            })
            .catch(function() {
                console.log('FAILURE!!');
                    });
            }
    }

    const create=function(){
      console.log("글 작성~")
    if (state.articleForm.title == '') {
      Swal.fire("!!!","제목 입력")
      return
    }
    if (state.articleForm.content == '') {
      Swal.fire("!!!","내용 입력")
      return
    }

    ImageUpload()

    state.store.dispatch("createArticle", 
        { title: state.articleForm.title, 
        content: state.articleForm.content, 
        member_id: state.articleForm.member_id ,
        writer: state.articleForm.writer} )
  
    }
    const handleRemove=function(file){
        console.log(file)
    }
    const handlePreview=function(file){
      console.log(file)
      this.state.fileList.push(file)
    }

  return {
    state,
    articleForm,
    ImageUpload,
    create,
    handleRemove,
    handlePreview
  }
},

}
</script>

<style scoped>
.appeal-img-preview-box {
    width: 70%;
    height: 200px;
    border-radius: none;
    overflow: hidden;
    border: 2px dashed #ff4e7e;
    margin: 10px auto;
}
.appeal-img-preview{
  width: 100%;
  height: 200px;
}
</style>
