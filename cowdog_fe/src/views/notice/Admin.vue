<template>
<el-container>
  <el-aside width="240px">
    <el-row class="tac">
      <el-col>
        <el-menu
          default-active="1"
          class="el-menu-vertical-demo"
          text-color="#324545"
          background-color="#ffffff"
          active-text-color="#ff4e7e">      
          <el-menu-item index="1">
            <i class="el-icon-menu"></i>
            <span><router-link to="/admin/notice">공지사항</router-link></span>
          </el-menu-item>
          <el-submenu index="2">
            <template #title>
              <i class="el-icon-location"></i>
              <span>신고관리</span>
            </template>
              <router-link to="/admin/user-report"><el-menu-item index="2-1">사용자</el-menu-item></router-link>
              <el-menu-item index="2-2"><router-link to="/admin/writing-report">게시글</router-link></el-menu-item>
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
// import { useStore } from 'vuex'
export default {
  name: 'Admin',
  components: {
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
  width: 100vw;
}
.el-menu-item {
  width: 200px;
}
a {
  text-decoration: none;
  color: inherit;
}
</style>