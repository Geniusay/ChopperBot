package org.example.core.section;

import org.example.bean.Live;
import org.example.bean.section.VideoSection;
import org.example.constpool.ConstPool;
import org.example.constpool.FileNameBuilder;
import org.example.constpool.GlobalFileCache;
import org.example.plugin.SpringGuardPlugin;
import org.example.sql.annotation.SQLInit;
import org.example.util.ExceptionUtil;
import org.example.util.FileUtil;
import org.example.util.TimeUtil;
import org.example.util.VideoUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.file.Paths;
import java.util.List;
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

    @Resource
    SectionParking sectionParking;

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
            VideoSection videoSection = generateSection(request);
            sectionParking.parking(videoSection);
        }catch (InterruptedException e){
            return;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private VideoSection generateSection(SectionRequest request){
        try {
            if(request!=null){
                long videoStartTime = TimeUtil.getTimeNaos(request.getDate());
                long start = (timeBias(request.getStartTime())-videoStartTime)/1000;
                start = start<0?0:start;
                long end = (timeBias(request.getEndTime())-videoStartTime)/1000;
                List<String> barrages = request.getBarrages();
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
                    this.info("切片生成", String.format("产生切片文件%s 主播:%s", newVideoName,liver),true);
                   return new VideoSection(newVideoName,request.getTag(),request.getLiver(),request.getPlatform());
                }
            }
        }catch (Exception e){
            this.error("切片生成失败", String.format("切片生成失败，原因:%s", ExceptionUtil.getCause(e)),true);
        }
        return null;
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

    @Override
    @SQLInit(table = "section_parking",tableSQL = "CREATE TABLE \"section_parking\" (\n" +
            "\t\"id\"\tINTEGER NOT NULL,\n" +
            "\t\"video_name\"\tTEXT NOT NULL,\n" +
            "\t\"liver\"\tTEXT NOT NULL,\n" +
            "\t\"barrage_file\"\tTEXT NOT NULL,\n" +
            "\t\"tag\"\tTEXT NOT NULL,\n" +
            "\t\"date\"\tTEXT NOT NULL,\n" +
            "\t\"platform\"\tTEXT,\n" +
            "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
            ");")
    public List<?> sqlInit() {
        return null;
    }
}
