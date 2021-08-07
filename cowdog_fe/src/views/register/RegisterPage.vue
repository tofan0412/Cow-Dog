<template>
<div class="register-background">
    <br>
    <br>
    <div class="register-body">
    <el-carousel :interval="1000000" arrow="never" autoplay="false" trigger="click">
        <el-carousel-item class="item">
            <div class="register-inside-carousel">
                <div class="register-left-half">
                    <h2>회원 가입</h2>
                    <div>
                        <el-form :model="state.form" :rules="state.rules" ref="registerForm"> 
                            <el-form-item prop="id">
                                <div class="input-name">아이디</div>
                                <el-input  class="elinput" v-model="state.form.id" autocomplete="off" ></el-input>
                            </el-form-item>
                            <el-form-item prop="nickname">
                                <div class="input-name">닉네임</div>
                                <el-input class="elinput" v-model="state.form.nickname" autocomplete="off"  ></el-input>
                            </el-form-item>
                            <el-form-item prop="email">
                                <div class="input-name">이메일</div>
                                <el-input class="elinput" v-model="state.form.email" autocomplete="off"></el-input>
                            </el-form-item>
                            <el-form-item prop="password">
                                <div class="input-name">비밀번호</div>
                                <el-input class="elinput" v-model="state.form.password" autocomplete="off"></el-input>
                            </el-form-item>
                            <el-form-item prop="passwordConfirm" :label-width="state.formLabelWidth">
                                <div class="input-name">비밀번호 확인</div>
                                <el-input class="elinput" v-model="state.form.passwordConfirm" autocomplete="off"></el-input>
                            </el-form-item>
                            <el-form-item>
                                <div class="input-name">나이</div>
                                <el-input class="elinput" v-model="state.form.age" autocomplete="off"></el-input>
                            </el-form-item>
                        </el-form>
                    </div>
                </div>
                <div class="register-right-half">
                    <div class="register-img-box">
                        <img class="register-img-item" :src="require('@/assets/images/regist_img1.png')">
                    </div>
                </div>
            </div>
        </el-carousel-item>


        <el-carousel-item class="item">
            <div class="register-inside-carousel">
                <div class="register-left-half">
                    <h2>회원 가입</h2>
                    <div>
                        <el-form :model="state.form" :rules="state.rules"  :label-position="state.form.align">
                            <el-form-item prop="religion">
                                <div class="input-name">종교를 선택해 주세요</div>
                                    <ul>
                                        <li><input v-model="state.form.religion" type="checkbox" id="무교" value="무교"><label for="무교">무교</label></li>
                                        <li><input v-model="state.form.religion" type="checkbox" id="기독교" value="기독교"><label for="기독교">기독교</label></li>
                                        <li><input v-model="state.form.religion" type="checkbox" id="천주교" value="천주교"><label for="천주교">천주교</label></li>
                                        <li><input v-model="state.form.religion" type="checkbox" id="불교" value="불교"><label for="불교">불교</label></li>
                                    </ul>
                            </el-form-item>
                            <el-form-item prop="hobby">
                                <div class="input-name">취미를 선택해 주세요(3개)</div>
                                <div>
                                    <ul class="ks-cboxtags">
                                        <li><input v-model="state.form.hobby" type="checkbox" id="운동" value="운동"><label for="운동">운동</label></li>
                                        <li><input v-model="state.form.hobby" type="checkbox" id="요리" value="요리"><label for="요리">요리</label></li>
                                        <li><input v-model="state.form.hobby" type="checkbox" id="영화" value="영화"><label for="영화">영화</label></li>
                                        <li><input v-model="state.form.hobby" type="checkbox" id="게임" value="게임"><label for="게임">게임</label></li>
                                        <li><input v-model="state.form.hobby" type="checkbox" id="낚시" value="낚시"><label for="낚시">낚시</label></li>
                                        <li><input v-model="state.form.hobby" type="checkbox" id="음악듣기" value="음악듣기"><label for="음악듣기">음악듣기</label></li>
                                        <li><input v-model="state.form.hobby" type="checkbox" id="그림그리기" value="그림그리기"><label for="그림그리기">그림그리기</label></li>
                                    </ul>
                                </div>
                            </el-form-item>
                        </el-form>
                    </div>
                </div>
                <div class="register-right-half">
                    <div class="register-img-box">
                        <img class="register-img-item" alt="cow dog logo" :src="require('@/assets/images/regist_img1.png')">
                    </div>
                </div>
            </div>
        </el-carousel-item>
        <el-carousel-item class="row">
            <el-row>
                <el-col :span="12" class="regist_input">
                    <br>
                    <br>
                    <h2>회원 가입</h2>
                    <div>
                        <el-form :model="state.form" :rules="state.rules" :label-position="state.form.align">
                            <el-form-item prop="personality"  :label-width="state.formLabelWidth" >
                                <label>본인의 성격을 선택해 주세요(최소3개)</label>
                                <br>
                                <div>
                                    <ul class="ks-cboxtags">
                                            <li><input v-model="state.form.personality" type="checkbox" id="활기찬" value="활기찬"><label for="활기찬">활기찬</label></li>
                                            <li><input v-model="state.form.personality" type="checkbox" id="진지한" value="진지한"><label for="진지한">진지한</label></li>
                                            <li><input v-model="state.form.personality" type="checkbox" id="재미있는" value="재미있는"><label for="재미있는">재미있는</label></li>
                                            <li><input v-model="state.form.personality" type="checkbox" id="웃음많은" value="웃음많은"><label for="웃음많은">웃음많은</label></li>
                                            <li><input v-model="state.form.personality" type="checkbox" id="장난끼많은" value="장난끼많은"><label for="장난끼많은">장난끼많은</label></li>
                                            <li><input v-model="state.form.personality" type="checkbox" id="꼼꼼한" value="꼼꼼한"><label for="꼼꼼한">꼼꼼한</label></li>
                                            <li><input v-model="state.form.personality" type="checkbox" id="세심한" value="세심한"><label for="세심한">세심한</label></li>
                                    </ul>
                                </div>
                            </el-form-item>
                            <br><br>
                            <br><br>
                            <br><br>
                            <el-form-item prop="interest"   :label-width="state.formLabelWidth">
                                <label>본인의 관심사를 선택해 주세요(최소3개)</label>
                                <br>
                                <div>
                                
                                    <ul class="ks-cboxtags">
                                            <li><input v-model="state.form.interest" type="checkbox" id="정치" value="정치"><label for="정치">정치</label></li>
                                            <li><input v-model="state.form.interest" type="checkbox" id="경제" value="경제"><label for="경제">경제</label></li>
                                            <li><input v-model="state.form.interest" type="checkbox" id="문화" value="문화"><label for="문화">문화</label></li>
                                            <li><input v-model="state.form.interest" type="checkbox" id="미술" value="미술"><label for="미술">미술</label></li>
                                    </ul>
                                
                                </div>
                            </el-form-item>
                            
                        </el-form>
                    </div>
                </el-col>
                <el-col :span="12" class="regist_img">
                    <img class="logo_img1" alt="cow dog logo" :src="require('@/assets/images/regist_img1.png')">
                </el-col>
            </el-row>
        </el-carousel-item>
        <el-carousel-item class="row">
            <el-row>
                <el-col :span="12" class="regist_input">
                    <br>
                    <br>
                    <h2>회원 가입</h2>
                    <div>
                        <el-form :model="state.form" :rules="state.rules"  :label-position="state.form.align">
                            <el-form-item prop="gender"  :label-width="state.formLabelWidth" >
                                <label>성별</label>
                                <br>
                                <div>
                                    <ul class="ks-cboxtags">
                                            <li><input v-model="state.form.gender" type="checkbox" id="남자" value="남자"><label for="남자">남자</label></li>
                                            <li><input v-model="state.form.gender" type="checkbox" id="여자" value="여자"><label for="여자">여자</label></li>
                                    </ul>
                                </div>
                            </el-form-item>
                            <el-form-item prop="smoking"  :label-width="state.formLabelWidth">
                                <label>흡연을 하시나요?</label>
                                <br>
                                <div>
                                    <ul class="ks-cboxtags">
                                            <li><input v-model="state.form.smoking" type="checkbox" id="예" value="true"><label for="예">예</label></li>
                                            <li><input v-model="state.form.smoking" type="checkbox" id="아니요" value="false"><label for="아니요">아니요</label></li>
                                    </ul>
                                </div>
                            </el-form-item>
                            <el-form-item prop="alcohol"  :label-width="state.formLabelWidth" >
                                <label>음주는 얼마나 하시나요?</label>
                                <br>
                                <div>
                                    <ul class="ks-cboxtags">
                                            <li><input v-model="state.form.alcohol" type="checkbox" id="안함" value="안함"><label for="안함">안함</label></li>
                                            <li><input v-model="state.form.alcohol" type="checkbox" id="거의 안마신다" value="거의 안마신다"><label for="거의 안마신다">거의 안마신다</label></li>
                                            <li><input v-model="state.form.alcohol" type="checkbox" id="가끔 마신다" value="가끔 마신다"><label for="가끔 마신다">가끔 마신다</label></li>
                                            <li><input v-model="state.form.alcohol" type="checkbox" id="자주 마신다" value="자주 마신다"><label for="자주 마신다">자주 마신다</label></li>
                                    </ul>
                                </div>
                            </el-form-item>
                        </el-form>
                    </div>
                </el-col>
                <el-col :span="12" class="regist_img">
                    <img class="logo_img1" alt="cow dog logo" :src="require('@/assets/images/regist_img1.png')">
                </el-col>
            </el-row>
        </el-carousel-item>
        
        <el-carousel-item >
        <el-row>
                <el-col :span="12" class="regist_input">
                    <br>
                    <br>
                    <h2>회원 가입</h2>
                    <div>
                    <label>상대방에게 보여줄 프로필을 선택하세요</label>
                    <input type="file" id="imgfiles" ref="imgfiles" v-on:change="handleFileUpload()" multiple />
                    </div>
                    <br>
                    <br>
                    <label>자신의 위치를 선택하세요(더블 클릭)</label>
                    <br>
                    <div id="map" ref="map" style="width:95%;height:300px; margin:0 auto; border:1px solid black">
                        <span id="centerAddr"></span>
                    </div>
                    <br><br>
                    <label>이성을 찾고 싶은 거리 제한을 설정해주세요</label>
                    <div>
                        <ul class="ks-cboxtags">
                            <li><input type="radio" v-model="state.form.distance" value=2>2km</li>
                            <li><input type="radio" v-model="state.form.distance" value=4>4km</li>
                            <li><input type="radio" v-model="state.form.distance" value=6>6km</li>
                            <li><input type="radio" v-model="state.form.distance" value=8>8km</li>
                            <li><input type="radio" v-model="state.form.distance" value=10>10km</li>
                        </ul>
                    </div>
                    <el-button class="loginBtn" @click="clickRegister">회원가입</el-button>
                </el-col>
                <el-col :span="12" class="regist_img">
                    <img class="logo_img1" alt="cow dog logo" :src="require('@/assets/images/regist_img1.png')">
                </el-col>
            </el-row>
        </el-carousel-item>
    </el-carousel>
    </div>

    <!-- 젤리 체크박스 테스트 -->
    <label class="checkbox">
        <input type="checkbox">
        <span class="icon"></span>
        <span class="text">약관에 동의합니다</span>
    </label>

