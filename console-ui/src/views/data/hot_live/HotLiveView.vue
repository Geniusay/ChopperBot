<template>
  <v-card rounded variant="flat" class="text-blue-grey-darken-3">
    <v-card-title>热门直播</v-card-title>
    <!-- 领域 -->
    <v-chip-group v-model="currentPlatform" style="margin-left: 10px" mandatory>
      <v-chip
        filter
        label
        variant="text"
        color="primary"
        v-for="label in platforms"
        :key="label"
        :value="label"
      >
        {{ label }}
      </v-chip>
    </v-chip-group>

    <!-- 分类 -->
    <v-chip-group v-model="currentModule" style="margin-left: 10px" mandatory>
      <v-chip
        filter
        label
        variant="text"
        color="primary"
        v-for="label in moduleLabel"
        :key="label"
        :value="label"
      >
        {{ label }}
      </v-chip>
    </v-chip-group>

    <v-divider class="my-3"></v-divider>
    <div class="d-flex align-center">
      <!-- 排序 -->
      <v-btn
        variant="text"
        color="primary"
        :active="sortBtnActive"
        class="mr-2"
      >
        {{ currentSort }}
        <v-menu activator="parent">
          <v-list>
            <v-list-item
              v-for="(item, index) in sortList"
              :key="index"
              :value="item"
              @click="currentSort = item"
            >
              <v-list-item-title>{{ item }}</v-list-item-title>
            </v-list-item>
          </v-list>
        </v-menu>
        <template v-slot:prepend>
          <v-icon>mdi-sort</v-icon>
        </template>

        <template v-slot:append>
          <v-icon>mdi-menu-down</v-icon>
        </template>
      </v-btn>

      <!-- 搜索 -->
      <v-spacer></v-spacer>
      <div class="" style="width: 300px">
        <v-text-field
          v-model="search"
          color="primary"
          variant="outlined"
          hide-details
          density="compact"
          filled
          rounded
          placeholder="搜索"
          class="mr-5"
        >
          <template v-slot:prepend-inner>
            <v-icon>mdi-magnify</v-icon>
          </template>
        </v-text-field>
      </div>
    </div>
  </v-card>
  <v-sheet class="mt-5">
    <perfect-scrollbar style="height: 800px">
      <v-container>
        <v-row align="center">
          <v-col
            v-for="item in lives"
            :key="item.liveId"
            cols="6"
            md="4"
            lg="3"
          >
            <v-card class="text-blue-grey-darken-3">
              <v-img :src="item.roomPic?item.roomPic:'/assets/404.png'">
                <v-btn
                  class="ma-2"
                  variant="text"
                  icon="mdi-star"
                  style="float: right;font-size: 20px"
                  @click="follow(item)"
                  color="orange-lighten-2"></v-btn>
              </v-img>
              <v-card-text
                style="height: 120px"
                class="d-flex flex-column justify-space-between"
              >
                  <p class="text-h6" style="word-wrap: break-word">
                    {{ item.liveName }}
                  </p>
                <span>
                    简介：{{ item.description }}
                </span>
                <span>
                    房间ID：{{ item.liveId }}
                </span>
                <div class="d-flex align-center justify-space-between">
                  <span class="text-blue-grey">
                    <v-icon>mdi mdi-account</v-icon>
                    {{ item.liver }}
                  </span>
                  <span>
                    <span><v-icon>mdi-eye</v-icon>
                      {{ item.watcherNum?numToUnit(item.watcherNum):0 }}</span
                    >
                  </span>
                </div>
              </v-card-text>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </perfect-scrollbar>
  </v-sheet>
</template>

<script lang="ts" setup>
import {useHotLiveStore} from "@/views/data/hot_live/hotLiveStore";
import {addFollow} from "@/api/hot/liveFollowApi";
import {numToUnit} from "@/utils/convert";
import {platforms} from "@/utils/platform";
import {Module} from "@/views/data/hot_live/ModuleTypes";
import {Live} from "@/views/data/hot_live/LiveTypes";
import {FocusLiver} from "@/views/app/hot/live_follow/focusLiverTypes";
import {useSnackbarStore} from "@/stores/snackbarStore";

const snackbarStore = useSnackbarStore()
const hotLiveStore = useHotLiveStore()
const ALL = "ALL"
const currentPlatform = ref(platforms[0])
const currentModule = ref(ALL)
const lives = ref<Live[]>([])
const modules = ref<Module[]>([])
const constModuleLabel = [ALL]
const moduleLabel = ref([...constModuleLabel])


const getLives = async ()=> {
  if (currentModule.value ===  ALL) {
    await hotLiveStore.getHotLiveData(currentPlatform.value)
    lives.value = hotLiveStore.platformLiveMap.get(currentPlatform.value)
  } else {
    const module = modules.value.find((item) => item.tagName === currentModule.value) as Module
    await hotLiveStore.getModuleLiveData(module.tagId, currentPlatform.value)
    lives.value = module.lives
  }
}

const getModules = async ()=>{
  await hotLiveStore.getHotModuleData(currentPlatform.value)
  modules.value = hotLiveStore.platformModuleMap.get(currentPlatform.value)
  modulesToLabels()
}

const initData = async () =>{
  await getLives();
  await getModules();
  console.log("init")
}

onMounted(async ()=>{
  await initData()
})

const modulesToLabels = () =>{
  console.log(modules.value)
  moduleLabel.value = constModuleLabel.concat(modules.value.map((element)=>element.tagName))
}

const follow = async (live: Live) => {
   const focusLiver = ref<FocusLiver>({
      id: undefined,
      liver:live.liver,
      platform:live.platform,
      roomId:live.liveId,
      tag:live.moduleName
   })
   await addFollow(focusLiver.value).then(res=>{
     if(res?.data?.success){
       snackbarStore.showSuccessMessage("关注成功")
     }else{
       snackbarStore.showErrorMessage("已经关注该主播")
     }
   })
}
watch(currentPlatform, async (newVal, oldVal) => {
  // 执行相应操作
  if(currentModule.value !== ALL){
    currentModule.value = ALL
  }else{
    await getLives()
  }
  await getModules()
});

watch(currentModule, async (newVal, oldVal) => {
  // 执行相应操作
  await getLives()
})
</script>

<style scoped>

</style>
