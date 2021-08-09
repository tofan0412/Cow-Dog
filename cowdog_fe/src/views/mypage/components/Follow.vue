<template>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css" integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
    <div v-if="state.followUsers" class="random_user_view"  >
        <el-row>
            <el-col :span="8"  v-for="user in followUsersPaginated" :key="user.id" style="display: inline-table;">
                <follow-detail :user="user" />
            </el-col>
        </el-row>
    </div>
    <div class="pagination" style="margin:0 auto; margin-top:10px">
    <el-pagination
    background
    layout="prev, pager, next"
    :page-size=pageSize
    :total="followUsers.length"
    v-model:current-page="currentPage">
    </el-pagination>
  </div>
    <div v-if="state.followUsers[0]===null" class="no_user">
        <h1 style="margin-top:20%">나를 팔로우 하는 사람이 없습니다!!!</h1>
    </div>
     <div>
        <el-button class="back" @click="back">뒤로 가기</el-button>
    </div>
</template>

<script>
import { reactive } from "@vue/reactivity"
import { onMounted } from "@vue/runtime-core"
import { mapGetters, useStore } from "vuex"
import router from "../../../router"
import FollowDetail from "./FollowDetail.vue"

export default ({

     data() {
        return {
            // for pagination
            pageSize: 6, // 한 페이지에 나오는 게시물 수
            currentPage: 1, // 현재 페이지(v-model 적용)
        }
    },
    methods: {
       
    },
   
    computed: {


        followUsersPaginated() {
            var start = 0 + (this.currentPage-1) * this.pageSize
            var end = this.currentPage * this.pageSize
            return this.followUsers.slice(start, end) //기본값 0~5번
        },

        ...mapGetters({
            followUsers: 'getFollowUsers'
        })
    },
    components:{
        FollowDetail
    },

    setup() {
        
        const store=useStore()
        const state = reactive({
           followUsers:store.getters.getFollowUsers
        })
        const back=function(){
            router.push("/main")
        }
        onMounted(() => {
            store.dispatch("getFollowUsers")
            state.followUsers=store.getters.getFollowUsers
        })
        return{
            state,back
            
        }
    }
})
</script>



<style>

</style>