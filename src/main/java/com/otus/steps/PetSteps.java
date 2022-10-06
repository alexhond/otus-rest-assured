package com.otus.steps;

import com.otus.endpoints.EndPoints;
import com.otus.entities.CommonPetStoreObject;
import com.otus.utils.RestUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

public class PetSteps {

  public static Response postResponse(CommonPetStoreObject request, EndPoints endPoints) {
    return RestUtils.sendPost(request, endPoints.getEndPointName()).extract().response();
  }

  public static Response getResponse(int id, EndPoints endPoints) {
    return RestUtils.sendGet(endPoints.getEndPointName(), id).extract().response();
  }

  public static void checkStatus(Response response, int statusCode) {
    response.then().statusCode(statusCode);
  }

  public static void compareObjects(Response actual, CommonPetStoreObject expected) {
    CommonPetStoreObject actualResponse = actual.as(expected.getClass());
    Assertions.assertEquals(expected, actualResponse);

  }
}
