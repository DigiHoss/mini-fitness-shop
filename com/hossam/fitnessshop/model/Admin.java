package com.hossam.fitnessshop.model;

import com.hossam.fitnessshop.repository.UserRepository;
import java.util.List;
import java.util.Scanner;

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

  @Override
  public void runSession(Scanner sc, Catalog catalog, UserRepository userRepository) {
    System.out.println("login in as an Admin");
    System.out.println("Full name: " + super.getFullName());
    int choice = 0;
    do {
      System.out.println("-----------------------------------");
      System.out.println("Menu :");
      System.out.println("1. View catalog products");
      System.out.println("2. View all users");
      System.out.println("3. Log out");
      System.out.println("-----------------------------------");
      System.out.print("Your choice : ");
      String input = sc.nextLine();
      choice = Integer.parseInt(input);
      switch (choice) {
        case 1: {
          System.out.println("-----------------------------------");
          System.out.println("Products Manager :");
          System.out.println(viewAllProducts(catalog));
          System.out.println("-----------------------------------");
          System.out.println("-----------------------------------");
          System.out.println("Menu :");
          System.out.println("1. Add a product to the catalog");
          System.out.println("2. Modify a product in the catalog");
          System.out.println("3. Remove a product from the catalog");
          System.out.println("4. Go back");
          System.out.println("-----------------------------------");
          System.out.print("Your choice : ");
          String innerInput = sc.nextLine();
          int innerChoice = Integer.parseInt(innerInput);
          switch (innerChoice) {
            case 1:
              System.out.print("To add product to the catalog, Enter the following informations : ");
              System.out.print("Name: ");
              String nameInput = sc.nextLine();
              System.out.print("Description: ");
              String descriptionInput = sc.nextLine();
              System.out.print("Price: ");
              String priceInput = sc.nextLine();
              Double price = Double.parseDouble(priceInput);
              if (price < 0)  {
                System.err.println("Failed : cannot have a negative price on a product");
                break;
              }
              if (nameInput.length() == 0 || descriptionInput.length() == 0) {
                System.err.println("Failed : cannot have en empty name or description");
                break;
              }
              System.out.println("Product added successfully");
              addProduct(catalog, nameInput, descriptionInput, price);
              break;
            case 2:
              System.out.print("To modify an existing product, Enter his id : ");
              String idInput = sc.nextLine();
              int idModifyProduct = Integer.parseInt(idInput);
              Product product = catalog.findProductById(idModifyProduct);
              if (product == null) {
                System.err.println("The id " + idModifyProduct + " doesn't correspond on an existing product");
              } else {
                System.out.print("New name: ");
                String newName = sc.nextLine();
                System.out.print("New Description: ");
                String newDescription = sc.nextLine();
                System.out.print("New Price: ");
                String newPrice = sc.nextLine();
                double newPriceParsed = Double.parseDouble(newPrice);
                if (newPriceParsed < 0)  {
                  System.err.println("Failed : cannot have a negative price on a product");
                  break;
                }
                if (newName.length() == 0 || newDescription.length() == 0) {
                  System.err.println("Failed : cannot have en empty name or description");
                  break;
                }
                System.out.println("Product Modified successfully");
                modifyProduct(catalog, idModifyProduct, newName, newDescription, newPriceParsed);
              }
              break;
            case 3:
              System.out.print("To remove a product from the catalog, Enter his Id : ");
              String inputId = sc.nextLine();
              int idRemoveProduct = Integer.parseInt(inputId);
              if (!removeProduct(catalog, idRemoveProduct)) {
                System.err.println("The product with id " + idRemoveProduct + " isn't removed !");
              } else {
                System.out.println("Product Removed successfully");

              }

              break;

            case 4:
              break;
            default:
              System.out.println("Please enter a number from the available choices !");
          }
        }; break;
        case 2: {
          System.out.println("-----------------------------------");
          System.out.println("Users Manager :");
          System.out.println(viewAllUsers(userRepository));
          System.out.println("-----------------------------------");
          System.out.println("-----------------------------------");
          System.out.println("Additional Menu :");
          System.out.println("1. Update the informations of a user");
          System.out.println("2. Remove a user");
          System.out.println("3. Go back");
          System.out.println("-----------------------------------");
          System.out.print("Your choice : ");
          String innerInput = sc.nextLine();
          int innerChoice = Integer.parseInt(innerInput);
          switch (innerChoice) {
            case 1:
              System.out.print("To update a user's informations Enter his username : ");
              String usernameModifyUser = sc.nextLine();
              System.out.print("new Full Name : ");
              String newFullName = sc.nextLine();
              System.out.print("new Password : ");
              String newPassword = sc.nextLine();
              if (!updateUser(userRepository, newFullName, usernameModifyUser, newPassword)) {
                System.err.println("Failed to update the user");
              } else {
                System.out.println("User Updated successfully");

              }
              break;
            case 2:
              System.out.print("To remove a user Enter his username : ");
              String usernameRemoveUser = sc.nextLine();
              if (!removeUser(userRepository, usernameRemoveUser)) {
                System.err.println("User isn't removed");
              } else {
                System.out.println("User Removed successfully");

              }

              break;
            case 3:
              break;
            default:
              System.out.println("Please enter a number from the available choices !");
          }
        }; break;

        case 3:
          System.out.println("Logging out ... ! see you the next time.");
          return;

        default:
          System.out.println("Please enter a number from the available choices !");
          break;
      }

    } while (choice != 3);
  }
}
