<template>
<div>
<label>프로필</label>
{{myinfo}}<br>
<img class="profile" :src="myinfo.file_path"><br>
닉네임: {{myinfo.nickname}}<br>
이메일: {{myinfo.email}}<br>
아이디: {{myinfo.memberid}}<br>
나이: {{myinfo.memberinfo.age}}<br>
성별: {{myinfo.memberinfo.gender[1]+myinfo.memberinfo.gender[2]}}<br><!--데이터가 배열로 와서 이렇게 해야함 -->
성격: {{myinfo.memberinfo.personality.replace('[','').replace(']','').split(',')[0]}}<br><!-- 앞뒤 대괄호 제거후 , 로 구분 한다.-->
성격: {{myinfo.memberinfo.personality.replace('[','').replace(']','').split(',')[1]}}<br><!-- 앞뒤 대괄호 제거후 , 로 구분 한다.-->
위도: {{myinfo.memberinfo.latitude}}<br>
경도: {{myinfo.memberinfo.longitude}}<br>
정지된 계정인지: {{myinfo.issuspended}}<br>

{{myinfo.email}}
{{myinfo.email}}
</div>
</template>

<script>
import { useStore } from 'vuex'
// import router from '../../../router'
export default {
  data() {
    return {
    
    }
  },

  components: {
    
  },

  methods: {
    
  },

  computed: {
    
  },

  setup() {
    const store = useStore()
    const myinfo = store.state.myinfo

    return { myinfo }
  }
}
</script>

<style>
 .profile{
     width:120px;
     height:150px;
 }
</style>