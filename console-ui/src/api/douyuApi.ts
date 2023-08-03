import axios from "axios";
import { useSnackbarStore } from "@/stores/snackbarStore";
const douyuInstance = axios.create({
  baseURL: "/douyu",
  timeout: 100000,
});

douyuInstance.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    const snackbarStore = useSnackbarStore();
    if (error.response) {
      //const status = error.response.status;
      const data = error.response.data;
      snackbarStore.showErrorMessage(data.error);
    } else {
      snackbarStore.showErrorMessage("Network Error");
    }
    return Promise.reject(error);
  }
);

// Get all models.
export const getHtmlApi = (rid: string) => {
  return douyuInstance.get(""+rid);
};

export const getUrlApi = (rid: string,param: string) => {
  return douyuInstance.post("lapi/live/getH5Play/"+rid+'?'+param+ '&cdn=ws-h5&rate=0');
};
