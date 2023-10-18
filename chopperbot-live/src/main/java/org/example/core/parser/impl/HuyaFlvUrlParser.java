// 参考：https://github.com/rain-dl/real-url-proxy-server/blob/master/huya.py
package org.example.core.parser.impl;

import cn.hutool.json.JSONException;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.example.core.creeper.loadconfig.HuyaLiveOnlineConfig;
import org.example.core.parser.PlatformVideoUrlParser;
import org.example.utils.HttpClientUtil;
import org.example.utils.RegexUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cn.hutool.crypto.SecureUtil.md5;

/**
 * @author dhx
 * @date 2023/10/9 20:04
 */
public class HuyaFlvUrlParser implements PlatformVideoUrlParser<HuyaLiveOnlineConfig> {
    private String room_id;
    private String user_id;
    private int mode;
    private Map<String,String> header;
    private Map<String, Map<String, String>> live_url_infos = new HashMap<>();


    private Map<String, String> decodeLiveUrlInfo(String srcAntiCode) {
        srcAntiCode = StringEscapeUtils.unescapeHtml4(srcAntiCode);
        String[] c = srcAntiCode.split("&");
        Map<String, String> n = new HashMap<>();
        for (String i : c) {
            if (!i.isEmpty()) {
                String[] parts = i.split("=");
                if (parts.length == 2) {
                    n.put(parts[0], parts[1]);
                }
            }
        }
        String fm = java.net.URLDecoder.decode(n.get("fm"));
        byte[] decodedBytes = Base64.getDecoder().decode(fm);
        String u = new String(decodedBytes);
        Map<String, String> liveUrlInfo = new HashMap<>();
        liveUrlInfo.put("hash_prefix", u.split("_")[0]);
        liveUrlInfo.put("uuid", n.get("uuid"));
        liveUrlInfo.put("ctype", n.get("ctype"));
        liveUrlInfo.put("txyp", n.get("txyp"));
        liveUrlInfo.put("fs", n.get("fs"));
        liveUrlInfo.put("t", n.get("t"));
        return liveUrlInfo;
    }

    private void clearLiveUrlInfos() {
        live_url_infos.clear();
    }

