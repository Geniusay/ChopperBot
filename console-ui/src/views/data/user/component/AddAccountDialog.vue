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
                md="4"
                sm="6"
              >
                <v-select
                  v-model="addForm.platform"
                  :items="platforms"
                  hint="选择主播平台"
                  label="主播平台"
                  persistent-hint
                  return-object
                  single-line
                ></v-select>
              </v-col>
              <v-col
                cols="12"
                md="4"
                sm="6"
              >
                <v-text-field
                  v-model="addForm.liver"
                  :rules="strRules"
                  hide-details="auto"
                  label="主播名称*"
                  required
                ></v-text-field>
              </v-col>
              <v-col
                cols="12"
                md="4"
                sm="6"
              >
                <v-text-field
                  v-model="addForm.roomId"
                  :rules="strRules"
                  hint="主播直播间房间号"
                  label="房间号*"
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
            :disabled="saveBtnDisable(addForm)"
            color="blue-darken-1"
            variant="text"
            @click="save()"
          >
            Save
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
export default {
  name: "AddAccountDialog"
}
</script>

<style scoped>

</style>
