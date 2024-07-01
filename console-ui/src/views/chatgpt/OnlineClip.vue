<!--
* @Component: 在线剪辑
* @Maintainer: Lisianthus
* @Description:
-->
<script setup lang="ts">
import Player from "./component/Player.vue";
import Timeline from "./component/Timeline.vue";
import VideoChoose from "./component/VideoChoose.vue";
import {useEditingStore} from "@/views/chatgpt/editingStore";
const editingStore = useEditingStore()
editingStore.initEditing()
</script>

<template>
  <v-card class="editing">
    <!--视频选择组件-->
    <VideoChoose></VideoChoose>
    <!--播放组件-->
    <Player></Player>
    <!--时间轴选择组件-->
    <Timeline></Timeline>
  </v-card>
</template>
<style scoped>
.editing{
  height:450px;
  width:700px;
}
</style>
