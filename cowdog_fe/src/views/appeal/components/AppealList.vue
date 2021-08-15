<template>
    <el-menu
      :router="true"
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color=""
      style="position: sticky; top: 0; z-index: 1;"
    >
      <el-row justify="center" align="middle" style="margin: 10px;">
        <el-col :span="8">
          <el-input 
          class="elinput" 
          placeholder="검색할 태그를 입력하세요." 
          v-model="state.searchKeyword" 
          @keyup.enter="appealSearch()">
          </el-input>
        </el-col>
      </el-row>
    </el-menu>
  
  <el-row>
    <el-col :span="12" :offset="6"> <!-- offset 설정하면 왼쪽 기준으로 공백 크기 설정 -->
      <div v-for="article in articleList" :key="article.articleno"> <!-- 왜 key에다가 콜론을 해줘야 하지..? -->
        <appealDetail :article="article"/>
      </div>
    </el-col>
  </el-row>
  <br><br><br>
  <!-- 글 작성 버튼 하단 고정 -->
  <div class="bottom-create-bar" @click="createArticle()">새 게시글 작성하기</div>
  <!-- <el-row class="bottom-create-bar" style="margin-top: 50px;">
    <el-button
    @click="createArticle()" 
    style="width: 100%; position: fixed; bottom: 0;"><dir>새 게시글 작성하기</dir>
    </el-button>
  </el-row> -->
</template>
<script>
import { reactive } from '@vue/reactivity'
import {  useStore, mapGetters } from 'vuex'
import router from '../../../router'
import appealDetail from './AppealDetail.vue'

export default {
  name: 'BOARDLIST',  
  components: {
    appealDetail,
  }, 
  
  setup() {
    const store = useStore()
    const state = reactive({
      searchKeyword: '',
      store: store
    })
    store.dispatch("checklogin")
    // vuex의 store의 변수 중에서 게시글 목록을 별도로 저장한다.
    store.dispatch("getArticles")
    store.dispatch("getNotification",store.getters.getUserId)//알림 뭐 왔나 백엔드에서 가져오는거
    return {
      state
    }
    
  },
  methods: {
    createArticle() {
      this.state.store.dispatch("checklogin")
      router.push("/appeal/create")
    },
    appealSearch() {
      this.state.store.dispatch("appealSearch", { searchKeyword: this.state.searchKeyword })
    },
  },
  computed: {
    ...mapGetters({
      articleList: 'getArticles', // 알림
    })
  }
  
}
</script>

<style>
.bottom-create-bar {
  position: fixed;
  bottom: 0;
  width: 100%;
  padding: 1%;
  border: 1px solid #f0f2f5;
  background: #f0f2f5;
  font-weight: bold;
}
.bottom-create-bar:hover {
  color: white;
  background: #ff4e7e;
  cursor: pointer;
}
</style>