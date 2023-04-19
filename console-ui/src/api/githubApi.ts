import axios from "axios";
import { useSnackbarStore } from "@/stores/snackbarStore";
const snackbarStore = useSnackbarStore();
// change the access key to your own
const ACCESS_KEY = import.meta.env.VITE_GITHUB_CLIENT_ID;

const instance = axios.create({
  baseURL: "https://api.github.com",
  timeout: 20000,
  headers: { Authorization: "Bearer" + " " + ACCESS_KEY },
});

instance.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    if (error.response) {
      const status = error.response.status;
      const data = error.response.data;
      snackbarStore.showErrorMessage(data.message);
    } else {
      snackbarStore.showErrorMessage("Network Error");
    }
    return Promise.reject(error);
  }
);

// https://api.github.com/users/yangjiakai/events/public

// Get public events for a user
export const getPublicEventsApi = (username: string) => {
  return instance.get("/users/" + username + "/events/public");
};

// Get public events for a network of repositories
export const getPublicEventsForNetworkApi = (username: string) => {
  return instance.get("/networks/" + username + "/events");
};
