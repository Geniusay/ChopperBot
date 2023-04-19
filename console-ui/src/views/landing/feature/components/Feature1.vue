<!--
* @Component:
* @Maintainer: J.K. Yang
* @Description:
-->
<script setup lang="ts"></script>

<template>
  <v-toolbar height="60">
    <v-toolbar-title class="text-h6 font-weight-bold">
      <span>Feature1</span>
    </v-toolbar-title>
  </v-toolbar>
  <v-sheet elevation="0" class="mx-auto landing-warpper text-left" rounded>
    <v-sheet
      elevation="0"
      color="transparent"
      max-width="1600 "
      class="mx-auto my-10"
    >
      <v-row no-gutters align="stretch">
        <v-col cols="12">
          <v-card
            elevation="0"
            height="100%"
            class="mx-auto pa-10 pa-md-15 d-flex flex-column justify-center"
          >
            <div>
              <RouterLink to="/" class="text-primary font-weight-bold"
                >WORK WITH US</RouterLink
              >
              <h1 class="font-weight-black text-h3 mt-5 mb-10">
                Get your startup ready for
                <span class="text-primary">Business</span>
              </h1>
              <p>
                Lorem ipsum dolor sit amet consectetur adipisicing elit. Natus
                impedit error labore doloremque fugit! Dolor fugit molestiae
                vero quos quisquam nobis, eos debitis magni omnis ea incidunt
                amet voluptate dignissimos!
              </p>
              <v-btn color="primary" size="large" class="mt-10"
                >Get In Touch</v-btn
              >
            </div>
          </v-card>
        </v-col>
        <v-col cols="12">
          <v-card height="100%"> </v-card>
        </v-col>
      </v-row>
    </v-sheet>
  </v-sheet>
</template>

<style scoped lang="scss"></style>
