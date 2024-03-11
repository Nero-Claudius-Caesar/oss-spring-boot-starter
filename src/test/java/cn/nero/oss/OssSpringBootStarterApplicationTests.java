package cn.nero.oss;

import cn.nero.oss.utils.OssUtils;
import com.alibaba.fastjson2.JSON;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@SpringBootTest
class OssSpringBootStarterApplicationTests {

    @Test
    public void test1() {
        PutObjectResult upload = OssUtils.upload("C:\\Users\\desti\\Pictures\\Screenshots\\屏幕截图 2023-03-17 182638.png", DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()) + "/test.png");
        log.info("{}", JSON.toJSONString(upload));
    }

    @Test
    public void test2() {
        OssUtils.downloadFile("2024-03-11/test.png", "C:\\data\\temp\\test.png");
    }

    @Test
    public void test3() {
        String url = OssUtils.uploadAndGetUrl("C:\\Users\\desti\\Pictures\\Screenshots\\屏幕截图 2023-03-17 182638.png", DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()) + "/test1.png");
        log.info("url : {}", url);
    }
}
