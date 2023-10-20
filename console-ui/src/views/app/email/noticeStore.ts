import { defineStore } from "pinia";
import {Notice} from "@/views/app/email/NoticeTypes";
import {ElNotification} from "element-plus";
import {getTimeAgo} from "@/utils/timeUtils";

export const showNotice = (notice:Notice) => {
  ElNotification({
    title: notice.title,
    message: '<p><strong>时间：</strong>'+notice.time+'</p>'+
      '<p><strong>来源：</strong>'+notice.from+'</p>'+
      '<p><strong>内容：</strong>'+notice.content+'</p>',
    dangerouslyUseHTMLString: true,
    type: notice.type
  });
};

export const useNoticeStore = defineStore({
  id: "notice",
  state: () => ({
    noticeBox: ref<Notice[]>([]),
    NoticeType:{
      "info":{type:"success",icon:'mdi mdi-email-alert-outline',color:'green'},
      "warn":{type:"warning",icon:'mdi mdi-alert',color:'orange'},
      "error":{type:"error",icon:'mdi mdi-cancel',color:'red'}
    }
  }),

  persist: {
    enabled: true,
    strategies: [
      {
        storage: localStorage,
        paths: ["notice"],
      },
    ],
  },

  getters: {
    getNoticeBox(){
      return this.noticeBox.map(item=>{
        return { ...item, ago:getTimeAgo(item.time) };
      })
    },
    getUnConfirmNoticeBox(){
      return this.noticeBox.map(item=>{
        if(!item.confirm){
          return { ...item, ago:getTimeAgo(item.time) };
        }
      })
    },
  },
  actions: {
    sendNotice(notice: Notice){
      notice.ago = getTimeAgo(notice.time)
      notice.color = this.NoticeType[notice.type].color
      notice.icon = this.NoticeType[notice.type].icon
      notice.type = this.NoticeType[notice.type].type
      this.noticeBox.push(notice)
      showNotice(notice)
    },

    confirmNotice(index:number){
      this.noticeBox[index].confirm = true;
    }
  },
});
