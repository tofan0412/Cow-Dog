<template>
  <el-container class="el-container" direction="vertical">

    <!-- 작성자 정보 -->
    <el-row justify="start" align="middle">
      <!-- 사용자가 등록한 이미지가 존재하는 경우 -->
      <el-col :span="2" v-if="state.file_path !== null" style="align: center;">
        <img class="profile" :src="state.file_path">&nbsp;
      </el-col>
      
      <!-- 사용자가 등록한 이미지가 존재하지 않는 경우 -->
      <el-col :span="2" v-if="state.file_path === null" style="align: center;">
        <img class="profile" :src="require('@/assets/images/defaultProfile.png')"
        >&nbsp;
      </el-col>

      <el-col :span="4">
        <span>{{ this.article.writer }}</span>
      </el-col>
      <!-- 게시글 삭제, 회원 정보 조회 -->
      <el-col :span="18" style="text-align: end; padding-right: 10px;">
        <i class="el-icon-more-outline" style="font-size: 25px; margin-right: 10px;" @click="centerDialogVisible = true"></i>
      </el-col>
      
    </el-row>
    
    <!-- 게시글 제목 -->
    <div class="header">
    <el-row>
      <el-col style="text-align: start;">
        <h2><strong>{{ this.article.title }}</strong></h2>
      </el-col>
    </el-row>
    <!-- 작성일 정보 -->
    <el-row> 
      <el-col style="text-align: start; font-size: 12px; color: grey">
        {{ this.article.regtime }}
      </el-col>
    </el-row>
    </div>

    <!-- 본문 내용 -->
    <el-main class="el-main">
      <el-row justify="end" v-if="this.state.loginId === this.article.memberId">
        <!-- Button Group -->
        <el-button size="mini" round @click="updateArticlePage(this.article)"><i class="el-icon-edit">수정</i></el-button>
        <el-button size="mini" round @click="deleteArticle(this.article)"><i class="el-icon-delete">삭제</i></el-button> 
      </el-row>

      <el-row style="text-align: justify; margin-top: 70px; margin-bottom: 70px; white-space:pre-wrap;">
        {{this.article.file_path}}
        {{ this.article.content }}
      </el-row>
      
      <!-- 태그 부분 -->
      <el-row style="color: #9F81F7; font-size: 15px; margin-top: 20px;">
        <div v-for="tag in state.tags" :key="tag" style="cursor: pointer;">
          <strong>{{ tag }}</strong>&nbsp;&nbsp;
        </div>
        
      </el-row>

      <!-- 댓글 부분 -->
      <el-row style="color: gray; font-size: 15px; margin-top: 20px;">
        <!-- 댓글이 1개 이상 존재하는 경우 -->
        <div v-if="state.comments.length > 0" style="cursor: pointer; margin:3px;">
          <div v-if="!state.default" @click="state.default = true">
            댓글 {{ state.comments.length }}개 모두 보기
          </div>
          <div v-else @click="state.default = false">
            댓글 숨기기
          </div>          
        </div>

        <!-- 댓글이 작성되지 않은 경우 -->
        <div v-else>
          아직 작성된 댓글이 없습니다.
        </div>
      </el-row>
      

      <!-- 기본 3개만 보여주기 -->
      <div v-if="!state.default">
        <el-row v-for="(comment, index) in state.comments" :key="index" justify="space-between">
          <el-col :span="22" v-if="index < 3" style="text-align: start;">
            <strong>{{ comment.id }}</strong> {{ comment.content }} <span style="color: grey; font-size: 12px;">-{{ comment.regtime }}</span>
          </el-col>
          <el-col :span="2" v-if="index < 3">
            <!-- 내가 작성한 댓글에 대해서만 삭제 가능하다. -->
            <span 
            v-if="comment.id === this.state.store.getters.getUserInfo.memberid" 
            style="color:#ff4e7e; cursor: pointer;"
            @click="deleteComment(comment.no)"
            >x</span>
          </el-col>
        </el-row>
      </div>

      <!-- 모든 댓글 다 보여주기 -->
      <div v-if="state.default">
        <el-row v-for="(comment, index) in state.comments" :key="index" justify="space-between">
          <el-col :span="22" style="text-align: start;">
            <strong>{{ comment.id }}</strong> {{ comment.content }} <span style="color: grey; font-size: 12px;">-{{ comment.regtime }}</span>
          </el-col>
          <el-col :span="2">
            <span 
            v-if="comment.id === this.state.store.getters.getUserInfo.memberid" 
            style="color:#ff4e7e; cursor: pointer;"
            @click="deleteComment(comment.no)"
            >x</span>
          </el-col>
        </el-row>
      </div>

      <el-divider/>
      <el-row align="middle">
        <el-col :span="22">
          <el-input size="mini" class="elinput" v-model="state.commentContent" placeholder="댓글 달기..." @keyup.enter="createComment()"/>
        </el-col>
        <el-col :span="2">
          <p id="commentCreateBtn" @click="createComment()" style="cursor: pointer;">게시</p>
        </el-col>
        
      </el-row>
    </el-main>
  </el-container>

  <!-- modal창 -->
  <el-dialog
    title=""
    v-model="centerDialogVisible"
    width="30%"
    destroy-on-close
    show-close="false"
    top="17%"
    center
    >
    <div class="dialog" style= "cursor: pointer; font-size: 20px;">
      <el-row @click="goToReportPage()">
        <el-col>
          <span style="color: red;">게시글 신고</span>
          <el-divider></el-divider>
        </el-col>
      </el-row>
      <el-row @click="true">
        <el-col>
          <span>사용자 조회</span>
          <el-divider></el-divider>
        </el-col>
      </el-row>
      <el-row @click="centerDialogVisible = false">
        <el-col>
          <span>취소</span>
        </el-col>
      </el-row>
    </div>
    <!-- <template #footer>
      <span class="dialog-footer">
      </span>
    </template> -->

  </el-dialog>
