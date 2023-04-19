<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import { useEmailStore } from "../emailStore";
import { Email } from "../emailTypes";
const router = useRouter();

const props = defineProps<{
  emails: Email[];
}>();

const emailStore = useEmailStore();
const searchKey = ref("");

const getLabelColor = (id: string) => {
  // Find the label by id from the labels array
  const label = emailStore.labels.find((l) => l.id === id);
  // Return the color for that label, or an empty string
  return label ? label.color : "";
};

// filterdEmailList is a computed value that will filter the emailList based on the searchKey value
const filterdEmailList = computed(() => {
  return props.emails.filter((email) => {
    return email.title.toLowerCase().includes(searchKey.value.toLowerCase());
  });
});
</script>

<template>
  <v-card height="100%">
    <v-alert color="#C51162" theme="dark" icon="mdi-material-design" border>
      正在施工中...
    </v-alert>
    <!-- ---------------------------------------------- -->
    <!-- Filter Input -->
    <!-- ---------------------------------------------- -->
    <v-text-field
      clearable
      variant="solo"
      class="elevation-1 ma-3"
      hide-details
      prepend-inner-icon="mdi-magnify"
      placeholder="Search Email"
      v-model="searchKey"
    ></v-text-field>

    <!-- ---------------------------------------------- -->
    <!-- List -->
    <!-- ---------------------------------------------- -->
    <perfect-scrollbar class="email-list">
      <transition-group name="fade">
        <div v-for="email in filterdEmailList" :key="email.id">
          <div
            class="email-item d-flex align-center pa-5"
            @click="router.push(`/apps/email/inbox/${email.id}`)"
          >
            <v-checkbox-btn
              v-model="email.read"
              color="primary"
              class="pe-2"
            ></v-checkbox-btn>
            <v-avatar size="40">
              <v-img
                src="https://avatars.githubusercontent.com/u/35951244?v=4"
                alt="alt"
              />
            </v-avatar>
            <v-btn
              class="ml-2"
              :icon="email.starred ? 'mdi-star' : 'mdi-star-outline'"
              :color="email.starred ? 'yellow' : 'grey'"
              variant="text"
            ></v-btn>
            <div class="flex-1 mx-5">
              <div class="font-weight-bold">
                {{ email.title }}
              </div>
              <div>
                {{ email.content }}
              </div>
              <div>
                <v-chip
                  size="x-small"
                  variant="outlined"
                  class="mr-1 mt-1"
                  :color="getLabelColor(tag)"
                  v-for="tag in email.labels"
                >
                  {{ tag }}
                </v-chip>
              </div>
            </div>
            <v-btn
              size="small"
              icon="mdi-delete-outline"
              variant="text"
              @click="emailStore.deleteEmailById(email.id)"
            ></v-btn>
          </div>
        </div>
      </transition-group>
    </perfect-scrollbar>
  </v-card>
</template>

<style scoped lang="scss">
.email-list {
  height: 100%;
  overflow: scroll;
  .email-item {
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
