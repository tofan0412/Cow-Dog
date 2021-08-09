<template>
<div class="main-top">
  <h1 class="main-intro">다양한 매칭을 통해 인연을 만나세요</h1>
  <div style="display:flex; justify-content:center; margin:3%">
    <h2>local icon/</h2>
    <h2>random icon/</h2>
    <h2>recom icon</h2>
  </div>
  <el-tabs class="main-tabs" v-model="activeName" @tab-click="handleClick">
    <el-tab-pane label="지역매칭" name="지역매칭"><distance-matching /></el-tab-pane>
    <el-tab-pane label="랜덤매칭" name="랜덤매칭"><random-matching /></el-tab-pane>
    <el-tab-pane label="맞춤매칭" name="맞춤매칭"><recom-matching /></el-tab-pane>
  </el-tabs>
</div>
</template>

<script>
import { reactive } from 'vue'
import RandomMatching from './components/RandomMatching.vue'
import DistanceMatching from './components/DistanceMatching.vue'
import RecomMatching from './components/RecomMatching.vue'

export default{
    data() {
      return {
        activeName: 'first'
      };
    },
    methods: {
      handleClick(tab, event) {
        console.log(tab, event);
      }
    },
    components : { RandomMatching, DistanceMatching, RecomMatching },

    setup() {
        
        const state = reactive({
            random:true,
            recom:Boolean,
            distance:Boolean,
        })

        return{
            state
        }
    },
}
</script>
<style>
.main-top {
    /* background: linear-gradient(90deg, rgba(255,209,221,1) 0%, rgba(228,230,241,1) 100%); */
}
.main-intro {
    margin-top: 0;
    padding-top: 5%;
}
.el-tabs__item:hover {
    color: #FF427E;
}
.el-tabs__item.is-active {
    color: #FF427E;
}
.el-tabs__active-bar {
    background-color: #ff4e7e;
}
.matching_btn{
    color:#FF427E ;
    border-color: #FF427E;
}
.matching_btn:hover{
    color:white ;
    background-color: #FF427E;
}
.matching_btn:focus{
    color: white;
    background-color: #ff427e;
}

.main-wrapper{
    margin-top: 50px;
}
</style>