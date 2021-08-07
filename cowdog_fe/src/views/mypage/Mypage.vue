<template>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css" integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
<div class="mypage">
  <div class="upward" style="display:flex">
    <div class="profile-img-box">
      <img class="profile" :src="myinfo.file_path"><br>
    </div>
    <div class="profile-info">
      <div class="nickname">
        {{myinfo.nickname}}
      </div>
      <div class="email-address">
        {{myinfo.email}}
      </div>
      <div style="display:flex">
        <div class="followers">
          33 followers
        </div>
        <div class="followings">
          34 following
        </div>
      </div>
      <div class="hashtag" style="display:flex">
        <div class="personality-item">
          # {{myinfo.memberinfo.personality.replace('[','').replace(']','').split(',')[0]}}
        </div>
        <div class="personality-item">
          #{{myinfo.memberinfo.personality.replace('[','').replace(']','').split(',')[1]}}
        </div>
        <div class="personality-item">
          #{{myinfo.memberinfo.personality.replace('[','').replace(']','').split(',')[2]}}
        </div>  
      </div>
      <div class="hashtag" style="display:flex">
        <div class="personality-item">
          # {{myinfo.memberinfo.hobby.replace('[','').replace(']','').split(',')[0]}}
        </div>
        <div class="personality-item">
          #{{myinfo.memberinfo.hobby.replace('[','').replace(']','').split(',')[1]}}
        </div>
        <div class="personality-item">
          #{{myinfo.memberinfo.hobby.replace('[','').replace(']','').split(',')[2]}}
        </div> 
      </div>
    </div>
  </div>
  <!-- tabs -->
  <el-tabs v-model="activeName">
    <el-tab-pane label="상세정보" name="myinfo"><MyInfo /></el-tab-pane>
    <el-tab-pane label="팔로잉" name="following"><Following /></el-tab-pane>
    <el-tab-pane label="팔로우" name="follow"><Follow /></el-tab-pane>
    <el-tab-pane label="맞팔로우" name="eachother"><EachOther /></el-tab-pane>
    <el-tab-pane label="계정설정" name="account-setting"><Setting /></el-tab-pane>
  </el-tabs>
</div>
</template>

<script>
import { useStore } from 'vuex'
import MyInfo from "./components/MyInfo.vue"
import Following from "./components/Following.vue"
import Follow from "./components/Follow.vue"
import EachOther from "./components/Eachother.vue"
import Setting from "./components/Setting.vue"
export default {
  name: 'Mypage',
  components: {
    MyInfo,
    Following,
    Follow,
    EachOther,
    Setting
  },

  data() {
    return {
      activeName: 'myinfo'
    }
  },
  setup() {
    const store = useStore()
    const myinfo = store.state.myinfo

    return { myinfo }
  },


}
</script>

<style>
.upward {
  margin-left: 15%;
}

.profile-img-box {
  margin-left: 20%;
  margin-top: 5%;
  width: 200px;
  height: 200px;
  border-radius: 70%;
  overflow: hidden;
}

.el-tabs__nav {
  margin: 0 37%;
}

.el-tabs__header {
  margin: 0;
}
.profile-info {
  text-align: left;
  margin: 4rem;
}

.profile-info .nickname {
  margin: 1rem;
  font-weight: bold;
  font-size: 2rem;
}
.profile-info .email-address {
  margin: 1rem;
  font-weight: bold;
  font-size: 1.2rem;
}
.profile-info .followers {
  margin: 0 1rem;
  font-weight: bold;
}
.profile-info .followings {
  margin: 0 1rem;
  font-weight: bold;
}
.hashtag {
  margin: 1rem;
}
.personality-item {
  border: 1px solid;
  font-weight: bold;
  margin: 3px;
  padding: 2px;
  border-radius: 7px;
}
.el-tabs__item {
  font-weight: bold;
}
</style>