</div>
</template>
<script>
import { reactive,ref } from 'vue'
import { useStore } from 'vuex'
import router from "@/router";
import axios from 'axios'
var geocoder
let road=''
let old=''
let latitude=0
let longitude=0
let files=[]
function searchAddrFromCoords(coords, callback) {
    // 좌표로 행정동 주소 정보를 요청합니다
    geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);         
}

function searchDetailAddrFromCoords(coords, callback) {
    // 좌표로 법정동 상세 주소 정보를 요청합니다
    geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
}

// 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
function displayCenterInfo(result, status) {
    if (status === kakao.maps.services.Status.OK) {
        var infoDiv = document.getElementById('centerAddr');

        for(var i = 0; i < result.length; i++) {
            // 행정동의 region_type 값은 'H' 이므로
            if (result[i].region_type === 'H') {
                infoDiv.innerHTML = result[i].address_name;
                break;
            }
        }
    }    
}
function displayMarker(map,locPosition) {

    
    
    // 지도 중심좌표를 접속위치로 변경합니다
    map.setCenter(locPosition);      
}    

export default {
    
    data () {
      return {
        interest:[],
        marker:Object,
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
         initMap() {
                var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
                    mapOption = {
                        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
                        level: 7 // 지도의 확대 레벨
                    };  
                // 지도를 생성합니다    
                var map = new kakao.maps.Map(mapContainer, mapOption); 
                if (navigator.geolocation) {    
                    // GeoLocation을 이용해서 접속 위치를 얻어옵니다
                    navigator.geolocation.getCurrentPosition(function(position) {  
                        var lat = position.coords.latitude, // 위도
                            lon = position.coords.longitude; // 경도
                        var locPosition = new kakao.maps.LatLng(lat, lon); // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
                        // 마커와 인포윈도우를 표시합니다
                        displayMarker(map,locPosition);
                    });
                } else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
                }
                // 주소-좌표 변환 객체를 생성합니다
                 geocoder = new kakao.maps.services.Geocoder();
                var marker = new kakao.maps.Marker(), // 클릭한 위치를 표시할 마커입니다
                    infowindow = new kakao.maps.InfoWindow({zindex:1}); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다
                // 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
                searchAddrFromCoords(map.getCenter(), displayCenterInfo);
                // 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
                kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
                    searchDetailAddrFromCoords(mouseEvent.latLng, function(result, status) {
                        if (status === kakao.maps.services.Status.OK) {
                            var detailAddr = result[0].road_address ? '<div>도로명주소 : ' + result[0].road_address.address_name + '</div>' : '';
                            detailAddr += '<div>지번 주소 : ' + result[0].address.address_name + '</div>';
                            
                            var content = '<div class="bAddr">' +
                                            '<span class="title">법정동 주소정보</span>' + 
                                            detailAddr + 
                                        '</div>';
                            
                            // 마커를 클릭한 위치에 표시합니다 
                            marker.setPosition(mouseEvent.latLng);
                            marker.setMap(map);

                            // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
                            infowindow.setContent(content);
                            infowindow.open(map, marker);
                            if(result[0].road_address!=null){
                                road=result[0].road_address.address_name
                            }
                            old=result[0].address.address_name
                            latitude=mouseEvent.latLng.getLat()
                            longitude=mouseEvent.latLng.getLng()
                            console.log(latitude)
                            console.log(longitude)
                            console.log(old)
                            console.log(road)
                        } 
                    });
                });
                
                // 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
                kakao.maps.event.addListener(map, 'idle', function() {
                    searchAddrFromCoords(map.getCenter(), displayCenterInfo);
                });

            setTimeout(() => {
                map.relayout();
            }, 1000)
        },
    },
    mounted() {
         if (window.kakao && window.kakao.maps) {
            this.initMap();
        } else {
            const script = document.createElement('script');
            script.onload = () => kakao.maps.load(this.initMap);
            script.src = 'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=0f0de04704d7e38d69c53bd08ce9a2b8&libraries=services';
            document.head.appendChild(script);
        }

        // axios.get('http://localhost:8080/cowdog/mem/getImageList')
        //             .then(res => {
        //                 console.log(res.data);
        //                 this.galleryDatas = res.data
        //             })
        //             .catch(error => console.log(error));
    },
    setup(){
    const registerForm = ref(null)
    const store = useStore()
    
    const confirmId = function(rule, value, callback) {
      if(value!==""){
      store.dispatch('confirmId',{id:value})
      .then(function(result){
        console.log(result)
        if(result.data==="EXIST_USERID"){
          callback(new Error("이미 있는 아이디입니다."))
        }
        else{
          callback()
        }
      })
      .catch(function(err){
        alert(err)
      })
      }
    }
    const confirmNickname = function(rule, value, callback) {
      if(value!==""){
      store.dispatch('confirmNickname',{nickName:value})
      .then(function(result){
        console.log(result)
        if(result.data==="EXIST_NICKNAME"){
          callback(new Error("이미 있는 닉네임입니다."))
        }
        else{
          callback()
        }
      })
      .catch(function(err){
        alert(err)
      })
      }
    }
    const validPass = (rule, value, callback) => {
     if(value != state.form.password){
       callback(new Error('입력한 비밀번호와 일치하지 않습니다.'))
     }
     else{
       callback()
     }

   }
    const state = reactive({
        form: {
            interest:[],
            personality:[],
            hobby:[],
            gender:[],
            id: '',
            password: '',
            nickname: '',
            email: '',
            passwordConfirm:'',
            age:'',
            align: 'left',
            smoking:[],
            alcohol:[],
            religion:[],
            address:'',
            distance:0,
          },
        rules: {
            id: [
                { required: true, message: '아이디를 입력하세요', trigger: 'blur' },
                { message: '최대 15 글자까지 입력 가능합니다', trigger: 'blur', max:15 },
                { message: '최소 4 글자를 입력해야 합니다.', trigger: 'blur', min:4},
                {validator:confirmId}
            ],
            age: [
              { required: true, message: '나이를 입력하세요', trigger: 'blur' }
            ],
            email: [
              { required: true, message: '이메일을 입력하세요 입력하세요', trigger: 'blur' },
              {pattern:/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/ ,message:"이메일형식이 올바르지 않습니다."}
            ],
            password: [
              {required: true,message:'비밀번호를 입력하세요', trigger:'blur'},
              { message: '최대 16 글자까지 입력 가능합니다', trigger: 'blur', max:16 },
              { message: '최소 9 글자를 입력해야 합니다.', trigger: 'blur', min:9},
              { pattern:/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]/, message:"비밀번호는 영문, 숫자, 특수문자가 조합되어야합니다." ,trigger:'blur'}
            ],
            nickname:[
                { required: true, message: '닉네입을 입력하세요', trigger: 'blur' },
                { message: '최소 2 글자를 입력해야 합니다.', trigger: 'blur', min:2},
                {validator:confirmNickname}
            ],
            passwordConfirm:[
                {required: true,message:'비밀번호를 입력하세요', trigger:'blur'},
                { message: '최대 16 글자까지 입력 가능합니다', trigger: 'blur', max:16 },
                { message: '최소 9 글자를 입력해야 합니다.', trigger: 'blur', min:9},
                { pattern:/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]/, message:"비밀번호는 영문, 숫자, 특수문자가 조합되어야합니다." ,trigger:'blur'},
                {validator:validPass}
            ],
             
        },
    })
    
    const profileImageUpload=function(){  
        console.log("이미지 업로드~")
        console.log(files)
        for (var i = 0; i < files.length; i++) {
            let formData = new FormData();
            formData.append('title', "profile");
            formData.append('files', files[i]);
            formData.append('userId',state.form.id)
            axios.post('http://localhost:8080/cowdog/mem/profileImgaeUpload',
            formData, {
                headers: {
                'Content-Type': 'multipart/form-data'
                },
            }
            )  
            .then(function() {
            console.log('SUCCESS!!');
            })
            .catch(function() {
                console.log('FAILURE!!');
                    });
            }
    }
    const clickRegister = function () {
        
        var dist=0
        if(state.form.distance==2){
            console.log(state.form.distance)
            dist=2
        }else if(state.form.distance==4){
            console.log(state.form.distance)
            dist=4
        }else if(state.form.distance==6){
            console.log(state.form.distance)
            dist=6
        }else if(state.form.distance==8){
            console.log(state.form.distance)
            dist=8
        }else{
            dist=10
        }
        console.log(dist)
        registerForm.value.validate((valid) => {
            if(old==''){
                state.form.address=road
                
            }else{
                state.form.address=old
            
            }
        console.log("회원가입 검사")
        if (valid) {
           
          console.log('submit')
          store.dispatch('requestRegister', { id:state.form.id, nickname:state.form.nickname, password:state.form.password, 
                                            email:state.form.email, age:state.form.age,  religion:state.form.religion, interest:state.form.interest,
                                            personality:state.form.personality, hobby:state.form.hobby, gender:state.form.gender, smoking:state.form.smoking,
                                            alcohol:state.form.alcohol,address:state.form.address,latitude:latitude,longitude:longitude,distance:dist})
          .then(function () {
            profileImageUpload()
            alert("회원가입 성공")
            router.push({name:"Login"})
          })
          .catch(function () {
            alert("")
          })
        } else {
          alert('회원가입 형식이 올바르지 않습니다.')
        }
      });
    }
    return { state,clickRegister,registerForm }
  }
}
</script>
<style>
.register-background {
    width: 100%;
    background: #f0f2f5;
}
.register-body{
    background: white;
    width: 60%;
    margin: auto;
    border-radius: 10px;
}

