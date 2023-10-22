import { defineStore } from "pinia";
import {Point} from "@/views/app/barrage/curve/PointTypes";

export const useBarrageCurveStore = defineStore({
  id: "notice",
  state: () => ({
    noticeOpen:ref(true),
    nowPoints:ref<Point[]>([]),
    barrageFiles:ref([]),
  }),

  getters: {

  },
  actions: {

  },
});
