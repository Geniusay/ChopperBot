package org.example.core.parser.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.example.core.creeper.loadconfig.DouyuLiveOnlineConfig;
import org.example.core.parser.PlatformVideoUrlParser;
import org.example.utils.HttpClientUtil;
import org.example.utils.RegexUtil;
import org.json.JSONObject;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.time.LocalDate;


public class DouyuFlvUrlParser implements PlatformVideoUrlParser<DouyuLiveOnlineConfig> {
    String did = "818074ef9c05a3fe94acdfe500091601";

    @Override
    public String getUrl(DouyuLiveOnlineConfig config) throws Exception {
        String roomId = config.getRoomId();
        int clarity = config.getClarity();
        String html = HttpClientUtil.get("https://www.douyu.com/" + roomId);
        // 流程： 获取直播间页面
        //       从页面内获取一个函数和两个变量（每个直播间不同）
        //       该函数会生成一段JavaScript代码（用于签名）然后执行
        //       生成的代码用到了CryPtoJS库进行md5加密，所以要在代码执行前将在java中加密好并将原来的部分替换，再执行
        //       执行后生成一个签名
        //       将签名作为参数请求/getH5play接口，从响应中获取直播间对应的flv文件名
        //       得到链接
        String js_fun = RegexUtil.match(html, "(function ub98484234[\\s\\S]*?return eval[\\s\\S]*?);}")[0];
        String param_str = js_fun.substring(js_fun.indexOf("(") + 1, js_fun.indexOf(")"));
        String[] params = param_str.split(",");
        int index = js_fun.indexOf("){");
        String js_fun_head = js_fun.substring(0, index + 2);
        js_fun = js_fun.substring(index + 2);
        String js_var = RegexUtil.match(html, "(var vdwdae325w_64we[\\s\\S]*?;)")[0];
        String var2 = RegexUtil.match(js_fun, "v = ([\\s\\S]*).slice[\\s\\S]*?")[0];
        String var2_name = var2.substring(4, var2.length() - 6);
        String js_var2 = RegexUtil.match(html, "\\b(var " + var2_name + "[\\s\\S]*?;)")[0];
        js_fun = js_var + js_var2 + js_fun;
        js_fun = js_fun_head + js_fun;
        long time = (long) (System.currentTimeMillis() / 1e3);
        String cb = roomId + did + time + "2201" + LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"));
//        cb = "3357246818074ef9c05a3fe94acdfe5000916011691158546220120230804";
        String rb = DigestUtils.md5Hex(cb);
        String insert = String.format(".replace(\"CryptoJS.MD5(cb).toString()\",\"'%s'\")", rb);  //js代码中用到了CryptoJS库md5加密，需要先加密好然后替换
        int index2 = js_fun.lastIndexOf("strc");
        js_fun = js_fun.substring(0, index2 + 4) + insert + js_fun.substring(index2 + 4);   //替换

        ScriptEngineManager manager = new ScriptEngineManager();   //执行js签名代码
        ScriptEngine engine = manager.getEngineByName("js");
//        String script = String.format("var ub = new Function('%s','%s','%s','%s')",params[0],params[1],params[2],js_fun);
        engine.eval(js_fun);
        Invocable inv = (Invocable) engine;
        Object ret = inv.invokeFunction("ub98484234", roomId, did, time);
        String resp = HttpClientUtil.post(String.format(config.getUrl(), roomId, ret));
        JSONObject respObj = new JSONObject(resp);
        if (respObj.getInt("error") == 0) {
            JSONObject dataObj = respObj.getJSONObject("data");
            if(dataObj!=null){
                String fileUrl = dataObj.getString("rtmp_live");
                String flvBaseUrl = dataObj.getString("rtmp_url");
                String token = fileUrl.substring(fileUrl.indexOf("."));
                if(fileUrl!=null){
                    String name = fileUrl.substring(0,fileUrl.indexOf("."));
                    return String.format(flvBaseUrl+"/%s",fileUrl);
                }
            }
        }
        throw new Exception("没有找到直播流地址");
    }
}
