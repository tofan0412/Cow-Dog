<template>
  <el-container class="article">
  <el-header>
    <el-row justify="space-between" align="middle">
      <el-col :span="12" style="text-align: start;">
        <h3><strong>{{ this.article.title }}</strong></h3>
      </el-col>
      <el-col :span="6" style="text-align: end;">
        {{ this.article.writer }}
      </el-col>
    </el-row>
    <el-row justify="start">
      {{ this.article.regtime }}
    </el-row>
  </el-header>
  
  <el-main style="margin-top: 30px;">
    <el-row justify="start" style="min-height: 150px;">
      {{ this.article.content }}
    </el-row>
  </el-main>

  <el-footer>
    <el-row justify="end">
      <!-- 게시글 작성자가 아닌 경우 출력하지 않는다. -->
      <div v-if="this.state.loginId === this.article.memberId">
        <el-button plain @click="updateArticlePage(this.article)">Update</el-button>
        <el-button plain @click="deleteArticle(this.article)">Delete</el-button>
      </div>  
    </el-row>
    
  </el-footer>
  </el-container>
  
  
  
</template>

<script>
import { reactive } from '@vue/reactivity'
import { useStore } from 'vuex'

export default {  
  name: 'ArticleDetail',
  props: {
      article: Object,
    },
  setup() {
    const store = useStore()
    const state = reactive({
      store: store,
      loginId: store.getters.getUserId
    })
    
    return {
      state
    }
  },
  methods: {
    deleteArticle() {
      this.state.store.dispatch("deleteArticle", { articleNo: this.article.articleNo, memberId: this.article.memberId })
      // 페이지 새로고침
      return window.location.reload()
    },
    updateArticlePage(article) {
      this.state.store.dispatch("updateArticlePage", { article : article })
    }

  },
  }
</script>
<style>
.article{
  border: 1px solid black;
  border-radius: 0.45rem;
  margin-top: 10px;
}
</style>