</template>

<script>
import { reactive, ref } from '@vue/reactivity'
import { useStore } from 'vuex'
import router from '../../../router'

export default {  
  name: 'ArticleDetail',
  props: {
      article: Object,
    },
  setup(props) {
    const store = useStore()
    const state = reactive({
      
      store: store,
      loginId: store.getters.getUserId,
      articleNo: props.article.articleNo,
      file_path: '',
      commentContent: '',
      comments: [],
      default: false,
      tags: [],
      
    })

    // 태그 처리
    if (props.article.tags !== null) {
      // # 앞, 뒤로 분할해서 0번째 인덱스가 공백이 들어간다. 따라서 slice 사용
      const tags = props.article.tags.split('#').slice(1)
      for (let i = 0; i < tags.length; i++) {
        tags[i] = '#' + tags[i]
      }
      state.tags = tags
    }
    

    // 게시글마다 댓글 조회한다.
    state.store.dispatch("findComments", { articleNo: props.article.articleNo })
    .then(resp => {
      state.comments = resp.data
    })
    .catch(err => {
      console.log(err)
    })

    // 게시글 마다 회원 정보를 조회한다. 회원이 등록한 이미지 정보를 출력하기 위해!
    state.store.dispatch("getUserInfo", { userId : props.article.memberId })
    .then(resp => {
      // 회원이 등록한 프로필 이미지 정보를 불러온다.
      state.file_path = resp.data.file_path
    })
    .catch(err => {
      console.log(err)
    })
    return {
      state,
      centerDialogVisible: ref(false),
    }
  },
  methods: {
    deleteArticle() {
      this.state.store.dispatch("deleteArticle", { articleNo: this.article.articleNo, memberId: this.article.memberId })
      // 페이지 새로고침
      return window.location.reload()
    },
    // 페이지 갱신
    updateArticlePage(article) {
      this.state.store.dispatch("updateArticlePage", { article : article })
    },
    
    // 댓글 작성
    createComment() {
      const comment = {
        articleNo: this.article.articleNo,
        memberId: this.state.store.getters.getUserId,
        content: this.state.commentContent,
      }

      // 빈칸인 경우 입력 방지
      if ( this.state.commentContent === '') {
        alert("내용을 입력해 주세요.")
        return
      }
      
      this.state.store.dispatch("createArticleComment", { comment: comment })
      .then(resp => {
        console.log(resp)
        // 댓글 작성후 작성한 내용 없애준다.
        this.state.commentContent = ''
      })
      .catch(err => {
        console.log(err)
      })

      // 댓글 작성 이후 페이지 갱신해야 한다.
      this.state.store.dispatch("findComments", { articleNo: this.state.articleNo })
      .then(resp => {
        this.state.comments = resp.data
        // 페이지 새로고침
        this.$router.go();
      })
      .catch(err => {
        console.log(err)
      })
    },

    deleteComment(commentNo) {
      console.log("삭제하고자 하는 댓글 : ", commentNo)
      const result = confirm("댓글을 삭제하시겠습니까?")
      if (result) {
        this.state.store.dispatch("deleteComment", { commentNo: commentNo })
        .then(resp => {
          console.log(resp.data)
          // 페이지 새로고침
          this.$router.go();
        })
        .catch(err => {
          console.log(err)
        })
      }
    },
    goToReportPage(){
      router.push("/admin/article-report")
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
.header{
  padding-left: 15px;
  padding-right: 15px;
} 
.el-main{
  padding: 15px;
}

.el-main > *{
  margin-top: 7px;
}
#commentCreateBtn{
  color: #323545;
  font-weight: bold;
}
.profile {
  position: relative;
  top: 8px;
  border-radius: 70%;
  margin: 5px;
}
.dialog > *{
  margin: 5px;
  text-align: center;
}

</style>
