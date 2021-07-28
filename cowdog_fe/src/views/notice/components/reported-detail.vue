<template>
  <el-button type="text" @click="dialogVisible = true">자세히</el-button>
  <el-dialog
    class="reported-dialog"
    title="상세보기"
    v-model="dialogVisible"
    width="70%"
    >
    <div class="reported_id">신고 대상 ID: {{ reportedUserData.reported_id }}</div>
    <br>
    <br>
    <div class="reported_reason">신고 사유</div>
    <br>
    <div class="reported_content">{{ reportedUserData.content }}</div>
    <br>
    <br>

    <div class="reported_reason">캡처 이미지</div>
    <br>
    <!-- image box -->
    <div class="reported_img_box">
      <img :src="require('@/assets/images/user_report_example_image.png')" alt="reportedScreenImage">
    </div>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">취소</el-button>
        <el-button @click="dialogVisible = false">사용자 제재</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>
  export default {
    data() {
      return {
        dialogVisible: false
      };
    },

    props: {
      reportedUserData: {
        type: Object
      }
    },
    methods: {
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

<style>
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
img {
  width: 300px;
  height: 100%;
}
</style>