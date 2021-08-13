<template>
  <div>
    <el-menu
      :router="true"
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      text-color="#323545"
      active-text-color="#ff4e7e"
      >
      <div v-if="!this.$store.state.userId" style="display:flex; justify-content: space-between">
        <el-menu-item index="/login" class="navMenu-logo"><img class="nav-logo" :src="require('@/assets/images/cowanddog.png')" alt="logo" style="max-width: 100%; height:auto;"></el-menu-item>
        <div style="display:flex">
          <el-menu-item index="/login" class="navMenu">로그인</el-menu-item>
          <el-menu-item index="/register" class="navMenu">회원가입</el-menu-item>
        </div>
      </div>
      <div v-else style="display:flex; justify-content:space-between">
        <div style="display:flex">
          <el-menu-item index="/main" class="navMenu-logo"><img class="nav-logo" :src="require('@/assets/images/cowanddog.png')" alt="logo" style="max-width: 100%; height:auto;"></el-menu-item>
          <el-menu-item index="/main" class="navMenu">매칭</el-menu-item>
          <el-menu-item index="/notices" class="navMenu">공지사항</el-menu-item>
          <el-menu-item index="/Appeal" class="navMenu">게시판</el-menu-item>
          <el-menu-item index="" class="navMenu" @click="getNotices">관리자</el-menu-item> <!-- 배포 전 숨기기 -->
          
          <el-menu-item index="/test" class="navMenu">test</el-menu-item>
        </div>
        <div style="display:flex; margin-right: 3%">
          <el-menu-item index="/mypage" class="navMenu" @click="getMyInfo">마이페이지</el-menu-item>
          <el-menu-item class="navMenu" @click="logout()">로그아웃</el-menu-item>
        </div>
      </div>
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
        store.dispatch('getFollowEachOther')
        store.dispatch("getFollowUsers")
        console.log("실행 완료입니다")
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
  color: #323545;
}

pre {
  font-size: 16px;
  font-family: SeoulNamsanM;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #323545;
  display: inline;
  margin-top: 0;
  margin-bottom: 0;
}
span {
  font-family: SeoulNamsanM;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #323545;
  display: inline;
  margin-top: 0;
  margin-bottom: 0;
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
    border-bottom: 0px;
}
.el-menu-item:hover {
  background: #f0f2f5;
}
.el-menu-item:focus {
  background: #fff;
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
