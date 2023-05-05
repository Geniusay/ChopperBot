package org.example.pojo.oss;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public interface OssAble {

    String simpleUpload(String filePath,String bucketName,String objectName);

    String simpleUpload(InputStream inputStream,String bucketName,String objectName);

    String multipartUpload(String filePath,String bucketName,String objectName);

    boolean endPointUpload(String filePath,String bucketName,String objectName);

    File downloadFile(String downloadPath,String bucketName,String objectName);

    boolean deleteFile(String fileName,String bucketName);

    boolean deleteFiles(List<String> fileNames,String bucketName);
}
