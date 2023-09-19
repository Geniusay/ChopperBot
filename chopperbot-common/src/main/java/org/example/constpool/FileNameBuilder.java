package org.example.constpool;

/**
 * @author Genius
 * @date 2023/09/17 01:19
 **/
public class FileNameBuilder {
    public static String buildVideoFileNameNoSuffix(String liver,String str){
        return String.format("%s_%s",liver, str.replace(":","_"));
    }

    public static String buildBarrageFileName(String liver,String str){
        return String.format("%s_%s.%s",liver,str.replace(":","_"),"json");
    }
}
