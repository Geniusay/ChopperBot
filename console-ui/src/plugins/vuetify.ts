/**
 * plugins/vuetify.js
 *
 * Framework documentation: https://vuetifyjs.com`
 */

// Styles
import "@mdi/font/css/materialdesignicons.css";
import "vuetify/styles";
// Composables
import { createVuetify } from "vuetify";
import type { ThemeDefinition } from "vuetify";
import * as components from "vuetify/components";
import * as directives from "vuetify/directives";
import { createVueI18nAdapter } from "vuetify/locale/adapters/vue-i18n";
import { useI18n } from "vue-i18n";
import i18n from "@/plugins/i18n";
import * as labs from "vuetify/labs/components";

// https://vuetifyjs.com/en/introduction/why-vuetify/#feature-guides

const Lighttheme: ThemeDefinition = {
  dark: false,
  variables: {
    "high-emphasis-opacity": 1,
  },
  colors: {
    background: "#f2f5f8",
    surface: "#ffffff",
    primary: "#344767",
    secondary: "#334155",
    accent: "#705CF6",
    error: "#ef476f",
    info: "#2196F3",
    success: "#06d6a0",
    "on-success": "#ffffff",
    warning: "#ffd166",
  },
};

const Darktheme: ThemeDefinition = {
  dark: true,
  colors: {
    background: "#111b27",
    surface: "#1E293B",
    primary: "#705CF6",
    secondary: "#598EF3",
    accent: "#705CF6",
    error: "#FF5252",
    info: "#2196F3",
    success: "#4CAF50",
    warning: "#FFC107",
  },
};

export default createVuetify({
  components: {
    ...components,
    ...labs,
  },
  directives,
  theme: {
    themes: {
      light: Lighttheme,
      dark: Darktheme,
    },
  },
  defaults: {
    VBtn: {
      rounded: "md",
      fontWeight: "400",
      letterSpacing: "0",
    },
    VCard: {},
    VSheet: {
      elevation: 1,
    },
    VTable: {
      elevation: 1,
    },

    VDataTable: {
      fixedHeader: true,
      noDataText: "Results not found",
    },
    VTextField: {
      variant: "solo",
    },
    VSelect: {
      variant: "solo",
    },
  },
  locale: {
    adapter: createVueI18nAdapter({ i18n, useI18n }),
  },
});
