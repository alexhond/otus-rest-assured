package com.otus.utils;

import com.otus.endpoints.EndPoints;
import com.otus.entities.CommonObject;
import com.otus.entities.User;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class UserApiUtils extends CommonUtils {

  public static Response createUser(User user) {
    return postValidatableResponse(user, EndPoints.USER.getEndPointName()).extract().response();
  }

  public static void checkSuccessUser(Response response) {
    checkStatus(response, 200);
  }

  public static Response getUser(String name) {
    return getValidatableResponse(EndPoints.USER.getEndPointName(), name).extract().response();
  }

  public static void validateJsonSchema(Response actual, String path) {
    actual.then().body(matchesJsonSchemaInClasspath(path));
  }

  public static void compareUsers(Response actual, User expected) {
    User actualResponse = actual.as(expected.getClass());
    Assertions.assertEquals(expected, actualResponse);
  }

  public static Response updateUser(User user, String param) {
    return putValidatableResponse(user, EndPoints.USER.getEndPointName(), param).extract().response();
  }

  public static void checkAssert(String param, Response responseGet) {
    Assertions.assertEquals(param, responseGet.then().extract().as(User.class).getUsername());
  }
}
