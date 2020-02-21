package com.server.ServerSPRC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages={"com.server", "META-INF"})
public class ServerSprcApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerSprcApplication.class, args);
	}

}
