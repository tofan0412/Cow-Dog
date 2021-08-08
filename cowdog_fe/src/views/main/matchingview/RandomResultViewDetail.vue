<template>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css" integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
        <el-card :body-style="{ padding: '0px', margin:'5px' }">
            <img :src="this.user.file_path" class="profiile_image">
            <div style="padding: 14px;">
                <h2>{{this.user.memberid}},  {{this.user.memberinfo.age}},  {{this.user.memberinfo.gender}}</h2>
                <div class="bottom clearfix">
                    <span>주량: {{this.user.memberinfo.alcohol.replace('[','').replace(']','')}}</span><br>
                    <span>종교: {{this.user.memberinfo.religion.replace('[','').replace(']','')}}</span><br>
                    <span>성격: {{this.user.memberinfo.personality.replace('[','').replace(']','')}}</span><br>
                    <span>취미: {{this.user.memberinfo.hobby.replace('[','').replace(']','')}}</span><br>
                    <span>사는곳: {{this.user.memberinfo.address}}</span><br>
                    <div style="margin-top:50px;">
                        <el-button  class="random-matcing-btn"><i class="fas fa-video" style="font-size:20px"></i>  화상챗 요청</el-button>
                        <el-button  class="random-matcing-btn"><i class="fas fa-heart" style="font-size:20px"></i></el-button>

                    </div>
                </div>
            </div>
        </el-card>
     
</template>

<script>
import { reactive } from '@vue/reactivity'
import router from '../../../router'

export default {  
  name: 'RandomResultViewDetail.vue',
  props: {
      user: Object,
},
  setup() {
    const state = reactive({
      
    })
    const back=function(){
        router.push("/main")
    }
    
    return {
      state,back
    }
  },
  methods: {},
  }
</script>

<style>
.el-card{
   
    margin: 0 auto;
    margin-left: 50px;
    width: 500px;
    height: 700px;
}
.random-matcing-btn{
    border-radius:10%;
    color:#FF427E ;
    border-color: #FF427E;
    width: 50%;
}
.random-matcing-btn:hover{
  color:white ;
    background-color: #FF427E;
}
.profiile_image{
    width: 250px;
    height: 300px;
    display: block;
    margin: 0 auto;
}
.back{
    margin: 0 auto;
    margin-top: 40px;
}
</style>