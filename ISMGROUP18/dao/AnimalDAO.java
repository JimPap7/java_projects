package dao;

import model.Animal;
import model.Organization;
import model.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {

  private DB db = new DB();

  public Animal getPetById(int petId) throws Exception {
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    String sql = "SELECT * FROM animal WHERE animal_id = ?";
    try {
      con = db.getConnection();
      stmt = con.prepareStatement(sql);
      stmt.setInt(1, petId);
      rs = stmt.executeQuery();
      if (rs.next()) {
        Animal animal = new Animal();
        animal.setId(rs.getInt("id"));
        animal.setSpecies(rs.getString("species"));
        animal.setColour(rs.getString("colour"));
        animal.setAge(rs.getString("age"));
        animal.setComments(rs.getString("comments"));
        animal.setImagePath(rs.getString("imagePath"));
        if (rs.getInt("reserve_id") != 0) {
          Reservation reservation = new Reservation();
          reservation.setId(rs.getInt("reserve_id"));
          animal.setReservation(reservation);
        }
        Organization organization = new Organization();
        organization.setId(rs.getInt("org_id"));
        animal.setOrganization(organization);
        animal.setRace(rs.getString("race"));
        animal.setSex(rs.getString("sex"));
        return animal;
      }
      return null;
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

  public List<Animal> searchPet(String species, String race, String sex, String colour, String age) throws Exception {
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    String sql = "SELECT * FROM animal WHERE species = ? AND race = ? AND sex = ? AND colour = ? AND age = ? AND reserve_id = NULL";
    List<Animal> animals = new ArrayList<Animal>();
    try {
      con = db.getConnection();
      stmt = con.prepareStatement(sql);
      stmt.setString(1, species);
      stmt.setString(2, race);
      stmt.setString(3, sex);
      stmt.setString(4, colour);
      stmt.setString(5, age);
      rs = stmt.executeQuery();
      while (rs.next()) {
        final Animal animal = new Animal(rs.getInt("id"),
            rs.getString("species"),
            rs.getString("colour"),
            rs.getString("age"),
            rs.getString("comments"),
            rs.getString("imagePath"),
            rs.getString("race"),
            rs.getString("sex"));
        animals.add(animal);
        Organization organization = new Organization().setId(rs.getInt("org_id"));
        animal.setOrganization(organization);
        if (rs.getInt("reserve_id") != 0) {
          Reservation reservation = new Reservation();
          reservation.setId(rs.getInt("reserve_id"));
          animal.setReservation(reservation);
        }
      }
      return animals;
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

  public List<Animal> getReservedForOrganization(int orgId) throws Exception {
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    String sql = "SELECT * FROM animal WHERE org_id = ? AND reserve_id != NULL";
    List<Animal> animals = new ArrayList<Animal>();
    try {
      con = db.getConnection();
      stmt = con.prepareStatement(sql);
      stmt.setInt(1, orgId);
      rs = stmt.executeQuery();
      while (rs.next()) {
        Animal animal = new Animal(rs.getInt("animal_id"),
            rs.getString("species"),
            rs.getString("colour"),
            rs.getString("age"),
            rs.getString("comments"),
            rs.getString("imagePath"),
            rs.getString("race"),
            rs.getString("sex"));
        animals.add(animal);
        Organization organization = new Organization().setId(orgId);
        animal.setOrganization(organization);
        if (rs.getInt("reserve_id") != 0) {
          Reservation reservation = new Reservation();
          reservation.setId(rs.getInt("reserve_id"));
          animal.setReservation(reservation);
        }
      }
      return animals;
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

  public void insertPet(Animal pet) throws Exception {
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    String sqlQuery = "INSERT INTO animal (species,colour,age,comments,reserve_id,imagePath,org_id,race,sex) " +
        "VALUES (?,?,?,?,?,?,?,?,?)";
    try {
      con = db.getConnection();


      stmt = con.prepareStatement(sqlQuery);
      stmt.setString(1, pet.getSpecies());
      stmt.setString(2, pet.getColour());
      stmt.setString(3, pet.getAge());
      stmt.setString(4, pet.getComments());
      if(pet.getReservation() != null) {
        stmt.setInt(5, pet.getReservation().getId());
      }
      stmt.setString(6, pet.getImagePath());
      stmt.setInt(7, pet.getOrganization().getId());
      stmt.setString(8, pet.getRace());
      stmt.setString(9, pet.getSex());
      stmt.executeUpdate();
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

  public void removePet(int petId) throws Exception {
    Connection con = null;
    PreparedStatement stmt = null;
    String sql = "DELETE FROM animal WHERE animal_id = ?";
    try {
      con = db.getConnection();
      stmt = con.prepareStatement(sql);
      stmt.setInt(1, petId);
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

  public void reserveAnimal(int petId, int reservationId) throws Exception {
    Connection con = null;
    PreparedStatement stmt = null;
    String sql = "UPDATE animal SET reserve_id = ? WHERE animal_id = ?";
    try {
      con = db.getConnection();
      stmt = con.prepareStatement(sql);
      stmt.setInt(1, reservationId);
      stmt.setInt(2, petId);
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

  public void removeReservation(int petId) throws Exception {
    Connection con = null;
    PreparedStatement stmt = null;
    String sql = "UPDATE animal SET reserve_id = NULL WHERE animal_id = ?";
    try {
      con = db.getConnection();
      stmt = con.prepareStatement(sql);
      stmt.setInt(1, petId);
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

  public List<String> getAvailableRaces(String species) throws Exception {
    List<String> availableRaces = new ArrayList<>();
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    String sql = "SELECT DISTINCT race FROM animal WHERE species = ?";
    try {
      con = db.getConnection();
      stmt = con.prepareStatement(sql);
      stmt.setString(1, species);
      rs = stmt.executeQuery();
      while(rs.next()) {
        availableRaces.add(rs.getString("race"));
      }
      return availableRaces;
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

  public boolean isAvailable(int petId) throws Exception {
    return getPetById(petId) != null;
  }

}