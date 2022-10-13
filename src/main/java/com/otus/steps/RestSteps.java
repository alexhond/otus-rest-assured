package com.otus.steps;

import com.otus.endpoints.EndPoints;
import com.otus.entities.CommonObject;
import com.otus.utils.RestUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

public class RestSteps {

  public static Response postResponse(CommonObject request, EndPoints endPoints) {
    return RestUtils.sendPost(request, endPoints.getEndPointName()).extract().response();
  }

  public static Response getResponse(String name, EndPoints endPoints) {
    return RestUtils.sendGet(endPoints.getEndPointName(), name).extract().response();
  }

  public static Response putResponse(CommonObject request, String name, EndPoints endPoints) {
    return RestUtils.sendPut(request, endPoints.getEndPointName(), name).extract().response();
  }

  public static Response putResponse(CommonObject request, EndPoints endPoints) {
    return RestUtils.sendPut(request, endPoints.getEndPointName()).extract().response();
  }

  public static void checkStatus(Response response, int statusCode) {
    response.then().statusCode(statusCode);
  }

  public static void compareObjects(Response actual, CommonObject expected) {
    CommonObject actualResponse = actual.as(expected.getClass());
    Assertions.assertEquals(expected, actualResponse);

  }
}