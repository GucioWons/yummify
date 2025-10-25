package com.guciowons.yummify;

import com.guciowons.yummify.file.config.MinioProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableConfigurationProperties(MinioProperties.class)
@EnableFeignClients
@SpringBootApplication
public class YummifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(YummifyApplication.class, args);
	}

}
