package com.nakul.firstSpringProject;

import com.mongodb.client.MongoClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringProject {

	public static void main(String[] args) {
		SpringApplication.run(SpringProject.class, args);
	}

	@Bean
	public PlatformTransactionManager ptm(MongoDatabaseFactory dbFactory){
		return new MongoTransactionManager(dbFactory);
	}
}
