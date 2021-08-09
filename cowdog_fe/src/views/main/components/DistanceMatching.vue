<template>
    <div class="distance-container">
        <div class="random-comments">
            <h2>롱디는 그만, 나와 가까운 사람 찾기!</h2>
        </div>
        <div class="block">
            <el-slider
            v-model="state.distance"
            :min="2"
            :max="10"
            :step="2"
            :marks="state.marks">
            </el-slider>
            <h3>거리 설정</h3>
        </div>
        <el-button class="distance-circle" @click="distanceMathcing">MATCHING</el-button>
    </div>
</template>
<script>
import { reactive } from 'vue'
import { useStore } from 'vuex'

export default{

     setup() {
        const store=useStore()
        const state = reactive({
            distance:4,
            marks:{
                2:"2km",
                4:"4km",
                6:"6km",
                8:"8km",
                10:"10km",
            }
        })
        const distanceMathcing =function(){
            console.log(state.distance)
            store.dispatch("disatnceMatching",state.distance)



        }

        return{
            state,distanceMathcing
        }
    },
    
}

</script>

<style >
.distance-container{
    border: 1px solid #C4C4C4;
    border-radius: 10px;
    width: 500px;
    height: 600px;
    margin: 0 auto;
    margin-top:100px;
    background-color: rgb(196, 196, 196,15%);
}
.block{
    width: 80%;
    
    margin: 0 auto;
    margin-top: 100px;
}
.distance-circle{
    width: 200px;
    height: 200px;
    border-radius: 50%;
    background-color: #FF4E7E;
    margin-top: 100px;
    font-size: 15px;
    color: white;
}
.distance-circle span:hover{
    color: #FF4E7E;
    background-color: white;
}
</style>