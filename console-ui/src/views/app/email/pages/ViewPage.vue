<!--
* @Component: 
* @Maintainer: J.K. Yang
* @Description: 
-->
<script setup lang="ts">
import EmailEditor from "../components/EmailEditor.vue";

const messagePanel = ref([3]);
const content = ref("");
const submit = () => {
  console.log(content.value);
};
</script>

<template>
  <div>
    <v-expansion-panels v-model="messagePanel" multiple>
      <v-expansion-panel elevation="1" v-for="i in 3">
        <v-expansion-panel-title>
          <template v-slot:default="{ expanded }">
            <div class="d-flex align-center w-full">
              <v-avatar size="36px">
                <img src="@/assets/images/svg1.svg" alt="" />
              </v-avatar>
              <div class="mx-3 flex-1">
                <div class="font-weight-bold mb-1 ml-1">title</div>
                <div v-show="expanded">
                  <v-menu>
                    <template v-slot:activator="{ props }">
                      <v-btn
                        class="px-1"
                        appendIcon="mdi-chevron-down"
                        v-bind="props"
                        variant="text"
                      >
                        to Me
                      </v-btn>
                    </template>

                    <v-card class="pa-4">
                      <div>
                        <span class="text-grey">from:</span>
                        johnnilson@whatthisisnotarealemail.com
                      </div>
                      <div>
                        <span class="text-grey">to:</span>
                        clara@whatthisisnotarealemail.com
                      </div>
                    </v-card>
                  </v-menu>
                </div>
                <div v-show="!expanded" class="ml-1 text-truncate">
                  Lorem ipsum dolor sit amet, consectetur adipisicing elit. Non
                  ut, soluta temporibus, culpa magnam harum quod asperiores
                  excepturi iste veniam possimus dignissimos laboriosam ipsum
                  voluptas repellat consequuntur vitae debitis consequatur.
                </div>
              </div>

              <v-menu>
                <template v-slot:activator="{ props }">
                  <v-btn v-bind="props" icon variant="text">
                    <v-icon>mdi-dots-vertical</v-icon>
                  </v-btn>
                </template>

                <v-list dense nav>
                  <v-list-item link>
                    <v-list-item-title>Forward</v-list-item-title>
                  </v-list-item>
                  <v-list-item link>
                    <v-list-item-title>Delete</v-list-item-title>
                  </v-list-item>
                </v-list>
              </v-menu>
            </div>
          </template>
        </v-expansion-panel-title>
        <v-expansion-panel-text>
          <p>Hello,</p>
          <p>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
            eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim
            ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut
            aliquip ex ea commodo consequat.
          </p>
          <p>Best regards</p>
        </v-expansion-panel-text>
      </v-expansion-panel>
    </v-expansion-panels>
    <EmailEditor />
    <v-card height="300">
      <v-btn color="success" @click="submit">text</v-btn>
      {{ content }}
    </v-card>
  </div>
</template>

<style scoped lang="scss"></style>
