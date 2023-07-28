package org.example.core.control;

/**
 * 弹幕下载任务
 * @author 燧枫
 * @date 2023/4/23 18:14
*/
public interface LoadTask<T> {

    // 开始爬虫
    T start();

    // 结束爬虫
    void end();

    // 查看爬虫运行状态
    boolean isRunning();

    // 获取缓存中弹幕条数
    int getCacheSize();

    // 将缓存保存至别处并且清空缓存
    // 返回保存成功条数
    int flushCacheAndSave(String key);
}
