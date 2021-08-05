<template>
  <h1>게시글 상세 내용</h1>
  {{ state.article_no }}<br>
  <button>Update</button><br>
  <button>Delete</button>

</template>

<script>
import { reactive } from '@vue/reactivity'
import { useStore } from 'vuex'

export default {  
  name: 'ArticleDetail',
  setup() {
    const store = useStore()
    const state = reactive({
      article_no: store.getters.getArticleNo
    })
    
    // DB에서 게시글 객체 불러오기
    store.dispatch("findArticleByArticleNo")

    return {
      state
    }
  },
  methods: {},
  }
</script>