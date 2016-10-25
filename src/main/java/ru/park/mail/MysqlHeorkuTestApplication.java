package ru.park.mail;

import java.net.URI;
import java.net.URISyntaxException;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
public class MysqlHeorkuTestApplication {

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource mainDataSource() throws URISyntaxException {
		URI dbUri = new URI(System.getenv("CLEARDB_DATABASE_URL"));

		return DataSourceBuilder.create()
				.username(dbUri.getUserInfo().split(":")[0])
				.password(dbUri.getUserInfo().split(":")[1])
				.url("jdbc:mysql://" + dbUri.getHost() + dbUri.getPath())
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(MysqlHeorkuTestApplication.class, args);
	}
}
