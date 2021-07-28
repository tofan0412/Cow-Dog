<template>
  <h1>게시글 상세 내용</h1>
  {{ state.id }}<br>
</template>

<script>
import { reactive } from '@vue/reactivity'
import { useRoute } from 'vue-router'

export default {  
  name: 'ArticleDetail',
  setup() {
    const route = useRoute()
    console.log(route.params.title)

    const state = reactive({
      id: route.params.id
    })

    return {
      state
    }
  },
  methods: {},
  }
</script>