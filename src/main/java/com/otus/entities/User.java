package com.otus.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

  // import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */
  public class Root {
    @JsonProperty("id")
    public int getId() {
      return this.id;
    }

    public void setId(int id) {
      this.id = id;
    }

    int id;

    @JsonProperty("username")
    public String getUsername() {
      return this.username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    String username;

    @JsonProperty("firstName")
    public String getFirstName() {
      return this.firstName;
    }

    public void setFirstName(String firstName) {
      this.firstName = firstName;
    }

    String firstName;

    @JsonProperty("lastName")
    public String getLastName() {
      return this.lastName;
    }

    public void setLastName(String lastName) {
      this.lastName = lastName;
    }

    String lastName;

    @JsonProperty("email")
    public String getEmail() {
      return this.email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    String email;

    @JsonProperty("password")
    public String getPassword() {
      return this.password;
    }

    public void setPassword(String password) {
      this.password = password;
    }

    String password;

    @JsonProperty("phone")
    public String getPhone() {
      return this.phone;
    }

    public void setPhone(String phone) {
      this.phone = phone;
    }

    String phone;

    @JsonProperty("userStatus")
    public int getUserStatus() {
      return this.userStatus;
    }

    public void setUserStatus(int userStatus) {
      this.userStatus = userStatus;
    }

    int userStatus;
  }


}
