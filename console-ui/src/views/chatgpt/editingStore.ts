import { defineStore } from "pinia";
import { Editing } from "./editingTypes"
import {sessionStorage} from "@/utils/storage";
import {formatDuration} from "@/utils/timeUtils"

export const useEditingStore = defineStore({
  id: "editing",
  state:()=>({
    video_url: '',
    video_long: 0, //视频时长
    start_time: 0, //剪辑起始时间
    end_time: 0, //剪辑结束时间
    timeline_accuracy: 1,// 时间轴比例
    play_status: false, // 播放状态
    play_start: 0 //播放开始时间
  }),
  getters:{
    getUrl:(state)=>{
      return state.video_url
    },
    getLong:(state)=>{
      return state.video_long
    },
    formattedVideoLong: (state) => {
      return formatDuration(state.video_long);
    },
    formattedStartTime: (state) => {
      return formatDuration(state.start_time);
    },
    formattedEndTime: (state) => {
      return formatDuration(state.end_time);
    },
    formattedPlayStart: (state) => {
      return formatDuration(state.play_start);
    },
  },
  actions:{
    initEditing(){
      // 从本地获取之前保存的编辑信息
      const editingMessage:Editing = sessionStorage.get('editingMessage') as Editing
      if (editingMessage) {
        console.log("刷新")
        this.video_url = editingMessage.video_url;
        this.video_long = editingMessage.video_long;
        this.start_time = editingMessage.start_time;
        this.end_time = editingMessage.end_time;
      }
    },
    updateMessage(){
      let message = <Editing>{}
      message.video_url= this.video_url;
      message.video_long = this.video_long
      message.start_time = this.start_time;
      message.end_time = this.end_time;
      console.log("更新",message.video_long,message.start_time,message.end_time)
      sessionStorage.set('editingMessage', message)
      console.log("存入")
    },
    // 改变播放状态
    setPlayStatus() {
      this.play_status = !this.play_status;
    },
    // 改变播放开始时间
    setPlayStart(start: number) {
        this.play_start = start;
    },
    // 添加视频
    setVideo(url:string) {
      this.video_url = url
      this.updateMessage()
    },
    setDuration(duration:number) {
      this.video_long = duration
      this.end_time = duration
      this.updateMessage()
    },
    // 改变剪辑开始位置
    setStartTime(start: number) {
        this.start_time = start;
        this.updateMessage()
    },
    // 改变剪辑结束位置
    setEndTime(end: number) {
        this.end_time = end;
        this.updateMessage()
    },
    // 改变时间轴精度
    setTimeline(timeline: number) {
        this.timeline_accuracy = timeline;
    },
  },
})

