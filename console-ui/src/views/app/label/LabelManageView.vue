<template>
  <v-card>
    <v-card-title class="font-weight-bold">
      <span>Label Manage</span>
      <v-spacer></v-spacer>
      <v-text-field
        @keyup.enter="addLabel()"
        v-model="input"
        :loading="loading"
        append-inner-icon="mdi-map-marker"
        label="输入要添加的标签"
        :rules="strRules"
      ></v-text-field>
    </v-card-title>
    <div>
      <v-chip
        v-for="(item, index) in labelManageStore.labels"
        :color="chooseColor(index)"
        class="ma-2 pa-2"
        closable
        @click="openUpdateDialog(item)"
        @click:close="removeLabel(item.label)"
      >
        {{ item.label }}
      </v-chip>
    </div>
  </v-card>
  <v-dialog v-model="dialog" persistent width="1024">
    <v-card>
      <v-card-title>
        <span class="text-h5">更新Label</span>
      </v-card-title>
      <v-card-text>
        <v-container>
          <v-row>
            <v-col cols="12">
              <v-text-field
                v-model="update.label"
                label="更新Label"
                :rules="strRules"
                required
              ></v-text-field>
            </v-col>
          </v-row>
        </v-container>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="blue-darken-1" variant="text" @click="dialog = false">
          Close
        </v-btn>
        <v-btn
          color="blue-darken-1"
          variant="text"
          @click="updateLabel()"
          :disabled="validate(strRules, update)"
        >
          Save
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script setup lang="ts">
import { list } from "@/api/account/labelApi";
import { useLabelManageStore } from "@/views/app/label/LabelManagerStore";
import { strRules, validate } from "~/src/utils/validate";
import { useSnackbarStore } from "~/src/stores/snackbarStore";
import { Label } from "@/views/app/label/LabelTypes";
const labelManageStore = useLabelManageStore();
const snackbarStore = useSnackbarStore();
const input = ref("");
const update = ref<Label>();
const loading = ref(false);
const dialog = ref(false);
const colorGroup = [
  "blue",
  "secondary",
  "orange",
  "pink",
  "red",
  "yellow",
  "green",
];

onMounted(async () => {
  await list().then((res) => {
    labelManageStore.labels = res.data.list;
  });
});

const chooseColor = (index: number) => {
  return colorGroup[index % colorGroup.length];
};
const addLabel = async () => {
  if (strRules.some((rule) => typeof rule(input.value) === "string")) {
    snackbarStore.showErrorMessage("输入不能为空");
    return;
  } else {
    loading.value = true;
    labelManageStore.addLabel(input.value);
    input.value = "";
    loading.value = false;
  }
};
const removeLabel = async (label) => {
  await labelManageStore.removeLabel(label);
};
const openUpdateDialog = (label: Label) => {
  update.value = label;
  dialog.value = true;
};

const updateLabel = async () => {
  await labelManageStore.updateLabel(update.value);
  dialog.value = false;
};
</script>

<style scoped>
.labels {
  display: flex;
  flex-direction: row;
}
</style>
