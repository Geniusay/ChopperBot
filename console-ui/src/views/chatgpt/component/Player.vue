<!--
* @Component: 播放组件
* @Maintainer: Lisianthus
* @Description:控制视频播放状态
-->
<script setup lang="ts">
import {useEditingStore} from "@/views/chatgpt/editingStore";
import PlayIcon from '@/assets/icons/play.svg'
import PauseIcon from '@/assets/icons/pause.svg'

const editingStore = useEditingStore();
</script>

<template>
  <div class="player flex">
    <div class="time-box">
      <label>{{ editingStore.formattedPlayStart }} </label>
      <label style="color:#c0c0c0"> / {{ editingStore.formattedVideoLong }}</label>
    </div>
    <button @click="editingStore.setPlayStatus" class="player-btn">
      <img :src="editingStore.play_status ? PlayIcon : PauseIcon" alt="Play Icon"/>
    </button>
    <div class="time-box">
      <label>{{ editingStore.formattedStartTime }}</label>
      <label> / {{ editingStore.formattedEndTime }}</label>
    </div>
  </div>
</template>

<style scoped lang="scss">
.flex{
  display: flex;
  align-items: center;
  justify-content: space-evenly;
}
.player{
  position: relative;
  left:10px;
  margin-top:15px;
  width:680px;
  height:45px;
  box-shadow: #D7D7D7 0 0 5px;
}
.player-btn{
  width:35px;
  height:35px;
  border-radius: 50px;
  box-shadow: 0 0 5px #D7D7D7;
  overflow: hidden;
}
.time-box{
  width:200px;
}
</style>
