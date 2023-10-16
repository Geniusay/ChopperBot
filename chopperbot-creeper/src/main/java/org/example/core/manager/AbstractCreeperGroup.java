package org.example.core.manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.bean.Barrage;
import org.example.core.loadconfig.LoadConfig;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Genius
 * @date 2023/09/07 14:54
 **/
@Component
public class AbstractCreeperGroup implements Serializable  {

    private final Set<CreeperMember> creeperMember;

    public AbstractCreeperGroup() {
        this.creeperMember = new TreeSet<>();
    }

    public void addMember(CreeperMember member){
        creeperMember.add(member);
    }

    public Set<CreeperMember> getMembers(){
        return creeperMember;
    }

    public Class<? extends LoadConfig> getLoadConfig(String configName){
        for (CreeperMember member : creeperMember) {
            if (!member.isDiscard()&&member.name.equals(configName)) {
                return member.clazz;
            }
        }
        return null;
    }

    public Class<? extends LoadConfig> getFirstConfig(){
        for (CreeperMember member : creeperMember) {
            if (!member.isDiscard()) {
                return member.clazz;
            }
        }
        return null;
    }

    public String getPlatform(){
        return null;
    }

    public String getFunctionName(){
        return null;
    }

    public String getGroupName(){
        return getPlatform()+"_"+getFunctionName();
    }

    @Data
    @AllArgsConstructor
    static
    class CreeperMember implements Serializable,Comparable<CreeperMember>{
        protected int priority;
        protected Class<? extends LoadConfig> clazz;
        protected boolean discard;
        protected String name;

        @Override
        public int compareTo(CreeperMember m) {
            if(this.name.equals(m.name)){
                return 0;
            }else{
                int val = m.priority - this.priority;
                if(val==0){
                    return m.name.compareTo(this.name);
                }
                return val;
            }
        }
    }

}
