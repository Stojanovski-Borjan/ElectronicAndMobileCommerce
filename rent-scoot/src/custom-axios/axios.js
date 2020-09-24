import axios from 'axios'

const instance = axios.create();
instance.defaults.baseURL = `http://${process.env.REACT_APP_BACKEND_IP}:8080`;

export default instance