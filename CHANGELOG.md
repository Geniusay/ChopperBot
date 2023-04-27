# 更新日志

```markdown
   格式：
    ## [版本号] - 日期
    ### 模板名称 (可选 console-ui, console, FileModule, common, ...)
    - 🎈新增: {模块名称} {功能介绍}
    - 🐞Bug: #{issue号} {bug描述}
    - ⛏修复: #{issue号} {修复描述}
    - 📝文档: {文件名} 添加注释
    - 🚀性能: {类} {方法} {描述}
    - 🎨样式: 
    - 🧹重构:
    - 🧪测试: {类|方法} {测试结果}
    - 🛑更名: {旧名} ➡ {新名}
    - ❌移除: {模块|方法}

    ------

```
------
# 目录
* [V 1.0.5]()
* [V 1.0.4]()
* [V 1.0.3]()
* [V 1.0.2]()
* [V 1.0.1]()
* [V 1.0.0]()

------
## [V 1.0.5] - 2023.4.28
### SectionModule
- 🎈新增: 新增 `VideoUtil` ,操作视频文件的类,目前可以进行视频剪辑,视频格式转换,视频封面截取
- 🧪测试: 测试 `VideoUtil` 剪辑，格式转换，视频封面截取，目前都可以正常使用，格式转换m3u8转mp4目前会出现片段丢失的情况
- 🐞Bug: **#00002** 格式转换m3u8转mp4目前会出现片段丢失的情况

### common
- 🎈新增: `ConstPool` 新增 `PIC_TYPE`数组,用于存储图片类型常量例如 `jpg,jepg,png`
------
## [V 1.0.4] - 2023.4.26
### FileModule
- 🎈新增: 新增 `FileType`枚举类 用于`ConfigFile`的配置文件分类
- 🎈新增: 新增 `ConfigVO`Config文件的前端渲染类
- 🎈新增: 新增 `FileService,FileServiceImpl` 文件服务接口,目前拥有获取文件模块与获取配置文件
- 🎈新增: 新增 `FileCacheManagerInit` 用于启动初始化FileCacheManager

### console
- 🎈新增: 新增 `FileController` 文件接口
- 🎈新增: `InitMachines`中新增了`FileCacheManagerInit`
### console-ui
- 🎈新增: 新增文件管理页面

------
## [V 1.0.3] - 2023.4.25
### CreeperModule
- 🎈新增: 新增 `LoadTaskManager` 任务管理器(类),对用户开放的顶层api,用户需要的所有操作都通过此管理器
- 🎈新增: 新增 `TaskFactory` 任务工厂(类),通过LoadConfig来创建一个弹幕爬取任务
- 🎈新增: 新增 `ProcessorFactory` 处理器工厂(类),通过LoadConfig来创建一个针对与直播平台和种类的处理器
- 🎈新增: 新增 `PipelineWriteJson` 数据处理(类),对处理器传过来的数据进行善后
- 🎈新增: 新增 `Barrage` 弹幕基类(类),所有直播平台的通用弹幕格式
- 🎈新增: 新增 `LoadConfig` 任务配置(类),保存一个任务的基本信息
- 🎈新增: 新增 `PachongConfig` 配置文件读取工具类(类),读取配置信息
- 🎈新增: 新增 `LoadTask_R_Douyu` 斗鱼录播下载任务(类)
- 🎈新增: 新增 `Process_R_Douyu` 斗鱼录播处理器(类)
- 🎈新增: 新增 `AbstractProcessor` 处理器抽象类(类)
- 🎈新增: 新增 `ConstPool` BARRAGE_ROOT常量
- 🎈新增: 新增 `BarrageSaveFile` 弹幕存储文件，负责存储当天直播弹幕
- 🧹重构: 重构 `PipelineWriteJson` 弹幕缓存 与 弹幕文件缓存建立联系
  HelloWorld:
  <img src=https://twj666.oss-cn-hangzhou.aliyuncs.com/img1/QQ%E6%88%AA%E5%9B%BE20230425201236.png style="zoom:40%;">

