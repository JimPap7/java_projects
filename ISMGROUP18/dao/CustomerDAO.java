package dao;

import model.Customer;
import model.Reservation;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerDAO {

  private DB db = new DB();

  public Customer getCustomerById(int customerId) throws Exception {
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    String sql = "SELECT * FROM customer WHERE cust_id = ?";
    try {
      con = db.getConnection();
      stmt = con.prepareStatement(sql);
      stmt.setInt(1, customerId);
      rs = stmt.executeQuery();
      if (rs.next()) {
        Customer customer = new Customer(
            customerId,
            rs.getString("cust_name"),
            rs.getString("cust_surname"),
            rs.getString("cust_address"),
            new UserDAO().getUserById(rs.getInt("user_id"))
        );
        return customer;
      }
      throw new Exception("Unknown customer for id " + customerId);
    } catch (Exception e) {
      System.out.println("Database error when executing query.");
      throw e;
    } finally {
      if (rs != null) {
        rs.close();
      }
      if (stmt != null) {
        stmt.close();
      }
      try {
        db.close();
      } catch (Exception e) {

      }
    }
  }

  public Customer getCustomerByUserId(User user) throws Exception {
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    String sql = "SELECT * FROM customer WHERE user_id = ?";
    try {
      con = db.getConnection();
      stmt = con.prepareStatement(sql);
      stmt.setInt(1, user.getId());
      rs = stmt.executeQuery();
      if (rs.next()) {
        Customer customer = new Customer(
            rs.getInt("cust_id"),
            rs.getString("cust_name"),
            rs.getString("cust_surname"),
            rs.getString("cust_address"),
            user
        );
        return customer;
      }
      throw new Exception("Unknown customer for user id " + user);
    } catch (Exception e) {
      System.out.println("Database error when executing query.");
      throw e;
    } finally {
      if (rs != null) {
        rs.close();
      }
      if (stmt != null) {
        stmt.close();
      }
      try {
        db.close();
      } catch (Exception e) {

      }
    }
  }

}
