package org.example.core.factory.videoPushFactory;

/**
 * @Description
 * @Author welsir
 * @Date 2024/5/17 12:14
 */
public abstract class StrategyFactory implements VideoStrategy{

    public static StrategyFactory connect(int x){
        switch (x) {
            case 0:
                return new DefaultVideoPushStrategy();
            default:
                throw new IllegalArgumentException("违法的策略选项!");
        }
    }
}
