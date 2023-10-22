package org.example.sectionwork;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;
import org.junit.Test;
import ws.schild.jave.Encoder;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;
import ws.schild.jave.encode.VideoAttributes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Genius
 * @date 2023/10/22 23:50
 **/
public class VoiceToTextTest {
    public static boolean videoToVideo(String videoSource, String videoTarget) {
//        Date time = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
//        System.out.println(simpleDateFormat.format(time));

        long start = System.currentTimeMillis();

        File source = new File(videoSource);
        File target = new File(videoTarget);

        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("aac");
        audio.setBitRate(236000 / 2);
        audio.setChannels(2);
        audio.setSamplingRate(8000);

        VideoAttributes video = new VideoAttributes();
        video.setCodec("h264");
        video.setBitRate(1000000);
        video.setFrameRate(25);
        video.setQuality(4);
//        video.setSize(new VideoSize(720, 480));

        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setOutputFormat("mp4");
        attrs.setAudioAttributes(audio);
        attrs.setVideoAttributes(video);


        Encoder encoder = new Encoder();
        try {
            encoder.encode(new MultimediaObject(source), target, attrs);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(encoder.getUnhandledMessages());
            return false;
        }finally {
//            time = new Date();
//            System.out.println(simpleDateFormat.format(time));
            long end = System.currentTimeMillis();
            System.out.println("总耗时："+ (end-start) +"ms");
        }
    }


    public static void main(String[] args) throws IOException {
        videoToAudio("E:\\Project\\chopperbot-1.0\\1.mp4","E:\\Project\\chopperbot-1.0\\audioTest.mp3");
        Configuration configuration = new Configuration();
        configuration.setAcousticModelPath("resource:/zh_cn.cd_cont_5000");
        configuration.setDictionaryPath("resource:/zh_cn.dic");
        configuration.setLanguageModelPath("resource:/zh_cn.lm.bin");
        StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer(configuration);
        InputStream stream = new FileInputStream("E:\\Project\\chopperbot-1.0\\audioTest.mp3");
        recognizer.startRecognition(stream);
        SpeechResult result;
        while ((result = recognizer.getResult()) != null) {
            System.out.format("识别内容: %s\n", result.getHypothesis());
        }
    }

    public static boolean videoToAudio(String videoPath, String audioPath){
        File fileMp4 = new File(videoPath);
        File fileMp3 = new File(audioPath);

        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libmp3lame");
        audio.setBitRate(128000);
        audio.setChannels(2);
        audio.setSamplingRate(44100);

        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setOutputFormat("mp3");
        attrs.setAudioAttributes(audio);
        Encoder encoder = new Encoder();
        MultimediaObject mediaObject = new MultimediaObject(fileMp4);
        try{
            encoder.encode(mediaObject,fileMp3,attrs);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
