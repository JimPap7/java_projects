package model;

public class Customer {

  private int id;
  private String name;
  private String surname;
  private String address;
  private User user;

  public int getId() {
    return id;
  }

  public Customer setId(int id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Customer setName(String name) {
    this.name = name;
    return this;
  }

  public String getSurname() {
    return surname;
  }

  public Customer setSurname(String surname) {
    this.surname = surname;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public Customer setAddress(String address) {
    this.address = address;
    return this;
  }

  public User getUser() {
    return user;
  }

  public Customer setUser(User user) {
    this.user = user;
    return this;
  }

  public Customer(int id, String name, String surname, String address, User user) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.address = address;
    this.user = user;
  }
}
