package com.hossam.fitnessshop.model;

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

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isPasswordMatch(String candidatePassword) {
    return this.password.equals(candidatePassword);
  }
}
