<template>
  <v-card class="pa-3" height="100%">
    <!-- ---------------------------------------------- -->
    <!-- Write Email -->
    <!-- ---------------------------------------------- -->

    <EmailCompose />

    <v-list class="mt-2 pa-0" nav>
      <v-list-item
        active-class="text-primary"
        link
        prepend-icon="mdi-inbox"
        title="VideoTool"
        to="/apps/tool/video"
      >
      </v-list-item>
      <v-list-item
        active-class="text-primary"
        link
        prepend-icon="mdi-send-outline"
        title="balabala"
        to="/apps/email/send"
      >
      </v-list-item>
    </v-list>
  </v-card>
</template>

<script>
export default {
  name: "ToolMenu"
}
</script>

<style scoped>

</style>
