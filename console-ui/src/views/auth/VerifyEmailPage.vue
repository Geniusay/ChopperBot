<script setup lang="ts">
/*
|---------------------------------------------------------------------
| Verify Email Page Component
|---------------------------------------------------------------------
|
| Template to wait for the verification on the user email
|
*/

const TIMEOUT = 5;
const isLoading = ref(false);
const disabled = ref(true);
const times = ref(0);
const seconds = ref("");
const secondsToEnable = ref(TIMEOUT);
const resendInterval = ref();

const setTimer = () => {
  disabled.value = true;
  times.value++;
  secondsToEnable.value = TIMEOUT * times.value;
  resendInterval.value = setInterval(() => {
    if (secondsToEnable.value === 0) {
      clearInterval(resendInterval.value);
      seconds.value = "";
      disabled.value = false;
    } else {
      seconds.value = `( ${secondsToEnable.value} )`;
      secondsToEnable.value--;
    }
  }, 1000);
};

const resend = () => {
  setTimer();
};

onMounted(() => {
  setTimer();
});

onUnmounted(() => {
  clearInterval(resendInterval.value);
});
</script>
<template>
  <v-card class="pa-5">
    <h1 class="text-h5 font-weight-bold">Please verify the email</h1>
    <div class="mb-5 text-grey text-caption">
      Please check your email for the link to verify the email.
    </div>
    <v-btn
      class="text-capitalize"
      block
      color="primary"
      size="x-large"
      :loading="isLoading"
      :disabled="disabled"
      @click="resend"
      >Re-send email{{ seconds }}
    </v-btn>
  </v-card>
</template>

<style scoped></style>
