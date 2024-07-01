<!--
* @Component: 视频选择
* @Maintainer: Lisianthus
* @Description:选择要编辑的视频
-->
<script setup lang="ts">
import { ref } from 'vue';
import { useEditingStore } from "@/views/chatgpt/editingStore";

const editingStore = useEditingStore()
const videoRef = ref<HTMLVideoElement | null>(null);
// 控制视频暂停和播放
function playMedia(status: boolean) {
  if (videoRef.value) {
    if(status){
      videoRef.value.play().catch(error => {
        console.error('Error playing video:', error);
      });
    }else{
      videoRef.value.pause();
    }
  }
}
// 通过监听store播放状态来控制播放
watch(() => editingStore.play_status, (newVal, oldVal) => {playMedia(newVal)}, { immediate: true })
// 上传视频文件
function onFileChange(event: Event) {
  const fileInput = event.target as HTMLInputElement;
  const file = fileInput.files?.[0];
  if (file) {
    console.log(URL.createObjectURL(file))
    editingStore.setVideo(URL.createObjectURL(file))
  }
}
// 获取视频时长
function handleVideoMetadata() {
  if (videoRef.value) {
    editingStore.setDuration(videoRef.value.duration)
  }
}
// 更新播放位置
function updateTime() {
  if (videoRef.value) {
    editingStore.play_start = videoRef.value.currentTime
    if(editingStore.play_start === editingStore.video_long){
      editingStore.play_status = false
    }
  }
}
</script>
<template>
  <div class="video-box">
    <input type="file" accept="video/*" @change="onFileChange" v-show="!editingStore.video_url"/>
    <video v-show="editingStore.getUrl"
      ref="videoRef"
      :src="editingStore.getUrl"
      @loadedmetadata="handleVideoMetadata"
      @timeupdate = "updateTime"
    ></video>
  </div>
</template>

<style scoped lang="scss">
.video-box{
  display: flex;
  align-items: center;
  justify-content: center;
  position:relative;
  left:125px;
  margin-top:15px;
  width:450px;
  height:250px;
}
input{
  position: absolute;
  width: 100%;
  height: 100%;
  background-color: #F2F2F2;
}
video{
  height: 250px;
}
</style>
