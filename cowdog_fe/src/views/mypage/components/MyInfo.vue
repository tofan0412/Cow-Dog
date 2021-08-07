<template>
<div class="myinfo-detail" style="display:flex">
  <div class="detail-box">
    <ul class="detail-info">
      <li>나이: {{myinfo.memberinfo.age}}</li>
      <li>성별: {{myinfo.memberinfo.gender[1]+myinfo.memberinfo.gender[2]}}</li>
      <li>이메일: {{myinfo.email}}</li>
      <li>성격: {{myinfo.memberinfo.personality.replace('[','').replace(']','')}}</li>
      <li>종교: {{myinfo.memberinfo.religion.replace('[','').replace(']','')}}</li>
      <li>취미: {{myinfo.memberinfo.hobby.replace('[','').replace(']','')}}</li>
      <li>흡연: {{myinfo.memberinfo.smoking}}</li>
      <li>음주: {{myinfo.memberinfo.alcohol.replace('[', '').replace(']', '')}}</li>
      <li>MBTI: </li>
      <li>지역: {{ myRegion }}</li>
    </ul>
  </div>
  <KakaoMap
  :lat="this.myinfo.memberinfo.latitude"
  :lng="this.myinfo.memberinfo.longitude"
  :meters="this.myinfo.memberinfo.distance" />
</div>


</template>


<script>
import { useStore } from 'vuex'
import KakaoMap from './MakeMap.vue'
let files=[]
export default {

  components: {
    KakaoMap
  },
  data () {
    return {
      interest:[],
      title: "",
      files: [],
      galleryDatas: [],
      url:null,
    };
  },
  
  methods:{
    handleFileUpload() {
      console.log(this.$refs.imgfiles.files)
      files = this.$refs.imgfiles.files;
      this.url=files[0].name
      console.log(files);
    },
  },
  computed: {
    myRegion() {
      var myRegionSplit = this.myinfo.memberinfo.address.split(' ')
      var myRegion = myRegionSplit[0] + ' ' + myRegionSplit[1]
      return myRegion
    }
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
    width: 100%;
    height: 100%;
    object-fit: cover;
  }
  .detail-box {
    width: 30%;
    margin-top: 3%;
    margin-left: 20%;
    margin-bottom: 3%;
    border: 1px solid white;
    border-radius: 10px;
    background: white;
  }
  .detail-info {
    list-style: none;
    text-align: left;
    font-weight: bold;
    font-size: 14px;
  }
  
  .detail-info li {
    margin-bottom: 2rem;
  }

  .el-tabs__content {
    background: #f0f2f5;
  }
</style>