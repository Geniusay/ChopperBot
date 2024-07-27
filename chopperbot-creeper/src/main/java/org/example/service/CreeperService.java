package org.example.service;

import org.example.api.CreeperManagerApi;
import org.example.api.MonitorCenterApi;
import org.example.api.TaskCenterApi;

public interface CreeperService {

    CreeperManagerApi creeperManagerApi();

    TaskCenterApi taskCenterApi();

    MonitorCenterApi monitorApi();
}
