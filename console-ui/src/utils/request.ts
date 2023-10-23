import axios from "axios";
import { useSnackbarStore } from "@/stores/snackbarStore";

const request = axios.create({
  baseURL: "/appApi",
  timeout: 100000,
});

request.interceptors.request.use(
  (config) => {
    //config.headers['Content-Type'] = 'application/json';
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// response 拦截器
// 可以在接口响应后统一处理结果
request.interceptors.response.use(
  (response) => {
    const snackbarStore = useSnackbarStore();
    if (response.status != 200) {
      snackbarStore.showSuccessMessage("服务异常!");
    }
    let res = response.data;
    if (res.code === 114514) {
      snackbarStore.showErrorMessage(res.msg);
    }
    // 如果是返回的文件
    if (response.config.responseType === "blob") {
      return res;
    }
    // 兼容服务端返回的字符串数据
    if (typeof res === "string") {
      res = res ? JSON.parse(res) : res;
    }
    return res;
  },
  (error) => {
    const snackbarStore = useSnackbarStore();
    snackbarStore.showErrorMessage("操作异常!");
    console.log("err" + error); // for debug
    // localStorage.removeItem('token')
    //router.replace({path:'/login'})
    return Promise.reject(error);
  }
);

export default request;
