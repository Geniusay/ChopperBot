<template>
  <v-dialog v-model="dialog" max-width="700">
    <v-card>
      <v-card-title class="pa-4 bg-secondary">
        <span class="title text-white">Edit Setting</span>
      </v-card-title>
      <v-card-text>
        <v-form
          class="mt-5"
          ref="form"
          v-model="refForm"
          lazy-validation
        >
          <v-row>
            <v-col cols="12" sm="6">
              <v-text-field
                variant="outlined"
                color="primary"
                density="compact"
                :rules="timeRules"
                v-model="editedItem.updateHotModuleTimes"
                label="热门模块更新时间(毫秒)"
                type="email"
              ></v-text-field>
            </v-col>
            <v-col cols="12" sm="6">
              <v-text-field
                variant="outlined"
                color="primary"
                density="compact"
                :rules="timeRules"
                v-model="editedItem.updateHotLivesTimes"
                label="热门直播更新时间(毫秒)"
                type="phone"
              ></v-text-field>
            </v-col>
          </v-row>
        </v-form>
      </v-card-text>
      <v-divider></v-divider>
      <v-card-actions class="pa-4">
        <v-spacer></v-spacer>
        <v-btn color="error" @click="close">Cancel</v-btn>
        <v-btn
          color="secondary"
          :disabled="saveBtnDisable(editedItem)"
          variant="flat"
          @click="save"
        >Save</v-btn
        >
      </v-card-actions>
    </v-card>
  </v-dialog>
  <v-table class="mt-5">
    <thead>
    <tr>
      <th class="text-subtitle-1 font-weight-semibold">Id</th>
      <th class="text-subtitle-1 font-weight-semibold">直播平台</th>
      <th class="text-subtitle-1 font-weight-semibold">热门模块监控</th>
      <th class="text-subtitle-1 font-weight-semibold">热门直播监控</th>
      <th class="text-subtitle-1 font-weight-semibold">跟风狗模式</th>
      <th class="text-subtitle-1 font-weight-semibold">热门模块更新时间</th>
      <th class="text-subtitle-1 font-weight-semibold">热门直播更新时间</th>
      <th class="text-subtitle-1 font-weight-semibold">操作</th>
    </tr>
    </thead>
    <tbody class="text-body-1">
    <tr v-for="(item,index) in hotGuardStore.getSettings" :key="item.id">
      <td class="font-weight-bold">{{ item.id }}</td>
      <td>
        <div class="d-flex align-center py-1">
          <div class="ml-5">
            <p class="font-weight-bold">{{ item.platform }}</p>
          </div>
        </div>
      </td>

      <td>
        <v-switch class="flex-col"
                  size="small"
                  style="padding-right: 65%"
                  v-model="item.enableHotModule"
                  color="rgb(33,150,243)"
                  @click="updateSwitch(item)"
                  inset
        ></v-switch>
      </td>
      <td>
        <v-switch class="flex-col"
                  style="padding-right: 65%"
                  size="small"
                  v-model="item.enableHotLive"
                  color="rgb(33,150,243)"
                  @click="updateSwitch(item)"
                  inset
        ></v-switch>
      </td>
      <td>
        <v-switch class="flex-col"
                  style="padding-right: 65%"
                  size="small"
                  v-model="item.followDogEnable"
                  color="green"
                  @click="updateFollowDogSwitch(item)"
                  inset
        ></v-switch>
      </td>
      <td>{{nanosToHMS_CN(item.updateHotModuleTimes)}}</td>
      <td>{{nanosToHMS_CN(item.updateHotLivesTimes)}}</td>
      <td>
        <div class="d-flex align-center">
          <v-tooltip text="Edit">
            <template v-slot:activator="{ props }">
              <v-btn
                color="blue"
                icon
                variant="text"
                @click="editItem(item,index)"
                v-bind="props"
              >
                <v-icon>mdi-pencil-outline</v-icon>
              </v-btn>
            </template>
          </v-tooltip>
        </div>
      </td>
    </tr>
    </tbody>
  </v-table>
</template>

<script setup lang="ts">
import {getGuards, getHotGuardSetting} from "@/api/hot/hotGuardApi";
import {openFollowDog} from "@/api/hot/heatRecommendApi";
import {useHotGuardStore} from "@/views/app/hot/hot_guard/hotGuardStore";
import {nanosToHMS_CN} from "@/utils/timeUtils";
import {Setting} from "@/views/app/hot/hot_guard/hotSettingTypes";
import {useSnackbarStore} from "@/stores/snackbarStore";

const hotGuardStore = useHotGuardStore()
const snackbarStore = useSnackbarStore();

  onMounted(async()=>{
    await getHotGuardSetting().then(res=>{
      hotGuardStore.settings = res.data['list'].map((setting: any)=>setting as Setting)
    })
  })
  const timeRules = [
    (v)=> !!v || "时间必须存在",
    (v)=> v > 0 || "时间必须大于0",
    (v)=> v >= 1000 || "时间必须大于1s",
  ]

  const dialog = ref(false);
  const refForm = ref<Setting>();
  const editedItem = ref<Setting>();
  const editedIndex = ref(-1);
  const defaultItem = ref<Setting>({
    id: 0,
    platform:"",
    failRetryTimes:0,
    enableHotModule:false,
    enableHotLive:false,
    followDogEnable:false,
    updateHotModuleTimes:0,
    updateHotLivesTimes:0
  });

  //Methods

  function saveBtnDisable(item: any){
    const setting = item as Setting
    return timeRules.some((rule)=>typeof rule(setting.updateHotModuleTimes) === "string")||
      timeRules.some((rule)=>typeof rule(setting.updateHotLivesTimes) === "string")

  }

  function editItem(item: Setting,index: number) {
    editedIndex.value = index;
    editedItem.value =  Object.assign({}, item);
    dialog.value = true;
  }

  function updateSwitch(item: Setting){
    hotGuardStore.updateSettingNoIndex(item)
  }

  function updateFollowDogSwitch(item: Setting){
    openFollowDog(item.platform,!item.followDogEnable).then(res=>{
      if(res?.data?.success){
        if(item.followDogEnable){
          snackbarStore.showSuccessMessage(item.platform+"跟风狗模式已打开!")
        }else{
          snackbarStore.showSuccessMessage(item.platform+"跟风狗模式已关闭!")
        }

      }else{
        if(item.followDogEnable){
          snackbarStore.showSuccessMessage(item.platform+"跟风狗模式打开失败!")
        }else{
          snackbarStore.showSuccessMessage(item.platform+"跟风狗模式已关闭失败!")
        }
        item.followDogEnable = !item.followDogEnable
      }
    })
  }

  function close() {
    dialog.value = false;
    editedItem.value = Object.assign({}, defaultItem.value);
    editedIndex.value = -1;
  }

  function save() {
    const setting = Object.assign({}, editedItem.value)
    hotGuardStore.updateSetting(editedIndex.value,setting)
    close();
  }
</script>

<style scoped>

</style>
