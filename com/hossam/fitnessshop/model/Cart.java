package com.hossam.fitnessshop.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Cart {
  private String owner;
  private List<Product> products;

  public Cart(String owner) {
    this.owner = owner;
    this.products = new ArrayList<Product>();
  }

  public void addProduct(Product product) {
    this.products.add(product);
  }

  public boolean removeProduct(int id) {
    Product productToRemove = null;
    for (Product product : this.products) {
      if (product.getId() == id) {
        productToRemove = product;
      }
    }
    if (productToRemove != null) {
      return this.products.remove(productToRemove);
    }
    return false;
  }

  public String viewCartProducts() {
    if (this.products.isEmpty()) {
      return "Cart is empty !";
    }
    StringBuilder sb = new StringBuilder();
    for (Product product : this.products) {
      sb.append(product.toString());
    }
    return sb.toString();
  }

  public double getTotalPrice() {
    double totalPrice = 0;
    for (Product product : this.products) {
      totalPrice += product.getPrice();
    }
    return totalPrice;
  }

  public Order checkout() {
    LocalDateTime localDateTime = LocalDateTime.now();
    List<Product> confirmedProducts = new ArrayList<Product>(this.products);
    Order order = new Order(this.owner, confirmedProducts, localDateTime, getTotalPrice());
    this.products.clear();
    return order;
  }
}
