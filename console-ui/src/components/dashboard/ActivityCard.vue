<script setup lang="ts">
import { Icon } from "@iconify/vue";
import { getPublicEventsApi } from "@/api/githubApi";
import moment from "moment";

const loading = ref(false);
const username = ref("yangjiakai");
const activityList = ref([
  {
    id: 1,
    type: "PushEvent",
    user: "yangjiakai",
    avatar: "https://avatars.githubusercontent.com/u/35951244?",
    repo: "yangjiakai/lux-admin-vuetify3",
    content: "Update Readme",
    created_at: "2023-04-06T16:01:30Z",
  },
  {
    id: 2,
    type: "IssuesEvent",
    user: "yangjiakai",
    avatar: "https://avatars.githubusercontent.com/u/35951244?",
    repo: "yangjiakai/lux-admin-vuetify3",
    content: "全局的配置管理，比如dev配置，和pro配置隔离开",
    created_at: "2023-04-06T16:01:30Z",
  },
]);
const getPublicEvent = async () => {
  loading.value = true;
  const response = await getPublicEventsApi(username.value);

  activityList.value = response.data.map((activity) => {
    return {
      id: activity.id,
      type: activity.type,
      user: activity.actor.display_login,
      avatar: activity.actor.avatar_url,
      repo: activity.repo?.name,
      content: getContent(activity),
      action:
        activity.type === "IssuesEvent" ? activity.payload.action : "Commit",
      created_at: activity.created_at,
    };
  });
  setTimeout(() => {
    loading.value = false;
  }, 1000);
};

const getContent = (activity: any) => {
  if (activity.type === "PushEvent") {
    return convertToHtml(activity.payload.commits[0].message);
  } else if (activity.type === "CreateEvent") {
    return activity.payload.ref_type;
  } else if (activity.type === "IssuesEvent") {
    return activity.payload.issue.title;
  } else {
    return "";
  }
};

const convertToHtml = (text) => {
  const lines = text.split("\n");
  let html = "";

  lines.forEach((line) => {
    if (line.startsWith("- ")) {
      html += `<div><span class='mr-1'>✅</span> ${line.slice(2)}</div>`;
    } else if (line.trim() === "") {
      html += "<br/>";
    } else {
      html += `<p>${line}</p>`;
    }
  });

  return html;
};

const getTagColor = (activity: any) => {
  if (activity.type === "PushEvent") {
    return "green";
  } else if (activity.type === "IssuesEvent") {
    return "red";
  } else {
    return "blue";
  }
};

onMounted(() => {
  getPublicEvent();
});
</script>
<template>
  <!-- loading spinner -->
  <div
    v-if="loading"
    class="h-full d-flex flex-grow-1 align-center justify-center"
  >
    <v-progress-circular indeterminate color="primary"></v-progress-circular>
  </div>
  <div v-else>
    <h6 class="text-h6 pa-5 d-flex align-center">
      <span class="flex-1 font-weight-bold">Github Activity</span>
      <v-menu location="bottom end" transition="slide-x-transition">
        <template v-slot:activator="{ props }">
          <v-btn
            v-bind="props"
            size="small"
            variant="text"
            icon="mdi-dots-vertical"
            rounded
            color="primary"
            class="my-n2"
          ></v-btn>
        </template>
        <v-list density="compact">
          <v-list-item @click="$emit('edit')">
            <v-list-item-title class="d-inline-flex align-center">
              <Icon
                icon="flat-color-icons:refresh"
                :rotate="2"
                :horizontalFlip="true"
                :verticalFlip="true"
                class="mr-1"
              />
              <span> Refresh</span>
            </v-list-item-title>
          </v-list-item>
          <v-list-item @click="$emit('delete')">
            <v-list-item-title class="d-inline-flex align-center">
              <Icon
                icon="icon-park:clear-format"
                :rotate="2"
                :horizontalFlip="true"
                :verticalFlip="true"
                :inline="true"
                class="mr-1"
              />
              Clear</v-list-item-title
            >
          </v-list-item>
        </v-list>
      </v-menu>
    </h6>
    <perfect-scrollbar class="timeline-container">
      <v-timeline
        class="time-line text-body-2"
        density="compact"
        side="end"
        truncate-line="start"
      >
        <v-timeline-item
          v-for="activity in activityList"
          :key="activity.id"
          size="small"
        >
          <template v-slot:icon>
            <v-avatar>
              <img :src="activity.avatar" />
            </v-avatar>
          </template>
          <template v-slot:opposite>
            <span>{{ moment(activity.created_at).format("MM,DD hh:mm") }}</span>
          </template>
          <div class="mb-1">
            <span class="text-h6 font-weight-bold">
              {{ activity.user }}
            </span>
            <span class="ml-2 text-grey">{{
              moment(activity.created_at).format("MM,DD hh:mm")
            }}</span>
          </div>

          <v-card width="500">
            <v-card-subtitle class="pt-4">
              <v-chip
                :color="getTagColor(activity)"
                size="small"
                label
                class="mr-2 font-weight-bold"
              >
                <span>{{ activity.type }}</span>
              </v-chip>
              <span class="text-body-2">{{ activity.repo }}</span>
            </v-card-subtitle>
            <v-card-text>
              <div v-html="activity.content"></div>
            </v-card-text>
          </v-card>
        </v-timeline-item>
      </v-timeline>
    </perfect-scrollbar>
  </div>
</template>

<style lang="scss" scoped>
.timeline-container {
  height: 360px;
  overflow: scroll;
}
.time-line {
  margin-left: 60px;
}
</style>
