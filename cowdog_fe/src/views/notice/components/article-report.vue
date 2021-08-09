<template>

<div class="container">
  <div class="batch" style="display:flex">
      <h1 class="title">게시글 신고 관리</h1>
  </div>
  <div class="reportedArticle-list">
    <div v-for="(reportedArticle, idx) in reportedArticlesPaginated" :key="idx">
      <el-card class="box-card">
        <template #header>
          <div class="card-header">
            <span>신고 대상 번호: {{ reportedArticle.reportedArticleNo }}</span>
          </div>
        </template>
        <div class="card-body">{{ reportedArticle.content }}</div>
        <br>
        <div class="card-footer">
          <el-button class="button" type="text"><reportedArticleDetail :reportedArticleData="reportedArticle" /></el-button>
          <el-button class="button" type="text" @click="deleteArticleReport(reportedArticle.articleReportNo)">삭제</el-button>
        </div>
      </el-card>
    </div>
  </div>
  <div class="pagination">
    <el-pagination
    background
    layout="prev, pager, next"
    :page-size=pageSize
    :total="reportedArticles.length"
    v-model:current-page="currentPage">
    </el-pagination>
  </div>  
</div>

<button @click="ArticleReport">게시글 신고 테스트</button>
</template>

<script>
import { mapGetters } from 'vuex'
import reportedArticleDetail from './reported-article-detail.vue'

export default {

  data() {
    return {
      // for pagination
      pageSize: 5,
      currentPage: 1,
    }
  },
  components: {
    reportedArticleDetail,
  },

  computed: {
    reportedArticlesPaginated() {
      var start = 0 + (this.currentPage-1) * this.pageSize
      var end = this.currentPage * this.pageSize
      return this.reportedArticles.slice(start, end) //기본값 0~5번
    },
    ...mapGetters({
      reportedArticles: 'getReportedArticles'
    })
  },

  // 신고 POST 예시
  methods: {
    ArticleReport() {
      this.$store.dispatch('postArticleReport', {"title": "정지시켜주세요.", "content": "악질이에요.", "reportedArticleNo": "173", "articleUrl": "http://www.naver.com"})
    },
    // 게시글 신고 삭제(게시글 삭제 아님)
    deleteArticleReport(articleReportNo) {
      this.$confirm('정말로 신고를 삭제하시겠습니까?')
        .then(res => {
          console.log(res)
          this.$store.dispatch('deleteArticleReport', articleReportNo)
        })
    }
  },
}
</script>

<style scoped>
  .title {
    text-align: left;
    margin-left: 10px;
  }

  .card-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
  }

  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .container {
    width: 60vw;
  }

  .batch {
    justify-content: space-between;
    align-items: baseline;
  }
  .reportedArticle-list {
    width: 80vw;
  }

  .box-card {
    width: 100%;
  }

  .reportedArticle-list .el-card {
    width: 100%;
    height: 20%;
    margin: 10px;
  }

  .el-pagination.is-background .el-pager li:not(.disabled).active {
    background-color: #FF4E7E;
    color: #FFF;
  }

  .pagination {
    width: 80vw;
  }

  .card-body {
    /* 글자 수 제한을 넘어가면 ... 처리, 좌측 정렬 /
    / https://junistory.blogspot.com/2017/06/css-ellipsis.html 참조 */
    text-align: left;
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 1;
    -webkit-box-orient: vertical;
    word-wrap:break-word;
    height: 16px;
  }

  .card-footer {
    text-align: right;
  }

  .button {
    color: #323545;
    font-weight: bold;
  }

  .button:hover {
    color: #FF4E7E;
  }

  .create {
    color: #323545;
    font-weight: bold;
  }
  .create:hover {
    background-color: #FFFFFF;
    border-color: #FF4E7E;
    color: #FF4E7E;
  }

  .el-button {
    color: #323545;
    font-weight: bold;
  }

  .el-button:hover {
    color: #FF4E7E;
  }

  .el-button:focus {
    color: #FF4E7E;
  }

</style>