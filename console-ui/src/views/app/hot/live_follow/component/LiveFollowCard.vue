<script setup lang="ts">
import CopyLabel from "@/components/common/CopyLabel.vue";
import {useLiveFollowStore} from "@/views/app/hot/live_follow/liveFollowStore";
import LiveFollowSetting from "@/views/app/hot/live_follow/component/LiveFollowSetting.vue";
import AddFormDiaLog from "@/views/app/hot/live_follow/component/AddFormDiaLog.vue";
import {FocusLiver} from "@/views/app/hot/live_follow/focusLiverTypes";

const liveFollowStore = useLiveFollowStore();
const props = defineProps<{
  liveFollows: FocusLiver[];
}>();
const loading = ref(true);

const headers = [
  { text: "id", align: "start", value: "id" },
  { text: "平台", value: "description" },
  { text: "主播昵称", value: "author" },
  { text: "房间号", value: "group" },
  { text: "标签", value: "priority" },
  { text: "操作", value: "option" },
];

const open = (item) => {};

onMounted(() => {
  setTimeout(() => {
    loading.value = false;
  }, 1000);
});

const keys = ["group","name"]


</script>

<template>
  <!-- loading spinner -->
  <LiveFollowSetting></LiveFollowSetting>
  <AddFormDiaLog></AddFormDiaLog>
  <div
    v-if="loading"
    class="h-full d-flex flex-grow-1 align-center justify-center"
  >
    <v-progress-circular indeterminate color="primary"></v-progress-circular>
  </div>
  <div v-else>
    <v-card>
      <v-card-title class="font-weight-bold">
        <span>Live Follow</span>
        <v-spacer></v-spacer>
        <v-btn
          style="margin-right: 20px"
          color="success"
          @click="liveFollowStore.addDialog = true"
        >
          <v-icon start icon="mdi mdi-plus-box"></v-icon>
          关注主播
        </v-btn>
        <v-btn
          color="primary"
          dark
          v-bind="props"
          @click="liveFollowStore.dialog = true"
        >
          <v-icon start icon="mdi mdi-cog"></v-icon>
          Setting
        </v-btn>
      </v-card-title>
      <v-table class="pa-3">
        <thead>
        <tr>
          <th class="text-left" v-for="header in headers" :key="header.text">
            {{ header.text }}
          </th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(item,index) in liveFollows" :key="item.id">
          <td class="font-weight-bold">
            <copy-label :text="`# ${index}`" />
          </td>
          <td>
            <copy-label :text="item.platform" />
          </td>
          <td>{{ item.liver }}</td>
          <td>{{ item.roomId }}</td>
          <td>
            <v-chip
              color="cyan"
              label>
              <v-icon start icon="mdi mdi-earth-box"></v-icon>
              {{ item.tag }}
            </v-chip>
          </td>
          <td>
            <v-btn density="comfortable" color="red" icon="mdi mdi-trash-can"></v-btn>
          </td>
        </tr>
        </tbody>
      </v-table>
    </v-card>

  </div>
</template>

<style lang="scss" scoped>

.v-table {
  table {
    padding: 4px;
    padding-bottom: 8px;

    th {
      text-transform: uppercase;
      white-space: nowrap;
    }

    td {
      border-bottom: 0 !important;
    }

    tbody {
      tr {
        transition: box-shadow 0.2s, transform 0.2s;

        &:not(.v-data-table__selected):hover {
          box-shadow: 0 3px 15px -2px rgba(0, 0, 0, 0.12);
          transform: translateY(-4px);
        }
      }
    }
  }
}
</style>
