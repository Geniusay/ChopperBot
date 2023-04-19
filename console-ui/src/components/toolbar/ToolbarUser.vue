<!--
* @Component: ToolbarNotifications
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import StatusMenu from "./StatusMenu.vue";
import { useAuthStore } from "@/stores/authStore";
import { useRouter } from "vue-router";
const router = useRouter();

const authStore = useAuthStore();
const handleLogout = () => {
  authStore.logout();
  console.log("---");
  console.log(router);
};

const navs = [
  {
    title: "Profile Details",
    key: "menu.profileDetails",
    link: "/profile",
    icon: "mdi-account-box-outline",
  },
  {
    title: "Plans and Billing",
    key: "menu.plansAndBilling",
    link: "/plans-and-billing",
    icon: "mdi-credit-card-outline",
  },
  {
    title: "Team",
    key: "menu.team",
    link: "/team",
    icon: "mdi-account-group-outline",
  },
  {
    title: "API Dashboard",
    key: "menu.apiDashboard",
    link: "/api-dashboard",
    icon: "mdi-monitor-dashboard",
  },
  {
    title: "Integrations",
    key: "menu.integrations",
    link: "/integrations",
    icon: "mdi-puzzle-outline",
  },
  {
    title: "Ask the Community",
    key: "menu.askCommunity",
    link: "/ask-the-community",
    icon: "mdi-help-circle-outline",
  },
];
</script>

<template>
  <v-menu
    :close-on-content-click="false"
    location="bottom right"
    transition="slide-y-transition"
  >
    <!-- ---------------------------------------------- -->
    <!-- Activator Btn -->
    <!-- ---------------------------------------------- -->
    <template v-slot:activator="{ props }">
      <v-btn class="mx-2" icon v-bind="props">
        <v-badge content="2" color="success" dot bordered>
          <v-avatar size="40">
            <v-img
              src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwrAiMevuwrbU9o0Ck2paVf4ufHUDb2dU48MEDrAlrQw&s"
            ></v-img>
          </v-avatar>
        </v-badge>
      </v-btn>
    </template>
    <v-card max-width="300">
      <v-list lines="three" density="compact">
        <!-- ---------------------------------------------- -->
        <!-- Profile Area -->
        <!-- ---------------------------------------------- -->
        <v-list-item to="/profile">
          <template v-slot:prepend>
            <v-avatar size="40">
              <v-img
                src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwrAiMevuwrbU9o0Ck2paVf4ufHUDb2dU48MEDrAlrQw&s"
              ></v-img>
            </v-avatar>
          </template>

          <v-list-item-title class="font-weight-bold text-primary">
            YANG J.K.
            <StatusMenu />
          </v-list-item-title>
          <v-list-item-subtitle>
            <!-- {{ $store.state.user.email  }} -->
            yjkbako@gmail.com
          </v-list-item-subtitle>
        </v-list-item>
      </v-list>
      <hr />
      <!-- ---------------------------------------------- -->
      <!-- Menu Area -->
      <!-- ---------------------------------------------- -->

      <v-list variant="flat" elevation="0" :lines="false" density="compact">
        <v-list-item
          color="primary"
          v-for="(nav, i) in navs"
          :key="i"
          :to="nav.link"
          link
          density="compact"
        >
          <template v-slot:prepend>
            <v-avatar size="30">
              <v-icon>{{ nav.icon }}</v-icon>
            </v-avatar>
          </template>

          <div>
            <v-list-item-subtitle class="text-body-2">{{
              nav.title
            }}</v-list-item-subtitle>
          </div>
        </v-list-item>
      </v-list>
      <hr />
      <!-- ---------------------------------------------- -->
      <!-- Logout Area -->
      <!-- ---------------------------------------------- -->
      <v-list variant="flat" elevation="0" :lines="false" density="compact">
        <v-list-item color="primary" to="nav.link" link density="compact">
          <template v-slot:prepend>
            <v-avatar size="30">
              <v-icon>mdi-lifebuoy</v-icon>
            </v-avatar>
          </template>

          <div>
            <v-list-item-subtitle class="text-body-2">
              Help Center
            </v-list-item-subtitle>
          </div>
        </v-list-item>
        <v-list-item
          color="primary"
          link
          @click="handleLogout"
          density="compact"
        >
          <template v-slot:prepend>
            <v-avatar size="30">
              <v-icon>mdi-logout</v-icon>
            </v-avatar>
          </template>

          <div>
            <v-list-item-subtitle class="text-body-2">
              Logout
            </v-list-item-subtitle>
          </div>
        </v-list-item>
      </v-list>
    </v-card>
  </v-menu>
</template>

<style scoped lang="scss">
// ::v-deep .v-list-item__append,
// ::v-deep .v-list-item__prepend {
//   height: 100%;
// }
</style>
