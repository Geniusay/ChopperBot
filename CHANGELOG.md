# 更新日志

```markdown
   格式：
    ## [版本号] - 日期
    ### 模块名称
    - 🎈新增
    - 🐞修复
    - 📝文档
    - 🚀性能
    - 🎨样式
    - 🧹重构
    - 🧪测试
    - 🚧工作中(勿动)
    ......
```
## [V 1.0.0] - 2023.4.20
### console-ui
- 🧹重构: 重构console-ui，使用vue3.0,vite,typescript进行重构

### FileModule
- 🎈新增: 新增 `JsonFileUtil` 工具类，用于进行json文件的读写操作
- 🎈新增: 新增 `FileUtil` 工具类，用于进行文件复制文件删除等操作
- 🎈新增: 新增 `FileCondition` 方法，用于对文件递归删除进行条件过滤
- 🧪测试: 测试 `FileUtil` 工具类, 测试 `JsonFileUtil` 工具类


