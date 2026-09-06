package com.hossam.fitnessshop.model;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
  private Cart cart;
  private List<Order> orders;

  public Customer(String fullName, String username, String password) {
    super(fullName, username, password);
    this.cart = new Cart(username);
    this.orders = new ArrayList<Order>();
  }

  public String viewCatalogProducts(Catalog catalog) {
    List<Product> products = catalog.getAllProducts();
    StringBuilder sb = new StringBuilder();
    for (Product product : products) {
      sb.append(product);
    }
    return sb.toString();
  }

  public void addProductToCart(Product product) {
    this.cart.addProduct(product);
  }

  public boolean removeProductFromCart(int id) {
    return this.cart.removeProduct(id);
  }

  public String viewCartProducts() {
    return this.cart.viewCartProducts();
  }

  public String checkout() {
    Order order = this.cart.checkout();
    this.orders.add(order);
    return order.getOrderTicket();
  }

  @Override
  public void runSession() {
    //
  }

  @Override
  public String getRole() {
    return "CUSTOMER";
  }
}
