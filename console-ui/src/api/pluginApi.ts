import request from '@/utils/request';


export function allPlugins() {
  return request({
    url: '/plugin/get/',
    method: 'get',
    params: {
    }
  });
}

export function closePlugin(plugin:string) {
  return request({
    url: '/plugin/close/',
    method: 'get',
    params: {
      plugin: plugin
    }

  });
}


export function startPlugin(plugin:string) {
  return request({
    url: '/plugin/start/',
    method: 'get',
    params: {
      plugin: plugin
    }

  });
}
