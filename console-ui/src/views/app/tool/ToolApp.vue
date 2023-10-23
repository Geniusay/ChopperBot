
<script>
export default {
  name: "ToolApp.vue"
}
</script>
<template>
  <div class="app-container">
    <!-- ---------------------------------------------- -->
    <!-- Side Bar -->
    <!-- ---------------------------------------------- -->
    <div class="d-none d-md-block sidebar">

    </div>

    <!-- ---------------------------------------------- -->
    <!--  List User-->
    <!-- ---------------------------------------------- -->
    <div class="main">
      <router-view v-slot="{ Component }">
        <transition name="fade">
          <component :is="Component" />
        </transition>
      </router-view>
    </div>


  </div>
</template>

<style lang="scss" scoped>
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