### FileModule
- 🧪测试: 测试 `FileCache` 方法 `get,writekeys,append`,功能正常，可以使用
- ❌移除: 移除 `FileCache-oldJsonFile变量`,不在用map来进行版本更替判断，取而代之的是判断写入字节是否为0的高效率方法
- 🎈新增: 新增 `FileCache` 方法 `get,writekeys,append` 更加方便的缓存获取，更加方便的写入与内容追加
- 🎈新增: 新增 `FileCacheManagerInstance` 将整个FileCacheManager转变为全局单例，防止重复使用调用
- 🎈新增: 新增 `GlobalFileCache` 全局文件缓存，也负责为`FileCacheManagerInstance`提供初始化的文件缓存队列
- 🎈新增: 新增 `FileCacheManager` 新增方法 `addFileCache` 负责在之后动态的添加新的文件缓存
- 🐞Bug: **#00001** `FileCache append()` 在进行数组追加时产生溢出
- ⛏修复: 修复 **#00001**, 在进行数组追加时不会再溢出



------

## [V 1.0.2] - 2023.4.24
### common
- 🎈新增: 新增 `ConfigFile` 方法 `onlyUpdateTime,updateConfigTime` 负责更新外部数据上传时间和配置文件本身的上传时间
- 🎈新增: 新增 `TimeUtil` 工具类，用于获取LocalDateTime的秒数
- 🎈新增: 新增 `FileCacheException` 异常类，用于处理文件池异常

### FileModule
- 🎈新增: 新增 `FileCache` 文件缓冲池类，负责缓存文件内容，文件的读取，修改，追加，能够根据刷入时间或者写入字节，来进行自动刷盘操作
- 🎈新增: 新增 `FileCacheManager` 文件缓冲池管理类，管理所有文件缓存池，轮询查看每个文件是否需要自动刷入，目前包含巡逻线程与刷入线程

    <img src="https://mynoteimages.oss-cn-hangzhou.aliyuncs.com/20230424204415054.png"  style="zoom:40%;"/>
------

## [V 1.0.1] - 2023.4.21
### common
- 🎈新增: 新增 `ConstPool` 常量池，用于存放常量，目前存放了模块名称常量，便于开发统一
- 🎈新增: 新增 `ConfigFile` 配置文件类，用于存放配置文件路径, 包装配置文件,目前配置文件主要内容为data,新增更新时间

### FileModule
- 🛑更名：`ModuleConfigSrcInit` ➡ `ModuleSrcConfigInit`
- 🛑更名：`ModuleConfigSrc` ➡ `ModuleSrcConfig`
- 🧹重构: 重构 `ModuleSrcConfig` 现在作为某块路径的配置文件类，负责管理模块的配置文件路径
- ❌移除: 移除 `ModuleSrcConfigInit` 模块配置文件路径管理功能，只负责**初始化**

------

## [V 1.0.0] - 2023.4.20
### console-ui
- 🧹重构: 重构console-ui，使用vue3.0,vite,typescript进行重构

### FileModule
- 🎈新增: 新增 `JsonFileUtil` 工具类，用于进行json文件的读写操作
- 🎈新增: 新增 `FileUtil` 工具类，用于进行文件复制文件删除等操作
- 🎈新增: 新增 `FileCondition` 方法，用于对文件递归删除进行条件过滤
- 🎈新增: 新增 `ModuleConfigSrcInit` 初始化模块，用于初始化各个模块的配置文件夹，以及模块配置文件路径管理
- 🎈新增: 新增 `ModuleConfigSrc` 存放文件src路径
- 🧪测试: 测试 `FileUtil` 工具类, 测试 `JsonFileUtil` 工具类

### console
- 🎈新增: 新增 `InitWord` 用于整个项目的初始化启动

### 🎈新增 common 模块
- 🎈新增: 新增 `InitMachine` 初始化机器接口，为所有模块初始化类提供统一接口

------
