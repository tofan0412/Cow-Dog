<template>
<div>
  <el-button type="text" @click="dialogVisible = true">자세히</el-button>
  <el-dialog
    class="notice-detail"
    title="상세보기"
    v-model="dialogVisible"
    width="70%"
    >
    <div class="reported_id">제목: {{ noticeDetailData.title }}</div>
    <br>
    <br>
    <div class="reported_reason">내용</div>
    <br>
    <div class="reported_content">{{ noticeDetailData.content }}</div>
    <br>
    <br>

    <div class="reported_reason">첨부파일</div>
    <br>
    <!-- image box -->
    <div class="reported_img_box">
      <!-- 첨부 이미지가 있으면 보여주기 -->
      <img :src="require('@/assets/images/user_report_example_image.png')" alt="reportedScreenImage">
    </div>

    <template #footer>
      <div class="dialog-footer" style="display:flex">
        <div style="margin-right:5px">
          <el-button @click="moveToDetailUpdate">수정</el-button>
        </div>
        <div>
          <el-button @click="dialogVisible = false">삭제</el-button>
        </div>
      </div>
    </template>
  </el-dialog>
</div>
</template>

<script>
import router from '../../../router'
  export default {
    data() {
      return {
        dialogVisible: false
      };
    },
    props: {
      noticeDetailData: {
        type: Object
      }
    },
    methods: {
      moveToDetailUpdate() {
        this.dialogVisible = false
        router.push('/admin/noticeDetailUpdate')
        
      },
      handleClose(done) {
        this.$confirm('Are you sure to close this dialog?')
          .then(res => {
            done();
            console.log(res)
            this.dialogVisible = false
          })
          .catch(res => {
            console.log(res)
          });
            
      }
    }
  };
</script>

<style scoped>
.el-dialog {
  text-align: left;
  border-radius: 10px;
}
.reported_id {
  font-size: 16px;
  color: black;
}
.reported_reason {
  font-size: 16px;
  color: black;
}
.reported_content {
  font-weight: normal;
}
.reported_img_box {
  width: 100%;
  border: 1px solid black;
  border-radius: 10px;
}

.el-button--primary {
  background: #ffffff;
  border-color: #ff4e7e;
}
.el-button--primary:hover {
  background: #ffffff;
  border-color: #ff4e7e;
}

.el-button--default {
  background: #ffffff;
  border-color: #324545;
}
.el-button--default:hover {
  background: #ffffff;
  border-color: #ff4e7e;
}

.el-button:focus {
  color: #FF4E7E;
}
img {
  width: 300px;
  height: 100%;
}

.dialog-footer {
  justify-content: flex-end;
}
</style>