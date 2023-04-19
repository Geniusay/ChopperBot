<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import usersData from "@/data/users";
import { Icon } from "@iconify/vue";
</script>

<template>
  <div class="">
    <v-card>
      <perfect-scrollbar class="user-card">
        <v-table fixed-header fixed-footer height="900">
          <thead>
            <tr>
              <th class="text-left">Id</th>
              <th class="text-left">Name</th>
              <th class="text-left">EMAIL</th>
              <th class="text-left">VERIFIED</th>
              <th class="text-left">ROLE</th>
              <th class="text-left">CREATED</th>
              <th class="text-left">LAST SIGNIN</th>
              <th class="text-left">DISABLED</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="user in usersData" :key="user.name">
              <td># {{ user.id }}</td>
              <td>
                <v-avatar>
                  <img :src="user.avatar" alt="alt" />
                </v-avatar>
                {{ user.name }}
              </td>
              <td>{{ user.email }}</td>
              <td>
                <Icon v-if="user.verified" icon="flat-color-icons:ok" />
                <Icon v-else icon="mdi:checkbox-blank-circle-outline" />
              </td>
              <td>
                <v-chip
                  v-if="user.role === 'ADMIN'"
                  color="blue"
                  dark
                  size="small"
                >
                  {{ user.role }}
                </v-chip>
                <v-chip
                  v-else-if="user.role === 'EDITOR'"
                  color="success"
                  dark
                  size="small"
                >
                  {{ user.role }}
                </v-chip>
                <v-chip v-else-if="user.role === 'USER'" size="small">
                  {{ user.role }}
                </v-chip>
              </td>
              <td>{{ user.created }}</td>
              <td>{{ user.lastSignIn }}</td>
              <td>{{ user.disabled }}</td>
            </tr>
          </tbody>
          <template v-slot:bottom>
            <div class="text-center pt-2">
              <v-pagination length="10"></v-pagination>
            </div>
          </template>
        </v-table>
      </perfect-scrollbar>
    </v-card>
  </div>
</template>

<style scoped lang="scss">
.user-card {
  height: 1000px;
}
</style>
