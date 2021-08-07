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
  <div class="map-box">
  <div class="map-title">만남을 원하는 위치는?</div>
    <div id="map" style="width: 450px; height: 450px;"></div>
  </div>
</div>


</template>


<script>
import { useStore } from 'vuex'
import { onMounted } from 'vue'
let files=[]
export default {
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

    // 지도 관련 setup
    const initMap = () => {
        var container = document.getElementById('map');
        var options = {
          center: new kakao.maps.LatLng(myinfo.memberinfo.latitude, myinfo.memberinfo.longitude),
          level: 8
        };

        const map = new kakao.maps.Map(container, options);

        var circle = new kakao.maps.Circle({
            center: new kakao.maps.LatLng(myinfo.memberinfo.latitude, myinfo.memberinfo.longitude),
            radius: myinfo.memberinfo.distance * 1000,
            strokeWeight: 5, // 선의 두께
            strokeColor: '#ff4e7e', // 선의 색깔
            strokeOpacity: 1, // 불투명
            strokeStyle: 'line',
            fillColor: '#CFE7FF', // 채우기 색깔
            fillOpacity: 0.4 // 채우기 불투명도
        })
        circle.setMap(map);
        // setTimeout(() => {
            map.relayout()
            map.setCenter(new kakao.maps.LatLng(myinfo.memberinfo.latitude, myinfo.memberinfo.longitude))
            console.log('done!!')
        // }, 0)
    }

    onMounted(() => {
      if (window.kakao && window.kakao.maps) {
          initMap();
      } else {
          const script = document.createElement('script');
          script.onload = () => kakao.maps.load(initMap);
          script.src = 'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=0f0de04704d7e38d69c53bd08ce9a2b8&libraries=services';
          document.head.appendChild(script);
      }
    })

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
  .map-title {
    font-weight: bold;
    margin-bottom: 5px;
  }
  .map-box {
    margin: 4rem 3rem;
  }
</style>