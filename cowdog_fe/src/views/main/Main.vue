<template>
    <h1>다양한 매칭을 통해 인연을 만나세요</h1>
    <div class="main-wrapper">
        <el-button class="matching_btn" @click="distance">지역 매칭</el-button>
        <el-button class="matching_btn" @click="random">랜덤 매칭</el-button>
        <el-button class="matching_btn" @click="recom">맞춤 매칭</el-button>
    </div>
    <random-matching v-if="state.random===true"/>
    <recom-matching v-if="state.recom===true"/>
    <distance-matching v-if="state.distance===true"/>
</template>

<script>
import { reactive } from 'vue'
import RandomMatching from './components/RandomMatching.vue'
import DistanceMatching from './components/DistanceMatching.vue'
import RecomMatching from './components/RecomMatching.vue'

export default{

    components : { RandomMatching, DistanceMatching, RecomMatching },







    setup() {
        
        const state = reactive({
            random:true,
            recom:Boolean,
            distance:Boolean,
        })
        const random = function(){
            state.random=true;
            state.recom=false;
            state.distance=false;
        }
        const recom = function(){
            state.random=false;
            state.recom=true;
            state.distance=false;
        }
        const distance = function(){
            state.random=false;
            state.recom=false;
            state.distance=true;
        }



        return{
            state,random,recom,distance
        }
    },
}
</script>
<style>
.matching_btn{
    border-radius:10%;
    color:#FF427E ;
    border-color: #FF427E;
}
.matching_btn:hover{
    color:white ;
    background-color: #FF427E;
    
}

.main-wrapper{
    margin-top: 50px;
}
</style>