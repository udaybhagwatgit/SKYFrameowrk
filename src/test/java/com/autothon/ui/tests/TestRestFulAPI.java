package com.autothon.ui.tests;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.autothon.util.FibonacciUtil;

import io.restassured.response.ValidatableResponse;

public class TestRestFulAPI {
	String url = "https://jsonplaceholder.typicode.com/posts";
	int maxNumber = 100;

	@Test
	public void testJson() {
		List<Integer> fibonacciSeries = FibonacciUtil.getFibonacciSeries(100);
		ValidatableResponse response = FibonacciUtil.getRestResponse("http://jsonplaceholder.typicode.com/posts");
		response.assertThat().statusCode(200);
		List<Map<String, Object>> responseMap = response.extract().path("");
		Assert.assertEquals(responseMap.size(), 100);

		// get the element for the fibonacci series

		List<Map<String, Object>> fibonacciResponseMap = FibonacciUtil.getResponseFibonacci(responseMap,
				fibonacciSeries);
		for (Map<String, Object> eachFibonacciElement : fibonacciResponseMap) {
			Assert.assertNotNull(eachFibonacciElement.containsKey("userId"));
			Assert.assertNotNull(eachFibonacciElement.get("userId"));

			Assert.assertNotNull(eachFibonacciElement.containsKey("id"));
			Assert.assertNotNull(eachFibonacciElement.get("id"));

			Assert.assertNotNull(eachFibonacciElement.containsKey("title"));
			Assert.assertNotNull(eachFibonacciElement.get("title"));

			Assert.assertNotNull(eachFibonacciElement.containsKey("body"));
			Assert.assertNotNull(eachFibonacciElement.get("body"));
		}

	}

}
