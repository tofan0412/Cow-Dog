import axios from 'axios'
import $axios from 'axios'
import router from '../router'
// import cookies from 'vue-cookies'

export function getMyInfo({state,commit}){
  // console.log(state.userId)
  // console.log(state.accessToken)
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

export function setUserInfo({state, commit}) {
  // console.log("로그인 성공! 유저 정보를 vuex store에 저장합니다..")
  axios.get("/mem/mypage/?userId="+state.userId,{
    headers:{
      Authorization:"Bearer "+state.accessToken
    }
  })
  .then(res=>{
    console.log(res.data)
    commit('SET_USERINFO', res.data);
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
      commit('GET_REPORTED_USERS', res.data)
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

export function deleteReportedArticle({ state, commit }, payload) {
  console.log(payload)
  const url = '/article-report/reported-article/' + payload.reportedArticleNo + '/' + payload.reportedArticleLongNo
  axios({
    url: url, method: 'delete', headers:{ Authorization:"Bearer "+state.accessToken},
  })
    .then(res => {
      console.log(res.data)
      commit('GET_REPORTED_ARTICLES', res.data)
    })
    .catch(err => {
      console.log(err)
    })
}

export function checklogin({ state }) {
  const accessToken = state.accessToken
  if (accessToken == '') {
    alert("로그인 해주세요.")
    router.push("/login")
    return
  }
}

export function createArticle({ state, commit }, payload) {
  console.log(payload)
  const url = "/appeal/create"
  axios({
    url: url,
    method: "POST",
    headers:{
      Authorization:"Bearer "+state.accessToken
    },
    data: {
      title: payload.title,
      content: payload.content,
      member_id: payload.member_id,
      writer:payload.writer
    }
  })
  .then(resp => {
    console.log("create result: ", resp)
    // 게시글 목록 재업로드
    axios({
      url: "/appeal",
      method: 'GET',
      headers:{
        Authorization:"Bearer "+state.accessToken
      },
    })
    .then(resp => {
      commit("SET_ARTICLES", resp.data)
      router.push("/appeal")
    })
    .catch(err => {
      console.log(err)
    })    
  })
  .catch(err => {
    console.log(err)
  })
}

export function getArticles({ state, commit }) {  
  console.log("게시글 목록 가져옵니다..")
  const url = "/appeal"
  axios({
    url: url,
    method: 'GET',
    headers:{
      Authorization:"Bearer "+state.accessToken
    },
  })
  .then(resp => {
    // 날짜 전처리 필요하다...
    console.log("날짜 전처리 시작합니다.")
    for (let i = 0; i < resp.data.length; i++) {
      console.log(resp.data[i].regtime)
      console.log(resp.data[i])
      const date = new Date(resp.data[i].regtime).toDateString()
      resp.data[i].regtime = date
    }

    commit("SET_ARTICLES", resp.data)
  })
  .catch(err => {
    console.log(err)
  })
}

export function getArticle({ state }, payload){
  console.log(state)
  const url = '/appeal/detail?articleNo=' + payload.articleNo
  return axios({
    url: url,
    method: 'GET',
    headers:{
      Authorization:"Bearer "+state.accessToken
    },
  })
}

export function userLogout({state,commit},payload){
  console.log(payload.id)
  const url = '/mem/logout/?id='+payload.id
  axios({
    url: url,
    method: 'get',
    headers:{
      Authorization:"Bearer "+state.accessToken
    },
  })
    .then(res => {
      console.log(res.data)
      if(res.data==="SUCCESS"){
        alert("로그아웃")
        commit("USER_LOGOUT")
      }
    })
    .catch(err => {
      console.log(err)
    })  
}

export function updateArticlePage({ state }, payload) {
  console.log(state)
  console.log(payload.article.articleNo)
  router.push({name: 'AppealUpdate', params: { articleNo: payload.article.articleNo }})
}

export function updateArticle({ state }, payload) {
  const url = '/appeal/update'
  axios({
    url: url,
    method: "PUT",
    headers:{
      Authorization:"Bearer "+state.accessToken
    },
    data: {
      articleNo: payload.articleNo,
      title: payload.title,
      content: payload.content,
    }
  })
  .then(resp => {
    console.log(resp)
    // 목록을 수정한다.
  })
  .catch(err => {
    console.log(err)
  })
}

export function deleteArticle({ state, commit }, payload) {
  // 현재 로그인한 사용자와 게시글을 작성한 사용자의 PK 비교 
  if (payload.memberId !== state.userId) {
    alert("권한이 없습니다.")
    return
  }
  else{

    const answer = confirm("게시글을 삭제하시겠습니까?")
    if (answer) {
      const url = "/appeal/delete?articleNo=" + payload.articleNo
      axios({
        url: url,
        method: "DELETE",
        headers:{
          Authorization:"Bearer "+state.accessToken
        },
      })
      .then(resp => {
        console.log("게시글 삭제 완료!!!", resp)
        
        // 게시글 목록 갱신
        axios({
          url: "/appeal",
          method: 'GET',
          headers:{
            Authorization:"Bearer "+state.accessToken
          },
        })
        .then(resp => {
          console.log("게시글 목록 갱신: ", resp.data)
          commit("SET_ARTICLES", resp.data)
        })
        .catch(err => {
          console.log(err)
        })
      })
      .catch(err => {
        console.log(err)
      })
    }
  }
  
}


export function deleteArticleReport({ state, commit }, payload) {
  const url = '/article-report/' + payload
  axios({
    url: url, method: 'delete', headers: {Authorization:"Bearer "+state.accessToken}
  })
    .then(res => { commit('GET_REPORTED_ARTICLES', res.data) })
    .catch(err => {console.log(err)})
}


//매칭 기능

export function randomMatching({state,commit}){
  console.log(state.userId)
  console.log("랜덤매칭~~~")
  const url='/matching/randomMatching/?id='+state.userId
  axios({
    url: url,
    method: 'get',
    headers:{
      Authorization:"Bearer "+state.accessToken
    },
  })
    .then(res => {
      console.log(res)
      if(res.data.message=="SUCCESS"){
        console.log(res.data.list[0])
        commit("SET_RANDOM_USER",res.data.list)
      }
      
    })
    .catch(err => {
      console.log(err)
    })  
}

export function disatnceMatching({state,commit},payload){
  console.log(state.userId)
  console.log("거리매칭~~~")
  console.log(payload)
  const url='/matching/distanceMatching/'
  axios({
    url: url,
    method: 'get',
    params:{
      dist:payload,
      id:state.userId
    },
    headers:{
      Authorization:"Bearer "+state.accessToken
    },
  })
    .then(res => {
      console.log(res)
      if(res.data.message=="SUCCESS"){
        console.log(res.data.list)
        commit("SET_DISTANCE_USER",res.data.list)
      }
      
    })
    .catch(err => {
      console.log(err)
    })  
}


export function alcohol({state,commit},payload){
  console.log(state.userId)
  console.log("주량 비슷한 사람")
  console.log(payload)
  const url='/matching/alcohol/?id='+state.userId
  axios({
    url: url,
    method: 'get',
    headers:{
      Authorization:"Bearer "+state.accessToken
    },
  })
    .then(res => {
      console.log(res)
      if(res.data.message=="SUCCESS"){
        console.log(res.data.list[0])
        commit("SET_RECOM_USER",res.data.list)
      }
      
    })
    .catch(err => {
      console.log(err)
    })  

}
export function sameHobby({state,commit},payload){
  console.log(state.userId)
  console.log("취미 비슷한 사람")
  console.log(payload)
  const url='/matching/sameHobby/?id='+state.userId
  axios({
    url: url,
    method: 'get',
    headers:{
      Authorization:"Bearer "+state.accessToken
    },
  })
    .then(res => {
      console.log(res)
      if(res.data.message=="SUCCESS"){
        console.log(res.data.list[0])
        commit("SET_RECOM_USER",res.data.list)
      }
      
    })
    .catch(err => {
      console.log(err)
    })  
}
export function wantMale({state,commit},payload){
  console.log(state.userId)
  console.log("남자를 원하는 사람")
  console.log(payload)
  const url='/matching/wantMale/?id='+state.userId
  axios({
    url: url,
    method: 'get',
    headers:{
      Authorization:"Bearer "+state.accessToken
    },
  })
    .then(res => {
      console.log(res)
      if(res.data.message=="SUCCESS"){
        console.log(res.data.list[0])
        commit("SET_RECOM_USER",res.data.list)
      }
      
    })
    .catch(err => {
      console.log(err)
    })  
}
export function wantFemale({state,commit},payload){
  console.log(state.userId)
  console.log("여자를 원하는 사람")
  console.log(payload)
  const url='/matching/wantFemale/?id='+state.userId
  axios({
    url: url,
    method: 'get',
    headers:{
      Authorization:"Bearer "+state.accessToken
    },
  })
    .then(res => {
      console.log(res)
      if(res.data.message=="SUCCESS"){
        console.log(res.data.list[0])
        commit("SET_RECOM_USER",res.data.list)
      }
      
    })
    .catch(err => {
      console.log(err)
    })  
}
export function nonSmoking({state,commit},payload){
  console.log(state.userId)
  console.log("담배 안피는 사람")
  console.log(payload)
  const url='/matching/nonSmoking/?id='+state.userId
  axios({
    url: url,
    method: 'get',
    headers:{
      Authorization:"Bearer "+state.accessToken
    },
  })
    .then(res => {
      console.log(res)
      if(res.data.message=="SUCCESS"){
        console.log(res.data.list[0])
        commit("SET_RECOM_USER",res.data.list)
      }
      
    })
    .catch(err => {
      console.log(err)
    })  
}
export function sameReligion({state,commit},payload){
  console.log(state.userId)
  console.log("종교가 같은 사람")
  console.log(payload)
  const url='/matching/sameReligion/?id='+state.userId
  axios({
    url: url,
    method: 'get',
    headers:{
      Authorization:"Bearer "+state.accessToken
    },
  })
    .then(res => {
      console.log(res)
      if(res.data.message=="SUCCESS"){
        console.log(res.data.list[0])
        commit("SET_RECOM_USER",res.data.list)
      }
      
    })
    .catch(err => {
      console.log(err)
    })  
}
export function sameMbti({state,commit},payload){
  console.log(state.userId)
  console.log("mbti")
  console.log(payload)
  const url='/matching/sameMbti/?id='+state.userId
  axios({
    url: url,
    method: 'get',
    headers:{
      Authorization:"Bearer "+state.accessToken
    },
  })
    .then(res => {
      console.log(res)
      if(res.data.message=="SUCCESS"){
        console.log(res.data.list[0])
        commit("SET_RECOM_USER",res.data.list)
      }
      
    })
    .catch(err => {
      console.log(err)
    })  
}
export function samePersonality({state,commit},payload){
  console.log(state.userId)
  console.log("성격 비슷한 사람")
  console.log(payload)
  const url='/matching/samePersonality/?id='+state.userId
  axios({
    url: url,
    method: 'get',
    headers:{
      Authorization:"Bearer "+state.accessToken
    },
  })
    .then(res => {
      console.log(res)
      if(res.data.message=="SUCCESS"){
        console.log(res.data.list[0])
        commit("SET_RECOM_USER",res.data.list)
      }
      
    })
    .catch(err => {
      console.log(err)
    })  
}

//팔로우 기능
export function getFollowUsers({state,commit}){

  console.log(state.userId)
  console.log("나를 팔로우 한 사람 가져오기")
  const url='/like/getFollowUsers/?id='+state.userId
  axios({
    url: url,
    method: 'get',
    headers:{
      Authorization:"Bearer "+state.accessToken
    },
  })
    .then(res => {
      console.log(res)
      if(res.data.message=="SUCCESS"){
        console.log(res.data.list[0])
        commit("SET_FOLLOW_USER",res.data.list)
      }
      
    })
    .catch(err => {
      console.log(err)
    })  
}

export function getFollowEachOther({state,commit}){

  console.log(state.userId)
  console.log("나랑 맞팔로우 한 사람 가져오기")
  const url='/like/getFollowEachOther/?id='+state.userId
  axios({
    url: url,
    method: 'get',
    headers:{
      Authorization:"Bearer "+state.accessToken
    },
  })
    .then(res => {
      console.log(res)
      if(res.data.message=="SUCCESS"){
        console.log(res.data.list[0])
        commit("SET_EACH_OTHER_FOLLOW_USER",res.data.list)
      }
      
    })
    .catch(err => {
      console.log(err)
    })  
}


export function AmIFollowed({ state, commit }) {
  console.log("내가 누구를 팔로우 했는지 확인하기")
  const url='/like/AmIFollowed/?id=' + state.userId
  axios({
    url: url,
    method: 'get',
    headers:{
      Authorization:"Bearer "+state.accessToken
    },
  })
    .then(res => {
      commit("AM_I_FOLLOWED", res.data)
    })
    .catch(err => {
      console.log(err)
    })
}

export function like({state, commit},payload){
  console.log(state.userId)
  console.log("팔로우 합니다~")
  console.log(payload)
  const url='/like/follow'
  axios({
    url: url,
    method: 'post',
    headers:{
      Authorization:"Bearer "+state.accessToken
    },
    data: {
     memberid:payload,//팔로우를 당하는 사람
     followerid:state.userId//팔로우를 하는 사람
    }
  })
    .then(res => {
      commit("AM_I_FOLLOWED", res.data)
    })
    .catch(err => {
      console.log(err)
    })
}



export function unlike({state, commit},payload){
  console.log(state.userId)
  console.log("언팔로우 합니다~")
  console.log(payload)
  const url='/like/follow/' + payload + '/' + state.userId
  axios({
    url: url,
    method: 'delete',
    headers:{
      Authorization:"Bearer "+state.accessToken
    }
  })
    .then(res => {
      commit("AM_I_FOLLOWED", res.data)
    })
    .catch(err => {
      console.log(err)
    })





}