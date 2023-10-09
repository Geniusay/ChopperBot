<script setup lang="ts">
import router from "@/router";
import {useHotGuardStore} from "@/views/app/hot/hot_guard/hotGuardStore";
const hotGuardStore = useHotGuardStore();

const currentLabel = ref<string>("设置")

if (router.currentRoute.value.path === '/apps/hotGuard/guards') {
  // 当前路由路径是/apps/hotGuard/guards
  currentLabel.value = '监控守卫'
} else {
  // 当前路由路径不是/apps/hotGuard/guards
  currentLabel.value = '设置'
}

watch(currentLabel, (newLabel, oldLabel) => {
  if(newLabel==='监控守卫'){
    router.push('/apps/hotGuard/guards')
  }else if(newLabel==='设置'){
    router.push('/apps/hotGuard/settings')
  }
});

//Computed Property
</script>
<template>
  <v-container>
    <v-card>
      <v-card-text>
        <v-row>
          <v-col cols="12" lg="4" md="6">
            <v-chip-group v-model="currentLabel" mandatory>
              <v-chip
                filter
                label
                variant="text"
                color="primary"
                v-for="label in hotGuardStore.fieldLabelList"
                :key="label"
                :value="label"
              >
                {{ label }}
              </v-chip>
            </v-chip-group>
          </v-col>
        </v-row>
      </v-card-text>
    </v-card>

    <v-card class="mt-2">
      <router-view v-slot="{ Component }">
        <transition name="fade">
          <component :is="Component" />
        </transition>
      </router-view>
    </v-card>
  </v-container>
</template>

