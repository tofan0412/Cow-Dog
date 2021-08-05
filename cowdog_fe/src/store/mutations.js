import router from '../router'
export const GET_NOTICES = (state, noticeDatas) => {
    state.notices = noticeDatas
    console.log(noticeDatas)
    router.push('/admin/notice') // Admin 클릭 => notices가 로드되고 난 후에 notice 페이지로 push
}
export const GET_MYINFO = (state, myinfoData) => {
    state.myinfo = myinfoData
    router.push('/mypage/myinfo') // Admin 클릭 => notices가 로드되고 난 후에 notice 페이지로 push
}
export const GET_REPORTED_USERS = (state, reportedUserDatas) => {
    state.reportedUsers = reportedUserDatas
    router.push('/admin/user-report')
}

export const GET_REPORTED_ARTICLES = (state, reportedArticleDatas) => {
    state.reportedArticles = reportedArticleDatas
    router.push('/admin/article-report')
}
export const GET_LOGIN_USER=(state,login_user)=>{
    console.log(login_user)
    state.userId=login_user.userId
    state.accessToken=login_user.accessToken

    console.log(state.userId)
    console.log(state.accessToken)
}

export const USER_LOGOUT=(state)=>{
    state.accessToken=''
    state.userId=''
    state.myinfo=[]
    router.push('/login')
}


export const SET_ARTICLES = (state, articles) => {
    state.articles = articles
}