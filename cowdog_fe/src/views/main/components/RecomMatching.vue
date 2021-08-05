<template>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css" integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
    <div class="recom-container">
        <div class="recom-comments">
            <h2>지금 만나보고 싶은 이성을 하나 골라본다면?</h2>
        </div>

       <el-button class="recom_btn"><i class="fas fa-wine-glass-alt"></i>술을 즐기는 사람!</el-button>
       <el-button class="recom_btn"><i class="fas fa-utensils"></i>맛집을 좋아하는 사람</el-button>
       <el-button class="recom_btn"><i class="fas fa-running"></i>스포츠를 좋아하는 사람</el-button>
       <el-button class="recom_btn"><i class="fas fa-cat"></i>우리 고양이 귀엽죠?</el-button>
       <el-button class="recom_btn"><i class="fas fa-handshake"></i>같이 운동하실 분</el-button>
       <el-button class="recom_btn"><i class="fas fa-gamepad"></i>듀오 ㄱ?</el-button>
    </div>
</template>
<script lang="ts">


export default{
    
}

</script>

<style >
.recom-container{
    
    border: 1px solid #C4C4C4;
    border-radius: 10px;
    width: 500px;
    height: 600px;
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