package com.otus;


import com.otus.entities.Category;
import com.otus.entities.Pet;
import com.otus.entities.Tag;
import com.otus.entities.User;
import com.otus.utils.PetApiUtils;
import com.otus.utils.UserApiUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@org.junit.jupiter.api.Tag("@rest")
public class PetStoreNewTest {

  /**
   * Отправить запрос по эндпоинту user, создать нового user
   * Проверить: что user успешно создан
   * Сравнить созданного user с ожидаемым user
   */
  @Test
  @DisplayName("Add a new user")
  void testAddNewUserAndCheckUserSuccess() {
    User user = new User(
        1337, "Pepl", "Peplov", "Peplovich",
        "mailmail", "pass", "12345678", 200);
    Response createdUser = UserApiUtils.createUser(user);
    UserApiUtils.checkSuccessUser(createdUser);

    Response checkCreatedUser = UserApiUtils.getUser("Pepl");
    UserApiUtils.checkSuccessUser(checkCreatedUser);

    UserApiUtils.validateJsonSchema(checkCreatedUser, "json-schema/user-schema.json");

    UserApiUtils.compareUsers(checkCreatedUser, user);
  }

  /**
   * Обновить user
   * Проверить: что user успешно обновлен
   */
  @Test
  @DisplayName("Update user")
  void testUpdateUser() {
    User user = new User(
        1337, "NewPepl", "Peplov", "Peplovich",
        "mailmail", "pass", "12345678", 200);

    Response updateUser = UserApiUtils.updateUser(user, "username");
    UserApiUtils.checkSuccessUser(updateUser);

    Response checkCreatedUser = UserApiUtils.getUser("NewPepl");
    UserApiUtils.checkSuccessUser(checkCreatedUser);

    UserApiUtils.checkAssert("NewPepl", checkCreatedUser);
  }

  /**
   * Отправить запрос по эндпоинту pet, создать нового user
   * Проверить: что pet успешно создан
   */
  @Test
  @DisplayName("Add a new pet")
  void testAddNewPetAndCheckPetSuccess() {
    List<String> photourls = new ArrayList<>();
    photourls.add("adssa");
    photourls.add("ddd");
    List<Tag> tags = new ArrayList<>();
    tags.add(new Tag(222, "111"));
    Pet pet = new Pet(
        133, new Category(2, "asd"), "newPet", photourls, tags, "status"
    );

    Response createPet = PetApiUtils.createPet(pet);
    PetApiUtils.checkSuccessPet(createPet);

    Response checkCreatedPet = PetApiUtils.getPet("133");
    PetApiUtils.validateJsonSchema(checkCreatedPet, "json-schema/pet-schema.json");
    PetApiUtils.checkSuccessPet(checkCreatedPet);
  }

  /**
   * Обновить pet
   * Проверить: что user успешно обновлен
   */
  @Test
  @DisplayName("Update pet")
  void testUpdatePet() {
    List<String> photourls = new ArrayList<>();
    photourls.add("adssa");
    photourls.add("ddd");
    List<Tag> tags = new ArrayList<>();
    tags.add(new Tag(222, "111"));
    Pet pet = new Pet(
        444, new Category(2, "asd"), "newPet", photourls, tags, "status"
    );

    Response updatePet = PetApiUtils.updatePet(pet);
    PetApiUtils.checkSuccessPet(updatePet);

    Response checkUpdatePet = PetApiUtils.getPet("444");
    PetApiUtils.checkSuccessPet(checkUpdatePet);

    PetApiUtils.checkAssert(444, checkUpdatePet);
  }
}


