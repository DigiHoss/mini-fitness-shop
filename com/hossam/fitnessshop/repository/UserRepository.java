package com.hossam.fitnessshop.repository;

import com.hossam.fitnessshop.model.Admin;
import com.hossam.fitnessshop.model.Customer;
import com.hossam.fitnessshop.model.User;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

public class UserRepository {
  private String filePath;

  public UserRepository(String filePath) {
    this.filePath = filePath;
  }

  public List<User> readUsers() {
    List<User> users = new ArrayList<User>();
    Path path = Path.of(this.filePath);
    try (BufferedReader reader = Files.newBufferedReader(path)) {
      String line = null;
      while ((line = reader.readLine()) != null) {
        String[] userData = line.split(",");
        if (userData[0].equals("ADMIN")) {
          User admin = new Admin(userData[3], userData[1], userData[2]);
          users.add(admin);
        } else {
          User customer = new Customer(userData[3], userData[1], userData[2]);
          users.add(customer);
        }
      }
    } catch (IOException e) {
      System.err.format("IOException: %s%n", e);
    }
    return users;
  }

  public void writeUsers(List<User> users) {
    Path path = Path.of(this.filePath);
    try (BufferedWriter writer = Files.newBufferedWriter(path)) {
      for (User user : users) {
        writer.write(
            user.getRole()
                + ","
                + user.getUsername()
                + ","
                + user.getPassword()
                + ","
                + user.getFullName()
                + "\n");
      }

    } catch (IOException e) {
      System.err.format("IOException : %s%n", e);
    }
  }

  public User findUserByUsername(String username) {
    List<User> users = readUsers();
    for (User user : users) {
      if (user.getUsername().equals(username)) {
        return user;
      }
    }
    return null;
  }

  public void updateUser(User user) {
    List<User> users = readUsers();
    OptionalInt positionUserCandidate = OptionalInt.empty();
    int counter = 0;
    for (User userCandidate : users) {
      if (userCandidate.getUsername().equals(user.getUsername())) {
        positionUserCandidate = OptionalInt.of(counter);
      }
      counter++;
    }
    users.set(positionUserCandidate.orElseThrow(), user);
    writeUsers(users);
  }

  public boolean deleteUser(String username) {
    List<User> users = readUsers();
    User userCandidate = null;
    for (User user : users) {
      if (user.getUsername().equals(username)) {
        userCandidate = user;
      }
    }
    if (userCandidate == null) {
      return false;
    }
    users.remove(userCandidate);
    writeUsers(users);
    return true;
  }
}
