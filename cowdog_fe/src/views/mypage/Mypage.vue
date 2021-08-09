<template>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css" integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
<div class="mypage">
  <div class="upward">
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
      <div class="follow" style="display:flex">
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
  <div>
    <el-tabs v-model="activeName">
      <el-tab-pane label="상세정보" name="myinfo"><MyInfo /></el-tab-pane>
      <el-tab-pane label="팔로우" name="follow"><Follow /></el-tab-pane>
      <el-tab-pane label="맞팔로우" name="eachother"><EachOther /></el-tab-pane>
      <el-tab-pane label="계정설정" name="account-setting"><Setting /></el-tab-pane>
    </el-tabs>
  </div>
</div>
</template>

<script>
import { useStore } from 'vuex'
import MyInfo from "./components/MyInfo.vue"
import Follow from "./components/Follow.vue"
import EachOther from "./components/Eachother.vue"
import Setting from "./components/Setting.vue"
export default {
  name: 'Mypage',
  components: {
    MyInfo,
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

    return { myinfo,
      getMyInfo:()=>{
        store.dispatch('getMyInfo')
      },
      GetFollowUsers:()=>{
        store.dispatch("getFollowUsers")
      },
    }
  },


}
</script>

<style>
.upward {
  display: flex;
  justify-content: center;
}
.profile-img-box {
  margin: 5% auto;
  margin-left: 20%;
  width: 250px;
  height: 250px;
  border-radius: 70%;
  overflow: hidden;
}

.el-tabs__nav {
  float: none;
  margin: 0 auto;
  display: inline-block
}

.el-tabs__header {
  margin: 0;
}
.profile-info {
  text-align: left;
  width: 50%;
  margin: 5% 0;
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

@media (max-width: 860px) {
  .upward {
    display: block;
  }
  .profile-img-box {
    margin: 5% auto;
    width: 250px;
    height: 250px;
    border-radius: 70%;
    overflow: hidden;
  }
  .profile-info {
    display: block;
    text-align: center;
    width: 50%;
    margin: 5% auto;
  }
  .follow {
    justify-content: center;
  }
  .hashtag {
    justify-content: center;
  }
}
</style>