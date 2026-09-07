package com.hossam.fitnessshop.app;

import com.hossam.fitnessshop.model.Catalog;
import com.hossam.fitnessshop.model.User;
import com.hossam.fitnessshop.repository.ProductRepository;
import com.hossam.fitnessshop.repository.UserRepository;
import java.util.Scanner;

public class Main {
  private static final String USERS_FILE_PATH = "users.txt";
  private static final String PRODUCTS_FILE_PATH = "products.txt";

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    UserRepository userRepository = new UserRepository(USERS_FILE_PATH);
    ProductRepository productRepository = new ProductRepository(PRODUCTS_FILE_PATH);
    Catalog catalog = new Catalog(productRepository);
    System.out.print("Username: ");
    String username = sc.nextLine();
    User user = userRepository.findUserByUsername(username.trim().toLowerCase());
    while (user == null) {
      System.err.println(
          "Error: no account found associated with this username, please try again !");
      System.out.print("Username: ");
      username = sc.nextLine();
      user = userRepository.findUserByUsername(username.trim().toLowerCase());
    }
    System.out.print("Password: ");
    String password = sc.nextLine();
    while (!user.isPasswordMatch(password.trim())) {
      System.err.println(
          "Error: incorrect password, forgot your password ? no recovering tip for now !");
      System.out.print("Password: ");
      password = sc.nextLine();
    }

    user.runSession(sc, catalog, userRepository);
  }
}
