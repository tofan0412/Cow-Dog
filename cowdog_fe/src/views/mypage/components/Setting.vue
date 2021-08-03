<template>
    
        <div style="border: 1px solid black; width:55%; height:400px">
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
            </el-form>

        </div>
        <div style="margin-top:5px;">
            <el-button style="float:left;" class="paasword_update_btn" v-on="click=''">비밀번호 변경</el-button>
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
 .password_update_btn{
     
 }

</style>