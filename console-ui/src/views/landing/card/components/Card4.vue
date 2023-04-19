<!--
* @Component:
* @Maintainer: J.K. Yang
* @Description:
-->
<script setup lang="ts"></script>

<template>
  <v-toolbar height="60">
    <v-toolbar-title class="text-h6 font-weight-bold">
      <span>Card4</span>
    </v-toolbar-title>
  </v-toolbar>
  <v-sheet
    color="#F2F5F8"
    elevation="0"
    class="mx-auto landing-warpper text-left"
    rounded
  >
    <v-sheet
      elevation="0"
      color="transparent"
      max-width="1600 "
      class="mx-auto my-10"
    >
      <v-item-group selected-class="active-card">
        <v-row align="stretch">
          <v-col cols="12" md="4" v-for="i in 3">
            <v-item v-slot="{ isSelected, selectedClass, toggle }">
              <v-card
                :theme="isSelected ? 'dark' : 'light'"
                elevation="0"
                height="100%"
                class="mx-auto pa-10 pa-md-15 d-flex flex-column justify-center"
                :class="selectedClass"
                @click="toggle"
              >
                <div>
                  <RouterLink to="/" class="text-primary font-weight-bold"
                    >WORK WITH US{{ i }}</RouterLink
                  >
                  <h1 class="font-weight-black text-h3 mt-5 mb-10">
                    Get your startup ready for
                    <span class="text-primary">Business</span>
                  </h1>
                  <p>
                    Lorem ipsum dolor sit amet consectetur adipisicing elit.
                    Natus impedit error labore doloremque fugit! Dolor fugit
                    molestiae vero quos quisquam nobis, eos debitis magni omnis
                    ea incidunt amet voluptate dignissimos!
                  </p>
                  <v-btn color="primary" size="large" class="mt-10"
                    >Get In Touch</v-btn
                  >
                </div>
              </v-card>
            </v-item>
          </v-col>
        </v-row>
      </v-item-group>
    </v-sheet>
  </v-sheet>
</template>

<style scoped lang="scss">
.active-card {
  border: 1px solid #e5e5e5;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.05);
}
</style>
