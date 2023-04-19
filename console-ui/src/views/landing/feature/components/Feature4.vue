<!--
* @Component:
* @Maintainer: J.K. Yang
* @Description:
-->
<script setup lang="ts"></script>

<template>
  <v-toolbar height="60">
    <v-toolbar-title class="text-h6 font-weight-bold">
      <span>Feature4</span>
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
        <v-card height="100%">
          <v-img
            height="100%"
            cover
            src="https://images.unsplash.com/photo-1531030874896-fdef6826f2f7?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1740&q=80"
          ></v-img>
        </v-card>
      </v-col>
      <v-col cols="12" md="6">
        <v-card
          color="white"
          elevation="0"
          height="100%"
          class="mx-auto pa-5 pa-sm-10 pa-md-15"
        >
          <RouterLink to="/" class="text-primary">WORK WITH US</RouterLink>
          <h1 class="font-weight-black text-h3 mt-5 mb-10">
            <span class="text-primary">Vuetify</span>
            your startup
          </h1>
          <p>
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Natus
            impedit error labore doloremque fugit! Dolor fugit molestiae vero
            quos quisquam nobis, eos debitis magni omnis ea incidunt amet
            voluptate dignissimos!
          </p>
          <h2 class="text-h6 mt-4 mx-auto">Premium Design</h2>
          <div class="my-5 ml-5">
            <li>Lifetime Updates</li>
            <li>Quality Support</li>
            <li>Easy to build websites</li>
          </div>

          <h2 class="text-h6 mt-4 mx-auto my-5">Building your project</h2>
          <p>
            Lorem ipsum dolor sit amet consectetur, adipisicing elit. Quis
            cupiditate totam, quae deleniti voluptate alias, doloribus
            doloremque qui corporis veritatis aspernatur harum. Expedita,
            blanditiis, nostrum eveniet consequatur porro quae, delectus ratione
            nam minus fugit odit quas dolore voluptatum! Molestiae, id
            voluptate. Ullam ut ratione iste quibusdam blanditiis itaque
            cupiditate totam dolor quisquam praesentium, ad necessitatibus
            beatae, doloremque fugiat esse repellendus nulla error sequi illo
            nihil sit eos asperiores expedita ducimus? Nisi quasi eius in, nobis
            modi cum numquam quibusdam sint. Ad vitae expedita dolores
            repudiandae ea laborum voluptatem, perferendis at corrupti fuga!
            Placeat sunt molestias eos quo corrupti omnis laborum?
          </p>

          <v-row class="mt-5">
            <v-col cols="12" sm="6">
              <v-btn block height="60" class="text-white mr-5" color="primary"
                >Get Stack</v-btn
              >
            </v-col>
            <v-col cols="12" sm="6">
              <v-btn variant="outlined" block height="60" class="text-primary"
                >Watch Demo</v-btn
              >
            </v-col>
          </v-row>
        </v-card>
      </v-col>
    </v-row>
  </v-sheet>
</template>

<style scoped lang="scss"></style>
