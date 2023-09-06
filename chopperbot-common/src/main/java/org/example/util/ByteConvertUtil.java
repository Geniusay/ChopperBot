package org.example.util;

/**
 * @author Genius
 * @date 2023/09/07 01:10
 **/
public class ByteConvertUtil {

    public static String byteConvert(long bytes){
        if (bytes < 1024) {
            return bytes + " bytes";
        } else if (bytes < 1024 * 1024) {
            double dataRateInKBs = bytes / 1024.0;
            return String.format("%.2f KB", dataRateInKBs);
        } else if (bytes < 1024 * 1024 * 1024) {
            double dataRateInMBs = bytes / (1024.0 * 1024.0);
            return String.format("%.2f MB", dataRateInMBs);
        } else {
            double dataRateInGBs = bytes / (1024.0 * 1024.0 * 1024.0);
            return String.format("%.2f GB", dataRateInGBs);
        }
    }


    public static String  byteSpeedConvert(long bytes){
        return byteConvert(bytes) + "/s";
    }

}