.el-carousel {
    height: 750px;
}
.el-carousel__container {
    height: 100%;
}
.register-inside-carousel {
    display: flex;
}
.register-left-half {
    width: 50%;
}
.register-right-half {
    width: 50%;
}
.el-carousel .input-name {
    text-align: left;
    margin-left: 1rem;
    font-weight: bold;
}
.el-carousel .el-form-item__error {
    margin-left: 1rem;
}
.el-carousel .register-img-box {
    width: 100%;
}
.el-carousel .register-img-item {
    width: 100%;
    border: 1px solid white;
}
.el-carousel__button {
    background-color: #ff4e7e;
    width: 40px;
    height: 5px;
}
/* ul.ks-cboxtags {
    list-style: none;
    padding: 20px;
} */

/* ul.ks-cboxtags li{
  display: inline;
} */

/* #centerAddr {
    display:block;
    margin-top:2px;
    font-weight: bold;} */

/* ul.ks-cboxtags li label{
    display: inline-block;
    background-color: #323545;
    border: 2px solid rgba(139, 139, 139, .3);
    color: white;
    border-radius: 25px;
    white-space: nowrap;
    margin: 3px 0px;
    -webkit-touch-callout: none;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    -webkit-tap-highlight-color: transparent;
    transition: all .2s;
} */

