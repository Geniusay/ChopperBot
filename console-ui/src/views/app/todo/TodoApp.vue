<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import TodoMenu from "@/views/app/todo/component/TodoMenu.vue";
import TodoCard from "@/views/app/todo/component/TodoCard.vue";
</script>

<template>
  <div class="app-container">
    <!-- ---------------------------------------------- -->
    <!-- Side Bar -->
    <!-- ---------------------------------------------- -->
    <div class="d-none d-md-block sidebar">
      <TodoMenu />
    </div>

    <!-- ---------------------------------------------- -->
    <!--  List Todo-->
    <!-- ---------------------------------------------- -->
    <div class="main">
      <router-view v-slot="{ Component }">
        <transition name="fade">
          <component :is="Component" />
        </transition>
      </router-view>
    </div>

    <TodoCard />
  </div>
</template>

<style scoped lang="scss">
.app-container {
  display: flex;
  height: calc(100vh - 240px);
  width: 100%;
  font-size: 13px;
  padding: 20px;

  .sidebar {
    display: flex;
    flex-direction: column;
    width: 300px;
    height: 100%;
    background-color: #fff;
    margin-right: 20px;
  }

  .main {
    flex: 1;
    width: 100%;
    height: 100%;
    background-color: #fff;
  }
}
</style>
