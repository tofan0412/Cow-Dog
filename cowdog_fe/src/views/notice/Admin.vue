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
          background-color="#ffffff"
          active-text-color="#ff4e7e">      
          <el-menu-item index="1">
            <i class="fas fa-clipboard-list" style="font-size: 16px"></i>
            <span><router-link to="/admin/notice" @click="getNotices">공지사항</router-link></span>
          </el-menu-item>
          <el-submenu index="2">
            <template #title>
              <i class="far fa-trash-alt" style="font-size: 16px"></i>
              <span>신고관리</span>
            </template>         
              <el-menu-item index="2-1" @click="getReportedUsers"><i class="fas fa-user"></i>사용자</el-menu-item>
              <el-menu-item index="2-2"><router-link to="/admin/writing-report">
              <i class="far fa-file-alt"></i>게시글</router-link></el-menu-item>
          </el-submenu>
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
import { useStore } from 'vuex'
export default {
  name: 'Admin',
  components: {
  },

  setup() {
    const store = useStore()
    return {
      getReportedUsers: () => {
        store.dispatch('getReportedUsers') //actions/getReportedUsers에 dispatch
      },
      getNotices: () => {
        store.dispatch('getNotices') // actions/getNotices에 dispatch
      }
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
}
.el-menu-item {
  width: 15vw;
}
a {
  text-decoration: none;
  color: inherit;
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