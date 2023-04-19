<!--
* @Component:
* @Maintainer: J.K. Yang
* @Description:
-->
<script setup lang="ts"></script>

<template>
  <v-toolbar height="60">
    <v-toolbar-title class="text-h6 font-weight-bold">
      <span>Feature3</span>
    </v-toolbar-title>
  </v-toolbar>
  <v-sheet
    elevation="0"
    class="mx-auto landing-warpper text-left"
    color="#F2F5F8"
    rounded
  >
    <v-row no-gutters align="stretch">
      <v-col cols="12" md="6">
        <v-card
          theme="dark"
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
              impedit error labore doloremque fugit! Dolor fugit molestiae vero
              quos quisquam nobis, eos debitis magni omnis ea incidunt amet
              voluptate dignissimos!
            </p>
            <v-btn variant="outlined" size="large" class="text-white mt-10"
              >Get In Touch</v-btn
            >
          </div>
        </v-card>
      </v-col>
      <v-col cols="12" md="6">
        <v-card height="100%">
          <v-img
            height="100%"
            cover
            src="https://images.unsplash.com/photo-1561677978-583a8c7a4b43?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1740&q=80"
          ></v-img>
        </v-card>
      </v-col>
    </v-row>
  </v-sheet>
</template>

<style scoped lang="scss"></style>
