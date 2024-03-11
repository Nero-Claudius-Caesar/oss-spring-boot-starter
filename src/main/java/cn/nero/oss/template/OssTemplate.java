package cn.nero.oss.template;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.io.InputStream;
import java.util.List;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @since 2024/3/11
 */
public interface OssTemplate {

    /**
     * 创建bucket
     * @param bucketName bucket name
     */
    void createBucket(String bucketName);

    /**
     * 获取所有的bucket
     * @return bucket列表
     */
    List<Bucket> getAllBuckets();

    /**
     * 移除bucket
     * @param bucketName bucket name
     */
    void removeBucket(String bucketName);

    /**
     * 上传文件
     * @param bucketName bucket name
     * @param objectName 文件名
     * @param stream 文件流
     * @param contextType 文件类型
     * @throws Exception 可能抛出异常
     */
    void putObject(String bucketName, String objectName, InputStream stream, String contextType) throws Exception;

    /**
     * 上传文件
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param stream 文件流
     * @throws Exception
     */
    void putObject(String bucketName, String objectName, InputStream stream) throws Exception;

    /**
     * 获取文件
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @return S3Object
     */
    S3Object getObject(String bucketName, String objectName);

    /**
     * 获取对象的url
     * @param bucketName
     * @param objectName
     * @param expires
     * @return
     */
    String getObjectURL(String bucketName, String objectName, Integer expires);

    /**
     * 通过bucketName和objectName删除对象
     * @param bucketName
     * @param objectName
     * @throws Exception
     */
    void removeObject(String bucketName, String objectName) throws Exception;

    /**
     * 根据文件前置查询文件
     * @param bucketName bucket名称
     * @param prefix 前缀
     * @param recursive 是否递归查询
     * @return S3ObjectSummary 列表
     */
    List<S3ObjectSummary> getAllObjectsByPrefix(String bucketName, String prefix, boolean recursive);

}
