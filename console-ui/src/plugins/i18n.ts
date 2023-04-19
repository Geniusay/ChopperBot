import { createI18n } from "vue-i18n";
import locales from "@/configs/locales";
const messages: any = locales.messages;

const i18n = createI18n({
  legacy: false,
  locale: "en", // 设置默认语言
  fallbackLocale: "en", // 设置回退语言
  messages,
});

export default i18n;
