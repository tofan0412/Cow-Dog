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
      <el-menu-item index="3" class="navMenu"><router-link to="/appeal/create">AppealCreate</router-link></el-menu-item>
      <el-menu-item index="4" class="navMenu"><router-link to="/admin" @click="getNotices">Admin</router-link></el-menu-item>
      <el-menu-item index="5" class="navMenu"><router-link to="/login">Login</router-link></el-menu-item>
      <el-menu-item index="6" class="navMenu"><router-link to="/register">Register</router-link></el-menu-item>
      <el-menu-item index="7" class="navMenu"><router-link to="/mypage" @click="getMyInfo">mypage</router-link></el-menu-item>
      <el-menu-item index="8" class="navMenu"><router-link to="/test">test</router-link></el-menu-item>
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
    return {
      getNotices: () => {
        store.dispatch('getNotices') // actions/getNotices에 dispatch
      },
      getMyInfo:()=>{
        store.dispatch('getMyInfo')
      }
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

.navMenu a {
  text-decoration: none;
  font-weight: bold;
  color: inherit;
}

@font-face {
    font-family: 'SeoulNamsanM';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_two@1.0/SeoulNamsanM.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
</style>
