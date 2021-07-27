<template>
  <div class="login-wrapper container-fluid">
    <div class="row">
      <div class="col-sm-7">
        <img class="logo-img" alt="cow dog logo" :src="require('@/assets/images/logo.png')">
      </div>
      <div class="col-sm-5">
        <div class="login-form">
         <el-form :model="state.form" :rules="state.rules" ref="loginForm" :label-position="state.form.align">
              <el-form-item prop="id"  :label-width="state.formLabelWidth" >
                <el-input class="elinput" v-model="state.form.id" autocomplete="off"  placeholder="아이디"></el-input>
              </el-form-item>
              <el-form-item prop="password"  :label-width="state.formLabelWidth">
                <el-input class="elinput" v-model="state.form.password" autocomplete="off" show-password  placeholder="비밀번호"></el-input>
              </el-form-item>
          </el-form>
          <el-button class="loginBtn" @click="clickLogin">로그인</el-button>
          <hr>
          <div>
            <router-link to='/RegisterPage' style="text-decoration: none; color: black;">비밀번호 찾기</router-link>
          </div>
        </div>
        
        <div class="register-form" >
          <span>계정이 없으신가요?    </span> <router-link to='/RegisterPage' style="text-decoration: none; color: #EC7357;">가입하기</router-link>
        </div>
      </div>
    </div>
  </div>
</template>




<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.login-wrapper{
  width: 70%;
  height: 60%;
  margin: 0 auto;
  margin-top: 5%;
}

.logo-img{
  width: 100%;
  height: 90%;
}
.login-form{
  width: 100%;
  height: 500px;
  border: 1px solid #BBABAB;
  margin-bottom:5%;
  border-radius: 5px;
  text-align: center;
}
.register-form{
  width:100%;
  height: 10%;
  border: 1px solid #BBABAB;
  border-radius: 5px;
}
.el-input{
  background: #BBABAB;
  width: 90%;
  margin: 0 auto;
  border-radius: 5px;
  
}
.el-input::placeholder{
  color: black;
}
.el-form{
  margin-top:40%;
}
.el-button{
  width: 90%;
  background-color:rgba(236,115,87,0.2);
  color: #EC7357;

}
hr{
  height: 1px;
  background:#bbb;
  background-image: -webkit-linear-gradient(left,#eee,#777,#eee);
  background-image: -moz-linear-gradient(left,#eee,#777,#eee);
  background-image: -o-linear-gradient(left,#eee,#777,#eee);
}

</style>


<script>
import { reactive,ref } from 'vue'
import { useStore } from 'vuex'

export default {
  name: "RegisterPage",
  props: {
    msg: String,
  },
 
  setup(){
    const loginForm = ref(null)
    const store = useStore()
    const state = reactive({

          form: {
            id: '',
            password: '',
            align: 'left',
            isLoading:Boolean
          },
          rules: {
            id: [
              { required: true, message: '아이디를 입력하세요', trigger: 'blur' }
            ],
            password: [
              {required: true,message:'비밀번호를 입력하세요', trigger:'blur'},
              { message: '최대 16 글자까지 입력 가능합니다', trigger: 'blur', max:16 },
              { message: '최소 9 글자를 입력해야 합니다.', trigger: 'blur', min:9},
              { pattern:/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]/, message:"비밀번호는 영문, 숫자, 특수문자가 조합되어야합니다." ,trigger:'blur'}
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
};
</script>
