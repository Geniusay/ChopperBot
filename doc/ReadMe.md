关于技术文档的更新
1. 请用idea打开ChopperBot中的doc文件夹
2. ![image](https://mynoteimages.oss-cn-hangzhou.aliyuncs.com/20230519015418105.png)
3. 修改docs中对应模块内容
4. 上线项目
- 如果github中已经有页面分支了请删除页面分支 `pages`
- 在当前的项目 doc/文件夹下进行 `yarn build`
```cmd

# 导航到构建输出目录
cd docs/.vuepress/dist

git init
git add -A
git commit -m 'deploy'

# 推到你仓库的的 gh-page 分支
# 将 <USERNAME>/<REPO> 替换为你的信息
git push -f git@github.com:Geniusay/ChopperBot-Doc.git master:gh-pages

```
