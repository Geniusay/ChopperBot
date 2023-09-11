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

export function enablePlugin(plugin:string) {
  return switchAutoStart(plugin,true);
}

export function disabledPlugin(plugin:string) {
  return switchAutoStart(plugin,false);
}

export function switchAutoStart(plugin:string,isOpen:boolean){
  return request({
    url: '/plugin/switchAutoStart/',
    method: 'get',
    params: {
      plugin: plugin,
      isOpen: isOpen
    }
  })
}
