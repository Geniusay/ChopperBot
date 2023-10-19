import request from '@/utils/request';

export function curveList(){
  return request({
    url: '/barrage/barrageScoreCurve/curveList',
    method: 'get',
  });
}

export function curveGenerate(filepath?:string,liver?:string) {
  return request({
    url: '/barrage/barrageScoreCurve/generate',
    method: 'get',
    params: {
      filepath,
      liver,
    }
  });
}


