import request from '@/utils/request';

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
