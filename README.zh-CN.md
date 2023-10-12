
<p align="center">
  <a href="https://github.com/969025903/ChopperBot">
   <img alt="ChopperBot" src="https://github.com/twj666/ChopperBot-Doc/blob/master/img/logo.png?raw=true">
  </a>
</p>

<p align="center">
  <strong>多功能，智能化，个性化，可扩展，易搭建，全自动的多平台智能直播视频剪辑发布机器人</strong>
</p>

<p align="center">
  <a href="https://969025903.github.io/ChopperBot-Doc/">
    <img src="https://img.shields.io/badge/文档-简体中文-blue.svg" alt="简体中文文档" />
  </a>

   <a href="https://github.com/969025903/ChopperBot/blob/master/CHANGELOG.md" >
    <img src="https://img.shields.io/badge/ChangeLog-English-blue.svg" alt="Update Log" />
  </a>

   <a target="_blank" href="https://www.oracle.com/technetwork/java/javase/downloads/index.html">
        <img src="https://img.shields.io/badge/JDK-11+-green.svg" />
    </a>
  <a target="_blank" href='https://github.com/969025903/ChopperBot'>
        <img src="https://img.shields.io/github/stars/969025903/ChopperBot.svg" alt="github stars"/>
   </a>

   <a target="_blank" href=''>
        <img src="https://img.shields.io/badge/Process-Developing-yellow" alt="github stars"/>
   </a>
</p>

<p align='center'>
  <b>简体中文</b> | <a href="https://github.com/969025903/ChopperBot/blob/master/README.md">English</a> 
</p>



# 📖 什么是ChopperBot


>一款多功能，智能化，个性化，可扩展，易搭建，全自动的多平台智能直播视频剪辑发布AI。发现各个平台最热直播，自动切片最有趣的片段，不要剪辑，不用文案，自动生成，自动发布，自动打造各个平台的切片视频账号。AI由多个模块组成，每个模块中包含着多种功能的插件，支持插件DIY开发以及插件热插拔功能。


# 📚 特点

- **跨平台服务**：目前支持Douyu，huya，b站，抖音，twitch等热门直播平台。
- **万物皆插件**: 多种多样的插件，便于管理，支持热插拔，随意扩展，自由定制，打造你的专属切片Bot。
- **热门分析**：ChopperBot为您自动分析时下流行元素，热门爆火主播，紧跟当前热点，获取最新最热直播内容。
- **全自动工作**：直播爬取，自动切片，封面生成，标题选取，内容上传，账号管理，电脑不用动，ChopperBot全自动。
- **个性化账号打造**：不管是搞笑内容，还是精彩操作，ChopperBot为每个账号打造不同的个性化内容生成。
- **0基础搭建**：不需要安装任何软件，只需一键运行，搭建属于你自己的直播视频剪辑平台。
- **可视化管理**: 提供可视化管理界面，让您的使用体验更方便。

# ⚙ 系统架构
![image](https://github.com/969025903/ChopperBot/assets/77137063/eeca58de-611b-41ee-973f-cd375f98e569)

# 🎥 项目预览
## 插件中心
![e6cb59bdbc2db690610d7dec956aed4](https://github.com/969025903/ChopperBot/assets/77137063/ac83fbae-d720-4b98-8701-687436605e97)
## 爬虫任务中心
![Task Center](https://github.com/969025903/ChopperBot/assets/77137063/62ab3560-c5c2-4808-be84-a765349e4c39)
![Task Monitor](https://github.com/969025903/ChopperBot/assets/77137063/95843e98-fdc3-4ff4-805a-4a0dd8ef28be)
## 爬虫仓库
![02754f052b22234db1081dec379e8fa](https://github.com/969025903/ChopperBot/assets/77137063/5e12b279-ff34-450f-a408-c4b1f2d797d9)
## 平台热门监控
![9f9a0e3127245ae70e5375c1bf90509](https://github.com/969025903/ChopperBot/assets/77137063/6d8c0f1e-a02f-4d04-a047-f03888358790)
## 热门直播推荐
![431e8b1880825e528b07f9c11360577](https://github.com/969025903/ChopperBot/assets/77137063/adeab4ae-25fe-4752-a6ce-2a79a6f5703e)
## 多平台热门直播
![T4)XK652SFQ1FH7BF53~1 J](https://github.com/969025903/ChopperBot/assets/77137063/6fe79608-6724-4768-8b39-3f4b9b2483c2)
**更多页面正在开发中....**

# 🕹 ChopperBot模块介绍
| 模块名 | 模块介绍 |
| :-: | :-: |
| ChopperBot| ChopperBot系统·，没错整个系统本身也是一个模块，而众多模块则作为ChopperBot的插件进行启动，当然ChopperBot也包含了一些系统线程池，工具等等插件，在之后会详细了解到 |
| File |文件模块， ChopperBot考虑到人人都能轻松搭建ChopperBot，没有使用数据库等工具，取而代之的是本地json文件，为了能流程高效的进行文件读写，文件模块提供了多种工具以及文件缓存池来进行高效读写|
| Creeper | 爬虫模块，负责爬取各个平台的热门数据，直播信息等等，除了爬取数据也会协助进行账号登录和视频发布 |
| Hot | 热门模块，负责监控每个平台的热门模块，热门直播，根据用户配置文件来进行热门直播的推荐和爬取任务发送。也会根据用户关注的主播来进行自动爬取 |
| LiveVideo | 直播视频模块，主要对爬取的直播视频进行存储，记录，管理，并进行一些视频的基础操作 |
| BarrageAnalysis | 弹幕分析模块，为所有弹幕进行打分并筛选出弹幕高分区间作为切片参考，同时也会对弹幕高分区间进行文本分类（游戏，搞笑，争论），为视频打上标签 |
| VideoSection | 视频切片模块，对已有的直播视频进行切片，并做好分类 |
| Process | 切片加工模块，对已有的切片使用AI算法获取视频标题，视频封面，视频简介以及视频剪辑创作等功能 |
| Account | 账号模块，负责对各个视频平台的账号进行管理，打造账号的人设标签(例如:LOL搞笑，Apex操作)，来筛选切片并输入给账号进行发布|
| DIY | 客制化模块，用户可以参考开发指南来自行开发一些插件帮助自己更好的使用ChopperBot |

# 📈 项目动态
![Alt](https://repobeats.axiom.co/api/embed/0ae23655bb105addf8d90a999df36f690d615af7.svg "Repobeats analytics image")
# 🔗 相关链接
👉 [项目文档](https://969025903.github.io/ChopperBot-Doc/)

👉 [项目开发指南](https://969025903.github.io/ChopperBot-Doc/pages/779a67/#chopperbot%E7%B3%BB%E7%BB%9F%E6%9E%B6%E6%9E%84)

👉 [更新日志](https://github.com/969025903/ChopperBot/blob/master/CHANGELOG.md)
