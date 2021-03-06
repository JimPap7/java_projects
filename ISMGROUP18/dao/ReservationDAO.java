package dao;

import model.Reservation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReservationDAO {

  private DB db = new DB();

  public Reservation getReservationById(int reservationId) throws Exception {
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    String sql = "SELECT * FROM reservation WHERE reserve_id = ?";
    try {
      con = db.getConnection();
      stmt = con.prepareStatement(sql);
      stmt.setInt(1, reservationId);
      rs = stmt.executeQuery();
      if (rs.next()) {
        Reservation reservation = new Reservation();
        reservation.setId(reservationId);
        reservation.setDate(rs.getDate("date"));
        reservation.setCustomer(new CustomerDAO().getCustomerById(rs.getInt("cust_id")));
        return reservation;
      }
      throw new Exception("Unknown reservation with id " + reservationId);
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

  public int insertReservation(Reservation reservation) throws Exception {
    Connection con = null;
    PreparedStatement stmt = null;
    String sql = "INSERT INTO reservation (date,cust_id) VALUES (?,?)";
    try {
      con = db.getConnection();
      stmt = con.prepareStatement(sql);
      stmt.setDate(1, new Date(reservation.getDate().getTime()));
      stmt.setInt(2, reservation.getCustomer().getId());
      stmt.executeUpdate();
      try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          return generatedKeys.getInt(1);
        } else {
          throw new Exception("Could not create reservation.");
        }
      }
    } catch (Exception e) {
      System.out.println("Database error when executing query.");
      throw e;
    } finally {
      if (stmt != null) {
        stmt.close();
      }
      try {
        db.close();
      } catch (Exception e) {

      }
    }
  }

  public void deleteReservation(int reservationId) throws Exception {
    Connection con = null;
    PreparedStatement stmt = null;
    String sql = "DELETE FROM reservation WHERE reserve_id = ?";
    try {
      con = db.getConnection();
      stmt = con.prepareStatement(sql);
      stmt.setInt(1, reservationId);
      stmt.executeUpdate();
    } catch (Exception e) {
      System.out.println("Database error when executing query.");
      throw e;
    } finally {
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
