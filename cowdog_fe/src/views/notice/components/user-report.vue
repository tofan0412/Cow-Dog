<template>

<div class="container">
  <div class="batch" style="display:flex">
      <h1 class="title">유저 신고 관리</h1>
  </div>
  <div class="reportedUser-list">
    <div v-for="(reportedUser, idx) in reportedUsersPaginated" :key="idx">
      <el-card class="box-card">
        <template #header>
          <div class="card-header">
            <span>신고 대상 번호: {{ reportedUser.reportedId }}</span>
          </div>
        </template>
        <div class="card-body">{{ reportedUser.content }}</div>
        <br>
        <div class="card-footer">
          <el-button class="button" type="text"><reportedDetail :reportedUserData="reportedUser" /></el-button>
          <el-button class="button" type="text" @click="deleteUserReport(reportedUser.userReportNo)">삭제</el-button>
        </div>
      </el-card>
    </div>
  </div>
  <div class="pagination">
    <el-pagination
    background
    layout="prev, pager, next"
    :page-size=pageSize
    :total="reportedUsers.length"
    v-model:current-page="currentPage">
    </el-pagination>
  </div>  
</div>

<button @click="userReport">신고 테스트</button>
</template>

<script>
import { mapGetters } from 'vuex'
import reportedDetail from './reported-detail.vue'

export default {

  data() {
    return {
      // for pagination
      pageSize: 5,
      currentPage: 1,
    }
  },
  components: {
    reportedDetail,
  },

  computed: {
    reportedUsersPaginated() {
      var start = 0 + (this.currentPage-1) * this.pageSize
      var end = this.currentPage * this.pageSize
      return this.reportedUsers.slice(start, end) //기본값 0~5번
    },
    ...mapGetters({
      reportedUsers: 'getReportedUsers'
    })
  },

  // 신고 POST 예시
  methods: {
    userReport() {
      this.$store.dispatch('postUserReport', {"title": "정지시켜주세요.", "content": "악질이에요.", "reportedId": "2"})
    },
    deleteUserReport(userReportNo) {
      this.$confirm('정말로 신고를 삭제하시겠습니까?')
        .then(res => {
          console.log(res)
          this.$store.dispatch('deleteUserReport', userReportNo)
        })
        .catch(err => {
          console.log(err)
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
  .reportedUser-list {
    width: 80vw;
  }

  .box-card {
    width: 100%;
  }

  .el-card {
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