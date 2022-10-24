package com.otus.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class CommonUtils {
  private static final String baseUrl = "https://petstore.swagger.io/v2";

  static RequestSpecification requestSpecification = new RequestSpecBuilder()
      .setContentType(ContentType.JSON)
      .setUrlEncodingEnabled(false)
      .setRelaxedHTTPSValidation()
      .log(LogDetail.ALL)
      .build();

  static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
      .expectStatusCode(200)
      .log(LogDetail.ALL)
      .build();

  public static ValidatableResponse getValidatableResponse(String endPoint, String name) {
    String uri = baseUrl + "/" + endPoint + "/" + name;

    return given(requestSpecification)
        .baseUri(uri)
        .when().get()
        .then().spec(responseSpecification);
  }

  public static ValidatableResponse putValidatableResponse(Object requestObject, String endPoint, String... name) {
    String idRequest = name.length == 0 ? "" : "/" + name[0];
    String path = endPoint + "/" + idRequest;

    return given(requestSpecification)
        .body(requestObject)
        .baseUri(baseUrl)
        .basePath(path)
        .when().put()
        .then().spec(responseSpecification);
  }

  public static ValidatableResponse postValidatableResponse(Object requestObject, String endPoint, String... id) {
    String idRequest = id.length == 0 ? "" : "/" + id[0];
    String path = endPoint + "/" + idRequest;

    return given(requestSpecification)
        .body(requestObject)
        .baseUri(baseUrl)
        .basePath(path)
        .when().post()
        .then().spec(responseSpecification);
  }

  public static void checkStatus(Response response, int statusCode) {
    response.then().statusCode(statusCode);
  }

}
