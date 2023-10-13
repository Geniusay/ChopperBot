import request from '@/utils/request'

export function getUsers() {
  return request({
    url: '/account/getUser',
    method: 'get',
    params: {
    }
  });
}
