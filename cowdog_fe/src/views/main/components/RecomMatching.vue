<template>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css" integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
    <div class="recom-container">
        <div class="recom-comments">
            <h2>지금 만나보고 싶은 이성을 하나 골라본다면?</h2>
        </div>

       <el-button class="recom_btn" @click="alcohol"><i class="fas fa-wine-glass-alt"></i> 주량이 맞는 사람</el-button>
       <el-button class="recom_btn" @click="sameHobby"><i class="fas fa-utensils"></i> 취미가 비슷한 사람</el-button>
       <el-button class="recom_btn" @click="wantMale"><i class="fas fa-male"></i> 멋있는 남자를 만나고 싶은 사람</el-button>
       <el-button class="recom_btn" @click="wantFemale"><i class="fas fa-female"></i> 이쁜 여자를 만나고 싶은 사람</el-button>
       <el-button class="recom_btn" @click="nonSmoking"><i class="fas fa-smoking-ban"></i> 비흡연자 만나고 싶은 사람~</el-button>
       <el-button class="recom_btn" @click="sameReligion"><i class="fas fa-pray"></i> 종교가 같은 사람</el-button>
       <el-button class="recom_btn" @click="sameMbti"><i class="fas fa-file-alt"></i> MBTI 맞는 사람</el-button>
       <el-button class="recom_btn" @click="samePersonality"><i class="fas fa-equals"></i> 성격 맞는 사람</el-button>
    </div>
</template>
<script lang="ts">
import { reactive } from '@vue/reactivity'
import { useStore } from 'vuex'


export default{
     setup() {
        const store=useStore()
        const state = reactive({
           




        })
        const alcohol=function(){
            console.log("주량이 비슷한 사람!")
            store.dispatch("alcohol")
            
        }
        const sameHobby=function(){
            console.log("취미!")
            store.dispatch("sameHobby")
            
        }
        const wantMale=function(){
            console.log("남자 원함!")
            store.dispatch("wantMale")
            
        }
        const wantFemale=function(){
            console.log("여자 원함!")
            store.dispatch("wantFemale")
            
        }
        const nonSmoking=function(){
            console.log("비흡연자!")
            store.dispatch("nonSmoking")
            
        }
        const sameReligion=function(){
            console.log("종교!")
            store.dispatch("sameReligion")
            
        }
        const sameMbti=function(){
            console.log("mbti!")
            store.dispatch("sameMbti")
            
        }
        const samePersonality=function(){
            console.log("성격!")
            store.dispatch("samePersonality")
            
        }
       

        return {
            state,sameMbti,sameReligion,nonSmoking,wantFemale,wantMale,sameHobby,alcohol,samePersonality
        }
    },
}

</script>

<style >
.recom-container{
    
    border: 1px solid #C4C4C4;
    border-radius: 10px;
    width: 500px;
    height: 700px;
    margin: 0 auto;
    margin-top:100px;
    background-color: rgb(196, 196, 196,15%);
}
.recom_btn span:hover{
    color: #FF4E7E;
    background-color: white;
}
.recom_btn
{
    background-color: white;
    color: #FF4E7E;
    width: 90%;
    margin-top: 20px;
}
</style>