export default [
  {
    icon: "mdi-file-lock-outline",
    key: "menu.auth",
    text: "Auth Pages",
    regex: /^\/auth/,
    items: [
      {
        icon: "mdi-login",
        key: "menu.authLogin",
        text: "Signin / Login",
        link: "/auth/signin",
      },
      {
        icon: "mdi-logout",
        key: "menu.authRegister",
        text: "Signup / Register",
        link: "/auth/signup",
      },
      {
        icon: "mdi-email-check",
        key: "menu.authVerify",
        text: "Signup / AuthVerify",
        link: "/auth/verify-email",
      },
      {
        icon: "mdi-file-outline",
        key: "menu.authForgot",
        text: "Forgot Password",
        link: "/auth/forgot-password",
      },
      {
        icon: "mdi-file-outline",
        key: "menu.authReset",
        text: "Reset Password",
        link: "/auth/reset-password",
      },
    ],
  },
  {
    icon: "mdi-file-cancel-outline",
    key: "menu.errorPages",
    text: "Error Pages",
    regex: /^\/error/,
    items: [
      {
        icon: "mdi-note-remove",
        key: "menu.errorNotFound",
        text: "Not Found / 404",
        link: "/error/not-found",
      },
      {
        icon: "mdi-note-remove-outline",
        key: "menu.errorUnexpected",
        text: "Unexpected / 500",
        link: "/error/unexpected",
      },
    ],
  },
  {
    icon: "mdi-file-cog-outline",
    key: "menu.utilityPages",
    text: "Utility Pages",
    regex: /^\/utility/,
    items: [
      {
        icon: "mdi-wrench-clock",
        key: "menu.utilityMaintenance",
        text: "Maintenance",
        link: "/utility/maintenance",
      },
      {
        icon: "mdi-timer-sand",
        key: "menu.utilitySoon",
        text: "Coming Soon",
        link: "/utility/coming-soon",
      },
      {
        icon: "mdi-comment-question-outline",
        key: "menu.utilityHelp",
        text: "FAQs / Help",
        link: "/utility/help",
      },
    ],
  },
];
