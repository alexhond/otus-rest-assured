package com.otus.utils;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class RestUtils {
  private static final String baseUrl = "https://petstore.swagger.io/";

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


  private RestUtils() {
  }

  public static ValidatableResponse sendPost(Object requestObject, String endPoint) {
    return postValidatableResponse(requestObject, endPoint);
  }

  public static ValidatableResponse sendGet(String endPoint, int id) {
    return getValidatableResponse(endPoint, id);
  }

  public static ValidatableResponse sendPost(String requestObject, String endPoint) {
    return postValidatableResponse(requestObject, endPoint);
  }

  private static ValidatableResponse postValidatableResponse(Object requestObject, String endPoint, String... id) {
    String idRequest = id.length == 0 ? "" : "/" + id[0];
    String path = endPoint + "/" + idRequest;

    return given(requestSpecification)
        .body(requestObject)
        .baseUri(baseUrl)
        .basePath("#/" + path)
        .when().post()
        .then().spec(responseSpecification);
  }

  private static ValidatableResponse getValidatableResponse(String endPoint, int id) {
    String uri = baseUrl + "/" + endPoint + "/" + id;

    return given(requestSpecification)
        .baseUri(uri)
        .when().get()
        .then().spec(responseSpecification);
  }
}
