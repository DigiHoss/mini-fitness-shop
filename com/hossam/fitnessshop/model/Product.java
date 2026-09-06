package com.hossam.fitnessshop.model;

public class Product {

  private final int id;
  private String name;
  private String description;
  private double price;

  public Product(int id, String name, String description, double price) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.id = id;
  }

  public int getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getDescription() {
    return this.description;
  }

  public double getPrice() {
    return this.price;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "id : " + this.id + " name : " + this.name + " price : " + this.price + "\n";
  }
}
