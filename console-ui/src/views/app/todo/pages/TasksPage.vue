<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import TodoList from "@/views/app/todo/component/TodoList.vue";
import { useTodoStore } from "@/views/app/todo/todoStore";
const todoStore = useTodoStore();
</script>

<template>
  <TodoList :tasks="todoStore.getTodoList" />
</template>

<style scoped lang="scss"></style>
