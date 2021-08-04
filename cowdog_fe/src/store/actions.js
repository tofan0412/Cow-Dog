import axios from 'axios'
import $axios from 'axios'
<<<<<<< HEAD
import router from '../router'
import cookies from 'vue-cookies'

=======
>>>>>>> merging_test
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

// 공지사항 관련 actions
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

<<<<<<< HEAD
export function postNotice({ state }, payload) {
=======
export function postNotice({ state, commit }, payload) {
>>>>>>> merging_test
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
<<<<<<< HEAD
      return res.data
=======
      commit('GET_NOTICES', res.data)
    })
    .catch(err => {
      console.log(err)
    })
}
export function updateNotice({ state, commit }, payload) {
  const url = '/notice'
  axios({
    url: url,
    method: 'put',
    headers: {
      Authorization:"Bearer "+state.accessToken
    },
    data: {
      noticeNo: payload.noticeNo,
      title: payload.title,
      content: payload.content
    }
  })
    .then(res => {
      console.log(res)
      commit('GET_NOTICES', res.data)
>>>>>>> merging_test
    })
    .catch(err => {
      console.log(err)
    })
}

<<<<<<< HEAD
=======
export function deleteNotice({ state, commit }, payload) {
  // noticeNo를 받아 백엔드에 전달
  console.log(payload)
  const url = '/notice/' + payload
  axios({
    url: url,
    method: 'delete',
    headers:{
      Authorization:"Bearer "+state.accessToken
    }
  })
    .then(res => {
      console.log(res)
      commit('GET_NOTICES', res.data)
    })
    .catch(err => {
      console.log(err)
    })
}
>>>>>>> merging_test
// 유저 신고 관련 actions
export function getReportedUsers({ state, commit }) {
  const url = '/user-report'
  axios({
    url: url,
    method: 'get',
    headers:{
      Authorization:"Bearer "+state.accessToken
    },
  })
    .then(res => {
      console.log(res.data)
      commit('GET_REPORTED_USERS', res.data)
    })
    .catch(err => {
      console.log(err)
    })  
}

<<<<<<< HEAD
=======
export function deleteUserReport({ state, commit }, payload) {
  const url = '/user-report/' + payload
  axios({
    url: url,
    method: 'delete',
    headers:{
      Authorization:"Bearer "+state.accessToken
    }
  })
    .then(res => {
      commit('GET_REPORTED_USERS', res.data)
    })
    .catch(err => {
      console.log(err)
    })
}

>>>>>>> merging_test
export function postUserReport({ state }, payload) {
  console.log(payload)
  const url = '/user-report'
  axios({
    url: url,
    method: 'post',
    headers:{
      Authorization:"Bearer "+state.accessToken
    },
    data: {
      title: payload.title,
      content: payload.content,
      reportedId: payload.reportedId
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

<<<<<<< HEAD
=======
export function deleteReportedUser({ state, commit }, payload) {
  const url = '/user-report/user/' + payload.user_id + '/' + payload.user_long_id // user의 id를 payload로
  axios({
    url: url, method: 'delete', headers: {Authorization:"Bearer "+state.accessToken}, 
  })
    .then(res => {
      console.log(res.data)
      commit('GET_REPORTED_ARTICLES', res.data)
    })
    .catch(err => {
      console.log(err)
    })
}

>>>>>>> merging_test
// 게시글 신고 관련 actions
export function getReportedArticles({ state, commit }) {
  const url = '/article-report'
  axios({
    url: url,
    method: 'get',
    headers:{
      Authorization:"Bearer "+state.accessToken
    },
  })
    .then(res => {
      console.log(res.data)
      commit('GET_REPORTED_ARTICLES', res.data)
    })
    .catch(err => {
      console.log(err)
    })  
}

export function postArticleReport({ state }, payload) {
  console.log(payload)
  const url = '/article-report'
  axios({
    url: url,
    method: 'post',
    headers:{
      Authorization:"Bearer "+state.accessToken
    },
    data: {
      title: payload.title,
      content: payload.content,
      reportedArticleNo: payload.reportedArticleNo,
      articleUrl: payload.articleUrl,
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

<<<<<<< HEAD
export function checklogin() {
  const cookie = cookies.get("csrftoken")
  if (!cookie) {
    alert("로그인 해주세요")
    router.push("/login")
  }
  
}

export function createArticle({ state }, payload) {
  console.log(state)

  const url = "/appeal/create"
  axios({
    url: url,
    method: "POST",
    data: {
      title: payload.title,
      content: payload.content,
      member_id: payload.member_id,
      headers:{
        Authorization:"Bearer "+state.accessToken
      },
    }
  })
  .then(resp => {
    console.log(resp)
  })
  .catch(err => {
    console.log(err)
  })
=======
export function deleteArticleReport({ state, commit }, payload) {
  const url = '/article-report/' + payload
  axios({
    url: url, method: 'delete', headers: {Authorization:"Bearer "+state.accessToken}
  })
    .then(res => { commit('GET_REPORTED_ARTICLES', res.data) })
    .catch(err => {console.log(err)})
>>>>>>> merging_test
}