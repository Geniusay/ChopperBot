/** 插入自定义html模块 (可用于插入广告模块等)
 * {
 *   homeSidebarB: htmlString, 首页侧边栏底部
 *
 *   sidebarT: htmlString, 全局左侧边栏顶部
 *   sidebarB: htmlString, 全局左侧边栏底部
 *
 *   pageT: htmlString, 全局页面顶部
 *   pageB: htmlString, 全局页面底部
 *   pageTshowMode: string, 页面顶部-显示方式：未配置默认全局；'article' => 仅文章页①； 'custom' => 仅自定义页①
 *   pageBshowMode: string, 页面底部-显示方式：未配置默认全局；'article' => 仅文章页①； 'custom' => 仅自定义页①
 *
 *   windowLB: htmlString, 全局左下角②
 *   windowRB: htmlString, 全局右下角②
 * }
 *
 * ①注：在.md文件front matter配置`article: false`的页面是自定义页，未配置的默认是文章页（首页除外）。
 * ②注：windowLB 和 windowRB：1.展示区块宽高最大是200*200px。2.请给自定义元素定一个不超过200px的固定宽高。3.在屏宽小于960px时无论如何都不会显示。
 */

// Adsense
// module.exports = {
//   sidebarB: `<!-- 正方形 -->
//       <ins class="adsbygoogle"
//           style="display:block"
//           data-ad-client="ca-pub-4147143076931995"
//           data-ad-slot="1866407052"
//           data-ad-format="auto"
//           data-full-width-responsive="true"></ins>
//       <script>
//           (adsbygoogle = window.adsbygoogle || []).push({});
//       </script>`,
//   pageB: `<!-- 横向自适应 -->
//       <ins class="adsbygoogle"
//           style="display:block"
//           data-ad-client="ca-pub-4147143076931995"
//           data-ad-slot="1866407052"
//           data-ad-format="auto"
//           data-full-width-responsive="true"></ins>
//       <script>
//           (adsbygoogle = window.adsbygoogle || []).push({});
//       </script>`,
//   windowRB: `<!-- 固定160*160px -->
//       <ins class="adsbygoogle"
//           style="display:inline-block;width:160px;height:160px"
//           data-ad-client="ca-pub-4147143076931995"
//           data-ad-slot="1866407052"></ins>
//       <script>
//           (adsbygoogle = window.adsbygoogle || []).push({});
//       </script>
//       `
// };

// 万维
// module.exports = {
//   sidebarT: `
//
//   `,
//   pageT: `
//     <div class="wwads-cn wwads-horizontal page-wwads" data-id="135"></div>
//     <style>
//       .page-wwads{
//         width:100%!important;
//         min-height: 0;
//         margin: 0;
//       }
//       .page-wwads .wwads-img img{
//         width:80px!important;
//       }
//       .page-wwads .wwads-poweredby{
//         width: 40px;
//         position: absolute;
//         right: 25px;
//         bottom: 3px;
//       }
//       .wwads-content .wwads-text, .page-wwads .wwads-text{
//         height: 100%;
//         padding-top: 5px;
//         display: block;
//       }
//     </style>
//   `,
//   windowRB: `
//     <ins class="adsbygoogle"
//     style="display:block"
//     data-ad-client="ca-pub-4147143076931995"
//     data-ad-slot="1866407052"
//     data-ad-format="auto"
//     data-full-width-responsive="true"></ins>
//     <script>
//         (adsbygoogle = window.adsbygoogle || []).push({});
//     </script>
//   `
// };
