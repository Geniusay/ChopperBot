const url = "ws://localhost:8888/chopperBot";

class WebSocketClient {
  private socket: WebSocket | null = null;

  constructor(openFunc,msgFunc) {
    openFunc= openFunc==null?()=> {
        console.log('WebSocket 连接已打开');
      }:openFunc

    msgFunc = msgFunc==null?(event)=>{
        console.log(`收到消息: ${event.data}`);
      }:msgFunc

    this.connect(openFunc,msgFunc);
  }


  private connect(openFunc,msgFunc) {
    this.socket = new WebSocket(url);

    this.socket.addEventListener('open', openFunc);

    this.socket.addEventListener('message', msgFunc);

    this.socket.addEventListener('error', (event) => {
      console.error('WebSocket 错误:', event);
    });

    this.socket.addEventListener('close', (event) => {
      if (event.wasClean) {
        console.log(`WebSocket 已关闭，状态码: ${event.code}, 原因: ${event.reason}`);
      } else {
        console.error('WebSocket 连接中断');
      }
    });
  }

  encodeMsg(type:string,data:string){

     return "type@="+type+"|data@="+data+"|"
  }

  decodeMsg(msg:string){
    const result = new Map();

    // 使用正则表达式匹配字符串中的 "key@=value" 对
    const regex = /(\w+)@=([^|]+)(?:\||$)/g;
    let match;

    // 使用正则表达式的 exec 方法来匹配所有的 "key@=value" 对
    while ((match = regex.exec(msg)) !== null) {
      const key = match[1];
      const value = match[2];

      // 将匹配到的 key 和 value 添加到 Map 中
      result.set(key, value);
    }

    return result;
  }

  sendMsg(message: string) {
    if (this.socket && this.socket.readyState === WebSocket.OPEN) {
      this.socket.send(message);
    } else {
      console.error('WebSocket 连接尚未打开');
    }
  }

  close() {
    if (this.socket) {
      this.socket.close();
      this.socket = null;
    }
  }
}

export default WebSocketClient;
