package com.hossam.fitnessshop.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
  private String ownerUsername;
  private List<Product> products;
  private LocalDateTime localDateTime;
  private double totalOrderPrice;

  public Order(
      String ownerUsername,
      List<Product> products,
      LocalDateTime localDateTime,
      double totalOrderPrice) {
    this.ownerUsername = ownerUsername;
    this.products = products;
    this.localDateTime = localDateTime;
    this.totalOrderPrice = totalOrderPrice;
  }

  public String getOrderTicket() {
    StringBuilder sb = new StringBuilder();
    sb.append("Ticket of " + this.ownerUsername + "\n")
        .append("-----------------" + this.localDateTime + "\n")
        .append("List Of Products :\n");
    for (Product product : this.products) {
      sb.append(
          "id : "
              + product.getId()
              + " name : "
              + product.getName()
              + " price : "
              + product.getPrice()
              + "\n");
    }
    sb.append("-------------\n")
        .append("Total Price : " + String.valueOf(this.totalOrderPrice) + "\n")
        .append("See you late !");
    return sb.toString();
  }
}
