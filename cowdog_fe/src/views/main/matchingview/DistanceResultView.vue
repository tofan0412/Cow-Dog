<template>
    <div v-if="state.distanceUsers" class="random_user_view"  >
        <el-row>
            <el-col :span="8"  v-for="user in distanceUsersPaginated" :key="user.id" style="display: inline-table;">
                <distance-result-view-detail :user="user" />
            </el-col>
        </el-row>
    </div>
    <div class="pagination" style="margin:0 auto; margin-top:10px">
    <el-pagination
    background
    layout="prev, pager, next"
    :page-size=pageSize
    :total="distanceUsers.length"
    v-model:current-page="currentPage">
    </el-pagination>
  </div>
    <div v-if="state.distanceUsers[0]==null" class="no_user">
        <h1 style="margin-top:20%">거리에 해당되는 사람이 없습니다!!!</h1>
    </div>
     <div>
        <el-button class="back" @click="back">뒤로 가기</el-button>
    </div>
</template>

<script>

import {  reactive } from 'vue'
import { mapGetters, useStore } from 'vuex'
import DistanceResultViewDetail from './DistanceResultViewDetail.vue'
import router from '../../../router'
export default {
        data() {
        return {
        // for pagination
        pageSize: 3, // 한 페이지에 나오는 게시물 수
        currentPage: 1, // 현재 페이지(v-model 적용)
        }
    },
    methods: {
        moveToNoticeCreate() {
        router.push('/admin/noticeCreate')
        }
    },
    computed: {
        distanceUsersPaginated() {
        var start = 0 + (this.currentPage-1) * this.pageSize
        var end = this.currentPage * this.pageSize
        return this.distanceUsers.slice(start, end) //기본값 0~5번
        },
        ...mapGetters({
        distanceUsers: 'getDistanceUser'
        })
    },
    components:{
        DistanceResultViewDetail
    },

    setup() {
        
        const store=useStore()
         const state = reactive({
           distanceUsers:store.getters.getDistanceUser
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

.no_user{
    width: 500px;
    height: 650px;
    margin: 0 auto;
    background-color: rgb(196, 196, 196,15%);
    border: 1px solid rgb(196, 196, 196,15%);
    border-radius: 5%;
}
</style>