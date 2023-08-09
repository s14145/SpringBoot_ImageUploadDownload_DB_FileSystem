package com.image;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringBootImageUploadDownloadDbFileSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootImageUploadDownloadDbFileSystemApplication.class, args);
	}
}