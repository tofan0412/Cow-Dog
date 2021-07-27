<template>
<div class="register_body container">
 <el-carousel :interval="1000000" arrow="never" autoplay="false" trigger="click">
    <el-carousel-item class="row">
        <div class="row">
            <div class="col-sm-6 regist_input">
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
            </div>
           <div class="col-sm-6 regist_img">
                <img class="logo_img1" alt="cow dog logo" :src="require('@/assets/images/regist_img1.png')">
            </div>
        </div>
    </el-carousel-item>
    <el-carousel-item class="row">
        <div class="row">
            <div class="col-sm-6 regist_input">
                <br>
                <br>
                <h2>회원 가입</h2>
                <div class="login">
                    <el-form :model="state.form" :rules="state.rules" ref="loginForm" :label-position="state.form.align">
                        <el-form-item prop="id"  :label-width="state.formLabelWidth" >
                            <label>종교를 선택해 주세요</label>
                            <br>
                            <div>
                                <div style="padding-bottom:10px">
                                    <el-radio v-model="radio1" label="1" border>Option A</el-radio>
                                    <el-radio v-model="radio2" label="2" border>Option B</el-radio>
                                    <el-radio v-model="radio3" label="3" border >Option A</el-radio>
                                </div>
                                <br>
                                <div>
                                    <el-radio v-model="radio4" label="4" border>Option A</el-radio>
                                    <el-radio v-model="radio5" label="5" border>Option B</el-radio>
                                    <el-radio v-model="radio6" label="6" border >Option A</el-radio>
                                </div>
                            </div>
                        </el-form-item>
                        <el-form-item prop="nickname"  :label-width="state.formLabelWidth">
                            <label>취미를 선택해 주세요.</label>
                            <el-input class="elinput" v-model="state.form.password" autocomplete="off" show-password ></el-input>
                        </el-form-item>
                        
                    </el-form>
                </div>
            </div>
           <div class="col-sm-6 regist_img">
                <img class="logo_img1" alt="cow dog logo" :src="require('@/assets/images/regist_img1.png')">
            </div>
        </div>
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
        radio1: '1',
        radio2: '1',
        radio3: '1',
        radio4: '1',
        radio5:'1',
        radio6:'1',
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
        if(result.data==="FAIL"){
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
                {max:16 , message:'최대 16 자까지 입력 사능합니다.'},
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
                { required: true, message: '닉네입을 입력하세요', trigger: 'blur' }
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

  .el-carousel__container {
    width: 80%;
    height: 800px;
    margin: 0 auto;
    border: 1px solid rgba(134, 123, 123, 0.212);
}
.register_body{
    width: 80%;
    height: 800px;
}
.regist_input{
    
}
.regist_image{
    
}
.logo_img1{
    width: 100%;
    height: 120%;
}
.row{
    margin: 0 auto;
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