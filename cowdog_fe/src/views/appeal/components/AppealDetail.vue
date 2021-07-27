<template>
  <h1>게시글 상세 내용</h1>
  {{ $route.query.article }}
</template>

<script>
import { useRoute } from 'vue-router'

export default {  
  name: 'ArticleDetail',
  setup() {
    const route = useRoute()
    const data = route.query.article
    console.log(data)

  },
  methods: {},
  }
</script>