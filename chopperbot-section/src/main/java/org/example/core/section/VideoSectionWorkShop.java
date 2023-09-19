package org.example.core.section;

import org.apache.coyote.Request;
import org.example.bean.Live;
import org.example.cache.FileCache;
import org.example.constpool.ConstPool;
import org.example.constpool.FileNameBuilder;
import org.example.constpool.GlobalFileCache;
import org.example.plugin.SpringGuardPlugin;
import org.example.util.FileUtil;
import org.example.util.TimeUtil;
import org.example.util.VideoUtil;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Genius
 * @date 2023/09/19 19:35
 **/
@Component
public class VideoSectionWorkShop extends SpringGuardPlugin {

    private BlockingQueue<SectionRequest> requests;

    private String liveRoot;

    @Override
    public boolean init() {
        requests = new LinkedBlockingQueue<>(1000);
        liveRoot = (String) GlobalFileCache.ModuleSrcConfigFile.get("src", ConstPool.LIVE_RECORD);
        return super.init();
    }

    @Override
    public void start() {
        try {
            SectionRequest request = requests.poll(1000, TimeUnit.SECONDS);
            long videoStartTime = TimeUtil.getTimeNaos(request.getDate());
            long start = (timeBias(request.getStartTime())-videoStartTime)/1000;
            start = start<0?0:start;
            long end = (timeBias(request.getEndTime())-videoStartTime)/1000;

            String root = Paths.get(liveRoot,request.getAction(),request.getPlatform()).toString();
            String startTime = VideoUtil.formatTimeToFFMpeg(start);
            String endTime = VideoUtil.formatTimeToFFMpeg(end);
            String videoName = request.getVideoName();
            String[] split = videoName.split("\\.");
            String oldPath = Paths.get(root,videoName).toString();
            String liver = request.getLiver();
            String date = request.getDate();
            String newVideoName = FileNameBuilder.buildVideoFileNameNoSuffix(liver, date)+"_section("+ FileUtil.convertTimeToFile(startTime) +"-"+FileUtil.convertTimeToFile(endTime)+")."+split[1];
            String newPath = Paths.get(root,newVideoName).toString();
            if (VideoUtil.cutVideoByFFMpeg(oldPath,newPath,startTime,endTime)) {
                VideoSection videoSection = new VideoSection(newVideoName,request.getTag(),request.getLiver(),request.getPlatform());
            }
        }catch (InterruptedException e){
            return;
        }catch (Exception e){

        }
    }
    public long timeBias(long time){
        return time+8*1000;
    }
    public SectionRequest wrapperReq(Live live,String suffix,String action,String date,long startTime,long endTime){
        String fileName = FileNameBuilder.buildVideoFileNameNoSuffix(live.getLiver(), date)+suffix;
        return new SectionRequest(fileName, action, startTime, endTime, live.getLiver(), live.getPlatform(),date);
    }

    public boolean request(SectionRequest request) {
        try {
           return requests.offer(request,1000,TimeUnit.SECONDS);
        }catch (Exception e){
            return false;
        }
    }

    public static void main(String[] args) {
        String s1 = "2023-09-20 00:22:45";
        long timeNaos = TimeUtil.getTimeNaos(s1);
        String s = VideoUtil.formatTimeToFFMpeg((1695140913000L-timeNaos)/1000);
        System.out.println(s);
    }
}
