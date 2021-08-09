<template>
  <el-container class="el-container" direction="vertical">
    <el-header class="el-header">
      <!-- 작성자 정보 -->
      <el-row>
        <el-col>
          {{ this.article.writer }}
        </el-col>
      </el-row>

      <!-- 게시글 제목 -->
      <el-row>
        <el-col>
          <h3><strong>{{ this.article.title }}</strong></h3>
        </el-col>
      </el-row>
      <!-- 작성일 정보 -->
      <el-row>
        <el-col style="font-size: 12px; color: grey">
          {{ this.article.regtime }}
        </el-col>
      </el-row>
    </el-header>


    <!-- 본문 내용 -->
    <el-main class="el-main" style="margin-top:50px;">
      <el-row style="text-align: justify;">
        {{ this.article.content }}
      </el-row>
      <el-row justify="end" v-if="this.state.loginId === this.article.memberId">
        <!-- Button Group -->
        <el-button size="mini" round @click="updateArticlePage(this.article)"><i class="el-icon-edit">수정</i></el-button>
        <el-button size="mini" round @click="deleteArticle(this.article)"><i class="el-icon-delete">삭제</i></el-button> 
      </el-row>
      <!-- 댓글 부분 -->
      <el-row style="color: gray; font-size: 15px; margin-top: 10px;">
        댓글 ??개 모두 보기
      </el-row>
      <el-row>
        댓글1
      </el-row>
      <el-row>
        댓글1
      </el-row>
      <el-row>
        댓글1
      </el-row>
      <el-row>
        댓글1
      </el-row>
      <el-divider/>
      <el-row>
        <el-input size="mini" class="elinput" placeholder="댓글 달기...">
          <template #append>게시</template>
        </el-input>
        
      </el-row>
    </el-main>
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
.el-container{
  margin: 10px;
  border: 1px solid black;
  border-radius: 0.45rem;
  height: auto;
}
.el-header > *{
  margin: 5px;
  text-align: start;
}
.el-main > *{
  margin: 5px;
  margin-top: 7px;
}

</style>