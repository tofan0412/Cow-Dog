import axios from 'axios'
export function getNotices ({ commit }) {
    axios.get('https://e7d14d3b-41a2-4dc5-874c-a55731c663b9.mock.pstmn.io/noticeList')
    .then(res => {
      console.log(res.data)
      commit('GET_NOTICES', res.data)
    })
    .catch(err => {
      console.log(err)
    })
}