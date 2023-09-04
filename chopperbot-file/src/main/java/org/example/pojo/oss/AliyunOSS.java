package org.example.pojo.oss;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Genius
 * @date 2023/05/01 16:38
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Configuration
public class AliyunOSS implements OssAble {

    private Logger logger = LoggerFactory.getLogger(AliyunOSS.class);
    private String ENDPOINT;

    private String ACCESS_KEY_ID;

    private String ACCESS_KEY_SECRET;

    private long PART_SIZE = 1024*1024;

    public OSS getOssInstance(){
        return new OSSClientBuilder().build(ENDPOINT,ACCESS_KEY_ID,ACCESS_KEY_SECRET);
    }


    /**
     * 单文件上传
     * @param filePath
     * @param bucketName
     * @param objectName
     * @return boolean
     */
    @Override
    public String simpleUpload(String filePath, String bucketName, String objectName) {
        File file = new File(filePath);
        try(InputStream inputStream = new FileInputStream(file)) {
           return this.simpleUpload(inputStream,bucketName,objectName);
        } catch (IOException e){
            return null;
        }
    }

    @Override
    public String simpleUpload(InputStream inputStream, String bucketName, String objectName) {
        OSS ossClient = this.getOssInstance();
        try{
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,objectName,inputStream);
            ossClient.putObject(putObjectRequest);
        }catch (OSSException e){
            loggerError(e);
            return null;
        }finally {
            shutDown(ossClient);
        }

        return Paths.get(bucketName,objectName).toString();
    }

    @Override
    public String multipartUpload(String filePath, String bucketName, String objectName) {
        OSS ossClient = this.getOssInstance();
        try {
            InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest(bucketName,objectName);
            InitiateMultipartUploadResult upresult = ossClient.initiateMultipartUpload(request);
            String uploadId = upresult.getUploadId();
            File sampleFile = new File(filePath);
            long fileLength = sampleFile.length();
            int partCount = (int)(fileLength/PART_SIZE);
            if(fileLength%PART_SIZE!=0){
                partCount++;
            }
            List<PartETag> partETags =  new ArrayList<PartETag>();
            for(int i=0;i<partCount;i++){
                long startPos = i * PART_SIZE;
                long curPartSize = (i+1==partCount)?(fileLength-startPos):PART_SIZE;
                try(InputStream inputStream = new FileInputStream(sampleFile)){
                    inputStream.skip(startPos);
                    UploadPartRequest uploadPartRequest = new UploadPartRequest();
                    uploadPartRequest.setBucketName(bucketName);
                    uploadPartRequest.setKey(objectName);
                    uploadPartRequest.setUploadId(uploadId);
                    uploadPartRequest.setInputStream(inputStream);
                    uploadPartRequest.setPartSize(curPartSize);
                    uploadPartRequest.setPartNumber(i+1);
                    UploadPartResult uploadPartResult = ossClient.uploadPart(uploadPartRequest);
                    partETags.add(uploadPartResult.getPartETag());
                }catch (IOException e){
                    return null;
                }
            }
            CompleteMultipartUploadRequest completeMultipartUploadRequest =
                    new CompleteMultipartUploadRequest(bucketName, objectName, uploadId, partETags);

            CompleteMultipartUploadResult completeMultipartUploadResult = ossClient.completeMultipartUpload(completeMultipartUploadRequest);
        }catch (OSSException oe){
            loggerError(oe);
            return null;
        }finally {
            shutDown(ossClient);
        }
        return Paths.get(bucketName,objectName).toString();
    }


    @Override
    public boolean endPointUpload(String filePath, String bucketName, String objectName) {
        return false;
    }

    @Override
    public File downloadFile(String downloadPath, String bucketName, String objectName) {
        OSS ossClient = this.getOssInstance();
        File file = new File(downloadPath);
        try {
            ossClient.getObject(new GetObjectRequest(bucketName, objectName), new File(downloadPath));
        }catch (OSSException e){
            loggerError(e);
        }finally {
            shutDown(ossClient);
        }
        return file;
    }

    @Override
    public boolean deleteFile(String fileName, String bucketName) {
        return deleteFiles(List.of(fileName),bucketName);
    }

    @Override
    public boolean deleteFiles(List<String> fileNames, String bucketName) {
        OSS ossClient = this.getOssInstance();
        try{
            DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(bucketName);
            deleteObjectsRequest.setKeys(fileNames);
            ossClient.deleteObjects(deleteObjectsRequest);
        }catch (OSSException e){
            loggerError(e);
            return false;
        }finally {
            shutDown(ossClient);
        }
        return true;
    }


    private void loggerError(OSSException e){
        logger.error("Error Message:{}",e.getErrorMessage());
        logger.error("Error Code:{}" + e.getErrorCode());
        logger.error("Request ID:{}" + e.getRequestId());
        logger.error("Host ID:{}" + e.getHostId());
    }

    private void shutDown(OSS ossClient){
        if(ossClient != null){
            ossClient.shutdown();
        }
    }
}
