package org.example.core.factory.videoPushFactory;

/**
 * @Description
 * @Author welsir
 * @Date 2024/5/17 12:14
 */
public abstract class StrategyFactory implements VideoPushStrategy {

    public static StrategyFactory selectStrategy(int x){
        switch (x) {
            case 0:
                return new DefaultVideoPushStrategy();
            default:
                throw new IllegalArgumentException("违法的策略选项!");
        }
    }
}
