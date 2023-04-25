import axios from "axios";

const request = axios.create({
  baseURL: "/appApi",
  timeout: 100000,
});

request.interceptors.request.use(config => {
  //config.headers['Content-Type'] = 'application/json';
 return config
}, error => {
  return Promise.reject(error)
});

// response 拦截器
// 可以在接口响应后统一处理结果
request.interceptors.response.use(
  response => {
    let res = response.data;

    // 如果是返回的文件
    if (response.config.responseType === 'blob') {
      return res
    }
    // 兼容服务端返回的字符串数据
    if (typeof res === 'string') {
      res = res ? JSON.parse(res) : res
    }
    return res;
  },
  error => {
    console.log('err' + error) // for debug
    // localStorage.removeItem('token')
    //router.replace({path:'/login'})
    return Promise.reject(error)
  }
)


export default request
