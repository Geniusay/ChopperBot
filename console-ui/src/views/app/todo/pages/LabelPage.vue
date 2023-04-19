<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import TodoList from "@/views/app/todo/component/TodoList.vue";
import { useTodoStore } from "@/views/app/todo/todoStore";

const todoStore = useTodoStore();
const route = useRoute();

watch(
  () => route.params.id,
  (id) => {
    todoStore.currentLabel = id as string;
  }
);
</script>

<template>
  <TodoList :tasks="todoStore.getLabelTodos" />
</template>

<style scoped lang="scss"></style>
