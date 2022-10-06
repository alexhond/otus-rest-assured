package com.otus;


import com.otus.endpoints.EndPoints;
import com.otus.entities.User;
import com.otus.steps.PetSteps;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class PetStoreTest {

  /**
   * Отправить запрос по эндпоинту user, создать нового питомца
   * Проверить: что человек успешно создан
   */
  @Test
  @DisplayName("Add a new user")
  void test1() {
    User user = new User(
        1337, "Pepl", "Peplov", "Peplovich",
        "mailmail", "pass", "12345678", 200);
    Response responsePost = PetSteps.postResponse(user, EndPoints.USER);
    PetSteps.checkStatus(responsePost, 200);

    Response responseGet = PetSteps.getResponse(1337, EndPoints.USER);
    PetSteps.checkStatus(responseGet, 200);

    PetSteps.compareObjects(responseGet, user);

  }

  static RequestSpecification requestSpecification = new RequestSpecBuilder()
      .setContentType(ContentType.JSON)
      .log(LogDetail.ALL)
      .build();

  static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
      .expectStatusCode(200)
      .log(LogDetail.ALL)
      .build();

  @Test
  void test2() {
    given(requestSpecification)
        .baseUri("https://petstore.swagger.io/v2")
        .basePath("/" + "user/")
        .when().get()
        .then().spec(responseSpecification);
  }
}


