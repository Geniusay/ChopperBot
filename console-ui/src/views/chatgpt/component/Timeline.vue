<!--
* @Component: 时间轴组件
* @Maintainer: Lisianthus
* @Description: 按时间轴剪辑视频
-->
<script setup lang="ts">
import { useEditingStore } from "@/views/chatgpt/editingStore";

const editingStore = useEditingStore()
let width = ref(400) // 视频分帧默认初始显示宽度
// 比例因子：1秒 = {scaleFactor}px
const scaleFactor = width.value / editingStore.getLong;

// 剪辑开始时间的像素位置
let start_time = ref(scaleFactor * editingStore.start_time)
// 剪辑结束时间的像素位置
let end_time = ref(scaleFactor * editingStore.end_time)

// 绘制时间刻度轴
function drawTimeline() {
  const canvas = document.getElementById('timelineCanvas') as HTMLCanvasElement
  if(!canvas)return;
  const ctx = canvas.getContext('2d');
  if(!ctx)return
  // 假设每个大格的高度是 20px，每个小格的高度是 5px
  const bigGridHeight = 20;
  const smallGridHeight = 5;

  // 清除画布
  ctx.clearRect(0, 0, canvas.width, bigGridHeight);

  // 绘制大格
  for (let i = 0; i * scaleFactor <= canvas.width; i++) {
    const x = i * scaleFactor * 60; // 默认一分钟一大格
    ctx.fillRect(x, 0, 1, bigGridHeight);
    ctx.fillText(i +":00",x+5,17)
  }

  // 绘制小格（如果需要的话）
  for (let i = 0; i <= canvas.width; i++) {
    const x = i * scaleFactor * 6; // 默认6秒一小格
    ctx.fillRect(x, 0, 1, smallGridHeight);
  }
}
// 监听 editingStore.video_long 的变化
watch(() => editingStore.getLong, (newVal) => {
  drawTimeline();
});

onMounted(() => {
  drawTimeline();
})


// 拖动句柄选择剪辑区域
const dragging = ref<'start' | 'end' | null>(null);
let startX = 0;
let endX = 0;

const container = ref<HTMLDivElement | null>(null);
// 拖动头句柄
const startDragStart = (e: MouseEvent) => {
    if (!container.value) return;
    dragging.value = 'start';
    // e.clientX是鼠标相对于浏览器窗口的X坐标
    // container.value.offsetLeft是相对于其最近的已定位父元素的左边界的距离
    startX = e.clientX - container.value.getBoundingClientRect().left
    console.log(e.clientX,container.value.getBoundingClientRect().left)
    document.addEventListener('mousemove', drag);
    document.addEventListener('mouseup', stopDrag);
};
// 拖动尾句柄
const startDragEnd = (e: MouseEvent) => {
    if (!container.value) return;
    dragging.value = 'end';
    endX = e.clientX - container.value.getBoundingClientRect().left;
    document.addEventListener('mousemove', drag);
    document.addEventListener('mouseup', stopDrag);
};
// 拖动事件
const drag = (e: MouseEvent) => {
    if (!dragging.value || !container.value) return;
    if(e.clientX < container.value.getBoundingClientRect().left ||
       e.clientX - container.value.getBoundingClientRect().left > width.value) return;
    if (dragging.value === 'start') {
        start_time.value = e.clientX - container.value.getBoundingClientRect().left;
    } else {
        end_time.value = e.clientX - container.value.getBoundingClientRect().left;
    }
    start_time.value = Math.min(start_time.value,end_time.value)
    editingStore.setStartTime(start_time.value / scaleFactor)
    end_time.value = Math.max(start_time.value,end_time.value)
    editingStore.setEndTime(end_time.value / scaleFactor)
};
// 停止拖动事件
const stopDrag = () => {
    dragging.value = null;
    // 清除拖动监听
    document.removeEventListener('mousemove', drag);
    document.removeEventListener('mouseup', stopDrag);
};
</script>
<template>
  <div class="timeline-container">
    <div class="timeline-bar" >
      <canvas id="timelineCanvas" width="680" height="20"></canvas>
    </div>
    <div ref="container" class="frame-bar" :style="{ width: `${width}px` }">
      <div class="framing"></div>
      <div class="cut-choose" :style="{ left: `${start_time}px`,width: `${end_time - start_time}px` }"></div>
      <div @mousedown="startDragStart" class="cut-hand" :style="{left: `${start_time}px`}"></div>
      <div @mousedown="startDragEnd" class="cut-hand" :style="{left: `${end_time}px`}"></div>
    </div>
  </div>
</template>
<style scoped lang="scss">
.timeline-container{
  background-color: whitesmoke;
}
.timeline-bar{
  position: relative;
  left:10px;
  margin-top:15px;
  width:680px;
  height:20px;
}
.frame-bar{
  position: relative;
  left:10px;
  margin-top:5px;
  height:60px;
  background-color: #cccccc;
}
.framing{
  position: absolute;
  height: 100%;
  width:100%;
}
.cut-choose{
  position: absolute;
  background-color: #3fc7f3;
  height:100%;
}
.cut-hand {
  display: flex;
  align-items: center;
  position: absolute;
  top: 0;
  user-select: none; /* 禁止选择文本 */
  width: 10px;
  height: 100%;
  background-color: white;
  cursor: ew-resize;
  border: solid 1px #e8e8e8;
}
</style>
