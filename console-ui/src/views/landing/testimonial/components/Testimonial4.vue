<!--
* @Component:
* @Maintainer: J.K. Yang
* @Description:
-->
<script setup lang="ts">
const comments = [
  {
    content:
      "Extremely down-to-earth and relaxed atmosphere within the company, it's extremely easy to talk to anyone, including C-level executives.The company as a whole is inclusive of the cultural values everyone promotes and shares.",
    name: "Derrick Mehaffy",
    position: "Solutions Engineer",
    avatar:
      "https://d2zv2ciw0ln4h1.cloudfront.net/uploads/Derrick_4ec7a55385_1da25a0d9d.png",
  },
  {
    content:
      "That's so inspiring to see a company fighting so hard for its values! If you take care, for example, the emergency days are still blowing up my mind. Strapi considers employees as a whole, not just workers. ",
    name: "Noémie Nana Gassa",
    position: "Talent Acquisition Manager",
    avatar:
      "https://d2zv2ciw0ln4h1.cloudfront.net/uploads/1631195677739_050c18b591.jfif",
  },
  {
    content:
      "In the US, people tend to value the Hustle culture: 'work, work, work.' In this context, it's easy to forget to take care of yourself. At Strapi, they really put people first and encourage us to take care of ourselves.",
    name: "Paul Bratslavsky",
    position: "Developer Advocate",
    avatar:
      "https://d2zv2ciw0ln4h1.cloudfront.net/uploads/Derrick_4ec7a55385_1da25a0d9d.png",
  },
];
</script>

<template>
  <v-toolbar height="60">
    <v-toolbar-title class="text-h6 font-weight-bold">
      <span>Testimonial4</span>
    </v-toolbar-title>
  </v-toolbar>
  <v-sheet elevation="0" class="mx-auto landing-warpper text-left" rounded>
    <v-sheet
      elevation="0"
      color="transparent"
      max-width="1400 "
      class="mx-auto my-10 pa-10"
    >
      <v-item-group mandatory>
        <v-row align="stretch">
          <v-col cols="12" md="4" v-for="comment in comments">
            <v-item v-slot="{ isSelected, selectedClass, toggle }">
              <v-card
                :elevation="isSelected ? 10 : 1"
                class="base-card mx-auto pa-10 d-flex flex-column justify-center"
                :class="selectedClass"
                @click="toggle"
              >
                <div>
                  <v-card elevation="0" class="pa-5">
                    {{ comment.content }}
                  </v-card>

                  <v-card
                    elevation="0"
                    color="transparent"
                    class="user-card d-flex align-center my-5"
                  >
                    <div>
                      <v-avatar size="50" class="mr-4">
                        <img :src="comment.avatar" />
                      </v-avatar>
                    </div>
                    <div class="user-info text-body-2">
                      <div class="font-weight-bold text-purple">
                        {{ comment.name }}
                      </div>
                      <div>{{ comment.position }}</div>
                    </div>
                  </v-card>
                </div>
              </v-card>
            </v-item>
          </v-col>
        </v-row>
      </v-item-group>
    </v-sheet>
  </v-sheet>
</template>

<style scoped lang="scss"></style>
