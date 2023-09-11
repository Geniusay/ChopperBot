package org.example.service;

import org.example.api.PluginApi;
import org.example.bean.Plugin;

import java.util.List;

public interface PluginService {

    List<Plugin> getPlugins(String moduleName);

    PluginApi pluginApi();
}
