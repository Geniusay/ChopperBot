package org.example.sectionwork;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;
import okhttp3.*;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

/**
 * @author Genius
 * @date 2023/10/22 23:50
 **/
public class VoiceToTextTest {
    public static final MediaType MEDIA_TYPE_WAV = MediaType.parse("audio/wav");

    public static void main(String... args) throws IOException {
//        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890));
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(120, TimeUnit.SECONDS).readTimeout(120,TimeUnit.SECONDS).writeTimeout(60,TimeUnit.SECONDS).build();

        long l = System.currentTimeMillis();
        // 音频文件
        File audioFile = new File("E:\\Project\\chopperbot-1.0\\audioTest.mp3");

        // 请求体
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", "audioTest.mp3",
                        RequestBody.create(MediaType.parse("video/mp4"), audioFile))
                .addFormDataPart("model", "whisper-1")
                .addFormDataPart("response_format", "srt")
                .build();

        // 请求
        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer " + "sk-xgUDtOdRgQLigz2D0e4cA665441e4287AfCf8458B1C21b0f")
//                .url("https://api.openai.com/v1/audio/transcriptions")
                //.url("https://oneapi.a9.gay/v1/audio/transcriptions")
                .url(" https://oneapi.a9.gay/openai/deployments/whisper-1/audio/transcriptions?api-version=2023-09-01-preview")
                .post(requestBody)
                .build();

        // 发送请求
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            System.out.println(response.body().string());
            System.out.println((System.currentTimeMillis() - l)/1000);
        }
    }

}
