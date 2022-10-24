package com.otus.utils;

import com.otus.endpoints.EndPoints;
import com.otus.entities.Pet;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PetApiUtils extends CommonUtils{

  public static Response createPet(Pet pet) {
    return postValidatableResponse(pet, EndPoints.PET.getEndPointName()).extract().response();
  }

  public static void checkSuccessPet(Response response) {
    checkStatus(response, 200);
  }

  public static Response getPet(String name) {
    return getValidatableResponse(EndPoints.PET.getEndPointName(), name).extract().response();
  }

  public static void validateJsonSchema(Response actual, String path) {
    actual.then().body(matchesJsonSchemaInClasspath(path));
  }

  public static void comparePets(Response actual, Pet expected) {
    Pet actualResponse = actual.as(expected.getClass());
    Assertions.assertEquals(expected, actualResponse);
  }

  public static Response updatePet(Pet pet) {
    return putValidatableResponse(pet, EndPoints.PET.getEndPointName()).extract().response();
  }

  public static void checkAssert(Integer param, Response responseGet) {
    Assertions.assertEquals(param, responseGet.then().extract().as(Pet.class).getId());
  }

}
