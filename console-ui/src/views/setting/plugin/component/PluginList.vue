<script setup lang="ts">
import {Plugin} from "../pluginTypes"
import {usePluginStore} from "@/views/setting/plugin/pluginStore";

const pluginStore = usePluginStore();

const props = defineProps<{
  plugins: Plugin[];
}>();



const searchKey = ref("");

const getLabelColor = (id: string) => {
  // Find the label by id from the labels array
  const label = pluginStore.labels.find((l) => l.id === id);
  // Return the color for that label, or an empty string
  return label ? label.color : "";
};

const searchPlugin = computed(()=>{
  return props.plugins.filter((plugin)=>{
    return plugin.pluginName_CN.includes(searchKey.value.toLowerCase())
  })
})

const getTags = (plugin: Plugin) => {
  const types:string[] = []
  if(plugin.register){
    types.push("running")
  }else{
    types.push("close")
  }
  if(plugin.start){
    types.push("autoStart")
  }
  return types
}

const clickToggle = (plugin: Plugin) => {
  if(plugin.register){
    pluginStore.closePlugin(plugin)
  }else{
    pluginStore.startPlugin(plugin)
  }
}
</script>

<template>
  <v-card height="100%">
    <!-- ---------------------------------------------- -->
    <!-- Filter Input -->
    <!-- ---------------------------------------------- -->
    <v-text-field
      clearable
      variant="solo"
      class="elevation-1 ma-3"
      hide-details
      prepend-inner-icon="mdi-magnify"
      placeholder="输入插件中文名"
      v-model="searchKey"
    ></v-text-field>
    <!-- ---------------------------------------------- -->
    <!-- List -->
    <!-- ---------------------------------------------- -->
    <perfect-scrollbar class="plugin-list">
      <transition-group name="fade">
        <div v-for="plugin in searchPlugin" :key="plugin.pluginName">

          <div class="todo-item d-flex align-center pa-xl-16">
            <v-avatar size="40">
              <v-img
                src="/src/assets/images/img/plugin.png"
                alt="alt"
              />
            </v-avatar>
            <div class="flex-1 mx-5" >
              <div
                class="font-weight-bold"
              >
                {{ plugin.pluginName }}
              </div>
              <div
                class="font-italic"
              >
                (中文名：{{plugin.pluginName_CN}})
              </div>
              <div>
                {{ plugin.pluginDescription===null?"该插件暂无介绍":plugin.pluginDescription}}
              </div>
              <div>
                <v-chip
                  size="x-small"
                  variant="outlined"
                  class="mr-1 mt-1"
                  :color="getLabelColor(tag)"
                  v-for="tag in getTags(plugin)"
                >
                  {{ tag }}
                </v-chip>
              </div>
            </div>
              <v-switch class="flex-col"
                        style="left: 50%"
                        size="small"
                        color="success"
                        v-model="plugin.register"
                        @click="clickToggle(plugin)"
                        inset
              ></v-switch>

          </div>
        </div>
      </transition-group>
    </perfect-scrollbar>
  </v-card>
</template>


<style lang="scss">
.plugin-list {
  height: 100%;
  overflow: scroll;
  .todo-item {
    transition: all 0.3s;
    border-bottom: 1px solid #eee;
    &:hover {
      transition: all 0.3s;
      background-color: rgba(99, 99, 99, 0.2);
      box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px !important;
      cursor: pointer;
    }
  }
}
.v-switch .v-selection-control{
  left: 80%;
}
</style>