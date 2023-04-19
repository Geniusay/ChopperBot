<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->

<script setup lang="ts">
import { useProfileStore } from "@/stores/profileStore";
import { Icon } from "@iconify/vue";
const profileStore = useProfileStore();
const basic = reactive({
  username: "",
  realname: "",
  email: "",
  avatar: "",
  location: "",
  role: "",
  disabled: false,
  about: "",
  lastSignIn: "2019-09-20T01:11:13Z",
});

const authorized = reactive({
  google: false,
  facebook: false,
});

const notifications = reactive({
  officialEmails: false,
  followerUpdates: false,
});

const passwords = reactive({
  currentPassword: "123456",
  newPassword: "",
  confirmPassword: "",
});

const currentPasswordShow = ref(false);
const newPasswordShow = ref(false);
const confirmPasswordShow = ref(false);

onMounted(() => {
  const profile = profileStore.getProfile();
  console.log(profile);

  basic.username = profile.basic.username;
  basic.realname = profile.basic.realname;
  basic.email = profile.basic.email;
  basic.avatar = profile.basic.avatar;
  basic.location = profile.basic.location;
  basic.role = profile.basic.role;
  basic.disabled = profile.basic.disabled;
  basic.about = profile.basic.about;
  basic.lastSignIn = profile.basic.lastSignIn;
  authorized.google = profile.authorized.google;
  authorized.facebook = profile.authorized.facebook;
  notifications.officialEmails = profile.notifications.officialEmails;
  notifications.followerUpdates = profile.notifications.followerUpdates;
});
</script>

