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
      <el-menu-item index="/admin/notice" class="navMenu" @click="getNotices">Admin</el-menu-item>
     <!-- 나중에 거의 완성 되면 로그인 빼고 다 막아야 한다~ -->
      <el-menu-item index="/main" class="navMenu">Main</el-menu-item>
      <el-menu-item v-if="this.$store.state.userId" class="navMenu" @click="logout()">Logout</el-menu-item>
      <el-menu-item v-else index="/login" class="navMenu">Login</el-menu-item>
      <el-menu-item index="/register" class="navMenu">Register</el-menu-item>
      <el-menu-item index="/mypage" class="navMenu" @click="getMyInfo">mypage</el-menu-item>
      <el-menu-item index="/test" class="navMenu">test</el-menu-item>
      
      <el-menu-item v-if="notifications.length==0"  class="navMenu" @click="state.dialogVisible = true"><i class="fas fa-envelope-open"></i></el-menu-item>
      <el-menu-item v-else class="navMenu" @click="state.dialogVisible = true"><i class="fas fa-envelope"></i> {{notifications.length}}</el-menu-item>
    </el-menu>
          <el-dialog
          class="notice-detail"
          title="알림"
          v-model="state.dialogVisible"
          width="70%"
          >
            <div  v-for="user in notifications" :key="user.id">
                <div style="display:inline;">
                  <p>{{user.mem.memberid}} 님이 팔로우를 걸었습니다.</p>
                  <el-button @click="checkNotification(user.id)">확인</el-button>
                </div>
            </div>   
            <br>
            <br>
            
            <br>


        </el-dialog>
  </div>

  
  
  <router-view/>
</template>

<script>
import { reactive } from '@vue/reactivity';
import { onMounted } from '@vue/runtime-core';
import { mapGetters, useStore } from 'vuex'
export default ({

  data() {
    return {
      activeIndex: '1',
      dialogVisible: false
    };
     
  },

 
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    }
  },
  computed: {
    ...mapGetters({
      notifications: 'getNotifications', // 내가 팔로우하는 사람들

    })
  },
  setup() {
    const state=reactive({
      dialogVisible:false
    })
    onMounted(() => {
       store.dispatch("getNotification",store.getters.getUserId)//알림 뭐 왔나 백엔드에서 가져오는거
    })
    const store = useStore()
    const logout =function(){
      console.log("로그아웃")
      store.dispatch("userLogout",{id:store.getters.getUserId})
    }
    const checkNotification=function(id){
      console.log(id)
      console.log("알림 확인")
      store.dispatch("checkNotification",id)
      state.dialogVisible=false
      store.dispatch("getNotification",store.getters.getUserId)
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
     checkNotification,
     state
     
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
