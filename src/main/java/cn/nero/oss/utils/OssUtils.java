package cn.nero.oss.utils;

import cn.nero.oss.configuration.OssProperties;
import com.alibaba.fastjson2.JSON;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @since 2024/3/8
 */
@Slf4j
public class OssUtils {

    public static OSS initOssClient () {
        String accessKeyId = OssProperties.ACCESS_KEY_Id;
        String accessKeySecret = OssProperties.ACCESS_KEY_SECRET;
        String endpoint = OssProperties.ENDPOINT;

        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

    /**
     * 上传文件
     * @param filePath 本地文件地址
     * @param newPath 上传路径
     * @return 上传结果
     */
    public static PutObjectResult upload (String filePath, String newPath) {
        String bucket = OssProperties.BUCKET;

        OSS ossClient = initOssClient();

        try (FileInputStream fis = new FileInputStream(filePath)) {
            PutObjectResult putResult = ossClient.putObject(bucket, newPath, fis);
            return putResult;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != ossClient) {
                ossClient.shutdown();
            }
        }
        return null;
    }

    public static String uploadAndGetUrl (String filePath, String newPath) {
        PutObjectResult putResult = upload(filePath, newPath);
        log.info("{}", JSON.toJSONString(putResult));

        log.warn("You are using the uploadAndGetUrl method to obtain the address of the uploaded image. Please note that this connection can only be used normally when the permission is Public Read");

        return "https://" + OssProperties.BUCKET + "." + OssProperties.ENDPOINT + "/" + newPath;
    }

    /**
     * 下载文件到本地
     * @param filePath oss文件路径
     * @param savePath 保存文件路径
     * @return metadata
     */
    public static ObjectMetadata downloadFile (String filePath, String savePath) {
        String bucket = OssProperties.BUCKET;
        OSS ossClient = initOssClient();
        try {
            return ossClient.getObject(new GetObjectRequest(bucket, filePath), new File(savePath));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != ossClient) {
                ossClient.shutdown();
            }
        }
        return null;
    }

    /**
     * 判断文件是否存在
     * @param bucketName bucket name
     * @param objectName object name
     * @return  object exists
     */
    public static boolean objectExists (String bucketName, String objectName) {
        return initOssClient().doesObjectExist(bucketName, objectName);
    }

}
