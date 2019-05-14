package com.autothon.dao.mongorepo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import com.autothon.entity.TestData;



@Component
public interface TestDataRepsitory extends MongoRepository<TestData, String> {

 	@Query("{ $and: [{ 'testClass' : ?0 }, { 'testMethod' : ?1 }]}")
 	TestData getTestData(String testClass, String testMethod);
}


