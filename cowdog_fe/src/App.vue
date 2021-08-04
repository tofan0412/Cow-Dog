<template>
  <div>
    <el-menu
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color="#323545"
      text-color="#ff4e7e"
      active-text-color="#ff4e7e">
      <el-menu-item index="1" class="navMenu"><router-link to="/">Home</router-link></el-menu-item>
      <el-menu-item index="2" class="navMenu"><router-link to="/appeal">Appeal</router-link></el-menu-item>
      <el-menu-item index="3" class="navMenu"><router-link to="/admin" @click="getNotices">Admin</router-link></el-menu-item>
     <!-- 나중에 거의 완성 되면 로그인 빼고 다 막아야 한다~ -->
      <el-menu-item  v-if="this.$store.state.userId" index="4" class="navMenu"><router-link @click="logout()" to="">Logout</router-link></el-menu-item>
      <el-menu-item  v-else index="4" class="navMenu"><router-link to="/login">Login</router-link></el-menu-item>
      <el-menu-item index="5" class="navMenu"><router-link to="/register">Register</router-link></el-menu-item>
      <el-menu-item index="5" class="navMenu"><router-link to="/mypage" @click="getMyInfo">mypage</router-link></el-menu-item>
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
      
      store.commit("USER_LOGOUT")
      
    }
    return {
      logout,
      getNotices: () => {
        store.dispatch('getNotices') // actions/getNotices에 dispatch
      },
      getMyInfo:()=>{
        store.dispatch('getMyInfo')
      },
      
      
    }
  },
})
</script>


<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

body {
  margin: 0;
  padding: 0;
}

.navMenu a {
  text-decoration: none;
  font-weight: bold;
  color: inherit;
}
</style>
