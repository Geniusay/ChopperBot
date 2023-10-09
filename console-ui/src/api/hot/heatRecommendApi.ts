import request from '@/utils/request';
import {FollowDog} from "@/views/app/hot/heat_recommend/FollowDogTypes";

export function openFollowDog(platform:string,isOpen:boolean) {
  return request({
    url: '/hot/hotRecommendation/open',
    method: 'get',
    params: {
      platform,
      isOpen
    }
  });
}

export function getFollowDogs(){
  return request({
    url: '/hot/hotRecommendation/list',
    method: 'get',
  });
}

export function addFollowDog(dog:FollowDog){
  return request.post("/hot/hotRecommendation/add",dog)
}

export function updateFollowDog(dog:FollowDog){
  return request.post("/hot/hotRecommendation/update",dog)
}

export function deleteFollowDog(dogId:string,platform:string){
  return request({
    url: '/hot/hotRecommendation/delete',
    method: 'get',
    params: {
      dogId,
      platform
    }
  });
}
