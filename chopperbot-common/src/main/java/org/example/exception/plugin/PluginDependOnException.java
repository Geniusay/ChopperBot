package org.example.exception.plugin;

import lombok.Data;
import org.example.exception.Impl.ResultCode;

/**
 * @author Genius
 * @date 2023/08/02 18:54
 **/
public class PluginDependOnException extends PluginException{

    private String fatherName;
    private String sonName;
    public PluginDependOnException(String msg,String fatherName,String sonName) {
        super(msg);
        this.fatherName = fatherName;
        this.sonName = sonName;
    }

    public static PluginDependOnException MissingFatherPlugin(String fatherName, String sonName){
        PluginDependOnException pluginDependOnException = new PluginDependOnException(
                String.format("Error! [%s] plugin missing [%s] plugin ! Please Check~",sonName,fatherName),fatherName,sonName);
        ResultCode code = ResultCode.PLUGIN_DEPEND_ERROR;
        code.setMsg(pluginDependOnException.msg);
        pluginDependOnException.setResultCode(code);
        return pluginDependOnException;
    }

    public static PluginDependOnException DependOnPlugin(String fatherName,String sonName){
        PluginDependOnException pluginDependOnException = new PluginDependOnException(
                String.format("Error! [%s] plugin depend on [%s] plugin !",sonName,fatherName),fatherName,sonName);
        ResultCode code = ResultCode.PLUGIN_DEPEND_ERROR;
        code.setMsg(pluginDependOnException.msg);
        pluginDependOnException.setResultCode(code);
        return pluginDependOnException;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getSonName() {
        return sonName;
    }
}
