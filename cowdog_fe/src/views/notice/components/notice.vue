<template>
<div v-for="(notice, idx) in noticesPaginated" :key="idx">
  <el-card class="box-card">
    <template #header>
      <div class="card-header">
        <span>{{ notice.title }}</span>
        <el-button class="button" type="text">자세히</el-button>
      </div>
    </template>
    <div>{{ notice.content }}</div>
  </el-card>
</div>

  <el-pagination
  background
  layout="prev, pager, next"
  :page-size=pageSize
  :total="notices.length"
  v-model:current-page="currentPage">
</el-pagination>

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
  methods: {

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

  .box-card {
    width: 80vw;
  }

  .el-card {
    margin: 10px;
  }
</style>