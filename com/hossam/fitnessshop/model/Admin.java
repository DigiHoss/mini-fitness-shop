package com.hossam.fitnessshop.model;

import java.util.List;

public class Admin extends User {
  public Admin(String fullName, String username, String password) {
    super(fullName, username, password);
  }

  public void addProduct(Catalog catalog, String name, String description, double price) {
    catalog.addProduct(name, description, price);
  }

  public boolean removeProduct(Catalog catalog, int id) {
    return catalog.removeProduct(id);
  }

  public boolean modifyProduct(
      Catalog catalog, int id, String newName, String newDescription, double newPrice) {
    return catalog.modifyProduct(id, newName, newDescription, newPrice);
  }

  public String viewAllProducts(Catalog catalog) {
    List<Product> products = catalog.getAllProducts();
    StringBuilder sb = new StringBuilder();
    for (Product product : products) {
      sb.append(product.toString());
    }
    return sb.toString();
  }

  public String viewAllUsers(UserRepository userRepository) {
    List<User> users = userRepository.readUsers();
    StringBuilder sb = new StringBuilder();
    for (User user : users) {
      sb.append(user);
    }
    return sb.toString();
  }

  public boolean updateUser(
      UserRepository userRepository, String fullName, String username, String password) {
    User user = userRepository.findUserByUsername(username);
    if (user == null) {
      return false;
    }
    user.setFullName(fullName);
    user.setPassword(password);
    userRepository.updateUser(user);
    return true;
  }

  @Override
  public String getRole() {
    return "ADMIN";
  }

  public boolean removeUser(UserRepository userRepository, String username) {
    return userRepository.deleteUser(username);
  }
}
