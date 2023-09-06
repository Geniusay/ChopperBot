import request from '@/utils/request';


export function allTask() {
  return request({
    url: '/creeper/taskCenter/getAllTask',
    method: 'get',
    params: {
    }
  });
}

export function stopTask(taskId:string) {
  return request({
    url: '/creeper/taskCenter/stop',
    method: 'get',
    params: {
      taskId:taskId
    }

  });
}

export function allCreeper() {
  return request({
    url: '/creeper/creeperManager/getAllCreeper',
    method: 'get',
    params: {

    }

  });
}

export function startMonitor(taskId:string){
  return request({
    url: '/creeper/monitor/start',
    method: 'get',
    params: {
      taskId:taskId
    }
  })
}

export function stopMonitor(taskId:string){
  return request({
    url: '/creeper/monitor/stop',
    method: 'get',
    params: {
      taskId:taskId
    }
  })
}
