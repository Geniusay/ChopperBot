<template>
  <v-dialog
    v-model="liveFollowStore.dialog"
    fullscreen
    :scrim="false"
    transition="dialog-bottom-transition"
  >

    <v-card>
      <v-toolbar
        dark
        color="primary"
      >
        <v-btn
          icon
          dark
          @click="liveFollowStore.dialog = false"
        >
          <v-icon>mdi-close</v-icon>
        </v-btn>
        <v-toolbar-title>Plugin Setting</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-toolbar-items>
          <v-btn
            variant="text"
            @click="save()"
          >
            Save
          </v-btn>
        </v-toolbar-items>
      </v-toolbar>
      <v-list
        lines="two"
        subheader
      >
        <v-list-item title="focusLive" subtitle="是否自动爬取关注主播的直播"></v-list-item>
        <v-list-item title="focusBarrage" subtitle="是否自动爬取关注主播的直播弹幕(该版本此配置项不生效)"></v-list-item>
        <v-list-item title="checkTime" subtitle="多少秒检测一次主播开播情况(单位：毫秒)"></v-list-item>
      </v-list>
      <v-divider></v-divider>
      <v-list
        lines="two"
        subheader
      >
        <v-text-field
          label="checkTime"
          v-model="setting.checkTime"
          :rules="timeRules"
          hide-details="auto"
        ></v-text-field>

        <v-switch class="flex-col"
                  style=""
                  size="small"
                  v-model="setting.focusLive"
                  color="green"
                  label="focusLive"
                  inset
        ></v-switch>
        <v-switch class="flex-col"
                  style=""
                  size="small"
                  disabled
                  v-model="setting.focusBarrage"
                  color="green"
                  label="focusBarrage"
                  inset
        ></v-switch>
      </v-list>
    </v-card>
  </v-dialog>
</template>

<script setup lang="ts">
import {useLiveFollowStore} from "@/views/app/hot/live_follow/liveFollowStore";
import {changeSetting, getSetting} from "@/api/hot/liveFollowApi";
import {useSnackbarStore} from "@/stores/snackbarStore";

type Setting = {
  focusLive: boolean,
  focusBarrage: boolean,
  checkTime: number,
}

const snackbarStore = useSnackbarStore()
const liveFollowStore = useLiveFollowStore();
const setting = ref<Setting>({
  focusLive: false,
  focusBarrage: false,
  checkTime:0
})

onMounted(()=>{
  getSetting().then(res=>{
    liveFollowStore.setting = res.data['setting']
    setting.value = res.data['setting']
    setting.value.focusBarrage = res.data['setting']?.focusBarrage===1
    setting.value.focusLive = res.data['setting']?.focusLive===1
  })
})


const timeRules = [
  (v)=> !!v || "时间必须存在",
  (v)=> v > 0 || "时间必须大于0",
  (v)=> v >= 1000 || "时间必须大于1s",
]

const save = async () =>{
   await changeSetting(setting.value.checkTime,
       setting.value.focusLive?1:0,
       setting.value.focusBarrage?1:0
   ).then(res=>{
     if(res?.data?.success){
        liveFollowStore.dialog = false
        snackbarStore.showSuccessMessage("更新设置成功")
     }else{
       snackbarStore.showSuccessMessage("更新设置失败")
     }
   })
}

</script>

<style scoped>
.dialog-bottom-transition-enter-active,
.dialog-bottom-transition-leave-active {
  transition: transform .2s ease-in-out;
}
</style>