    private void updateLiveUrlInfo() {
        try {
            if (mode == 0) {
                String room_url = "https://m.huya.com/" + room_id;
                String responseText = HttpClientUtil.get(room_url,header);
                Pattern pattern = Pattern.compile("\"liveLineUrl\":\"([^\"]*?)\"");
                Matcher matcher = pattern.matcher(responseText);
                if (matcher.find()) {
                    String livelineurl_base64 = matcher.group(1);
                    String livelineurl;
                    try {
                        livelineurl = new String(Base64.getDecoder().decode(livelineurl_base64), StandardCharsets.UTF_8);
                    } catch (Exception e) {
                        livelineurl = livelineurl_base64;
                    }
                    if (!livelineurl.contains("replay")) {
                        String[] parts = livelineurl.split("\\?");
                        if (parts.length == 2) {
                            String url = parts[0];
                            String anti_code = parts[1];
                            Map<String, String> live_url_info = new HashMap<>();
                            live_url_info.put("stream_name", url.substring(url.lastIndexOf('/') + 1).replace(".flv", "").replace(".m3u8", ""));
                            live_url_info.put("base_url", "http:" + url.substring(0, url.lastIndexOf('/' + live_url_info.get("stream_name"))));
                            live_url_info.put("hls_url", "http:" + url);
                            Map<String, String> decodedInfo = decodeLiveUrlInfo(anti_code);
                            live_url_info.putAll(decodedInfo);
                            live_url_infos.put("TX", live_url_info);
                        }
                    }
                }
            } else if (mode == 1) {
                String room_url = "https://www.huya.com/" + room_id;
                Map<String,String> header2 = new HashMap<>();
                header2.put("Content-Type", "application/x-www-form-urlencoded");
                header2.put("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36");
                String responseText = HttpClientUtil.get(room_url,header2);

                String streamInfo = null;
                Pattern streamPattern = Pattern.compile("stream: ([^\\n]*?)\\n");
                Matcher streamMatcher = streamPattern.matcher(responseText);
                if (streamMatcher.find()) {
                    streamInfo = streamMatcher.group(1);
                } else {
                    Pattern base64Pattern = Pattern.compile("\"stream\": \"([^\"]*?)\"");
                    Matcher base64Matcher = base64Pattern.matcher(responseText);
                    if (base64Matcher.find()) {
                        String liveDataBase64 = base64Matcher.group(1);
                        streamInfo = new String(Base64.getDecoder().decode(liveDataBase64), StandardCharsets.UTF_8);
                    }
                }

                if (streamInfo != null) {
                    JSONObject liveData = new JSONObject(streamInfo);
                    JSONArray streamInfoList = liveData.getJSONArray("data").getJSONObject(0).getJSONArray("gameStreamInfoList");
                    for (int i = 0; i < streamInfoList.length(); i++) {
                        JSONObject streamInfoObj = streamInfoList.getJSONObject(i);
                        Map<String, String> live_url_info = new HashMap<>();
                        String sCdnType = streamInfoObj.getString("sCdnType");
                        live_url_info.put("stream_name", streamInfoObj.getString("sStreamName"));
                        live_url_info.put("base_url", streamInfoObj.getString("sHlsUrl"));
                        live_url_info.put("hls_url", streamInfoObj.getString("sHlsUrl") + "/" + streamInfoObj.getString("sStreamName") + "." + streamInfoObj.getString("sHlsUrlSuffix"));
                        String sHlsAntiCode = streamInfoObj.getString("sHlsAntiCode");
                        Map<String, String> decodedInfo = decodeLiveUrlInfo(sHlsAntiCode);
                        live_url_info.putAll(decodedInfo);
                        live_url_infos.put(sCdnType, live_url_info);
                    }
                }
            } else if (mode == 2) {
                String room_url = "https://mp.huya.com/cache.php?m=Live&do=profileRoom&roomid=" + room_id;
                CloseableHttpClient httpClient = HttpClients.createDefault();
                HttpGet httpGet = new HttpGet(room_url);
                httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
                httpGet.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36");
                CloseableHttpResponse response = httpClient.execute(httpGet);

                String responseText = org.apache.commons.io.IOUtils.toString(response.getEntity().getContent(), "UTF-8");

                JSONObject liveData = new JSONObject(responseText);
                if (liveData.has("data") && liveData.getJSONObject("data").has("stream") && liveData.getJSONObject("data").getJSONObject("stream").has("baseSteamInfoList")) {
                    JSONArray streamInfoList = liveData.getJSONObject("data").getJSONObject("stream").getJSONArray("baseSteamInfoList");
                    for (int i = 0; i < streamInfoList.length(); i++) {
                        JSONObject streamInfoObj = streamInfoList.getJSONObject(i);
                        Map<String, String> live_url_info = new HashMap<>();
                        String sCdnType = streamInfoObj.getString("sCdnType");
                        live_url_info.put("stream_name", streamInfoObj.getString("sStreamName"));
                        live_url_info.put("base_url", streamInfoObj.getString("sHlsUrl"));
                        live_url_info.put("hls_url", streamInfoObj.getString("sHlsUrl") + "/" + streamInfoObj.getString("sStreamName") + "." + streamInfoObj.getString("sHlsUrlSuffix"));
                        String sHlsAntiCode = streamInfoObj.getString("sHlsAntiCode");
                        Map<String, String> decodedInfo = decodeLiveUrlInfo(sHlsAntiCode);
                        live_url_info.putAll(decodedInfo);
                        live_url_infos.put(sCdnType, live_url_info);
                    }
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public List<String> getRealUrl() {
        String ratio = "";
        List<String> urls = new ArrayList<>();
        String seqid = String.valueOf((System.currentTimeMillis() / 1000) + Long.parseLong(user_id));
        String wsTime = Long.toHexString(System.currentTimeMillis() / 1000 + 3600);
        for (Map<String, String> live_url_info : live_url_infos.values()) {
            String hash0 = org.apache.commons.codec.digest.DigestUtils.md5Hex(seqid + "|" + live_url_info.get("ctype") + "|" + live_url_info.get("t"));
            String hash1 = org.apache.commons.codec.digest.DigestUtils.md5Hex(StringUtils.join(new String[]{live_url_info.get("hash_prefix"), user_id, live_url_info.get("stream_name"), hash0, wsTime}, '_'));
            String url;
            if (live_url_info.get("ctype").contains("mobile")) {
                url = String.format("%s?wsSecret=%s&wsTime=%s&uuid=%s&uid=%s&seqid=%s&ratio=%s&txyp=%s&fs=%s&ctype=%s&ver=1&t=%s",
                        live_url_info.get("hls_url"), hash1, wsTime, live_url_info.get("uuid"), user_id, seqid, ratio, live_url_info.get("txyp"),
                        live_url_info.get("fs"), live_url_info.get("ctype"), live_url_info.get("t"));
            } else {
                url = String.format("%s?wsSecret=%s&wsTime=%s&seqid=%s&ctype=%s&ver=1&txyp=%s&fs=%s&ratio=%s&u=%s&t=%s&sv=2107230339",
                        live_url_info.get("hls_url"), hash1, wsTime, seqid, live_url_info.get("ctype"), live_url_info.get("txyp"), live_url_info.get("fs"),
                        ratio, user_id, live_url_info.get("t"));
            }
            urls.add(url);
        }
        return urls;
    }


    @Override
    public String getUrl(HuyaLiveOnlineConfig LoadConfig) throws Exception {
        this.room_id = LoadConfig.getRoomId();
        this.user_id = "1463389097687";
        this.mode = 1;
        this.header = LoadConfig.getHeader();
        this.updateLiveUrlInfo();
        List<String> list = getRealUrl();
        return list.get(0).replace("hls","flv").replace("m3u8","flv");
    }
}
