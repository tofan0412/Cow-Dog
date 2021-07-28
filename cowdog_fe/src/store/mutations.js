import router from '../router'
export const GET_NOTICES = (state, noticeDatas) => {
    state.notices = noticeDatas
    router.push('/admin/notice') // Admin 클릭 => notices가 로드되고 난 후에 notice 페이지로 push
}

export const GET_REPORTED_USERS = (state, reportedUserDatas) => {
    state.reportedUsers = reportedUserDatas
    router.push('/admin/user-report')
}