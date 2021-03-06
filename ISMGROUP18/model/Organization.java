package model;

public class Organization {

  private int id;
  private int phone;
  private String location;
  private String name;
  private User user;

  public int getId() {
    return id;
  }

  public Organization setId(int id) {
    this.id = id;
    return this;
  }

  public int getPhone() {
    return phone;
  }

  public Organization setPhone(int phone) {
    this.phone = phone;
    return this;
  }

  public String getLocation() {
    return location;
  }

  public Organization setLocation(String location) {
    this.location = location;
    return this;
  }

  public String getName() {
    return name;
  }

  public Organization setName(String name) {
    this.name = name;
    return this;
  }

  public User getUser() {
    return user;
  }

  public Organization setUser(User user) {
    this.user = user;
    return this;
  }
}