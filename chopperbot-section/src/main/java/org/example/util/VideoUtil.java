package org.example.util;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;
import org.bytedeco.ffmpeg.avcodec.AVCodecParameters;
import org.bytedeco.ffmpeg.avformat.AVFormatContext;
import org.bytedeco.ffmpeg.avformat.AVStream;
import org.bytedeco.ffmpeg.avutil.AVRational;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacv.*;
import org.bytedeco.javacv.Frame;
import org.example.constpool.ConstPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.*;
import java.util.List;

/**
 * @author Genius
 * @date 2023/04/27 16:01
 **/
public class VideoUtil {

    private static final Logger logger = LoggerFactory.getLogger(VideoUtil.class);

    private static final String FFMPEG_PATH = "./ffmpeg.exe";

    /**
     * 获取视频采集器
     * @param sourcePath 视频地址
     * @return FFmpegFrameGrabber
     */
    private static FFmpegFrameGrabber getGrabber(String sourcePath){
        if(Objects.isNull(sourcePath)){
            return null;
        }
        return new FFmpegFrameGrabber(sourcePath);
    }

    /**
     * 获取视频录制器
     * @param targetPath 目标地址
     * @return  FFmpegFrameRecorder
     */
    private static FFmpegFrameRecorder getRecorder(String targetPath,FFmpegFrameGrabber grabber){
        if(Objects.isNull(targetPath)){
            return null;
        }
        return new FFmpegFrameRecorder(targetPath,grabber.getImageWidth(),grabber.getImageHeight(),grabber.getAudioChannels());
    }

    /**
     * 获取视频录制器
     * @param targetPath 目标地址
     * @param width 宽度
     * @param height    高度
     * @param audioChannels 音频通道
     * @return
     */
    private static FFmpegFrameRecorder getRecorder(String targetPath,Integer width,Integer height,Integer audioChannels){
        if(Objects.isNull(targetPath)){
            return null;
        }
        return new FFmpegFrameRecorder(targetPath,width,height,audioChannels);
    }

    /*-----------------------------视频信息---------------------------------*/

    /**
     * 获取视频时长
     * @param videoPath 视频地址
     * @return long 时长
     */
    public static long getVideoTime(String videoPath){
        return getVideoTime(getGrabber(videoPath));
    }

    //获取视频时长
    public static long getVideoTime(FFmpegFrameGrabber grabber){
        long times = 0L;
        try(grabber){
            grabber.start();
            times = grabber.getLengthInTime() /(1000*1000);
        }catch (Exception e){
            e.printStackTrace();
        }
        return times;
    }


    /*------------------------------截图------------------------------------*/

    /**
     * 获取视频某一帧 作为截图
     * @param videoPath  视频地址
     * @param savePicPath 保存地址
     * @param fileName  保存文件名
     * @param seconds   要截取的秒数数组
     * @return
     */
    public static List<File> getVideoPic(String videoPath,String savePicPath,String fileName,List<Integer> seconds){
        return getVideoPic(videoPath,savePicPath,fileName,"jpg",-1,seconds);
    }

    /**
     * 获取视频某一帧 作为截图
     * @param videoPath 视频地址
     * @param savePicPath 保存地址
     * @param fileName  文件名
     * @param suffix    文件保存后缀
     * @param resizeWidth   重新缩放的宽度
     * @param seconds   时间
     * @return
     */
    public static List<File> getVideoPic(String videoPath,String savePicPath,String fileName,String suffix,Integer resizeWidth,List<Integer> seconds){
        return getVideoPic(getGrabber(videoPath),savePicPath,fileName,suffix,resizeWidth,seconds);
    }

