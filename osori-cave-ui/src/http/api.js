import Axios from 'axios'

const instance = Axios.create({
    baseURL: process.env.NODE_ENV === 'production' ? 'http://localhost:8080' : 'http://localhost:8080' // it's temporary url which is to test jwt auth
});
export default instance
