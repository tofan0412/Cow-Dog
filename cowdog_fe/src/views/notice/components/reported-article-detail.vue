<template>
  <el-button type="text" @click="dialogVisible = true">자세히</el-button>
  <el-dialog
    class="reported-article-dialog"
    title="상세보기"
    v-model="dialogVisible"
    width="70%"
    >
    <div class="reported_id">신고 대상 게시글 번호: {{ reportedArticleData.reportedArticleNo }}</div>
    <br>
    <br>
    <div class="reported_reason">신고 사유</div>
    <br>
    <div class="reported_content">{{ reportedArticleData.content }}</div>
    <br>
    <br>

    <div class="reported_reason">게시글 url</div>
    <br>
    <!-- image box -->
    <div class="reported_url_box">
      <a :href="reportedArticleData.articleUrl">{{ reportedArticleData.articleUrl }}</a>
    </div>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">취소</el-button>
        <el-button @click="dialogVisible = false">게시글 제재</el-button>
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
      reportedArticleData: {
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
.reported_url_box {
  width: 50%;
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
</style>