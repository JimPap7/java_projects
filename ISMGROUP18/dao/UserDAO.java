package dao;

import model.Organization;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

  private DB db = new DB();

  public User getUserById(int userId) throws Exception {
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    String sql = "SELECT * FROM user U LEFT JOIN organization O ON U.user_id = O.user_org_id WHERE user_id = ?";
    try {
      con = db.getConnection();
      stmt = con.prepareStatement(sql);
      stmt.setInt(1, userId);
      rs = stmt.executeQuery();
      User user = new User();
      if (rs.next()) {
        user.setId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));

        Organization organization = new Organization();
        // check if column names are correct
        organization.setId(rs.getInt("org_id"));
        organization.setPhone(rs.getInt("phone"));
        organization.setName(rs.getString("name"));
        organization.setLocation(rs.getString("location"));
        user.setOrganization(organization);
        return user;
      }
      throw new Exception("Unknown user for id " + userId);
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
      } catch (SQLException e) {

      }
    }
  }

  public User getUserByUsername(String username) throws Exception {
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    String sql = "SELECT * FROM user WHERE username = ?";
    try {
      con = db.getConnection();
      stmt = con.prepareStatement(sql);
      stmt.setString(1, username);
      rs = stmt.executeQuery();
      User user = new User();
      if (rs.next()) {
        user.setId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        return user;
      } else {
        return null;
      }
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
      } catch (SQLException e) {

      }
    }
  }

  public void insertUser(User user) throws Exception {
    Connection con = null;
    PreparedStatement stmt = null;
    String sql = "INSERT INTO user (username,password,email) VALUES (?,?,?)";
    try {
      con = db.getConnection();
      stmt = con.prepareStatement(sql);
      stmt.setString(1, user.getUsername());
      stmt.setString(2, user.getPassword());
      stmt.setString(3, user.getEmail());
      stmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println("Database error when executing query.");
      throw e;
    } finally {
      if (stmt != null) {
        stmt.close();
      }
      try {
        db.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}