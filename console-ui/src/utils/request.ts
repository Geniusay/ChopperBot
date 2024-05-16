import axios from "axios";
import { useSnackbarStore } from "@/stores/snackbarStore";
// import {storage} from "@/utils/storage";

const request = axios.create({
  baseURL: "/appApi",
  timeout: 10000,
});

const retries = 2; // 设置重试次数为3次
const retryDelay = 1000; // 设置重试的间隔时间
// request 拦截器
request.interceptors.request.use(
  (config) => {
    //config.headers['Content-Type'] = 'application/json';
    // const token = storage.get<string>('token')
    // config.headers.token = token
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
      snackbarStore.showErrorMessage("请求拦截器异常!");
    }
    let res = response.data;
    if (res.code !== 200) {
      snackbarStore.showErrorMessage(res.msg);
    }
    if (res.code === 114514) {
      snackbarStore.showSuccessMessage(res.msg);
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
    // 失败重连
    if(error.response &&
      (error.response.status === 500 || // 服务器发生错误
        error.response.status === 502 || // 网关错误
        error.response.status === 503 || // 服务不可用
        error.response.status === 504 || // 网关超时
        error.response.status === 408// 请求超时
      )
    ){
      const config = error.config
      // 如果config不存在或未设置重试选项，则拒绝
      if (!config || !retries) {
        return Promise.reject(error)
      }
      // 设置变量来跟踪重试次数
      config.__retryCount = config.__retryCount || 0
      // 检查是否达到最大重试次数
      if (config.__retryCount >= retries) {
        return Promise.reject(error)
      }
      // 增加重试计数器
      config.__retryCount += 1
      // 创建一个新的Promise来处理每次重试之前等待一段时间
      const backoff = new Promise((resolve) => {
        setTimeout(() => {
          resolve('重新请求：' + config.__retryCount)
        }, retryDelay || 1)
      })
      // 返回Promise，以便Axios知道我们已经处理了错误
      return backoff.then((txt) => {
        return request(config)
      })
    }
    // 状态码列表
    const statusTextMap: Record<number, string> = {
      204: '无内容',
      400: '您的请求存在语法错误，服务器无法理解',
      401: '登录失效，请重新登录',
      403: '您没有权限执行此操作',
      404: '您所请求的资源无法找到',
      405: '该操作被禁止',
      406: '服务器无法根据请求的内容特性完成请求',
      407: '请求需要代理认证',
      408: '请求超时',
      409: '服务器处理请求时发生了冲突',
      410: '请求的资源已被删除',
      411: '需要Content-Length',
      412: '客户端请求信息的先决条件错误',
      413: '请求的实体过大，服务器无法处理',
      414: '请求的URI过长，服务器无法处理',
      415: '不支持的媒体类型',
      416: '请求的范围不符合要求',
      417: '服务器无法满足请求头中 Expect 字段指定的预期行为',
      418: 'Genius',
      500: '服务器发生错误，请检查服务器',
      501: '服务器不支持请求的功能，无法完成请求',
      502: '网关错误',
      503: '服务不可用，服务器暂时过载或维护中',
      504: '网关超时',
      505: '服务器不支持请求的HTTP协议的版本，无法完成处理'
    }
    const snackbarStore = useSnackbarStore();
    if (error.response && error.response.status) {
      const statusText = statusTextMap[error.response.status] ?? '其他错误'
      snackbarStore.showErrorMessage(`${statusText}(${error.response.status})`)
      if (error.response.status === 401) {
        // storage.remove('token')
        // router.replace({path:'/login'})
      }
      return Promise.reject(error)
    }
    return Promise.reject(error);
  },
);

export default request;
