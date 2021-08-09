<template>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css" integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
    <div class="random_user_view">
        <el-row >
            <el-col :span="8"  v-for="user in randomUsersPaginated" :key="user.id" style="display: inline-table;" >
                <random-result-view-detail :user="user" />
            </el-col>
        </el-row>
        
    </div>
     <div>
         <div class="pagination" style="margin:0 auto; margin-top:10px;">
            <el-pagination
                background
                layout="prev, pager, next"
                :page-size=pageSize
                :total="randomUsers.length"
                v-model:current-page="currentPage">
            </el-pagination>
        </div>
        <el-button class="back" @click="back">뒤로 가기</el-button>
    </div>
</template>

<script>

import {  reactive } from 'vue'
import { mapGetters, useStore } from 'vuex'
import RandomResultViewDetail from './RandomResultViewDetail.vue'
import router from '../../../router'
export default {

    data() {
    return {
      // for pagination
      pageSize: 3, // 한 페이지에 나오는 게시물 수
      currentPage: 1, // 현재 페이지(v-model 적용)
    }
  },
  computed:{
      randomUsersPaginated() {
      var start = 0 + (this.currentPage-1) * this.pageSize
      var end = this.currentPage * this.pageSize
      return this.randomUsers.slice(start, end) //기본값 0~5번
    },
     ...mapGetters({
      randomUsers: 'getRandomUser'
    })
  },
    components:{
        RandomResultViewDetail
    },

    setup() {
        
        const store=useStore()
         const state = reactive({
           randomUsers:store.getters.getRandomUser
        })
         const back=function(){
            router.push("/main")
        }
        return{
            state,back
            
        }
    }
}

</script>
<style>
.random_user_view{
    margin: 0 auto;
}
.back{
    margin: 0 auto;
    margin-top: 40px;
}
.el-card.is-always-shadow, .el-card.is-hover-shadow:focus, .el-card.is-hover-shadow:hover {
    -webkit-box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
    box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
    margin: 0 auto;
}
</style>