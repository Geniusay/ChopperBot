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
    - 🚧施工
    - 💪增强: {类}
    

    ------

```
------
# 目录
* [V 1.0.11]()
* [V 1.0.10]()
* [V 1.0.9]()
* [V 1.0.8]()
* [V 1.0.7]()
* [V 1.0.6]()
* [V 1.0.5]()
* [V 1.0.4]()
* [V 1.0.3]()
* [V 1.0.2]()
* [V 1.0.1]()
* [V 1.0.0]()

------
## [V 1.0.11] - 2023.8.3
**💥GREAT CHANGE：**
- 插件框架完善，新增`Plugin`注解,进行插件扫描，所有模块和插件的初始化都变为非侵入式代码，增加里自动识别插件注册顺序以及插件循环依赖，支持插件热插拔
- 更新三大插件:`CommonPlugin,GuardPlugin,ConfigFile`
- 插件进行细分，分为插件和启动器
- 完善并加强了插件注册表
- 更改了项目的启动顺序，详情请看官方文档中的开发指南
- 完善异常类，添加AOP `CheckPlugin` 在调用api时会检查插件是否注册
### common
- 🧹重构:`CommonInitMacine`重构，包含了插件的信息，以及一些方法进行改写
- 🧹重构:`ModuleInitMachine`重构，init()方法重写
- 💪增强:`InitPluginRegister` 增加了更多插件方法以及插件信息存储map
- 💪增强:`GlobalExecption` 增加了`PluginException`异常类的拦截方法
- 💪增强:`ResultCode` 增加了`Plugin`异常的状态码
- 🎈新增:`Plugin`注解，用于表示启动需要启动的插件信息，并且注入到插件中
- 🎈新增:`PluginUtil`新增插件模块依赖环路检测方法与拓扑启动路径寻找方法
- 🎈新增:`ChopperBotPlugin`系统插件接口，所有插件类需要实现该类
- 🎈新增:`CommonPlugin,GuardPlugin,ConfigFile`分别为普通插件，守卫插件，文件插件
- 🎈新增:`ModuleName`存放模块的名称
- 🎈新增:`PluginException`插件异常类,其子异常类包括`PluginDependOnException`,`PluginNotRegisterException`
- 🎈新增:`CheckPlugin``注解`,用于检测api中使用的插件是否注册
### console
- 🎈新增:`PluginController`插件模块的Controller
- 🎈新增:`Plugin`实体类
------
## [V 1.0.10] - 2023.7.31
- 🐞Bug:**#00005** 删除config文件无法修复
- ⛏修复:**#00005** 将ModuleSrcConfigInitMachine移除插件范围，不作为File模块的插件启动，将ModuleSrcConfigInitMachine提前到WorldInit前启动。详情请看 https://github.com/Geniusay/ChopperBot/issues/5
------
## [V 1.0.9] - 2023.7.30
**💥GREAT CHANGE：**
- 热门监控功能，负责监控并获取平台热度
- 热门信息数据中心，负责缓存当前的热门爬取数据
- 热门模块的后端api实现
- 热门推荐模块，推送当前热门的直播，并生成对应的爬虫任务
- 爬虫模块的任务中心，负责将各个模块发送的爬虫请求分发封装成爬虫任务，并处理，保存，记录，恢复爬虫任务
- 模块化插件框架完善，新增 初始化后执行操作，插件依赖，插件注册表功能
- 新增系统线程池，一些插件的运行交给系统线程池处理，例如：HeatRecommendation,OddJobBoy等

### common
- 💪增强:`InitMachine` 增加了`afterInit`方法，用于项目初始化启动后的一些操作
- 💪增强:`ComponetInitMachine`增加了`checkNeedPlugin`方法，用于检查该插件启动时依赖的插件是否注册
- 🎈新增:`InitPluginRegister`负责注册当前启动的所有插件
- 🎈新增:`ChopperBotGuardPool`该类是系统线程池，负责运行系统的一些伴随系统生命周期运行的插件，例如：OddJobBoy，实现`ChopperBotGuardTask`即可将插件放入池中运行
- 🎈新增:`PluginName`该类是插件名称常量池，存储插件名称

### FileModule
- 🎈新增:`ConfigInitMachine`该类是模块配置文件插件初始化抽象类
- 🧹重构:更改了大部分的模块配置文件初始化类的继承

### CreeperModule
- 🎈新增:`CreeperConfigFile`爬虫模块配置文件
- 🎈新增:`CreeperLogConfigFile`爬虫日志模块配置文件
- 🎈新增:`TaskCenterConfig`任务中心配置类
- 🎈新增:`ReptileRequest`爬虫请求`抽象类`，其他模块需要申请爬虫操作时需要发送的类
- 🎈新增:`ReptileTask`爬虫任务类，包含`LoadTask`方法，包含具体的爬虫任务
- 🎈新增:`TaskCenter`该类`爬虫任务中心插件`,是`ReptileRequest`的接收中心，是`ReptileTask`的处理，执行，记录，保存，恢复中心
- 🎈新增:`TaskHandler`该类负责`ReptileRequest`的分发和包装，根据其内容来包装成对应的`ReptileTask`
- 🎈新增:`BootStrapTaskHandler`该类是最原始的`ReptileRequest`接收和分发中心，负责分发到其他的`TaskHandler`类进行分发包装
- 🎈新增:`LiveTaskHandler`直播请求分发包装中心
- 🎈新增:`CreeperModuleInitMachine,CreeperConfigInitMachine,TaskCenterInitMachine`分别为:爬虫模块，爬虫模块配置文件，任务中心初始化类

### HotModule
- 🎈新增:`Guard` 热度监控类，监控具体平台的具体任务的监控类
- 🎈新增:`HotModuleGuard` `热度监控中心插件`，监控具体平台的具体任务的监控中心
- 🎈新增:`HotModuleGuardInstance` `HotModuleGuard`的单例类
- 🎈新增:`HeatRecommendation` `热门直播推荐插件`，负责热门直播的推荐
- 🎈新增:`HotModuleDataCenter` 热门模块数据中心，缓存热门模块的热门信息
- 🎈新增:`HotModuleConfigInitMachine,HotModuleGuardInitMachine,HeatRecommendationInitMachine`分别为:热门模块配置文件，热度监控中心插件，热门直播推荐插件初始化类

### console
- 🎈新增:`HotController` 热门模块的一些后端功能接口
------
## [V 1.0.8] - 2023.7.26
**💥GREAT CHANGE：**
- 项目模块化启动，以及插件启动框架完成
- 项目启动日志，结束日志
- 热门模块的搭建以及相关热门信息爬取类

### common
- 💪增强:`InitMachine` 增加了一些方法，包含`init,shutdown`，用于模块化启动
- 💪增强:`TimeUtil`新增一些时间方法
- 🎈新增:`ComponentInitMachine,CommonInitMachine,ModuleInitMachine` 1个接口2个类，分别对应组件初始化接口，常规插件初始化类，模块初始化类
- 🎈新增:`ChopperLogFactory` 其作为整个ChopperBot的日志工厂，用于获取模块日志类，提供`LoggerType`枚举变量中的类型即可返回相应的模块日志
- 🎈新增:`ResultLogger` 是一个结果日志`接口`，该接口包含一些成功和失败日志方法
- 🎈新增:`OddJobBoy` 这是一个`单线程任务队列`，负责**处理项目中需要异步执行且不紧急的任务**，对应的任务类是`OddJob`
- 🎈新增:`ClassUtil`对Java对象的一些操作工具
- 🎈新增:`Live,HotModule`等一些子类，这个是直播类，和热门模块类其子类是具体平台的直播和模块类，子类的前缀一般表示直播平台
- 🎈新增:`NamedThreadFactory`用于更改线程池名字
- 🎈新增&🚧施工:`ChineseConvertUtil` 将中文进行一些操作的工具,需要完善


### CreeperModule
- 🎈新增:`HotModuleLoadTask` 这是一个热门模块任务的抽象类,平台热门模块和热门直播的爬取任务需要继承该类
- 🎈新增:`DouyuHotModuleLoadTask,DouyuHotLiveLoadTask` 以及对应的`Processor` 主要负责热门模块和热门直播的爬取
- 🧹重构:`LoadConfig` 该类不再作为一个弹幕日志配置爬取类，而是作为一个爬取任务配置的抽象类，其他的内容爬取配置类需要继承该类
- 🎈新增:`LoadBarrageConfig,LoadHotModuleConfig,LoadLiveConfig` 弹幕任务配置类，热门模块任务配置类，直播配置类
- 💪增强:`LoadFactory`新增`Douyu热门直播，热门模块 LoadTask`

### HotModule
- 🎈新增:`HotModuleApi`，热门模块的爬虫方法api包装实现
- 🎈新增:`HotModuleConfig`,该类作为整个热门模块的配置类,包含了各个平台的热门模块设置，热门监控设置，以及自动推荐功能
- 🎈新增:`HotModuleConfigInitMachine`,热门配置文件的创建初始化类
- 🎈新增:`HotModuleInitMachine`,热门模块初始化类

### FileModule
- 💪增强:`ModuleSrcConfigFileInitMachine,FileCacheManagerInitMachine`实现ComponentInitMachine,其将作为FileModule的插件启动

### console
- 🎈新增:`WorldInitMachine` 整个ChopperBot的项目启动，负责启动所有模块和一些系统类
- 🧹重构:`InitWorld`不再执行原本的职能，其只负责执行`WorldInitMachine`，并判断启动是否成功来决定是否shutdown项目
------
## [V 1.0.7] - 2023.5.19
### 🎈 CreeperModule
- 🛑更名: 弹幕爬取统一移至CreeperModule的danmuku包下,并重命名了大部分类名
- 🎈新增: 直播流下载功能(很多功能待优化),位于CreeperModule的video包下,之后考虑移至新模块LiveRecord下
- 🎈新增: 新增 `FlvHandle` Flv流下载类,一个简易的单线程下载器,新能待优化
- 🎈新增: 新增 `StatusMonitor` 流下载监控类,可以监控下载过程中的下载速度,下载状态的基本下载信息
- 🎈新增: 新增 `BilibiliFlvUrlParse` B站Flv流首地址解析器,通过b站主播房间号来解析出一场直播的首地址,可以选择画质
- 🎈新增: 新增 `HttpClientPool` HttpClient请求的链接池
- 🎈新增: 新增 `HttpClientUtil` 简单的get,post请求工具类,可以携带请求头等参数

### 🎈 doc 
- ChopperBot技术文档

### 🎈 LiveRecordModule
- 录播模块
------

## [V 1.0.6] - 2023.5.6
### FileModule
- 🚧施工: 所有的`OSS`方法类，正在施工，不建议使用
- 🐞Bug: **#00004** `FileCache sync方法` 出现ConcurrentModifiedException
- ⛏修复: 修复 **#00004**, 文件缓存的map地址虽然改变，但是Object没改变，导致更改文件为公共的，产生并发错误，更改push的Map是全新的不会有公用数据
### CreeperModule
- 🐞Bug: **#00003** `BarrageSaveFile` 文件初始化失败
- ⛏修复: 修复 **#00003**, 应该创建主播文件夹但是创建的是文件，将主播文件创建修改成主播文件夹创建
- ❌移除: `BarrageSaveFile`的文件缓存不再加入`FileCacheManager`，因为弹幕文件几乎不会再进行修改，所以不让弹幕文件缓存占用轮询队列

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
