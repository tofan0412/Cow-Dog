<template>
<div>
  <div class="container">
    <div class="batch" style="display:flex">
        <h1 class="title">공지사항</h1>
        <div><el-button class="create" @click="moveToNoticeCreate">Create</el-button></div>
    </div>
    <div class="notice-list">
      <div v-for="(notice, idx) in noticesPaginated" :key="idx">
        <el-card class="box-card">
          <template #header>
            <div class="card-header">
              <span>{{ notice.title }}</span>
            </div>
          </template>
          <div class="card-body">{{ notice.content }}</div>
          <br>
          <div class="card-footer">
            <noticeDetail :noticeDetailData="notice" />
          </div>
        </el-card>
      </div>
    </div>
  </div>

  <div class="pagination">
    <el-pagination
    background
    layout="prev, pager, next"
    :page-size=pageSize
    :total="notices.length"
    v-model:current-page="currentPage">
    </el-pagination>
  </div>
</div>
</template>

<script>
// import { useStore } from 'vuex'
import { mapGetters } from 'vuex'
import router from '../../../router'
import noticeDetail from './notice-detail.vue'
export default {
  data() {
    return {
      // for pagination
      pageSize: 5, // 한 페이지에 나오는 게시물 수
      currentPage: 1, // 현재 페이지(v-model 적용)
    }
  },

  components: {
    noticeDetail,
  },

  methods: {
    moveToNoticeCreate() {
      router.push('/admin/noticeCreate')
    },
  },

  computed: {
    noticesPaginated() {
      var start = 0 + (this.currentPage-1) * this.pageSize
      var end = this.currentPage * this.pageSize
      return this.notices.slice(start, end) //기본값 0~5번
    },
    ...mapGetters({
      notices: 'getNotices'
    })
  },
}
</script>

<style>
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
  .notice-list {
    width: 60vw;
  }

  .box-card {
    width: 100%;
  }

  .notice-list .el-card {
    width: 100%;
    height: 30%;
  }
  .notice-list .el-card.is-always-shadow {
    margin: 10px auto;
  }

  .el-pagination.is-background .el-pager li:not(.disabled).active {
    background-color: #FF4E7E;
    color: #FFF;
  }

  .pagination {
    width: 60vw;
  }

  .card-body {
    /* 글자 수 제한을 넘어가면 ... 처리, 좌측 정렬 /
    / https://junistory.blogspot.com/2017/06/css-ellipsis.html 참조 */
    text-align: left;
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 3;
    -webkit-box-orient: vertical;
    word-wrap:break-word;
    line-height: 1.2em;
    height: 3.6em;
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