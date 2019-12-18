package com.itrip.itripbiz;

import cn.itrip.common.SystemConfig;
import cn.itrip.common.ValidationToken;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.StringRedisTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("cn.itrip.dao")
@PropertySource("classpath:systemConfig.properties")
public class ItripBizApplication {
    @Autowired
    private Environment environment;

    @Bean
    public SystemConfig systemConfig() {
        SystemConfig systemConfig = new SystemConfig();
        systemConfig.setFileUploadPathString(environment.getProperty("sysConfig.fileUploadPath"));
        systemConfig.setVisitImgUrlString(environment.getProperty("sysConfig.visitImgUrl"));
        systemConfig.setMachineCode(environment.getProperty("sysConfig.machineCode"));
        systemConfig.setOrderProcessOK(environment.getProperty("sysConfig.orderProcessOK"));
        systemConfig.setOrderProcessCancel(environment.getProperty("sysConfig.orderProcessCancel"));
        //赋值
        return systemConfig;
    }

    @Bean
    public ValidationToken validationToken(StringRedisTemplate stringRedisTemplate) {
        ValidationToken validationToken = new ValidationToken();
        validationToken.setRedisAPI(stringRedisTemplate);
        return validationToken;
    }

    public static void main(String[] args) {
        SpringApplication.run(ItripBizApplication.class, args);
    }

}
