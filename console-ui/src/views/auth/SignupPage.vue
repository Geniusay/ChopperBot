<script setup lang="ts">
import { Icon } from "@iconify/vue";
import { useAuthStore } from "@/stores/authStore";

const authStore = useAuthStore();
const username = ref("");

// sign in buttons
const isLoading = ref(false);
const isSignInDisabled = ref(false);

const refLoginForm = ref();
const isFormValid = ref(true);
const email = ref("");
const password = ref("");

// show password field
const showPassword = ref(false);

// Submit
const handleRegister = async () => {
  const { valid } = await refLoginForm.value.validate();
  if (valid) {
    isLoading.value = true;
    isSignInDisabled.value = true;
    authStore.registerWithEmailAndPassword(email.value, password.value);
  } else {
    console.log("no");
  }
};

// Error Check
const emailRules = ref([
  (v: string) => !!v || "E-mail is required",
  (v: string) => /.+@.+\..+/.test(v) || "E-mail must be valid",
]);

const usernameRules = ref([(v: string) => !!v || "UserNmae is required"]);

const passwordRules = ref([
  (v: string) => !!v || "Password is required",
  (v: string) =>
    (v && v.length <= 10) || "Password must be less than 10 characters",
]);

// error provider
const errorProvider = ref(false);
const errorProviderMessages = ref("");

const error = ref(false);
const errorMessages = ref("");

const resetErrors = () => {
  error.value = false;
  errorMessages.value = "";
};
</script>
<template>
  <v-card color="white" class="pa-3" elevation="3">
    <v-card-title primary-title class="my-4 text-h4">
      <span class="flex-1"> {{ $t("register.title") }} </span>
    </v-card-title>
    <v-card-subtitle>Let's build amazing products</v-card-subtitle>
    <!-- sign in form -->

    <v-card-text>
      <v-form
        ref="refLoginForm"
        class="text-left"
        v-model="isFormValid"
        lazy-validation
      >
        <v-text-field
          v-model="username"
          required
          :error="error"
          :label="$t('register.username')"
          density="default"
          variant="underlined"
          color="primary"
          bg-color="#fff"
          :rules="usernameRules"
          name="username"
          outlined
          validateOn="blur"
          @keyup.enter="handleRegister"
          @change="resetErrors"
        ></v-text-field>
        <v-text-field
          ref="refEmail"
          v-model="email"
          required
          :error="error"
          :label="$t('register.email')"
          density="default"
          variant="underlined"
          color="primary"
          bg-color="#fff"
          :rules="emailRules"
          name="email"
          outlined
          validateOn="blur"
          @keyup.enter="handleRegister"
          @change="resetErrors"
        ></v-text-field>
        <v-text-field
          ref="refPassword"
          v-model="password"
          :append-inner-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
          :type="showPassword ? 'text' : 'password'"
          :error="error"
          :error-messages="errorMessages"
          :label="$t('register.password')"
          density="default"
          variant="underlined"
          color="primary"
          bg-color="#fff"
          :rules="passwordRules"
          name="password"
          outlined
          validateOn="blur"
          @change="resetErrors"
          @keyup.enter="handleRegister"
          @click:append-inner="showPassword = !showPassword"
        ></v-text-field>
        <v-btn
          :loading="isLoading"
          :disabled="isSignInDisabled"
          block
          size="x-large"
          color="primary"
          @click="handleRegister"
          class="mt-2"
          >{{ $t("register.button") }}</v-btn
        >

        <div
          class="text-grey text-center text-caption font-weight-bold text-uppercase my-5"
        >
          {{ $t("register.orsign") }}
        </div>

        <!-- external providers list -->
        <v-btn
          class="mb-2 lighten-2 text-capitalize"
          block
          size="x-large"
          color="white"
          @click="authStore.loginWithGoogle()"
          :disabled="isSignInDisabled"
        >
          <Icon icon="logos:google-icon" class="mr-3 my-2" />
          Google
        </v-btn>
        <v-btn
          class="mb-2 lighten-2 text-capitalize"
          block
          color="white"
          size="x-large"
          :disabled="isSignInDisabled"
        >
          <Icon icon="logos:facebook" class="mr-3" />
          Facebook
        </v-btn>

        <div v-if="errorProvider" class="error--text my-5">
          {{ errorProviderMessages }}
        </div>

        <div class="my-5 text-center">
          {{ $t("register.agree") }}
          <br />
          <router-link class="text-primary" to="">{{
            $t("common.tos")
          }}</router-link>
          &
          <router-link class="text-primary" to="">{{
            $t("common.policy")
          }}</router-link>
        </div>
      </v-form></v-card-text
    >
  </v-card>
  <div class="text-center mt-6">
    {{ $t("register.account") }}
    <router-link to="/auth/signin" class="text-primary font-weight-bold">
      {{ $t("register.signin") }}
    </router-link>
  </div>
</template>
