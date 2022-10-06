package com.otus.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Store {
  @JsonProperty("id")
  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  int id;

  @JsonProperty("petId")
  public int getPetId() {
    return this.petId;
  }

  public void setPetId(int petId) {
    this.petId = petId;
  }

  int petId;

  @JsonProperty("quantity")
  public int getQuantity() {
    return this.quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  int quantity;

  @JsonProperty("shipDate")
  public Date getShipDate() {
    return this.shipDate;
  }

  public void setShipDate(Date shipDate) {
    this.shipDate = shipDate;
  }

  Date shipDate;

  @JsonProperty("status")
  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  String status;

  @JsonProperty("complete")
  public boolean getComplete() {
    return this.complete;
  }

  public void setComplete(boolean complete) {
    this.complete = complete;
  }

  boolean complete;
}
