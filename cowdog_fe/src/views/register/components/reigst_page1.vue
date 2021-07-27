<template>
  <div class="login">
    <el-form :model="state.form" :rules="state.rules" ref="loginForm" :label-position="state.form.align">
        <el-form-item prop="id"  :label-width="state.formLabelWidth" >
            <label>아이디</label>
            <el-input class="elinput" v-model="state.form.id" autocomplete="off" ></el-input>
        </el-form-item>
        <el-form-item prop="password"  :label-width="state.formLabelWidth">
            <el-input class="elinput" v-model="state.form.password" autocomplete="off" show-password  placeholder="비밀번호"></el-input>
        </el-form-item>
        </el-form>
    <el-button class="loginBtn" @click="clickLogin">로그인</el-button>
  </div>
</template>

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
}
</script>

<style>

.login{
    margin-top: 10%;
}

</style>