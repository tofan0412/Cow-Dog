<template>
  <h1>Appeal 게시판</h1>
    <div v-for="article in state.articlelist" :key="article.articleno"> <!-- 왜 key에다가 콜론을 해줘야 하지..? -->
      <router-link :to="{ name: 'AppealDetail', params: { id: article.articleno }}">{{ article.title }}</router-link>
      <p>{{ article.regtime }}</p>      
    </div>
    <button @click="createArticle()">게시글 생성</button>
</template>
<script>
import { reactive } from '@vue/reactivity'
import { useStore } from 'vuex'
import { onMounted } from 'vue'
import router from '../../../router'

export default {
  name: 'BOARDLIST',  
  setup() {
    const store = useStore()
    const state = reactive({
      articleList: {
        type: Object,
      },
      store: store
    })
    onMounted(() => {
      store.dispatch("checklogin")
    })

    return {
      state
    }
  },
  methods: {
    createArticle() {
      this.state.store.dispatch("checklogin")

      // 로그인 여부 확인 후 이동
      router.push("/appeal/create")
    }
  }
}
</script>