<script setup lang="ts">
import PluginList from "@/views/setting/plugin/component/PluginList.vue";
import {usePluginStore} from "@/views/setting/plugin/pluginStore";
import {allPlugins} from "@/api/pluginApi";
const pluginStore = usePluginStore();

onMounted(async()=>{
  await allPlugins().then(res=>{
    pluginStore.pluginList = res.data
  })
})


</script>


<template>
  <PluginList :plugins="pluginStore.getAutoStartList" />
</template>


<style scoped lang="scss"></style>
