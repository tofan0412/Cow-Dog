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
                    <el-form :model="state.form" :rules="state.rules" ref="loginForm" :label-position="state.form.align">
                        <el-form-item prop="id"  :label-width="state.formLabelWidth" >
                            <label>아이디</label>
                            <el-input class="elinput" v-model="state.form.id" autocomplete="off" ></el-input>
                        </el-form-item>
                        <el-form-item prop="nickname"  :label-width="state.formLabelWidth">
                            <label>닉네임</label>
                            <el-input class="elinput" v-model="state.form.password" autocomplete="off" show-password ></el-input>
                        </el-form-item>
                        <el-form-item prop="email"  :label-width="state.formLabelWidth">
                            <label>이메일</label>
                            <el-input class="elinput" v-model="state.form.password" autocomplete="off" show-password ></el-input>
                        </el-form-item>
                        <el-form-item prop="password"  :label-width="state.formLabelWidth">
                            <label>비밀번호</label>
                            <el-input class="elinput" v-model="state.form.password" autocomplete="off" show-password ></el-input>
                        </el-form-item>
                        <el-form-item prop="passwordConfirm"  :label-width="state.formLabelWidth">
                            <label>비밀번호 확인</label>
                            <el-input class="elinput" v-model="state.form.password" autocomplete="off" show-password ></el-input>
                        </el-form-item>
                        <el-form-item prop="age"  :label-width="state.formLabelWidth">
                            <label>나이</label>
                            <el-input class="elinput" v-model="state.form.password" autocomplete="off" show-password ></el-input>
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
                <div class="login">
                    <el-form :model="state.form" :rules="state.rules" ref="loginForm" :label-position="state.form.align">
                        <el-form-item prop="id"  :label-width="state.formLabelWidth" >
                            <label>종교를 선택해 주세요</label>
                            <br>
                            <div>
                                <el-radio v-model="relegion" label="1" border>무교</el-radio>
                                <el-radio v-model="relegion" label="2" border>천주교</el-radio>
                                <el-radio v-model="relegion" label="3" border>불교</el-radio>
                                <el-radio v-model="relegion" label="4" border>기독교</el-radio>
                            </div>
                        </el-form-item>
                        <el-form-item prop="nickname"  :label-width="state.formLabelWidth">
                            <label>취미를 선택해 주세요.</label>
                            <br>
                            <div>
                                <el-radio v-model="hobby" label="1" border>운동</el-radio>
                                <el-radio v-model="hobby" label="2" border>영화</el-radio>
                                <el-radio v-model="hobby" label="3" border>게임</el-radio>
                                <br>
                                <el-radio v-model="hobby" label="4" border>요라</el-radio>
                                <el-radio v-model="hobby" label="4" border>독서</el-radio>
                                <el-radio v-model="hobby" label="4" border>낚시</el-radio>
                                <br>
                                <el-radio v-model="hobby" label="4" border>음악듣기</el-radio>
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
      <p>sdfsf</p>
    </el-carousel-item>
    <el-carousel-item >
      <p>wwwww</p>
    </el-carousel-item>
    <el-carousel-item >
      <p>asddsfswwfasf</p>
    </el-carousel-item>
    <el-carousel-item >
      <p>qqq</p>
    </el-carousel-item>
  </el-carousel>
</div>
</template>
<script>
import { reactive,ref } from 'vue'
import { useStore } from 'vuex'

export default {
    data () {
      return {
        relegion: '',
        hobby:''
      };
    },
    setup(){
    const loginForm = ref(null)
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
        if(result.data==="FAIL"){
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
            id: '',
            password: '',
            nickname: '',
            email: '',
            passwordConfirm:'',
            age:'',
            align: 'left',
            isLoading:Boolean
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

    const clickLogin = function () {
      state.form.isLoading=true;
      // 로그인 클릭 시 validate 체크 후 그 결과 값에 따라, 로그인 API 호출 또는 경고창 표시
      loginForm.value.validate((valid) => {
        console.log("로그인 검사 통과")
        if (valid) {
          console.log('submit')
          store.dispatch('requestLogin', { id: state.form.id, password: state.form.password })
          .then(function (result) {
            store.commit('SET_USER',{
              result
            })
              alert("로그인 성공")

          })
          .catch(function () {
            alert("아이디나 비밀번호가 일치하지 않습니다")
          })
        } else {
          state.form.isLoading=false
          alert('로그인 형식이 올바르지 않습니다.')
        }
      });
    }
    return { state,clickLogin,loginForm }
  }
}
</script>
<style >
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
.el-radio.is-bordered {
    padding: 12px 20px 0 10px;
    border-radius: 20px;
    border: 1px solid #DCDFE6;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    height: 40px;
    color: white;
    background-color: #323545;
}
.el-radio.is-focus{
    color: white !important;
    background-color: #d66767;
}
.el-radio:hover{
    color: white;
    background-color: #d66767;
}
.el-radio{
    border-radius: 20px;
}
span.el-radio__input.is-chcked{
    color: white;
    background-color: #d66767;
}
span.el-radio__inner{
    display:none;
    color: white;
}
.el-radio__input.is-checked+.el-radio__label {
   color: white;
}
.el-radio.is-bordered.is-checked {
    border-color: white;
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
}
.el-carousel__button{
    background-color: black;
    width: 30px;
    height: 20px;
}
</style>