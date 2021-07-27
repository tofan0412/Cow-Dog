<template>
<<<<<<< HEAD
<div></div>
=======
  <h1>게시글 상세 내용</h1>
  {{ state.id }}<br>
>>>>>>> 6415cdfc66c81f7c329dcebc0f39e4b6e8981195
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