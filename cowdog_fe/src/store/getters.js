export function getNotices (state) {
	return state.notices
}
export function getReportedUsers (state) {
	return state.reportedUsers
}
export function getReportedArticles (state) {
	return state.reportedArticles
}

export function getUserId(state){
	return state.login_user
}

export function getUserToken(state){
	return state.access_token
}