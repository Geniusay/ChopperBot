package org.example.service;

import org.example.bean.Plugin;

import java.util.List;

public interface PluginService {

    List<Plugin> getPlugins(String moduleName);
}
