import { defineStore } from "pinia";
import { useSnackbarStore } from "~/src/stores/snackbarStore";
import { add, remove, update } from "@/api/account/labelApi";
import { Label } from "@/views/app/label/LabelTypes"
const snackbarStore = useSnackbarStore();

export const useLabelManageStore = defineStore({
  id: "labelManage",
  state: () => ({
    labels: ref<Label[]>([]),
  }),

  getters: {},

  actions: {
    async removeLabel(label: string) {
      await remove(label).then((res) => {
        if (res?.data?.success) {
          this.labels = this.labels.filter((item) => item.label !== label);
          snackbarStore.showSuccessMessage("删除成功");
        } else {
          snackbarStore.showErrorMessage("删除失败");
        }
      });
    },
    async addLabel(label: string) {
      await add(label).then((res) => {
        if (res.code===200) {
          snackbarStore.showSuccessMessage("添加成功");
          this.labels.push(res.data.data as Label);
        } else {
          snackbarStore.showErrorMessage("添加失败");
        }
      });
    },
    async updateLabel(label: Label) {
      await update(label).then((res) => {
        if (res?.data?.success) {
          snackbarStore.showSuccessMessage("更新成功");
          const index = this.labels.findIndex((item) => item.label === label.label);
          this.labels[index].label = label.label;
        } else {
          snackbarStore.showErrorMessage("更新失败");
        }
      });
    },
  },
});
