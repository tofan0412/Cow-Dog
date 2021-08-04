import axios from 'axios'
import $axios from 'axios'
import router from "@/router";
export function getNotices ({ commit }) {
  axios.get('https://e7d14d3b-41a2-4dc5-874c-a55731c663b9.mock.pstmn.io/notice')
    .then(res => {
      console.log(res.data)
      commit('GET_NOTICES', res.data)
    })
    .catch(err => {
      console.log(err)
    })
}

export function getMyInfo({state,commit}){
  console.log(state.userId)
  console.log(state.accessToken)
  axios.get("/mem/mypage/?userId="+state.userId,{
    headers:{
      Authorization:"Bearer "+state.accessToken
    }
  })
  .then(res=>{
    console.log(res.data)
    commit('GET_MYINFO',res.data);
  })
  .catch(err=>{
    console.log(err)
  })
}
export function Authentication({ state },payload){
  console.log("비밀번호 초기화~" ,state,payload)
    axios.put("/EmailAuthentication/searchPassword",{id:payload.id, email:payload.email}
  )
  .then(res=>{
    console.log("비밀번호 초기화 메일 성공")
    if(res.data==="SUCCESS"){
      alert("비밀번호 초기화 성공 메일을 확인해주세요")
    }else if(res.date==="NOT_EXISTS_EMAIL"){
      alert("입력하신 이메일이 정확하지 않습니다.")
    }
    else if(res.data==="FAIL"){
      alert("비밀번호 초기화 실패")
    }
  })
  .catch(err=>{
    console.log(err)
    return err
  })
}

export function requestLogin ({ state }, payload) {
  console.log('requestLogin', state, payload)
  console.log("여기는 로그인: ", state, payload)
  const url = '/mem/login'
  let body = payload
  return $axios.post(url, body)
}

export function requestRegister ({ state }, payload) {
  console.log('requestLogin', state, payload)
  console.log("여기는 회원가입: ", state, payload)
  
  const url = '/mem/register'
  let body = payload
  return $axios.post(url, body)
}

export function confirmId({state},payload){
  console.log('confirmId', state, payload)
  console.log(payload.id)
  

  const url='/mem/confirmId'
  let body=payload.id
  return $axios.post(url,body)
}

export function changePassword({state},payload){
  console.log('changePassword',  payload)
  console.log(payload.newPassword)
  axios.put("/mem/changePassword",{id:payload.id, newPassword:payload.newPassword, curPassword:payload.curPassword}
    ,{
    headers:{
      Authorization:"Bearer "+state.accessToken
    }
  })
  .then(res=>{
    console.log("비밀번호 변경 성공")
    if(res.data==="SUCCESS"){
      alert("비밀번호 변경 성공, 다시 로그인 해주세요")
      state.userId=''
      state.accessToken=''
      state.myinfo=[]
      router.push({name:"Login"})
    }else if(res.data==="FAIL"){
      alert("현재 비밀번호가 틀립니다. 다시 확인해주세요")
    }
  })
  .catch(err=>{
    console.log(err)
    return err
  })
}
export function deleteMember({state},payload){
  console.log("회원 탈퇴 "+payload.id)
  axios.delete("/mem/deleteMember/?id="+payload.id,{
    headers:{
      Authorization:"Bearer "+state.accessToken
    }
  })
    .then(res=>{
      console.log("회원 탈퇴 성공")
      if(res.data==="SUCCESS"){
        alert("회원을 탈퇴 하셨습니다.")
        state.userId=''
        state.accessToken=''
        state.myinfo=[]
        router.push({name:"Login"})
      }else if(res.data==="FAIL"){
        alert("탈퇴 실패?? 가 뜨면 백엔드 문제")
      }
    })
    .catch(err=>{
      console.log(err)
      return err
  })
  

}


export function confirmNickname({state},payload){
  console.log('confirmNickname', state, payload)
  console.log(payload.nickName)
  const url='/mem/confirmNickname'
  let body=payload.nickName
  return $axios.post(url,body)
}


export function getReportedUsers({ commit }) {
  axios.get('https://e7d14d3b-41a2-4dc5-874c-a55731c663b9.mock.pstmn.io/reportedUserList')
    .then(res => {
      console.log(res.data)
      commit('GET_REPORTED_USERS', res.data)
    })
    .catch(err => {
      console.log(err)
    })  
}

export function postNotice({ state }, payload) {
  console.log( payload)
  const url = '/notice'
  axios({
    url: url,
    method: 'post',
    data: {
      title: payload.title,
      content: payload.content
    },
    headers:{
      Authorization:"Bearer "+state.accessToken
    }
  })
    .then(res => {
      console.log(res)
      return res.data
    })
    .catch(err => {
      console.log(err)
    })
}