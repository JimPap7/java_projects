package model;

public class User {

  private int id;
  private String username;
  private String password;
  private String email;
  private Organization organization;

  public int getId() {
    return id;
  }

  public User setId(int id) {
    this.id = id;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public User setUsername(String username) {
	  this.username = username;
	  return this;
  }

  public String getPassword() {
  	  return password;
  }

  public User setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getEmail() {
    	  return email;
  }

  public Organization getOrganization() {
    return organization;
  }

  public User setOrganization(Organization organization) {
    this.organization = organization;
    return this;
  }

  public User setEmail(String email) {
      this.email = email;
      return this;
  }
}
