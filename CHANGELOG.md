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

## [V 1.0.2] - 2023.4.24
### common
- 🎈新增: 新增 `ConfigFile` 方法 `onlyUpdateTime,updateConfigTime` 负责更新外部数据上传时间和配置文件本身的上传时间
- 🎈新增: 新增 `TimeUtil` 工具类，用于获取LocalDateTime的秒数
- 🎈新增: 新增 `FileCacheException` 异常类，用于处理文件池异常

### FileModule
- 🎈新增: 新增 `FileCache` 文件缓冲池类，负责缓存文件内容，文件的读取，修改，追加，能够根据刷入时间或者写入字节，来进行自动刷盘操作
- 🎈新增: 新增 `FileCacheManager` 文件缓冲池管理类，管理所有文件缓存池，轮询查看每个文件是否需要自动刷入，目前包含巡逻线程与刷入线程

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
