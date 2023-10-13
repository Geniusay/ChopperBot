<template>
  <v-row justify="center">
    <v-dialog
      v-model="liveFollowStore.addDialog"
      persistent
      width="1024"
    >
      <v-card>
        <v-card-title>
          <span class="text-h5">添加主播</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col
                cols="12"
                sm="6"
                md="4"
              >
                <v-select
                  v-model="addForm.platform"
                  hint="选择主播平台"
                  :items="platforms"
                  label="主播平台"
                  persistent-hint
                  return-object
                  single-line
                ></v-select>
              </v-col>
              <v-col
                cols="12"
                sm="6"
                md="4"
              >
                <v-text-field
                  label="主播名称*"
                  v-model="addForm.liver"
                  :rules="strRules"
                  hide-details="auto"
                  required
                ></v-text-field>
              </v-col>
              <v-col
                cols="12"
                sm="6"
                md="4"
              >
                <v-text-field
                  label="房间号*"
                  hint="主播直播间房间号"
                  v-model="addForm.roomId"
                  :rules="strRules"
                  persistent-hint
                  required
                ></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field
                  v-model="addForm.tag"
                  label="Tags(请用,分隔标签)"
                  required
                ></v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="blue-darken-1"
            variant="text"
            @click="liveFollowStore.addDialog = false"
          >
            Close
          </v-btn>
          <v-btn
            color="blue-darken-1"
            variant="text"
            :disabled="saveBtnDisable(addForm)"
            @click="save()"
          >
            Save
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script setup lang="ts">
import {useLiveFollowStore} from "@/views/app/hot/live_follow/liveFollowStore";
import {FocusLiver} from "@/views/app/hot/live_follow/focusLiverTypes";
import {addFollow} from "@/api/hot/liveFollowApi";
import {useSnackbarStore} from "@/stores/snackbarStore";
import {strRules,timeRules} from "@/utils/validate";
import {platforms} from "@/utils/platform";
import {Setting} from "@/views/app/hot/hot_guard/hotSettingTypes";

const snackbarStore = useSnackbarStore();
const liveFollowStore = useLiveFollowStore();
const defaultForm = ref<FocusLiver>({
  id:0,
  platform:'douyu',
  liver:'',
  roomId:'',
  tag:''
})
const addForm = ref<FocusLiver>(Object.assign({},defaultForm.value))

const saveBtnDisable = (item: FocusLiver) =>{
  return strRules.some((rule)=>typeof rule(item.roomId) === "string")||
    strRules.some((rule)=>typeof rule(item.liver) === "string") ||
    strRules.some((rule)=>typeof rule(item.platform) === "string")
}

const save = async () =>{
    await addFollow(addForm.value).then(res=>{
       if(res?.data?.success){
          snackbarStore.showSuccessMessage("添加成功")
          liveFollowStore.addLiveFollow(addForm.value)
          addForm.value = Object.assign({},defaultForm.value)
          liveFollowStore.addDialog = false
       }else{
         snackbarStore.showErrorMessage("添加失败")
       }
    })
}
</script>

<style scoped>

</style>
