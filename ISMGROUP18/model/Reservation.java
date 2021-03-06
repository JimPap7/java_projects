package model;

import java.util.Date;

public class Reservation {

  private int id;
  private Date date;
  private Customer customer;

  public int getId() {
    return id;
  }

  public Reservation setId(int id) {
    this.id = id;
    return this;
  }

  public Date getDate() {
    return date;
  }

  public Reservation setDate(Date date) {
    this.date = date;
    return this;
  }

  public Customer getCustomer() {
    return customer;
  }

  public Reservation setCustomer(Customer customer) {
    this.customer = customer;
    return this;
  }
}
