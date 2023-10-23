<!--
* @Component:
* @Maintainer: J.K. Yang
* @Description:
-->
<script setup lang="ts">
import UserMenu from "@/views/data/user/component/UserMenu.vue";
import UserCard from "@/views/data/user/component/UserCard.vue";
import { useUserStore } from "./userStore";
import {getUsers} from "@/api/userApi";

const userStore = useUserStore();
var labels = new Set();
getUsers().then((res)=>{
  let users = res.data
  for(let i = 0;i < users.length;i ++){
    userStore.addNewUser(users[i])
    for(let j = 0;j < users[i].typeList.length;j ++){
      labels.add(users[i].typeList[j].type)
    }
  }
  labels.forEach(value => {
    userStore.labels.push({
      id: String(value),
      title: String(value),
      color: "black",
    })
  });
});
</script>

<template>
  <div class="app-container">
    <!-- ---------------------------------------------- -->
    <!-- Side Bar -->
    <!-- ---------------------------------------------- -->
    <div class="d-none d-md-block sidebar">
      <UserMenu />
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

    <UserCard />
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
