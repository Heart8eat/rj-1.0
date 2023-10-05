package com.rj.backendjixian.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "files.upload")
@Getter
@Setter
public class FileUploadConfig {
    private String fileUploadPath;
    private List<String> permittedFileType;

}
