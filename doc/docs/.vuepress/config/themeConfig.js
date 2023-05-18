const nav = require("./nav.js");
const htmlModules = require("./htmlModules.js");

// Theme Config
module.exports = {
  nav,
  sidebarDepth: 2,
  logo: "/img/logo.svg",
  repo: "969025903/ChopperBot",
  searchMaxSuggestions: 10,
  lastUpdated: "上次更新",

  docsRepo: "/twj666/Chopper-Doc",
  docsDir: "docs",
  docsBranch: "master",
  editLinks: true,
  editLinkText: "帮助我们改善此页面！",

  // Vdoing Theme Config
  sidebar: { mode: "structuring", collapsable: false },

  updateBar: {
    showToArticle: false
  },

  category: false,
  tag: false,
  archive: true,

  author: {
    name: "Genius",
    href: "https://github.com/969025903"
  },

  social: {
    icons: [
      {
        iconClass: "icon-github",
        title: "GitHub",
        link: "https://github.com/969025903"
      },
      {
        iconClass: "icon-gitee",
        title: "Gitee",
        link: "https://gitee.com/sbg-genius"
      },
      {
        iconClass: "icon-youjian",
        title: "发邮件",
        link: "mailto:geniusssbg@gmail.com"
      }
    ]
  },

  footer: {
    createYear: 2016,
    copyrightInfo: [
      '<a href="https://969025903.github.io/" target="_blank" style="font-weight:bold">TimeMachine Lab</a>',
      ' | ',
      'Sponsored by <a href="https://www.jetbrains.com" target="_blank" style="font-weight:bold">JetBrains</a>',
      ' | ',
      '<a href="http://beian.miit.gov.cn/" target=_blank>渝ICP备2021000141号-1</a>',
      '<p>友情链接：',
      '<a href="https://969025903.github.io/" target="_blank" style="font-weight:bold">GeniusBlog</a>',
      '</p>'
    ].join('')
  },

  htmlModules
};
