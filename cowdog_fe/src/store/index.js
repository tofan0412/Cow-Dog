// Vuex 조립
import { createStore } from 'vuex'
import * as getters from './getters'
import * as mutations from './mutations'
import * as actions from './actions'
import createPersistedState from 'vuex-persistedstate'
const state = {
  articleNo: "기본값",
  articles: "기본값",
  notices: "기본값",
  reportedUsers: '기본값',
  reportedArticles: '기본값',
  articleDetailData: '기본값',
  noticeDetailData: '기본값',
  accessToken:'',// 
  userId:'',
}

const root = createStore({
  namespaced: true,
  state,
  getters,
  mutations,
  actions,
  plugins: [
    createPersistedState()
  ]
})

export default root
