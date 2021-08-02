<template>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css" integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
<el-container>
  <!-- sidebar -->
  <el-aside width="15vw">
    <el-row class="tac">
      <el-col>
        <el-menu
          default-active="1"
          class="el-menu-vertical-demo"
          text-color="#324545"
          background-color="#323545"
          active-text-color="#ff4e7e">      
          <el-menu-item index="1">
            <i class="fas fa-clipboard-list" style="font-size: 16px"></i>
            <span><router-link to="/myinfo" >내 정보</router-link></span>
          </el-menu-item>
          <el-menu-item index="2">
            <i class="fas fa-user-friends" style="font-size: 16px"></i>
            <span><router-link to="" >팔로잉</router-link></span>
          </el-menu-item>
          <el-menu-item index="3">
            <i class="fas fa-user-friends" style="font-size: 16px"></i>
            <span><router-link to="" >팔로우</router-link></span>
          </el-menu-item>
          <el-menu-item index="4">
            <i class="fas fa-heart" style="font-size: 16px"></i>
            <span><router-link to="" >맞팔로우</router-link></span>
          </el-menu-item>
          <el-menu-item index="5">
            <i class="fas fa-user-cog" style="font-size: 16px"></i>
            <span><router-link to="" >계정 설정</router-link></span>
          </el-menu-item>
          <el-menu-item index="6">
            <i class="fas fa-user-cog" style="font-size: 16px"></i>
            <span><router-link to="" >인적사항 수정</router-link></span>
          </el-menu-item>
          <el-menu-item index="7">
            <i class="fas fa-key" style="font-size: 16px"></i>
            <span><router-link to="" >비밀번호 변경하기</router-link></span>
          </el-menu-item>
         
        </el-menu>
      </el-col>
    </el-row>
  </el-aside>
  <el-main>
    <router-view></router-view>
  </el-main>
</el-container>
<el-footer>
  @footer
</el-footer>
</template>

<script>
export default {
  name: 'Mypage',
  components: {
  },

  setup() {
    return {
      
    }
  },


}
</script>

<style scoped>
.el-aside {
  text-align: left;
}
.el-row {
  display: inline-block;
  height: 100vh;
  width: 15vw;
  background-color: #324545;
}
.el-menu-item {
  width: 15vw;
}
a {
  text-decoration: none;
  color: white;
}

i {
  margin: 0 10px 0 0;
}

.el-main {
    /* for Firefox */
    scrollbar-width: none;
    /* for IE, Edge */
    -ms-overflow-style: none;
}

/* For Chrome, Safari and Opera */
.el-main::-webkit-scrollbar {
    display: none;
}
</style>