
import VueAxios from 'vue-axios'
import axios from 'axios'
// import config from '../config.j'

const BASE_URL = 'localhost://8080/cowdog'
const DEFAULT_ACCEPT_TYPE = 'application/json'

axios.defaults.baseURL = BASE_URL
axios.defaults.headers['Content-Type'] = DEFAULT_ACCEPT_TYPE
axios.defaults.headers.common['Content-Type'] = 'application/x-www-form-urlencoded' 
axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';

export default { VueAxios, axios }
