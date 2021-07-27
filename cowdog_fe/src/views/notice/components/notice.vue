<template>
<div class="container">
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
        <el-button class="button" type="text">수정</el-button>
        <el-button class="button" type="text">삭제</el-button>
      </div>
    </el-card>
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
</template>
<script>
import { useStore } from 'vuex'

export default {

  data() {
    return {
      // for pagination
      pageSize: 5,
      currentPage: 1,
    }
  },
  computed: {
    noticesPaginated() {
      var start = 0 + (this.currentPage-1) * this.pageSize
      var end = this.currentPage * this.pageSize
      return this.notices.slice(start, end) //기본값 0~5번
    }
  },

  setup() {
    const store = useStore()
    const notices = store.state.notices
    return { notices }
  }
}
</script>
<style>
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
    width: 60vw;
  }

  .card-body {
    /* 글자 수 제한을 넘어가면 ... 처리, 좌측 정렬 */
    /* https://junistory.blogspot.com/2017/06/css-ellipsis.html 참조 */
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
    color: #FF4E7E;
  }
</style>