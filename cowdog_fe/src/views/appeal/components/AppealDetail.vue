<template>
  <h1>게시글 상세 내용</h1>
  {{ $route.query.title }}<br>
  {{ $route.query.content }}<br>
  {{ $route.query.member_id }}<br>
  {{ $route.query.regtime }}<br>
  {{ $route.query.image }}<br>
  {{ $route.query.longitude }}<br>
  {{ $route.query.distance }}<br>
  {{ $route.query.address }}<br>
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