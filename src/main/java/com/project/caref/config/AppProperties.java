
package com.project.caref.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Copyright (c) 2020, KTACENT, All Right Reserved.
 * https://www.linkedin.com/in/alex-kouasseu/
 * <p>
 * When : 07/11/2020 -- 13:14
 * By : @author alexk
 * Project : lp-user-service
 * Package : com.lukapharma.api.files.repositories
 */
@Component
@ConfigurationProperties(prefix = "app")
@Data
public class AppProperties {

	private final Storage storage = new Storage();
	private final Ftp ftp = new Ftp();
	@Data
	public static class Storage {

		private String path;
		private String directory;
	}
	@Data
	public static class Ftp {

		private String ip;
		private String user;
		private String pwd;
	}

}
