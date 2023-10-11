<script setup lang="ts">
import CopyLabel from "@/components/common/CopyLabel.vue";
import {Creeper} from "../creeperTypes"


const props = defineProps<{
  creepers: Creeper[];
}>();

const dialog = ref(false)
const notifications = false
const sound = true
const widgets = false

const loading = ref(true);

const headers = [
  { text: "id", align: "start", value: "id" },
  { text: "平台", value: "description" },
  { text: "主播昵称", value: "author" },
  { text: "房间号", value: "group" },
  { text: "标签", value: "priority" },
];

const open = (item) => {};

onMounted(() => {
  setTimeout(() => {
    loading.value = false;
  }, 1000);
});

const searchKey = ref("")
const keys = ["group","name"]

const search = computed(()=> {
  for (let i = 0; i < keys.length; i++) {
    let key = keys[i]
    if (searchKey.value.startsWith(key + ":")) {
      let param = searchKey.value.split(key + ":")
      if (param.length >= 2) {
        return props.creepers.filter((creeper) => {
          return creeper[key].includes(param[1].toLowerCase())
        })
      }
    }
  }
  return props.creepers
})

const priorityColor = (priority:Number)=>{
  if(priority<3){
    return "green"
  }else if(priority<5){
    return "secondary"
  }else if(priority<8){
    return "orange"
  }else{
    return "red"
  }
}
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
    <v-card>
      <v-card-title class="font-weight-bold">
        <span>Live Follow</span>
        <v-spacer></v-spacer>
        <v-btn
          color="primary"
          dark
          v-bind="props"
          @click=""
        >
          <v-icon start icon="mdi mdi-cog"></v-icon>
          Setting
        </v-btn>
        <v-dialog
          v-model="dialog"
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
                @click="dialog = false"
              >
                <v-icon>mdi-close</v-icon>
              </v-btn>
              <v-toolbar-title>Settings</v-toolbar-title>
              <v-spacer></v-spacer>
              <v-toolbar-items>
                <v-btn
                  variant="text"
                  @click="dialog = false"
                >
                  Save
                </v-btn>
              </v-toolbar-items>
            </v-toolbar>
            <v-list
              lines="two"
              subheader
            >
              <v-list-subheader>User Controls</v-list-subheader>
              <v-list-item title="Content filtering" subtitle="Set the content filtering level to restrict apps that can be downloaded"></v-list-item>
              <v-list-item title="Password" subtitle="Require password for purchase or use password to restrict purchase"></v-list-item>
            </v-list>
            <v-divider></v-divider>
            <v-list
              lines="two"
              subheader
            >
              <v-list-subheader>General</v-list-subheader>
              <v-list-item title="Notifications" subtitle="Notify me about updates to apps or games that I downloaded">
                <template v-slot:prepend>
                  <v-checkbox v-model="notifications"></v-checkbox>
                </template>
              </v-list-item>
              <v-list-item title="Sound" subtitle="Auto-update apps at any time. Data charges may apply">
                <template v-slot:prepend>
                  <v-checkbox v-model="sound"></v-checkbox>
                </template>
              </v-list-item>
              <v-list-item title="Auto-add widgets" subtitle="Automatically add home screen widgets">
                <template v-slot:prepend>
                  <v-checkbox v-model="widgets"></v-checkbox>
                </template>
              </v-list-item>
            </v-list>
          </v-card>
        </v-dialog>
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
        <tr v-for="(item,index) in search" :key="item.name">
          <td class="font-weight-bold">
            <copy-label :text="`# ${index}`" />
          </td>
          <td>
            <copy-label :text="item.name" />
          </td>
          <td>{{ item.description }}</td>
          <td>{{ item.author }}</td>
          <td>
            <v-chip
              class="ma-2"
              color="cyan"
              label>
              <v-icon start icon="mdi mdi-earth-box"></v-icon>
              {{ item.group }}
            </v-chip>
          </td>
        </tr>
        </tbody>
      </v-table>
    </v-card>

  </div>
</template>

<style lang="scss" scoped>
.dialog-bottom-transition-enter-active,
.dialog-bottom-transition-leave-active {
  transition: transform .2s ease-in-out;
}
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