/* ul.ks-cboxtags li label {
    padding: 5px 20px;
    cursor: pointer;
    margin-left: 20px;
} */

/* 버튼 돌아가는 액션 */
/* ul.ks-cboxtags li label::before {
    display: inline-block;
    font-style: normal;
    font-variant: normal;
    text-rendering: auto;
    -webkit-font-smoothing: antialiased;
    font-family: "Font Awesome 5 Free";
    font-weight: 900;
    font-size: 12px;
    padding: 2px 6px 2px 2px;
    content: "\f067";
    transition: transform .3s ease-in-out;
} */

/* 버튼 돌아가는 액션 */
/* ul.ks-cboxtags li input[type="checkbox"]:checked + label::before {
    content: "\f00c";
    transform: rotate(-360deg);
    transition: transform .3s ease-in-out;
} */

/* 버튼 색깔 */
/* ul.ks-cboxtags li input[type="checkbox"]:checked + label {
    border: 2px solid #FF4E7E;
    background-color: #FF4E7E;
    color: #fff;
    transition: all .2s;
} */

/* ul.ks-cboxtags li input[type="checkbox"] {
  display: absolute;
}
ul.ks-cboxtags li input[type="checkbox"] {
  position: absolute;
  opacity: 0;
}
ul.ks-cboxtags li input[type="checkbox"]:focus + label {
  border: 2px solid #e9a1ff;
} */
.register-body .elinput{
    margin: 0 1rem;
    display: block;
    width: 90%;
}

