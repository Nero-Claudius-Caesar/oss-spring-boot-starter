package cn.nero.oss.configuration;

import lombok.Getter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @since 2024/3/8
 */
@Getter
@Component
@ConfigurationProperties(prefix = "oss")
public class OssConfiguration implements InitializingBean {

    /**
     * 对象存储服务url
     */
    private String endpoint;

    /**
     * 地区
     */
    private String region;

    /**
     * true path-style nginx 反向代理和S3默认支持 pathStyle模式 {http://endpoint/bucketname}
     * false supports virtual-hosted-style 阿里云等需要配置为 virtual-hosted-style 模式{http://bucketname.endpoint}
     * 只是url的显示不一样
     */
    private boolean pathStyleAccess = true;

    /**
     * access key
     */
    private String accessKeyId;

    /**
     * access secret
     */
    private String accessKeySecret;

    /**
     * bucket name
     */
    private String bucket;

    /**
     * aliyun url
     */
    private String domain;

    /**
     * 同access key secret
     */
    private String secretKey;

    /**
     * 最大线程数,默认100
     */
    private Integer maxConnections = 100;


    public static String ACCESS_KEY_Id;
    public static String ACCESS_KEY_SECRET;
    public static String ENDPOINT;
    public static String BUCKET;
    public static String DOMAIN;


    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_Id = this.getAccessKeyId();
        ACCESS_KEY_SECRET = this.getAccessKeySecret();
        ENDPOINT = this.getEndpoint();
        BUCKET = this.getBucket();
        DOMAIN = this.getDomain();
    }
}
