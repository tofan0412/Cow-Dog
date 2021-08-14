<template>
  <el-row>
    <el-col :span="12" :offset="6"> <!-- offset 설정하면 왼쪽 기준으로 공백 크기 설정 -->
      <div v-for="article in state.articleList" :key="article.articleno"> <!-- 왜 key에다가 콜론을 해줘야 하지..? -->
        <appealDetail :article="article"/>
      </div>
      <button @click="createArticle()">게시글 생성</button>
    </el-col>
  </el-row>
</template>
<script>
import { reactive } from '@vue/reactivity'
import { useStore } from 'vuex'
import router from '../../../router'
import appealDetail from './AppealDetail.vue'
import { onMounted } from 'vue'

export default {
  name: 'BOARDLIST',  
  components: {
    appealDetail,
  }, 
  setup() {
    const store = useStore()
    const state = reactive({
      articleList: {
        type: Array,
      },
      store: store
    })
    store.dispatch("checklogin")
    
    // 게시글 목록 갱신
    onMounted(() => {
      store.dispatch("getArticles")
      state.articleList = store.getters.getArticles
    })

    return {
      state
    }
    
  },
  methods: {
    createArticle() {
      this.state.store.dispatch("checklogin")
      router.push("/appeal/create")
    },
  }
}
</script>

<style>
</style>