/* 젤리 체크박스 */
.checkbox input {
    display:none;
}
.checkbox span {
    display: inline-block;
    vertical-align: middle;
    cursor: pointer;
}
.checkbox .icon {
    position:relative;
    width: 17px;
    height: 17px;
    border: 1px solid #c8ccd4;
    border-radius: 3px;
    transition: background 0.1s ease;
}
.checkbox .icon::after {
    content: '';
    position: absolute;
    top: 1px;
    left: 5px;
    width: 5px;
    height: 11px;
    border-right: 2px solid #fff;
    border-bottom: 2px solid #fff;
    transform: rotate(45deg) scale(0);
    transition: all 0.3s ease;
    transition-delay: 0.15s;
    opacity: 0;
}
.checkbox .text {
    margin-left: 5px;
}
.checkbox input:checked ~ .icon {
    border-color: transparent;
    background: #ff1616;
    animation: jelly 0.6s ease;
}
.checkbox input:checked ~ .icon::after{
    opacity: 1;
    transform: rotate(45deg) scale(1);
}
@keyframes jelly {
    from {transform: scale(1, 1);}
    30% {transform: scale(1.25, 0.75);}
    40% {transform: scale(0.75, 1.25);}
    50% {transform: scale(1.15, 0.85);}
    65% {transform: scale(0.95, 1.05);}
    75% {transform: scale(1.05, 0.95);}
    90% {transform: scale(1.02, 0.97);}
    to {transform: scale(1, 1);}
}
</style>
