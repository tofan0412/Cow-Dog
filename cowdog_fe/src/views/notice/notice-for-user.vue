<template>
<div class="bg">
  <div class="container">
    <div class="batch" style="display:flex">
    </div>
    <div class="notice-list">
      <div v-for="(notice, idx) in noticesPaginated" :key="idx">
        <el-card class="box-card">
          <template #header>
            <div class="card-header">
              <div class="userNoticeTitle" style="font-weight:bold">{{ notice.title }}</div>
            </div>
          </template>
          <pre class="card-body">{{ notice.content }}</pre>
          <br>
          <div class="card-footer">
            <notice-detail-for-user :noticeDetailData="notice" />
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
import { mapGetters, useStore } from 'vuex'
import NoticeDetailForUser from './components/notice-detail-for-user.vue'
export default {
  data() {
    return {
      // for pagination
      pageSize: 5, // 한 페이지에 나오는 게시물 수
      currentPage: 1, // 현재 페이지(v-model 적용)
    }
  },

  components: {
    NoticeDetailForUser,
  },
  computed: {
    noticesPaginated() {
      var start = 0 + (this.currentPage-1) * this.pageSize
      var end = this.currentPage * this.pageSize
      return this.notices.slice(start, end) //기본값 0~5번
    },
    ...mapGetters({
      notices: 'getNoticesForUser'
    }),
  },
  methods: {
    // noticeContent(content) {
    //   return content.replace("\n", "<br/>")
    // }
  },

  setup() {
    const store=useStore()
    store.dispatch("getNotification",store.getters.getUserId)//알림 뭐 왔나 백엔드에서 가져오는거

  }
}
</script>

<style scoped>
.container {
  width: 100vw;
}
.notice-list {
  width: 90vw;
  margin: 5% auto;
}
.pagination {
  display: block;
  margin: 0 auto;
}
/* .bg {
  background: #f0f2f5;
} */
</style>