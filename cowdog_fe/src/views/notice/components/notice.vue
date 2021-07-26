<template>
  <div>
    {{ notices }}
  </div>
</template>
<script>
import { useStore } from 'vuex'

export default {
  setup() {
    const store = useStore()
    const notices = store.state.notices
    return { notices }
  }
}
</script>
<style>
  
</style>