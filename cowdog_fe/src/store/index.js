// Vuex 조립
import { createStore } from 'vuex'
import * as getters from './getters'
import * as mutations from './mutations'
import * as actions from './actions'
import createPersistedState from 'vuex-persistedstate';

const state = {
  notices: "기본값",
  reportedUsers: '기본값',
  noticeDetailData: '기본값',
  myinfo:[],//유저 정보
  accessToken:'',// 토큰
  userId:'',//유저 pk 값
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
