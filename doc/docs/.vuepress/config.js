const head = require('./config/head.js');
const plugins = require('./config/plugins.js');
const themeConfig = require('./config/themeConfig.js');

module.exports = {
  theme: "vdoing",
  title: "ChopperBot",
  description: 'ChopperBot 官方文档',
  base: "/ChopperBot-Doc/",
  markdown: {
    lineNumbers: true
  },
  head,
  themeConfig,
  plugins
}
