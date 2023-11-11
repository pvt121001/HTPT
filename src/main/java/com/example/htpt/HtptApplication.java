package com.example.htpt;

import com.example.htpt.config.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)// sử dụng thông số cấu hình
public class HtptApplication {

	public static void main(String[] args) {
		SpringApplication.run(HtptApplication.class, args);
	}

}
