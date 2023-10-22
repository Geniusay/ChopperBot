<!--
* @Component: ToolbarNotifications
* @Maintainer: J.K. Yang
* @Description:
-->
<script setup lang="ts">
import {Notice} from "@/views/app/email/NoticeTypes"
import WebSocketClient from "@/utils/ws/webSocket";

import {useNoticeStore} from "@/views/app/email/noticeStore";
const noticeStore = useNoticeStore()

const unCheckNotice = ref<Notice[]>(noticeStore.getUnConfirmNoticeBox)

const webSocket = new WebSocketClient(()=>{
  webSocket.sendMsg(webSocket.encodeMsg("notice","666"))
},(event)=>{
  let dataMap = webSocket.decodeMsg(event.data)
  let data = JSON.parse(dataMap.get("data"))
  if(data!=null){
    const notice: Notice = {...data,ago:'',color:'',icon:'',confirm:false };
    noticeStore.sendNotice(notice)
    unCheckNotice.value = noticeStore.getUnConfirmNoticeBox
  }
});


const confirmNotice = (index:number) =>{
  unCheckNotice.value.splice(index, 1);
  noticeStore.confirmNotice(index);
}

const timeMap = new Map()

const hoverConfirm = (index:number) =>{
  timeMap.set(index,setTimeout(()=>{
    confirmNotice(index)
  },500));
}

const unHoverConfirm = (index:number) =>{
  timeMap.get(index)&& clearTimeout(timeMap.get(index))
}


</script>

<template>
  <v-menu location="bottom right" transition="slide-y-transition">
    <!-- ---------------------------------------------- -->
    <!-- Activator Btn -->
    <!-- ---------------------------------------------- -->
    <template v-slot:activator="{ props }">
      <v-btn icon v-bind="props" class="text-none">
        <v-badge v-if="unCheckNotice.length!==0" :content="unCheckNotice.length" color="error">
          <v-icon>mdi-bell-outline</v-icon>
        </v-badge>
        <v-icon v-else>mdi-bell-outline</v-icon>
      </v-btn>
    </template>
    <v-list elevation="1" lines="three" density="compact" width="400">
      <v-list-subheader>Notifications</v-list-subheader>
      <v-list-item v-for="(message, i) in unCheckNotice" :key="i" @click="confirmNotice(i)">
        <template v-slot:prepend>
          <v-avatar size="40" :color="message.color">
            <v-icon color="white">{{ message.icon }}</v-icon>
          </v-avatar>
        </template>

        <template v-slot:append>
          <div class="full-h d-flex align-center">
            <span class="text-body-2 text-grey"> {{ message.ago }}</span>
          </div>
        </template>
        <!-- ---------------------------------------------- -->
        <!-- Main Content-->
        <!-- ---------------------------------------------- -->
        <div>
          <v-list-item-title class="font-weight-bold text-primary">{{
            message.title
          }}</v-list-item-title>
          <v-list-item-subtitle><strong>来源：</strong>{{ message.from }}</v-list-item-subtitle>
          <v-list-item-subtitle><strong>内容：</strong>{{ message.content }}</v-list-item-subtitle>
        </div>
      </v-list-item>
      <!-- ---------------------------------------------- -->
      <!-- See all Btn-->
      <!-- ---------------------------------------------- -->
      <div class="text-center py-5">
        <v-btn size="small" variant="elevated" elevation="1"> See all </v-btn>
      </div>
    </v-list>
  </v-menu>
</template>

<style scoped lang="scss">
// ::v-deep .v-list-item__append,
// ::v-deep .v-list-item__prepend {
//   height: 100%;
// }
</style>
