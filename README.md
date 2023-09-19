
<p align="center">
  <a href="https://github.com/969025903/ChopperBot">
   <img alt="ChopperBot" src="https://github.com/twj666/ChopperBot-Doc/blob/master/img/logo.png?raw=true">
  </a>
</p>

<p align="center">
  <strong>A multifunctional, intelligent, personalized, scalable, easy to build, and fully automated multi platform intelligent live video editing and publishing robot</strong>
</p>

<audio id="audio" controls="" preload="none">
      <source id="mp3" src="音频地址">
</audio>

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
  <b>English</b> | <a href="https://github.com/969025903/ChopperBot/blob/master/README.zh-CN.md">简体中文</a> 
</p>



# 📖 What is ChopperBot


>A multifunctional, intelligent, personalized, scalable, easy to build, fully automated multi platform intelligent live video editing and publishing AI. Discover the hottest live streaming on various platforms, automatically slice the most interesting clips, do not edit or copy, automatically generate and publish, and automatically create slicing video accounts for each platform. AI is composed of multiple modules, each containing multiple functional plugins, supporting plugin DIY development and plugin hot swapping functionality.


# 📚 Feature

- **Cross Platform**：At present, it supports Douyu, huya, station b, Tiktok, twitter and other popular live broadcast platforms.
- **Everything Plugin**: A variety of plugins are easy to manage, support hot swapping, freely expand, customize, and create your own slice bot。
- **Hottest Live**：ChopperBot automatically analyzes current popular elements, popular anchors, and keeps up with current hotspots to obtain the latest and hottest live content。
- **Full-Automatic**：Live crawling, automatic slicing, cover generation, title selection, content uploading, account management, computer not touching, ChopperBot fully automatic。
- **Personalized**：Whether it's funny content or exciting operations, ChopperBot creates different personalized content generation for each account。
- **Quick Build**：No need to install any software, just run with one click to build your own live video editing platform。
- **Visualization**: Provide a visual management interface to make your user experience more convenient。

# ⚙ Architecture
![image](https://github.com/969025903/ChopperBot/assets/77137063/eeca58de-611b-41ee-973f-cd375f98e569)


# 🕹 ChopperBot Module
| Module | Introduction |
| :-: | :-: |
| ChopperBot| `ChopperBot System`, yes, the entire system itself is also a module, and many modules are launched as plugins for the ChopperBot. Of course, the ChopperBot also includes some system thread pools, tools, and other plugins. We will learn more about them later. |
| File |The file module, ChopperBot, takes into account that everyone can easily build ChopperBot without using tools such as databases. Instead, local JSON files are used. In order to efficiently read and write files, the file module provides multiple tools and file cache pools for efficient reading and writing|
| Creeper | `Sptile module` is responsible for crawling popular data, live streaming information, and more from various platforms. In addition to crawling data, it also assists in account login and video publishing |
| Hot | `Popular module`, responsible for monitoring the popular modules of each platform, popular live streaming, and sending recommendations and crawling tasks for popular live streaming based on user configuration files. It will also automatically crawl based on the anchors that users follow |
| LiveVideo | `Live video module` mainly stores, records, manages, and performs basic video operations on crawled live videos |
| BarrageAnalysis | `Barrage analysis module` scores all barrages and selects high scoring intervals as slicing references. At the same time, it also classifies the high scoring intervals of barrages into text (games, jokes, debates) and labels videos |
| VideoSection | `Video slicing module`, slicing and classifying existing live videos |
| Process | `Slice processing module`, which uses AI algorithm to obtain video titles, video covers, video introductions, and video editing creation functions for existing slices |
| Account | `Account module` is responsible for managing the accounts of various video platforms, creating personalized tags for accounts (such as LOL funny, Apex operation), filtering slices, and inputting them to the account for publishing|
| DIY | `Customized module`, users can refer to the development guide to develop their own plugins to help them better use ChopperBot |

# 📈 Project Activity
![Alt](https://repobeats.axiom.co/api/embed/0ae23655bb105addf8d90a999df36f690d615af7.svg "Repobeats analytics image")

# 🔗 Links
👉 [Document](https://969025903.github.io/ChopperBot-Doc/)

👉 [Developer's Guide](https://969025903.github.io/ChopperBot-Doc/pages/779a67/#chopperbot%E7%B3%BB%E7%BB%9F%E6%9E%B6%E6%9E%84)

👉 [CHANGE LOG](https://github.com/969025903/ChopperBot/blob/master/CHANGELOG.md)
