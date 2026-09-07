package com.hossam.fitnessshop.model;

import com.hossam.fitnessshop.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

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
  public void runSession(Scanner sc, Catalog catalog, UserRepository userRepository) {
    // System.out.println("customer session active");
    int choice = 0;
    System.out.println("login in as a Customer");
    System.out.println("Full name: " + super.getFullName());
    do {
      System.out.println("-----------------------------------");
      System.out.println("Menu :");
      System.out.println("1. View catalog products");
      System.out.println("2. View my actual cart");
      System.out.println("3. Confirm your selected products");
      System.out.println("4. Log out");
      System.out.println("-----------------------------------");
      System.out.print("Your choice : ");
      String input = sc.nextLine();
      choice = Integer.parseInt(input);
      switch (choice) {
        case 1: {
          System.out.println("-----------------------------------");
          System.out.println("Available products :");
          System.out.println(viewCatalogProducts(catalog));
          System.out.println("-----------------------------------");
          System.out.println("-----------------------------------");
          System.out.println("Menu :");
          System.out.println("1. Add a product to my cart");
          System.out.println("2. Go back");
          System.out.println("-----------------------------------");
          System.out.print("Your choice : ");
          String innerInput = sc.nextLine();
          int innerChoice = Integer.parseInt(innerInput);
          switch (innerChoice) {
            case 1:
              System.out.print("To add product to your cart, Enter his Id: ");
              String inputId = sc.nextLine();
              int id = Integer.parseInt(inputId);
              Product selectedProduct = catalog.findProductById(id);
              if (selectedProduct == null) {
                System.out.println("Product with id : " + id + " does not exist !");
              } else {
                this.cart.addProduct(selectedProduct);
                System.out.println("Product with id : " + id + " Added Successfully");
              }
              break;
            case 2:
              System.out.println("-----------------------------------");
              System.out.println("Menu :");
              System.out.println("1. View catalog products");
              System.out.println("2. View my actual cart");
              System.out.println("3. Confirm your selected products");
              System.out.println("4. Log out");
              System.out.println("-----------------------------------");
              break;
              default:
                System.out.println("Please enter a number from the available choices !");
          }
        }; break;
        case 2: {
          System.out.println("-----------------------------------");
          System.out.println("Cart's products :");
          System.out.println(this.cart.viewCartProducts());
          System.out.println("Total to pay : " + this.cart.getTotalPrice());
          System.out.println("-----------------------------------");
          System.out.println("-----------------------------------");
          System.out.println("Additional Menu :");
          System.out.println("1. Remove a product from my cart");
          System.out.println("2. Go back");
          System.out.println("-----------------------------------");
          System.out.print("Your choice : ");
          String innerInput = sc.nextLine();
          int innerChoice = Integer.parseInt(innerInput);
          switch (innerChoice) {
            case 1:
              System.out.print("To remove a product from your cart, Enter his Id: ");
              String inputId = sc.nextLine();
              int id = Integer.parseInt(inputId);
              this.cart.removeProduct(id);
              break;
            case 2:
              System.out.println("-----------------------------------");
              System.out.println("Menu :");
              System.out.println("1. View catalog products");
              System.out.println("2. View my actual cart");
              System.out.println("3. Confirm your selected products");
              System.out.println("4. Log out");
              System.out.println("-----------------------------------");
              break;
            default:
              System.out.println("Please enter a number from the available choices !");
          }
        }; break;
        case 3: {
          System.out.println("To confirm your products to checkout, please enter \"YES\"");
          System.out.print("Your enter : ");
          String innerInput = sc.nextLine();
          if (innerInput.trim().toUpperCase().equals("YES")) {
            System.out.println("Passed");
            System.out.println(checkout());
          } else {
            System.out.println("Refused ! please reselect again the choice 3 and follow the instructions !");
          }
        }; break;
        case 4:
          System.out.println("Logging out ... ! see you the next time.");
          return;

          default:
            System.out.println("Please enter a number from the available choices !");
            break;
      }

    } while (choice != 4);
  }

  @Override
  public String getRole() {
    return "CUSTOMER";
  }
}
