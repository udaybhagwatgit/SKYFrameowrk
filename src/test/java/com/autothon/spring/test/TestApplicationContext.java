package com.autothon.spring.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.autothon.mainapp.TestApplication;

@Configuration
@Import(TestApplication.class)
@EnableAutoConfiguration // create MongoTemplate and MongoOperations
@EnableMongoRepositories(basePackages = "com.autothon.dao.mongorepo")
@ComponentScan(basePackages = "com.autothon")
public class TestApplicationContext {

	@Autowired
	MongoDbFactory mongoDbFactory;
	@Autowired
	MongoMappingContext mongoMappingContext;
	@Autowired
	Environment env;
	private static final Logger log = LogManager.getLogger();

	@Bean
	public MappingMongoConverter mappingMongoConverter() {

		log.info("spring.data.mongodb.uri = {} :: spring.profiles.active = {}",
				env.getProperty("spring.data.mongodb.uri"), env.getProperty("spring.profiles.active"));
		DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
		MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
		converter.setTypeMapper(new DefaultMongoTypeMapper(null));

		return converter;
	}
}
