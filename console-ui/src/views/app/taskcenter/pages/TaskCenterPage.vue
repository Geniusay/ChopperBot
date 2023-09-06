<script setup lang="ts">

import TaskList from "@/views/app/taskcenter/componet/TaskList.vue";
import {useTaskStore} from "@/views/app/taskcenter/taskStore";
import { allTask} from "@/api/creeperApi";

const taskStore = useTaskStore();

onMounted(async()=>{
  await allTask().then(res=>{
    taskStore.taskList = res.data["list"]
    taskStore.loading = false
  })
})

</script>

<template>
  <TaskList :tasks="taskStore.getAllList" />
</template>

<style scoped lang="scss"></style>
