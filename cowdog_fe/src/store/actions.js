import axios from 'axios'
import $axios from 'axios'
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