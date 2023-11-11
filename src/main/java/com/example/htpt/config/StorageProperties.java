package com.example.htpt.config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
@Data// sinh ra getter setter
public class StorageProperties {
    private String location;// vị trí lưu file dược upload lên server
}
