<template>
  <div>
    <el-menu
      :router="true"
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      text-color="#ff4e7e"
      active-text-color="#ff4e7e">
      <el-menu-item index="/" class="navMenu-logo"><img class="nav-logo" :src="require('@/assets/images/cowanddog.png')" alt="logo" style="max-width: 100%; height:auto;"></el-menu-item>
      <el-menu-item index="/Appeal" class="navMenu">Appeal</el-menu-item>
      <el-menu-item index="#" class="navMenu" @click="getNotices">Admin</el-menu-item>
     <!-- 나중에 거의 완성 되면 로그인 빼고 다 막아야 한다~ -->
      <el-menu-item index="/main" class="navMenu">Main</el-menu-item>
      <el-menu-item v-if="this.$store.state.userId" class="navMenu" @click="logout()">Logout</el-menu-item>
      <el-menu-item v-else index="/login" class="navMenu">Login</el-menu-item>
      <el-menu-item index="/register" class="navMenu">Register</el-menu-item>
      <el-menu-item index="#" class="navMenu" @click="getMyInfo">mypage</el-menu-item>
      <el-menu-item index="/test" class="navMenu">test</el-menu-item>
    </el-menu>
  </div>
  
  <router-view/>
</template>

<script>
import { useStore } from 'vuex'
export default ({

  data() {
    return {
      activeIndex: '1',
    };
     
  },
  
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    }
  },
  setup() {
    const store = useStore()
    const logout =function(){
      console.log("로그아웃")
      store.dispatch("userLogout",{id:store.getters.getUserId})
      
      
      
    }
    return {
      logout,
      getNotices: () => {
        store.dispatch('getNotices') // actions/getNotices에 dispatch
      },
      getMyInfo:()=>{
        store.dispatch('getMyInfo')
      },
      GetFollowUsers:()=>{
          store.dispatch("getFollowUsers")
        },
     
    }
  },
})
</script>


<style>
#app {
  font-family: SeoulNamsanM;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

body {
  margin: 0;
  padding: 0;
}
.navMenu-logo {
  text-decoration: none;
  width: 100px;
  border-bottom-color: #ffffff !important;
}
.navMenu {
  text-decoration: none;
  font-weight: bold;
  color: inherit;
}

.el-menu.el-menu--horizontal {
    border-bottom: 0px #e6e6e6;
}
html::-webkit-scrollbar {
  width: 10px;
}
html::-webkit-scrollbar-thumb {
  width: 5px;
  background-color: #ffe7e7;
  border-radius: 5px;
}
html::-webkit-scrollbar-track {
  background-color: #ffffff;
}

@font-face {
    font-family: 'SeoulNamsanM';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_two@1.0/SeoulNamsanM.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
</style>
