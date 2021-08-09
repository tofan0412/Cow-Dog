<template>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css" integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
    <div v-if="state.recomUsers" class="random_user_view">
        <el-row >
            <el-col :span="8"  v-for="user in recomUsersPaginated" :key="user.id" style="display: inline-table;" >
                <recom-result-view-detail :user="user" />
            </el-col>
        </el-row>
         <div class="pagination" style="margin:0 auto; margin-top:10px;">
            <el-pagination
                background
                layout="prev, pager, next"
                :page-size=pageSize
                :total="recomUsers.length"
                v-model:current-page="currentPage">
            </el-pagination>
        </div>
    </div>
     <div>
        <div v-if="state.recomUsers[0]==null" class="no_user">
            <h1 style="margin-top:20%">매칭 조건이 맞는 사람이 없습니다!!!</h1>
        </div>
        <el-button class="back" @click="back">뒤로 가기</el-button>
    </div>
</template>

<script>

import {  reactive } from 'vue'
import { mapGetters, useStore } from 'vuex'
import RecomResultViewDetail from './RecomResultViewDetail.vue'
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
      
      recomUsersPaginated() {
      var start = 0 + (this.currentPage-1) * this.pageSize
      var end = this.currentPage * this.pageSize
      return this.recomUsers.slice(start, end) //기본값 0~5번
    },
     ...mapGetters({
      recomUsers: 'getRecomUser'
    })
  },
    components:{
        RecomResultViewDetail
    },

    setup() {
        
        const store=useStore()
         const state = reactive({
           recomUsers:store.getters.getRecomUser
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