    public static List<File> getVideoPic(FFmpegFrameGrabber grabber,String savePicPath,String fileName,String suffix,Integer resizeWidth,List<Integer> seconds){
        ArrayList<File> files = new ArrayList<>();
        try(grabber){
            assert grabber != null;
            grabber.start();
            int length = grabber.getLengthInAudioFrames();
            logger.info("视频长度{}",length);
            double rate = grabber.getFrameRate();
            logger.info("视频帧率{}",rate);

            int i =0;
            Frame frame;
            Arrays.sort(seconds.toArray());
            int first = 0;
            int secondLength = seconds.size();
            while(i<length){
                frame = grabber.grabImage();
                if (i >= (int) (grabber.getFrameRate() * seconds.get(first)) && frame.image != null) {
                    files.add(writeToPIC(frame, savePicPath,fileName,suffix,resizeWidth,first));
                    first++;
                    if(first>=secondLength){
                        break;
                    }
                }
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return files;
    }


    /**
     * 视频帧写成图片
     * @param frame
     * @param saveFile
     * @param fileName
     * @param suffix
     * @param newWidth
     * @param second
     * @return
     */
    public static File writeToPIC(Frame frame, String saveFile,String fileName,String suffix,Integer newWidth,int second){
        suffix = suffix.toLowerCase();

        if(!ConstPool.PIC_TYPES.contains(suffix)){
            return null;
        }

        fileName = Paths.get(saveFile,fileName+second+"."+suffix).toString();
        File targetFile = new File(fileName);

        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage srcBi = converter.getBufferedImage(frame);
        int owidth = srcBi.getWidth();
        int oheight = srcBi.getHeight();
        // 对截取的帧进行等比例缩放
        newWidth = newWidth==-1?owidth:newWidth;
        BufferedImage bi = proResize(owidth, oheight, newWidth);
        bi.getGraphics().drawImage(srcBi.getScaledInstance(bi.getWidth(), bi.getHeight(), Image.SCALE_SMOOTH), 0, 0, null);
        try {
            ImageIO.write(bi, suffix, targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return targetFile;
    }

    //按比例缩放图片
    public static BufferedImage proResize(int oldWidth,int oldHeight,int newWidth){
        int newHeight = (int) (((double) newWidth / oldWidth) * oldHeight);
        return new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_3BYTE_BGR);
    }


    /*------------------------------视频转换-----------------------------------*/

    /**
     * 视频转分辨率转视频编码
     *
     * @param inputFile   源文件
     * @param outputPath  输出目录
     * @param fileName 文件名
     * @param width       需要转成的视频的宽度
     * @param height      需要转成的视频的高度
     * @param videoFormat 需要转换成的视频格式
     * @return 返回新文件名称，可以根据实际使用修改
     */
    public static String videoConvert(String inputFile, String outputPath, String fileName,Integer width, Integer height, String videoFormat) {;
        String newFileName = fileName + "." + videoFormat;
        String resultPath = Paths.get(outputPath,newFileName).toString();
        //FFmpegLogCallback.set();
        Frame frame;
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputFile);
        FFmpegFrameRecorder recorder = null;
        try {
            // 初始化帧抓取器，例如数据结构（时间戳、编码器上下文、帧对象等），
            // 如果入参等于true，还会调用avformat_find_stream_info方法获取流的信息，放入AVFormatContext类型的成员变量oc中
            grabber.start(true);
            // grabber.start方法中，初始化的解码器信息存在放在grabber的成员变量oc中
            AVFormatContext avformatcontext = grabber.getFormatContext();
            // 文件内有几个媒体流（一般是视频流+音频流）
            int streamNum = avformatcontext.nb_streams();
            // 没有媒体流就不用继续了
            if (streamNum < 1) {
                logger.info("视频文件格式不对");
                return "error";
            }
            width = width==-1?grabber.getImageWidth():width;
            height = height==-1?grabber.getImageHeight():height;
            // 取得视频的帧率
            int framerate = (int) grabber.getVideoFrameRate();
            logger.info("视频帧率:{}，视频时长:{}秒，媒体流数量:{}", framerate, avformatcontext.duration() / 1000000,
                    streamNum);
            // 遍历每一个流，检查其类型
            for (int i = 0; i < streamNum; i++) {
                AVStream avstream = avformatcontext.streams(i);
                AVCodecParameters avcodecparameters = avstream.codecpar();
                logger.info("流的索引:{}，编码器类型:{}，编码器ID:{}", i, avcodecparameters.codec_type(),
                        avcodecparameters.codec_id());
            }
            // 源视频宽度
            int frameWidth = grabber.getImageWidth();
            // 源视频高度
            int frameHeight = grabber.getImageHeight();
            // 源音频通道数量
            int audioChannels = grabber.getAudioChannels();
            logger.info("源视频宽度:{}，源视频高度:{}，音频通道数:{}", frameWidth, frameHeight, audioChannels);
            recorder = new FFmpegFrameRecorder(resultPath, width, height, audioChannels);
            // 设置H264编码
            recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
            // 设置视频格式
            recorder.setFormat(videoFormat);
            // 使用原始视频的码率，若需要则自行修改码率
            recorder.setVideoBitrate(grabber.getVideoBitrate());
            // 一秒内的帧数，帧率
            recorder.setFrameRate(framerate);
            // 两个关键帧之间的帧数
            recorder.setGopSize(framerate);
            // 设置音频通道数，与视频源的通道数相等
            recorder.setAudioChannels(grabber.getAudioChannels());
            recorder.start();
            int videoFrameNum = 0;
            int audioFrameNum = 0;
            int dataFrameNum = 0;
            // 持续从视频源取帧
            while (null != (frame = grabber.grab())) {
                // 有图像，就把视频帧加一
                if (null != frame.image) {
                    videoFrameNum++;
                    // 取出的每一帧，都保存到视频
                    recorder.record(frame);
                }
                // 有声音，就把音频帧加一
                if (null != frame.samples) {
                    audioFrameNum++;
                    // 取出的每一帧，都保存到视频
                    recorder.record(frame);
                }
                // 有数据，就把数据帧加一
                if (null != frame.data) {
                    dataFrameNum++;
                }
            }
            logger.info("转码完成，视频帧:{}，音频帧:{}，数据帧:{}", videoFrameNum, audioFrameNum, dataFrameNum);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "error";
        } finally {
            if (recorder != null) {
                try {
                    recorder.close();
                } catch (Exception e) {
                    logger.info("recorder.close异常" + e);
                }
            }
            try {
                grabber.close();
            } catch (FrameGrabber.Exception e) {
                logger.info("frameGrabber.close异常" + e);
            }
        }
        return newFileName;
    }


    /*------------------------------视频剪辑-----------------------------------*/
    /**视频剪辑
     * @param videoPath 视频地址
     * @param outPutPath 输出地址
     * @param startSecond 视频开始时间
     * @param endSecond 视频结束时间
     */
    public static String cutVideo(String videoPath,String outPutPath,int startSecond,int endSecond){
        Frame frame;
        FFmpegFrameRecorder recorder = null;
        try(FFmpegFrameGrabber grabber = getGrabber(videoPath)) {
            // 初始化帧抓取器，例如数据结构（时间戳、编码器上下文、帧对象等），
            // 如果入参等于true，还会调用avformat_find_stream_info方法获取流的信息，放入AVFormatContext类型的成员变量oc中
            grabber.start(true);
            // grabber.start方法中，初始化的解码器信息存在放在grabber的成员变量oc中
            AVFormatContext avformatcontext = grabber.getFormatContext();
            // 文件内有几个媒体流（一般是视频流+音频流）
            int streamNum = avformatcontext.nb_streams();
            // 没有媒体流就不用继续了
            if (streamNum < 1) {
                logger.info("视频文件格式不对");
                return "error";
            }
            // 取得视频的帧率
            int framerate = (int) grabber.getVideoFrameRate();
            logger.info("视频帧率:{}，视频时长:{}秒，媒体流数量:{}", framerate, avformatcontext.duration() / 1000000,
                    streamNum);
            // 遍历每一个流，检查其类型
            for (int i = 0; i < streamNum; i++) {
                AVStream avstream = avformatcontext.streams(i);
                AVCodecParameters avcodecparameters = avstream.codecpar();
                logger.info("流的索引:{}，编码器类型:{}，编码器ID:{}", i, avcodecparameters.codec_type(),
                        avcodecparameters.codec_id());
            }
            // 源视频宽度
            int frameWidth = grabber.getImageWidth();
            // 源视频高度
            int frameHeight = grabber.getImageHeight();
            // 源音频通道数量
            int audioChannels = grabber.getAudioChannels();
            logger.info("源视频宽度:{}，源视频高度:{}，音频通道数:{}", frameWidth, frameHeight, audioChannels);
            recorder = new FFmpegFrameRecorder(outPutPath, frameWidth, frameHeight, audioChannels);
            // 设置H264编码
            recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
            // 设置视频格式
            recorder.setFormat(grabber.getFormat());
            // 使用原始视频的码率，若需要则自行修改码率
            recorder.setVideoBitrate(grabber.getVideoBitrate());
            // 一秒内的帧数，帧率
            recorder.setFrameRate(framerate);
            // 两个关键帧之间的帧数
            recorder.setGopSize(framerate);
            // 设置音频通道数，与视频源的通道数相等
            recorder.setAudioChannels(grabber.getAudioChannels());
            recorder.start();
            // 持续从视频源取帧
            int startFrames = framerate*startSecond;
            int endFrames = framerate*endSecond;
            int i = 0;
            while (i<=endFrames) {
                frame = grabber.grabImage();
                if(i>=startFrames){
                    // 有图像，就把视频帧加一
                    if (null != frame.image) {
                        recorder.record(frame);
                    }
                    // 有声音，就把音频帧加一
                    if (null != frame.samples) {
                        // 取出的每一帧，都保存到视频
                        recorder.record(frame);
                    }
                }
                i++;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "error";
        }finally {
            if (recorder != null) {
                try {
                    recorder.close();
                } catch (Exception e) {
                    logger.info("recorder.close异常" + e);
                }
            }

        }
        return outPutPath;
    }


    public static String formatTimeToFFMpeg(long seconds){
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long remainingSeconds = seconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
    }

    public static boolean cutVideoByFFMpeg(String inputFilePath,String outputFilePath,String start,String end){
        try {
            String ffmpegCmd = FFMPEG_PATH + " -i " + "\""+inputFilePath+"\"" + " -ss " + start + " -to " + end + " -c copy " + "\""+outputFilePath+"\"" +" -y";

            // 执行FFmpeg命令
            Process process = Runtime.getRuntime().exec(ffmpegCmd);

            // 等待命令执行完成
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.printf("in:%s 视频分割完成。 out:%s\n",inputFilePath, outputFilePath);
                return true;
            } else {
                System.err.printf("in:%s 视频分割失败。\n",inputFilePath);
                return false;
            }
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
            return false;
        }
    }
    public static boolean cutVideoByFFMpeg(String inputFilePath,String outputFilePath,long startSeconds,long endSeconds){
        String startTime = formatTimeToFFMpeg(startSeconds);
        String endTime = formatTimeToFFMpeg(endSeconds);
        return cutVideoByFFMpeg(inputFilePath,outputFilePath,startTime,endTime);
    }

    public static void main(String[] args) {
        cutVideoByFFMpeg(
                "E:\\Project\\ChopperBot\\config\\LiveRecord\\online\\douyu\\即将拥有人鱼线的PDD_2023-09-15 14_55_32.flv",
                "E:\\Project\\ChopperBot\\config\\LiveRecord\\online\\douyu\\即将拥有人鱼线的PDD_2023-09-15 14_55_32_split.flv",
                0,30
        );
    }
}
