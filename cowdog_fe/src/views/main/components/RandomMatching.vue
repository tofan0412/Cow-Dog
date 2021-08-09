<template>
    <div class="random-container">
        <div class="random-comments">
            <h2>남자 여자 상관없이 친구를 만나보세요</h2>
        </div>
        <el-button class="circle" @click="randomMatching">RANDOM</el-button>
    </div>
</template>
<script lang="ts">
import { useStore } from 'vuex'
import { reactive } from 'vue'
export default{




     setup() {
        const store=useStore()
        const state = reactive({
           




        })
        const randomMatching=function(){
            console.log("램덤매팅!")
            store.dispatch("randomMatching")

        }



        return {
            state,randomMatching
        }
    },
    
}

</script>

<style >
.random-container{
    
    border: 1px solid #C4C4C4;
    border-radius: 10px;
    width: 500px;
    height: 600px;
    margin: 0 auto;
    margin-top:100px;
    background-color: rgb(196, 196, 196,15%);
}
.circle{
    width: 200px;
    height: 200px;
    border-radius: 50%;
    background-color: #FF4E7E;
    margin-top: 100px;
    font-size: 30px;
    color: white;
}
.circle span:hover{
    color: #FF4E7E;
    background-color: white;
}

</style>