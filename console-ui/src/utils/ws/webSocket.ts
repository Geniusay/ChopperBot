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

     return "type@="+type+"/data@="+data+"/"
  }

  decodeMsg(msg:string){

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
