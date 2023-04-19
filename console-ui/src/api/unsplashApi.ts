import axios from "axios";
import { useSnackbarStore } from "@/stores/snackbarStore";
const snackbarStore = useSnackbarStore();
// change the access key to your own
const ACCESS_KEY = import.meta.env.VITE_UNSPLASH_ACCESS_KEY;

const instance = axios.create({
  baseURL: "https://api.unsplash.com",
  timeout: 20000,
  headers: { Authorization: "Client-ID" + " " + ACCESS_KEY },
});

instance.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    if (error.response) {
      const status = error.response.status;
      const data = error.response.data;
      snackbarStore.showErrorMessage(data.errors[0]);
    } else {
      snackbarStore.showErrorMessage("Network Error");
    }
    return Promise.reject(error);
  }
);

interface Query {
  page?: number;
  per_page?: number;
}

// List photos 图片一览
export const getPhotosApi = (query?: Query) => {
  return instance.get("/photos/", { params: query });
};

// Get a photo 获取图片信息
export const getPhotoApi = (id: string) => {
  return instance.get("/photos/" + id);
};

// Get a random photo 获取一张随机图片
export const getRandomPhotoApi = () => {
  return instance.get("/photos/random");
};

// Get a photo’s statistics 获取照片的统计数据
export const getPhotoStatisticsApi = (id: string) => {
  return instance.get("/photos/" + id + "/statistics");
};

// Get a photo’s related 获取照片的相关照片
export const getPhotoRelatedApi = (id: string) => {
  return instance.get("/photos/" + id + "/related");
};

// Track a photo download
// Update a photo
// Like a photo
// Unlike a photo

// Topic
// List topics
export const getTopicsApi = (query?: Query) => {
  return instance.get("/topics", { params: query });
};

// Get a topic
export const getTopicApi = (id_or_slug: string | string[]) => {
  return instance.get("/topics/" + id_or_slug);
};

// Get a topic’s photos
export const getTopicPhotosApi = (
  id_or_slug: string | string[],
  query?: Query
) => {
  return instance.get("/topics/" + id_or_slug + "/photos", { params: query });
};

// Get a user
export const getUserApi = (username: string | string[]) => {
  return instance.get("/users/" + username);
};
// Get a user’s portfolio
export const getUserPortfolioApi = (username: string | string[]) => {
  return instance.get("/users/" + username + "/portfolio");
};
// List a user’s photos
export const getUserPhotosApi = (
  username: string | string[],
  query?: Query
) => {
  return instance.get("/users/" + username + "/photos", { params: query });
};
// List a user’s liked photos
export const getUserLikesApi = (username: string | string[], query?: Query) => {
  return instance.get("/users/" + username + "/likes", { params: query });
};
// List a user’s collections
export const getUserCollectionsApi = (
  username: string | string[],
  query?: Query
) => {
  return instance.get("/users/" + username + "/collections", { params: query });
};
// Get a user’s statistics
export const getUserStatisticsApi = (username: string | string[]) => {
  return instance.get("/users/" + username + "/statistics");
};

// Collections 图集
// List collections 图集一览
export const getCollectionsApi = (query?: Query) => {
  return instance.get("/collections", { params: query });
};

// Get a collection 获取图集信息
export const getCollectionApi = (id: string | string[]) => {
  return instance.get("/collections/" + id);
};
// Get a collection’s photos 获取该图集下所有图片
export const getCollectionPhotosApi = (
  id: string | string[],
  query?: Query
) => {
  return instance.get("/collections/" + id + "/photos", { params: query });
};
// List a collection’s related collections 获取该图集相关联图集
export const getCollectionRelatedApi = (id: string | string[]) => {
  return instance.get("/collections/" + id + "/related");
};
// Create a new collection 新增图集
// Update an existing collection 更新现存图集
// Delete a collection 删除某个图集
// Add a photo to a collection 添加图片到图集
// Remove a photo from a collection 从图集删除图片

// Search
// Search All
export const searchAllApi = (query?: Query) => {
  return instance.get("/search", { params: query });
};

// Search photos
export const searchPhotosApi = (query?: Query) => {
  return instance.get("/search/photos", { params: query });
};

// Search collections
export const searchCollectionsApi = (query?: Query) => {
  return instance.get("/search/collections", { params: query });
};

// Search users
export const searchUsersApi = (query?: Query) => {
  return instance.get("/search/users", { params: query });
};
