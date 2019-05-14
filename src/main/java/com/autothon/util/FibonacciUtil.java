package com.autothon.util;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class FibonacciUtil {

    static List<Map<String, Object>> fibonacciList = null;
    String url = "https://jsonplaceholder.typicode.com/posts";

    public static List<Integer> getFibonacciSeries(int maxNumber) {
        int currentNumber = 1;
        int previousNumber = 1;
        List<Integer> series = new ArrayList<Integer>();
        series.add(currentNumber);
        while (currentNumber < maxNumber) {
            int temp = currentNumber;
            currentNumber = currentNumber + previousNumber;
            previousNumber = temp;
            if (currentNumber < maxNumber)
                series.add(currentNumber);

        }
        return series;

    }

    public static ValidatableResponse getRestResponse(String url) {
        RequestSpecification request = given().contentType(ContentType.JSON).when();
        Response response = request.get(url);
        return response.then();
    }

    public static List<Map<String, Object>> getResponseFibonacci(List<Map<String, Object>> responseMap, List<Integer> series) {
        List<Map<String, Object>> fibonacciElementList = new ArrayList<Map<String, Object>>();
        for (int i : series) {
            Map<String, Object> eachElement = responseMap.get(i - 1);
            fibonacciElementList.add(eachElement);
        }
        return fibonacciElementList;

    }

    public static List<Map<String, Object>> generateTwoRandomNumber(int maxValue, int limit, String url) {
        int i = 0;
        List<Integer> series = getFibonacciSeries(limit);
        ValidatableResponse response = getRestResponse("http://jsonplaceholder.typicode.com/posts");
        List<Map<String, Object>> responseMap = response.extract().path("");
        List<Map<String, Object>> randomTwoMap = new ArrayList<Map<String, Object>>();
        fibonacciList = getResponseFibonacci(responseMap, series);

        List<Integer> twoNumber = new ArrayList<Integer>();
        Random rand = new Random();
        int randomNum = rand.nextInt(maxValue);
        int randomNumSecond;
        randomNumSecond = randomNum + 1;
        randomTwoMap.add(i, fibonacciList.get(randomNum));
        fibonacciList.remove(randomNum);
        i++;
        randomTwoMap.add(i, fibonacciList.get(randomNumSecond));
        fibonacciList.remove(randomNumSecond);
        return randomTwoMap;
    }

}
