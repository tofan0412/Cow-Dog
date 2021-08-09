<template>
  <h3>{{ this.article.title }}</h3>
  <div>
    {{ this.article.content }}
  </div>
  <div>
    <button>Update</button>
    <button>Delete</button>
  </div>
  <hr>
</template>

<script>
import { reactive } from '@vue/reactivity'

export default {  
  name: 'ArticleDetail',
  props: {
      article: Object,
    },
  setup() {
    const state = reactive({
      
    })
    
    return {
      state
    }
  },
  methods: {},
  }
</script>