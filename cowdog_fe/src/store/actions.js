import axios from 'axios'
import $axios from 'axios'
export function getNotices ({ state, commit }) {
  const url = '/notice'
  axios({
    url: url,
    method: 'get',
    headers:{
      Authorization:"Bearer "+state.accessToken
    },
  })
    .then(res => {
      console.log(res)
      commit('GET_NOTICES', res.data)
    })
    .catch(err => {
      console.log(err)
    })    
}

export function getMyInfo({state}){
  console.log(state.userId)
  console.log(state.accessToken)
  axios.get("/mem/mypage/?userId="+state.userId,{
    headers:{
      Authorization:"Bearer "+state.accessToken
    }
  })
  .then(res=>{
    console.log(res.data)
  })
  .catch(err=>{
    console.log(err)
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
  console.log(payload)
  const url = '/notice'
  axios({
    url: url,
    method: 'post',
    headers:{
      Authorization:"Bearer "+state.accessToken
    },
    data: {
      title: payload.title,
      content: payload.content
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