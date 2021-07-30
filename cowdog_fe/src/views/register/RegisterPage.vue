<template>
<div class="register_body container">
 <el-carousel :interval="1000000" arrow="never" autoplay="false" trigger="click">
    <el-carousel-item class="item">
        <el-row>
            <el-col :span="12" class="regist_input">
                <br>
                <br>
                <h2>회원 가입</h2>
                <div class="login">
                    <el-form :model="state.form" :rules="state.rules" ref="registerForm" :label-position="state.form.align">
                        <el-form-item prop="id"  :label-width="state.formLabelWidth" >
                            <label>아이디</label>
                            <el-input  class="elinput" v-model="state.form.id" autocomplete="off" ></el-input>
                        </el-form-item>
                        <el-form-item prop="nickname"  :label-width="state.formLabelWidth">
                            <label>닉네임</label>
                            <el-input class="elinput" v-model="state.form.nickname" autocomplete="off"  ></el-input>
                        </el-form-item>
                        <el-form-item prop="email"  :label-width="state.formLabelWidth">
                            <label>이메일</label>
                            <el-input class="elinput" v-model="state.form.email" autocomplete="off" s ></el-input>
                        </el-form-item>
                        <el-form-item prop="password"  :label-width="state.formLabelWidth">
                            <label>비밀번호</label>
                            <el-input class="elinput" v-model="state.form.password" autocomplete="off" show-password ></el-input>
                        </el-form-item>
                        <el-form-item prop="passwordConfirm"  :label-width="state.formLabelWidth">
                            <label>비밀번호 확인</label>
                            <el-input class="elinput" v-model="state.form.passwordConfirm" autocomplete="off" show-password ></el-input>
                        </el-form-item>
                        <el-form-item  :label-width="state.formLabelWidth">
                            <label>나이</label>
                            <el-input class="elinput" v-model="state.form.age" autocomplete="off"  ></el-input>
                        </el-form-item>
                    </el-form>
                </div>
            </el-col>
           <el-col :span="12"  class=" regist_img">
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
                <div >
                    <el-form :model="state.form" :rules="state.rules"  :label-position="state.form.align">
                        <el-form-item prop="religion"  :label-width="state.formLabelWidth" >
                            <label>종교를 선택해 주세요</label>
                            <br>
                            <div>
                                <ul class="ks-cboxtags">
                                        <li><input v-model="state.form.religion" type="checkbox" id="무교" value="무교"><label for="무교">무교</label></li>
                                        <li><input v-model="state.form.religion" type="checkbox" id="기독교" value="기독교"><label for="기독교">기독교</label></li>
                                        <li><input v-model="state.form.religion" type="checkbox" id="천주교" value="천주교"><label for="천주교">천주교</label></li>
                                        <li><input v-model="state.form.religion" type="checkbox" id="불교" value="불교"><label for="불교">불교</label></li>
                                </ul>
                            </div>
                        </el-form-item>
                        <br><br>
                        <br><br>
                        <br><br>
                        <el-form-item prop="hobby"  :label-width="state.formLabelWidth">
                            <label>취미를 선택해 주세요(3개)</label>
                            <br>
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
                
                <label>상대방에게 보여줄 프로필을 선택하세요</label>
                <br>
                <div style="width:95%; height:200px; border:1px solid black; margin:0 auto;">
                </div>
                <br>
                <label>자신의 위치를 선택하세요(더블 클릭)</label>
                <br>
                <div id="map" ref="map" style="width:95%;height:300px; margin:0 auto; border:1px solid black">
                    <span id="centerAddr"></span>
                </div>
                <br><br>
                <el-button class="loginBtn" @click="clickRegister">회원가입</el-button>
            </el-col>
            <el-col :span="12" class="regist_img">
                <img class="logo_img1" alt="cow dog logo" :src="require('@/assets/images/regist_img1.png')">
            </el-col>
        </el-row>
    </el-carousel-item>
  </el-carousel>
  
</div>
</template>
<script>
import { reactive,ref } from 'vue'
import { useStore } from 'vuex'
import router from "@/router";
var geocoder
let road=''
let old=''
let latitude=0
let longitude=0

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
function displayMarker(map,locPosition, message) {

    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({  
        map: map, 
        position: locPosition
    }); 
    
    var iwContent = message, // 인포윈도우에 표시할 내용
        iwRemoveable = true;

    // 인포윈도우를 생성합니다
    var infowindow = new kakao.maps.InfoWindow({
        content : iwContent,
        removable : iwRemoveable
    });
    
    // 인포윈도우를 마커위에 표시합니다 
    infowindow.open(map, marker);
    
    // 지도 중심좌표를 접속위치로 변경합니다
    map.setCenter(locPosition);      
}    

