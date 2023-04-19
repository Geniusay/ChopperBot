<template>
  <v-container grid-list-xs>
    <v-row>
      <v-col>
        <v-card width="600" height="800" class="pa-5" elevation="12">
          <v-form ref="form" v-model="valid" lazy-validation class="formStyle">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="40"
              height="40"
              viewBox="0 0 40 40"
              class="mx-auto mb-6"
            >
              <defs>
                <linearGradient
                  id="clearbit-a"
                  x1="50%"
                  x2="100%"
                  y1="0%"
                  y2="100%"
                >
                  <stop offset="0%" stop-color="#DEF2FE"></stop>
                  <stop offset="100%" stop-color="#DBF1FE"></stop>
                </linearGradient>
                <linearGradient
                  id="clearbit-b"
                  x1="0%"
                  x2="50%"
                  y1="0%"
                  y2="100%"
                >
                  <stop offset="0%" stop-color="#57BCFD"></stop>
                  <stop offset="100%" stop-color="#51B5FD"></stop>
                </linearGradient>
                <linearGradient
                  id="clearbit-c"
                  x1="37.5%"
                  x2="62.5%"
                  y1="0%"
                  y2="100%"
                >
                  <stop offset="0%" stop-color="#1CA7FD"></stop>
                  <stop offset="100%" stop-color="#148CFC"></stop>
                </linearGradient>
                <filter
                  id="ck-icon-shadow"
                  x="-50%"
                  y="-50%"
                  width="200%"
                  height="200%"
                >
                  <feOffset
                    result="offOut"
                    in="SourceGraphic"
                    dx="0"
                    dy="1"
                  ></feOffset>
                  <feGaussianBlur
                    result="blurOut"
                    in="offOut"
                    stdDeviation="1"
                  ></feGaussianBlur>
                  <feBlend
                    in="SourceGraphic"
                    in2="blurOut"
                    mode="normal"
                  ></feBlend>
                </filter>
              </defs>
              <g fill="none">
                <path
                  d="M27.9195733,37 L12.0804267,37 L11.4338943,36.9949826 C8.75934941,36.9515623 7.69554096,36.6271471 6.62367147,36.053905 C5.46935048,35.4365674 4.56343261,34.5306495 3.94609499,33.3763285 L3.81824005,33.1283803 C3.30403747,32.0897188 3.02217708,30.9433817 3.00125617,28.250264 L3,12.0804267 C3,8.92296455 3.32875737,7.77799245 3.94609499,6.62367147 C4.56343261,5.46935048 5.46935048,4.56343261 6.62367147,3.94609499 L6.87161969,3.81824005 C7.91028124,3.30403747 9.05661831,3.02217708 11.749736,3.00125617 L27.9195733,3 C31.0770355,3 32.2220075,3.32875737 33.3763285,3.94609499 C34.5306495,4.56343261 35.4365674,5.46935048 36.053905,6.62367147 L36.18176,6.87161969 C36.6959625,7.91028124 36.9778229,9.05661831 36.9987438,11.749736 L37,27.9195733 L36.9949826,28.5661057 C36.9515623,31.2406506 36.6271471,32.304459 36.053905,33.3763285 C35.4365674,34.5306495 34.5306495,35.4365674 33.3763285,36.053905 L33.1283803,36.18176 C32.0481723,36.7165306 30.8515024,37 27.9195733,37 Z"
                  id="clearbit-edge"
                  stroke-opacity="0.2"
                  stroke="#9BADBC"
                  fill-opacity="0"
                  fill="#9BADBC"
                ></path>
                <path
                  d="M27.9195733,37 L12.0804267,37 L11.4338943,36.9949826 C8.75934941,36.9515623 7.69554096,36.6271471 6.62367147,36.053905 C5.46935048,35.4365674 4.56343261,34.5306495 3.94609499,33.3763285 L3.81824005,33.1283803 C3.30403747,32.0897188 3.02217708,30.9433817 3.00125617,28.250264 L3,12.0804267 C3,8.92296455 3.32875737,7.77799245 3.94609499,6.62367147 C4.56343261,5.46935048 5.46935048,4.56343261 6.62367147,3.94609499 L6.87161969,3.81824005 C7.91028124,3.30403747 9.05661831,3.02217708 11.749736,3.00125617 L27.9195733,3 C31.0770355,3 32.2220075,3.32875737 33.3763285,3.94609499 C34.5306495,4.56343261 35.4365674,5.46935048 36.053905,6.62367147 L36.18176,6.87161969 C36.6959625,7.91028124 36.9778229,9.05661831 36.9987438,11.749736 L37,27.9195733 L36.9949826,28.5661057 C36.9515623,31.2406506 36.6271471,32.304459 36.053905,33.3763285 C35.4365674,34.5306495 34.5306495,35.4365674 33.3763285,36.053905 L33.1283803,36.18176 C32.0481723,36.7165306 30.8515024,37 27.9195733,37 Z"
                  id="clearbit-fx"
                  fill-opacity="0.2"
                  fill="#9BADBC"
                  filter="url(#ck-icon-shadow)"
                ></path>
                <path
                  fill="url(#clearbit-a)"
                  d="M37,20 L37,27.9195733 C37,31.0770355 36.6712426,32.2220075 36.053905,33.3763285 C35.4365674,34.5306495 34.5306495,35.4365674 33.3763285,36.053905 C32.2220075,36.6712426 31.0770355,37 27.9195733,37 L20,37 L20,20 L37,20 Z"
                ></path>
                <path
                  fill="url(#clearbit-b)"
                  d="M20,3 L27.9195733,3 C31.0770355,3 32.2220075,3.32875737 33.3763285,3.94609499 C34.5306495,4.56343261 35.4365674,5.46935048 36.053905,6.62367147 C36.6712426,7.77799245 37,8.92296455 37,12.0804267 L37,20 L20,20 L20,3 Z"
                ></path>
                <path
                  fill="url(#clearbit-c)"
                  d="M12.0804267,3 L20,3 L20,37 L12.0804267,37 C8.92296455,37 7.77799245,36.6712426 6.62367147,36.053905 C5.46935048,35.4365674 4.56343261,34.5306495 3.94609499,33.3763285 C3.32875737,32.2220075 3,31.0770355 3,27.9195733 L3,12.0804267 C3,8.92296455 3.32875737,7.77799245 3.94609499,6.62367147 C4.56343261,5.46935048 5.46935048,4.56343261 6.62367147,3.94609499 C7.77799245,3.32875737 8.92296455,3 12.0804267,3 Z"
                ></path>
              </g>
            </svg>
            <h1 class="text-center text-h6">Get Started</h1>
            <div class="label-text">Email</div>
            <v-text-field
              :density="formStyle.density"
              :variant="formStyle.variant"
              :color="formStyle.borderColor"
              :bg-color="formStyle.bgColor"
              placeholder="you@company-email.com"
              prepend-inner-icon="mdi-email-outline"
            ></v-text-field>
            <div class="label-text">Message</div>
            <v-textarea
              :density="formStyle.density"
              :variant="formStyle.variant"
              :color="formStyle.borderColor"
              :bg-color="formStyle.bgColor"
              placeholder="What are you hoping to accomplish with Clearbit?"
            ></v-textarea>
            <div class="label-text">How did you hear about us? (Optional)</div>
            <v-text-field
              :density="formStyle.density"
              :variant="formStyle.variant"
              :color="formStyle.borderColor"
              :bg-color="formStyle.bgColor"
            ></v-text-field>
            <v-btn
              :color="formStyle.mainColor"
              size="large"
              block
              @click="resetValidation"
              theme="dark"
              class="text-capitalize"
              >Request a Demo</v-btn
            >
          </v-form>
        </v-card>
      </v-col>
      <v-col>
        <v-card class="pa-5" height="800" color="#ddd">
          <v-form ref="form2" v-model="valid" lazy-validation class="formStyle">
            <h1 class="text-center text-h6">Operator</h1>
            <div class="label-text">Density</div>
            <v-btn-toggle v-model="formStyle.density" color="primary" mandatory>
              <v-btn value="default">default</v-btn>
              <v-btn value="comfortable">comfortable</v-btn>
              <v-btn value="compact">compact</v-btn>
            </v-btn-toggle>
            <div class="label-text">Variant</div>
            <v-btn-toggle v-model="formStyle.variant" color="primary" mandatory>
              <v-btn value="flat">flat</v-btn>
              <v-btn value="elevated">elevated</v-btn>
              <v-btn value="tonal">tonal</v-btn>
              <v-btn value="outlined">outlined</v-btn>
              <v-btn value="text">Subscribe</v-btn>
              <v-btn value="plain">plain</v-btn>
            </v-btn-toggle>
          </v-form>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script setup lang="ts">
const formStyle = reactive({
  labelColor: "3B64A0",
  mainColor: "#3F69F4",
  borderColor: "#42a5f5",
  bgColor: "#fff",
  density: "comfortable", //	'default' | 'comfortable' | 'compact'
  variant: "outlined", // 'flat' | 'elevated' | 'tonal' | 'outlined' | 'text' | 'plain'
});

const toggleDensity = ref("");
</script>

<style scoped>
.formStyle {
  height: 100%;
  background-color: rgba(250, 251, 252, 1);
  background: linear-gradient(0deg, #fafbfc, #e6e9f4);
  /* background-color: #fff; */
  border: 1px solid #ddd;
  padding: 4rem;
  border-radius: 10px;
}

.v-text-field ::-webkit-input-placeholder {
  /* WebKit browsers */
  color: #64748b;
}

.label-text {
  font-weight: 500;
  color: #3b64a0;
  margin-top: 0.5rem;
  margin-bottom: 0.5rem;
}
</style>
