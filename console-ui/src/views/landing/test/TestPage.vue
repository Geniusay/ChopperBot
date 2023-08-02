<template>
  <div style="height: 100vh;width: 100vw;margin:200px">
    <input  v-model="room_id" placeholder="房间号">
    <button @click="douyu_start">启动！</button>
    <video id="videoEl" controls width="1000" height="800"></video>
  </div>
</template>

<script>
  // 开始-》房间号
  // get请求...douyu/{房间号}
  // 重定向至直播间页面
  // 从返回的页面获取’ub9848...‘函数(生成加密函数（字符串）并用eval运行）
  // 运行’ub9848...‘函数
  // 将加密函数（字符串）中的加密部分（用到CryptoJS）在外面处理，然后将结果替换
  // eval运行加密函数
  // 返回get请求部分参数（后面用）
  // get请求douyu/.../getH5Play/{房间号}/参数
  // 从返回值中获取一段字符串（{房间号}xxxxx.flv)
  import axios from 'axios'
  import flvjs from "flv.js"
  import CryptoJS from "crypto-js"
  import {getHtmlApi,getUrlApi} from "../../../api/douyuApi";

  var flvPlayer
  export default {
    name: '',
    data() {
      return {
        room_id: "9450066",
        did: "818074ef9c05a3fe94acdfe500091601",
        baseUrl: "http://openflv-huos.douyucdn2.cn/dyliveflv1/",
        flv_url: "",
        xs_url: ""
      }
    },
    mounted () {

    },
    methods: {
      douyu_start () {
        getHtmlApi(this.room_id).then((res)=>{
          var html = res.data
          var result = html.match(/(function ub98484234[\s\S]*?return eval[\s\S]*?);}/)
          if(result == undefined){
            console.log('未开放')
            return;
          }
          var js_fun = result[0]  //截取函数部分
          var param_str = js_fun.slice(js_fun.indexOf('(') + 1,js_fun.indexOf(')'))  //截取形参部分
          var params = param_str.split(',');  //获取形参名
          var index = js_fun.indexOf('){')
          js_fun = js_fun.slice(index+2,js_fun.length-2)  //保留函数体


          var js_var = html.match(/(var vdwdae325w_64we[\s\S]*?;)/)[0]  //截取需要的第一个变量（固定名字）
          var var2_name = js_fun.match(/v = ([\s\S]*).slice[\s\S]*?/)[1]  //获取需要截取的另一个变量名称
          var js_var2 = html.match("\\b(var " + var2_name + "[\\s\\S]*?;)", "g")[0]  //截取另一个变量
          js_fun = js_var + js_var2 + js_fun  //将定义变量放在函数体前

          var cb = this.room_id + this.did + parseInt((new Date).getTime() / 1e3, 10) + this.param_v();
          var rb =  CryptoJS.MD5(cb).toString();
          var insert = ".replace('CryptoJS.MD5(cb).toString()','\""+rb+"\"')"
          var index2 = js_fun.lastIndexOf('strc');
          js_fun = js_fun.slice(0,index2+4)+insert+js_fun.slice(index2+4)
          var ub = new Function(params[0],params[1],params[2],js_fun)  //定义新函数
          getUrlApi(this.room_id,ub(this.room_id, "818074ef9c05a3fe94acdfe500091601", parseInt((new Date).getTime() / 1e3, 10))).then((res2)=>{
            if(res2.data.error === -5){
              console.log('未开播')
              return;
            }
            var name = res2.data.data.rtmp_live;
            name = name.slice(0,name.indexOf('.'));
            this.flv_url = this.baseUrl+name+'.flv';
            this.xs_url = this.baseUrl+name+'.xs';
            this.openPlayer()
          })
        })
      },
      param_v () {
        const date = new Date();
        const year = date.getFullYear();
        const month = (date.getMonth() + 1).toString().padStart(2, '0');
        const day = date.getDate().toString().padStart(2, '0');
        const formattedDate = `${year}${month}${day}`;
        return '2201' + formattedDate
      },
      openPlayer(){
        if (flvjs.isSupported()) {
          if(flvPlayer)
            flvPlayer.destroy()
          var videoEl = document.getElementById('videoEl')
          flvPlayer = flvjs.createPlayer({
            isLive:false,
            type: 'flv',
            url: this.xs_url.toString()
          })
          flvPlayer.attachMediaElement(videoEl)
          flvPlayer.load()
          flvPlayer.play()
        }
      }
    }
  }
</script>

<style scoped>
  *{
    margin:none;
  }
  input{
    height:40px;
    border:rgb(102,102,102) 1px solid;
    outline:none;
  }
  button{
    height:41px;
    border:rgb(102,102,102) 1px solid;
    background-color:white;
    padding: 5px;
    text-align:center
  }
  button:hover{
    background-color: grey
  }
</style>