export default {
    
    data () {
      return {
        interest:[],
        marker:Object,
       

      };
      
      
    },
    
    methods:{
        
         initMap() {
                var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
                    mapOption = {
                        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
                        level: 1 // 지도의 확대 레벨
                    };  


                  


                // 지도를 생성합니다    
                var map = new kakao.maps.Map(mapContainer, mapOption); 
                
                if (navigator.geolocation) {
                    
                    // GeoLocation을 이용해서 접속 위치를 얻어옵니다
                    navigator.geolocation.getCurrentPosition(function(position) {
                        
                        var lat = position.coords.latitude, // 위도
                            lon = position.coords.longitude; // 경도
                        
                        var locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
                            message = '<div style="padding:5px;">여기에 계신가요?!</div>'; // 인포윈도우에 표시될 내용입니다
                        
                        // 마커와 인포윈도우를 표시합니다
                        displayMarker(map,locPosition, message);
                            
                    });
                    
                } else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
                    
                    var locPosition = new kakao.maps.LatLng(33.450701, 126.570667),    
                        message = 'geolocation을 사용할수 없어요..'
                        
                    displayMarker(locPosition, message);
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
                            if(result[0].road_address.address_name){
                                road=result[0].road_address.address_name
                            }
                            old=result[0].address.address_name
                            latitude=mouseEvent.latLng.getLat()
                            longitude=mouseEvent.latLng.getLng()
                        } 
                        
                          
                    });
                    
                    
                    
                    
                });
                
                // 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
                kakao.maps.event.addListener(map, 'idle', function() {
                    searchAddrFromCoords(map.getCenter(), displayCenterInfo);
                });
                

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
            address:''
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
              { required: true, message: '이메일을 입력하세요 입력하세요', trigger: 'blur' }
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

    const clickRegister = function () {
        
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
                                            alcohol:state.form.alcohol,address:state.form.address,latitude:latitude,longitude:longitude,distance:0})
          .then(function () {
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
.el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 300px;
    margin: 0;
  }
.el-row{
    widows: 100%;
    height: 100%;
    margin: 0 auto;
}
.el-carousel__container {
    width: 80%;
    height: 800px;
    margin: 0 auto;
    border: 1px solid rgba(134, 123, 123, 0.212);
}
.register_body{
    width: 80%;
    height: 800px;
    margin: 0 auto;
}

.logo_img1{
    width: 100%;
    height: 100%;
}

.item{
    width: 100%;
}
.el-carousel__arrow{
    background-color: rgb(255 255 255 / 62%);
    color: #d66767;
    border: 1px solid black;
    display: none;
}
label{
    float: left;
    padding-left: 10px;
}
.el-carousel__button{
    background-color: black;
    width: 30px;
    height: 20px;
}
ul.ks-cboxtags {
    list-style: none;
    padding: 20px;
}
ul.ks-cboxtags li{
  display: inline;
}
#centerAddr {display:block;margin-top:2px;font-weight: normal;}
ul.ks-cboxtags li label{
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
   
}

ul.ks-cboxtags li label {
    padding: 5px 20px;
    cursor: pointer;
    margin-left: 20px;
}

ul.ks-cboxtags li label::before {
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
}

ul.ks-cboxtags li input[type="checkbox"]:checked + label::before {
    content: "\f00c";
    transform: rotate(-360deg);
    transition: transform .3s ease-in-out;
}

ul.ks-cboxtags li input[type="checkbox"]:checked + label {
    border: 2px solid #FF4E7E;
    background-color: #FF4E7E;
    color: #fff;
    transition: all .2s;
}

ul.ks-cboxtags li input[type="checkbox"] {
  display: absolute;
}
ul.ks-cboxtags li input[type="checkbox"] {
  position: absolute;
  opacity: 0;
}
ul.ks-cboxtags li input[type="checkbox"]:focus + label {
  border: 2px solid #e9a1ff;
}
.elinput{
    width: 95%;
}
</style>
