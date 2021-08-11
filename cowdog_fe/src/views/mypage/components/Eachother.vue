<template>
    <div v-if="state.eachOtherFollowUsers" class="followview"  >
        <el-row>
            <el-col :span="8"  v-for="user in eachOtherFollowUsersPaginated" :key="user.id" style="display: inline-table;">
                <eachother-detail :user="user" />
            </el-col>
        </el-row>
    </div>
    <div class="pagination" style="margin:0 auto; margin-top:10px">
    <el-pagination
    background
    layout="prev, pager, next"
    :page-size=pageSize
    :total="eachOtherFollowUsers.length"
    v-model:current-page="currentPage">
    </el-pagination>
    
  </div>
    <div v-if="state.eachOtherFollowUsers[0]===null" class="no_user">
        <h1 style="margin-top:20%">맞팔로우인 사람이 없습니다!!!</h1>
    </div>
    
</template>

<script>
import { reactive } from "@vue/reactivity"
import { onMounted } from "@vue/runtime-core"
import { mapGetters, useStore } from "vuex"
import router from "../../../router"
import EachotherDetail from "./EachotherDetail.vue"

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


        eachOtherFollowUsersPaginated() {
            var start = 0 + (this.currentPage-1) * this.pageSize
            var end = this.currentPage * this.pageSize
            return this.eachOtherFollowUsers.slice(start, end) //기본값 0~5번
        },

        ...mapGetters({
            eachOtherFollowUsers: 'getEachOtherFollowUsers'
        })
    },
    components:{
        EachotherDetail
    },

    setup() {
        
        const store=useStore()
        const state = reactive({
           eachOtherFollowUsers:store.getters.getEachOtherFollowUsers
        })
        const back=function(){
            router.push("/main")
        }
        onMounted(() => {
            store.dispatch("getFollowEachOther")
            state.eachOtherFollowUsers=store.getters.getEachOtherFollowUsers
        })
        return{
            state,back
            
        }
    }
})
</script>



<style>

</style>