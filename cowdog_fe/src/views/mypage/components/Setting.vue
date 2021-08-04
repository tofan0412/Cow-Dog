<template>
    
        <div style="border: 1px solid black; width:55%; height:400px">
            <el-form :model="state.form" :rules="state.rules" ref="changePasswordForm" :label-position="state.form.align">
                        <el-form-item prop="curPassword"  :label-width="state.formLabelWidth" >
                            <label>현재 비밀번호</label>
                            <el-input  class="elinput" v-model="state.form.curPassword" autocomplete="off" show-password></el-input>
                        </el-form-item>
                        <el-form-item prop="newPassword"  :label-width="state.formLabelWidth">
                            <label>새로운 비밀번호</label>
                            <el-input class="elinput" v-model="state.form.newPassword" autocomplete="off"  show-password></el-input>
                        </el-form-item>
                        <el-form-item prop="newPasswordConfirm"  :label-width="state.formLabelWidth">
                            <label>새로운 비밀번호 확인</label>
                            <el-input class="elinput" v-model="state.form.newPasswordConfirm" autocomplete="off" show-password ></el-input>
                        </el-form-item>
            </el-form>

        </div>
        <div style="margin-top:5px;">
            <el-button style="float:left;" class="paasword_update_btn" @click="change_password">비밀번호 변경</el-button>
        </div>


        <div style=" width:55%; height:200px; margin-top:100px">
            <el-form :model="state.form" :rules="state.rules" ref="deleteForm" :label-position="state.form.align">
                        <el-form-item prop="confirmMessage"  :label-width="state.formLabelWidth" >
                            <label>탈퇴하시겠습니까?</label>
                            <el-input  class="elinput" v-model="state.form.confirmMessage" autocomplete="off" placeholder="탈퇴하겠습니다"></el-input>
                        </el-form-item>
            </el-form>
        </div>
        <div style="margin-top:5px;">
                <el-button style="float:left; color:red;" class="delete_user" @click="delete_user">계정 탈퇴</el-button>
        </div>
    
</template>

<script>
import { useStore } from 'vuex'
import { reactive,ref } from 'vue'
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
    const changePasswordForm=ref(null)
    const deleteForm=ref(null)
    const store = useStore()
    const validPass = (rule, value, callback) => {
        if(value != state.form.newPassword){
        callback(new Error('입력한 비밀번호와 일치하지 않습니다.'))
        }
        else{
        callback()
        }

    }
    const validMessage=(rule,value,callback)=>{
        if(value != "탈퇴하겠습니다"){
        callback(new Error('"탈퇴하겠습니다"를 올바르게 써주세요'))
        }
        else{
        callback()
        }
    }
   
    const state = reactive({
          
          form: {
            curPassword:'',
            newPassword: '',
            newPasswordConfirm:'',
            confirmMessage:''
          },
          rules: {
            curPassword:[
                {required: true,message:'비밀번호를 입력하세요', trigger:'blur'},
              { message: '최대 16 글자까지 입력 가능합니다', trigger: 'blur', max:16 },
              { message: '최소 9 글자를 입력해야 합니다.', trigger: 'blur', min:9},
              { pattern:/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]/, message:"비밀번호는 영문, 숫자, 특수문자가 조합되어야합니다." ,trigger:'blur'},
             
            ],
           
            newPassword: [
              {required: true,message:'새로운 비밀번호를 입력하세요', trigger:'blur'},
              { message: '최대 16 글자까지 입력 가능합니다', trigger: 'blur', max:16 },
              { message: '최소 9 글자를 입력해야 합니다.', trigger: 'blur', min:9},
              { pattern:/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]/, message:"비밀번호는 영문, 숫자, 특수문자가 조합되어야합니다." ,trigger:'blur'}
            ],
            newPasswordConfirm:[
                {required: true,message:'새로운 비밀번호를 입력하세요', trigger:'blur'},
                { message: '최대 16 글자까지 입력 가능합니다', trigger: 'blur', max:16 },
                { message: '최소 9 글자를 입력해야 합니다.', trigger: 'blur', min:9},
                { pattern:/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]/, message:"비밀번호는 영문, 숫자, 특수문자가 조합되어야합니다." ,trigger:'blur'},
                {validator:validPass}
             ],
             confirmMessage:[
                 {validator:validMessage}
             ]
             
          },
    
    })
    const delete_user = function () {
        console.log("회원 탈퇴")
        deleteForm.value.validate((valid) => {
        if (valid) {
           
          console.log('delete')
          store.dispatch('deleteMember', {id:store.getters.getUserId})
          
        } 
      });
    }
    const change_password = function () {
        console.log("비밀번호 변경")
        changePasswordForm.value.validate((valid) => {
        if (valid) {
           
          console.log('submit')
          store.dispatch('changePassword', {newPassword:state.form.newPassword
          , id:store.getters.getUserId, curPassword:state.form.curPassword})
          
        } 
      });
    }




    return { state, change_password,changePasswordForm,delete_user,deleteForm} 
  }
}
</script>

<style>
 .password_update_btn{
     
 }

</style>