import axios from 'axios'
import $axios from 'axios'
import router from '../router'
// import cookies from 'vue-cookies'

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

export function postNotice({ state, commit }, payload) {
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
    })
    .catch(err => {
      console.log(err)
    })
}

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

export function checklogin({ state }) {
  const accessToken = state.accessToken
  if (accessToken == '') {
    alert("로그인 해주세요")
    router.push("/login")
  }
}

export function createArticle({ state, commit }, payload) {
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
    // 게시글 작성 후 디테일 페이지로 이동한다.
    const article_no = resp.data.message
    // vuex state에 저장한다. 저장과 동시에 디테일 페이지로 이동한다.
    commit("SET_ARTICLENO", article_no)

    
  })
  .catch(err => {
    console.log(err)
  })
}

export function getArticles({ commit }) {  
  const url = "/appeal"
  axios({
    url: url,
    method: 'GET',
  })
  .then(resp => {
    commit("SET_ARTICLES", resp.data)
  })
  .catch(err => {
    console.log(err)
  })
}

export function goToDetail({ commit }, payload) {
  // store에 저장되어 있는 게시글 번호를 수정하고
  // 해당 게시글의 상세 페이지로 이동한다.
  const article_no = payload.article_no
  commit("SET_ARTICLENO", article_no)
}

export function findArticleByArticleNo({ state, commit }) {
  // console.log("게시글 객체 시작: ", state.articleNo)
  
  const url = "/appeal/detail"
  axios({
    url: url,
    method: "GET",
    headers:{
      Authorization:"Bearer "+state.accessToken
    },
    data: {
      articleNo: state.articleNo
    }
  })
  .then(resp => {
    console.log(resp.data)
    commit("SET_ARTICLEDATA", resp.data)
  })
  .catch(err => {
    console.log(err)
  })
}


export function deleteArticleReport({ state, commit }, payload) {
  const url = '/article-report/' + payload
  axios({
    url: url, method: 'delete', headers: {Authorization:"Bearer "+state.accessToken}
  })
    .then(res => { commit('GET_REPORTED_ARTICLES', res.data) })
    .catch(err => {console.log(err)})
}