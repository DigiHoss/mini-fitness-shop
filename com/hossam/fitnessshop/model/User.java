package com.hossam.fitnessshop.model;

import com.hossam.fitnessshop.repository.UserRepository;
import java.util.Scanner;

public abstract class User {
  private String fullName;
  private String username;
  private String password;

  public User(String fullName, String username, String password) {
    this.fullName = fullName;
    this.username = username;
    this.password = password;
  }

  public String getFullName() {
    return this.fullName;
  }

  public String getUsername() {
    return this.username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public boolean isPasswordMatch(String candidatePassword) {
    return this.password.equals(candidatePassword);
  }

  public abstract void runSession(Scanner sc, Catalog catalog, UserRepository userRepository);

  public abstract String getRole();

  @Override
  public String toString() {
    return "Full name : " + this.fullName + "\nUsername : " + this.username + "\n";
  }
}
