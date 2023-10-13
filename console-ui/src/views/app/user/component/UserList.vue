<!--
* @Component:
* @Maintainer: J.K. Yang
* @Description:
-->
<script setup lang="ts">
import { useUserStore } from "../userStore";
import { User } from "../userTypes";

const props = defineProps<{
  tasks: User[];
}>();

const userStore = useUserStore();
const searchKey = ref("");

const getLabelColor = (id: string) => {
  // Find the label by id from the labels array
  const label = userStore.labels.find((l) => l.id === id);
  // Return the color for that label, or an empty string
  return label ? label.color : "";
};

// filterdUserList is a computed value that will filter the userList based on the searchKey value
const filterdUserList = computed(() => {
  return props.tasks.filter((user) => {
    return user.username.toLowerCase().includes(searchKey.value.toLowerCase());
  });
});
</script>

<template>
  <v-card height="100%">
    <!-- ---------------------------------------------- -->
    <!-- Filter Input -->
    <!-- ---------------------------------------------- -->
    <v-text-field
      clearable
      variant="solo"
      class="elevation-1 ma-3"
      hide-details
      prepend-inner-icon="mdi-magnify"
      placeholder="Filter Tasks"
      v-model="searchKey"
    ></v-text-field>

    <!-- ---------------------------------------------- -->
    <!-- List -->
    <!-- ---------------------------------------------- -->
    <perfect-scrollbar class="user-list">
      <transition-group name="fade">
        <div v-for="user in filterdUserList" :key="user.id">
          <div class="user-item d-flex align-center pa-5">
            <!--<v-checkbox-btn
              v-model="user.completed"
              color="primary"
              class="pe-2"
            ></v-checkbox-btn>-->
            <v-avatar size="40">
              <v-img
                src="https://avatars.githubusercontent.com/u/35951244?v=4"
                alt="alt"
              />
            </v-avatar>
            <div class="flex-1 mx-5">
              <div
                class="font-weight-bold"
                :class="user.completed ? 'text-decoration-line-through' : ''"
              >
                {{ user.username }}
              </div>

              <div>
                <v-chip
                  size="x-small"
                  variant="outlined"
                  class="mr-1 mt-1"
                  :color="getLabelColor(tag)"
                  v-for="tag in user.typeList"
                >
                  {{ tag.type }}
                </v-chip>
              </div>
            </div>
            <div class="flex-1 mx-5">
              {{user.password}}
            </div>
            <!--<v-btn
              size="small"
              icon="mdi-delete-outline"
              variant="text"
              @click="userStore.deleteUserById(user.id)"
            ></v-btn>-->
          </div>
        </div>
      </transition-group>
    </perfect-scrollbar>
  </v-card>
</template>

<style scoped lang="scss">
.user-list {
  height: 100%;
  overflow: scroll;
  .user-item {
    transition: all 0.3s;
    border-bottom: 1px solid #eee;
    &:hover {
      transition: all 0.3s;
      background-color: rgba(99, 99, 99, 0.2);
      box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px !important;
      cursor: pointer;
    }
  }
}
</style>
