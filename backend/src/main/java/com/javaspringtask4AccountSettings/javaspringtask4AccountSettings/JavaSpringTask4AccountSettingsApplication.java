package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.web.multipart.support.MultipartFilter;


@SpringBootApplication
@EnableCaching
public class JavaSpringTask4AccountSettingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaSpringTask4AccountSettingsApplication.class, args);
	}
}
