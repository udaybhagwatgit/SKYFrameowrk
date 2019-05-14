package com.autothon.dao.mongorepo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import com.autothon.entity.LoginProperties;


@Component
public interface LoginPropertiesRepository extends MongoRepository<LoginProperties, String> {

	 	@Query("{}")
	    LoginProperties getLoginProperties();
}