Basic with Icons
<template>
  <v-sheet elevation="0" class="mx-auto" color="transparent" max-width="1600">
    <v-row>
      <v-col cols="12" md="3">
        <v-card>
          <div class="d-flex flex-column pa-10">
            <v-avatar size="120" class="mx-auto elevation-12" color="white">
              <v-img :src="basic.avatar"> </v-img>
            </v-avatar>

            <div class="text-center mt-5">
              <h3 class="text-h6 font-weight-bold">
                {{ basic.username }}
                <v-chip size="small" class="font-weight-bold" color="blue">
                  {{ basic.role }}
                </v-chip>
              </h3>
              <p class="text-body-2">{{ basic.about }}</p>
            </div>
          </div>
          <v-divider></v-divider>
          <div class="py-5 px-10">
            <v-icon color="grey"> mdi-map-marker </v-icon>
            <span class="ml-4">{{ basic.location }}</span>
          </div>

          <v-divider></v-divider>
          <div class="py-5 px-10">
            <v-icon color="grey"> mdi-email-check-outline </v-icon>
            <span class="ml-4">{{ basic.email }}</span>
          </div>
          <v-divider></v-divider>

          <div class="py-5 px-10">
            <v-icon color="grey"> mdi-phone-outline </v-icon>
            <span class="ml-4">070-4444-4444</span>
          </div>
        </v-card>
      </v-col>
      <v-col cols="12" md="9">
        <!-- ---------------------------------------------- -->
        <!--   Basic Infomation -->
        <!-- ---------------------------------------------- -->
        <v-card class="mb-5">
          <v-card-title class="bg-white py-4 font-weight-bold">
            Basic Infomation
          </v-card-title>
          <v-divider></v-divider>
          <v-card-text class="pa-7">
            <v-row>
              <v-col cols="12" sm="6">
                <v-label class="font-weight-medium mb-2">Username</v-label>
                <v-text-field
                  v-model="basic.username"
                  color="primary"
                  variant="outlined"
                  density="compact"
                  type="text"
                  placeholder="John Deo"
                  hide-details
                />
              </v-col>
              <v-col cols="12" sm="6">
                <v-label class="font-weight-medium mb-2">Realname</v-label>
                <v-text-field
                  v-model="basic.realname"
                  color="primary"
                  variant="outlined"
                  density="compact"
                  type="text"
                  placeholder="John Deo"
                  hide-details
                />
              </v-col>
              <v-col cols="12" sm="6">
                <v-label class="font-weight-medium mb-2">Email</v-label>
                <v-text-field
                  class="bg-blue-grey-lighten-5"
                  readonly
                  v-model="basic.email"
                  color="primary"
                  variant="outlined"
                  density="compact"
                  type="text"
                  placeholder="John Deo"
                  hide-details
              /></v-col>
            </v-row>
          </v-card-text>
          <v-divider></v-divider>
          <v-card-actions class="pa-5">
            <v-spacer></v-spacer>
            <v-btn
              class="px-5"
              color="primary"
              elevation="1"
              variant="elevated"
            >
              Unpdate Basic Info</v-btn
            >
          </v-card-actions>
        </v-card>

        <!-- ---------------------------------------------- -->
        <!--   Authentication  -->
        <!-- ---------------------------------------------- -->
        <v-card class="mb-5">
          <v-card-title class="bg-white py-4 font-weight-bold">
            Authentication</v-card-title
          >
          <v-divider></v-divider>
          <v-card-text class="pa-7">
            <v-row>
              <v-col cols="12" md="6">
                <v-btn
                  color="primary"
                  size="large"
                  block
                  elevation="1"
                  variant="elevated"
                >
                  <Icon
                    icon="logos:google-icon"
                    class="mr-3 my-2"
                  />Google</v-btn
                >
              </v-col>

              <v-col cols="12" md="6">
                <v-btn size="large" block variant="outlined" disabled>
                  <Icon
                    icon="logos:facebook"
                    class="mr-3 my-2"
                  />Facebook</v-btn
                >
              </v-col>

              <v-col cols="12" md="6">
                <v-btn size="large" block variant="outlined" disabled>
                  <Icon
                    icon="logos:github-icon"
                    class="mr-3 my-2"
                  />Github</v-btn
                >
              </v-col>
              <v-col cols="12" md="6">
                <v-btn size="large" block variant="outlined" disabled>
                  <Icon icon="logos:twitter" class="mr-3 my-2" />Twitter</v-btn
                >
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>

        <!-- ---------------------------------------------- -->
        <!--   Change Password  -->
        <!-- ---------------------------------------------- -->
        <v-card class="mb-5">
          <v-card-title class="bg-white py-4 font-weight-bold">
            Change Password
          </v-card-title>
          <v-divider></v-divider>
          <v-card-text class="pa-7">
            <v-row>
              <v-col cols="12" sm="6">
                <v-label class="font-weight-medium mb-2"
                  >Current Password</v-label
                >
                <v-text-field
                  readonly
                  v-model="passwords.currentPassword"
                  class="bg-blue-grey-lighten-5"
                  density="compact"
                  color="primary"
                  variant="outlined"
                  :type="currentPasswordShow ? 'text' : 'password'"
                  placeholder="Current Password"
                  hide-details
                  :append-inner-icon="
                    currentPasswordShow ? 'mdi-eye' : 'mdi-eye-off'
                  "
                  @click:append-inner="
                    currentPasswordShow = !currentPasswordShow
                  "
                >
                </v-text-field>
              </v-col>
              <v-col cols="12" sm="6"> </v-col>
              <v-col cols="12" sm="6">
                <v-label class="font-weight-medium mb-2">Password</v-label>
                <v-text-field
                  v-model="passwords.newPassword"
                  density="compact"
                  color="primary"
                  variant="outlined"
                  :type="newPasswordShow ? 'text' : 'password'"
                  placeholder="new password"
                  hide-details
                  :append-inner-icon="
                    newPasswordShow ? 'mdi-eye' : 'mdi-eye-off'
                  "
                  @click:append-inner="newPasswordShow = !newPasswordShow"
                >
                </v-text-field>
              </v-col>
              <v-col cols="12" sm="6">
                <v-label class="font-weight-medium mb-2"
                  >Confirm Password</v-label
                >
                <v-text-field
                  v-model="passwords.confirmPassword"
                  density="compact"
                  color="primary"
                  variant="outlined"
                  :type="confirmPasswordShow ? 'text' : 'password'"
                  placeholder="confirm password"
                  hide-details
                  :append-inner-icon="
                    confirmPasswordShow ? 'mdi-eye' : 'mdi-eye-off'
                  "
                  @click:append-inner="
                    confirmPasswordShow = !confirmPasswordShow
                  "
                >
                </v-text-field
              ></v-col>
            </v-row>
          </v-card-text>
          <v-divider></v-divider>
          <v-card-actions class="pa-5">
            <v-spacer></v-spacer>
            <v-btn
              class="px-5"
              color="primary"
              elevation="1"
              variant="elevated"
            >
              Unpdate Password</v-btn
            >
          </v-card-actions>
        </v-card>

        <!-- ---------------------------------------------- -->
        <!--   Notifications  -->
        <!-- ---------------------------------------------- -->
        <v-card class="mb-5">
          <v-card-title class="bg-white py-4 font-weight-bold">
            Notifications</v-card-title
          >
          <v-divider></v-divider>
          <v-card-text class="pa-7">
            <div>
              <v-switch
                v-model="notifications.officialEmails"
                color="primary"
                class="mr-4"
                hide-details
                label=" Receive newsletters, promotions and news from lux"
              ></v-switch>
            </div>
            <div>
              <v-switch
                v-model="notifications.followerUpdates"
                color="primary"
                class="mr-4"
                hide-details
                label=" Notify me when someone I follow uploads new workx"
              ></v-switch>
            </div>
          </v-card-text>
          <v-divider></v-divider>
          <v-card-actions class="pa-5">
            <v-spacer></v-spacer>
            <v-btn
              class="px-5"
              color="primary"
              elevation="1"
              variant="elevated"
            >
              Unpdate Notifications</v-btn
            >
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-sheet>
</template>

<style scoped lang="scss"></style>
