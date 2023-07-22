package org.example.guard;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Genius
 * @date 2023/07/21 11:39
 **/
public class HotModuleGuardInstance {

    private static List<Guard> guardList = new ArrayList<>();

    private static int guardNum;
    private static volatile HotModuleGuard Instance;

    public static HotModuleGuard getInstance(){
        if(Instance==null){
            synchronized (HotModuleGuardInstance.class){
                if(Instance==null){
                    Instance = new HotModuleGuard(guardList,guardNum);
                }
            }
        }
        return Instance;
    }

    public static void InitInstance(List<Guard> guards,int num){
        guardList = guards;
        guardNum = num;
    }

}
