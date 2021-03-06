package dao;

import model.Organization;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrganizationDAO {

  private DB db = new DB();

  public Organization getById(int orgId) throws Exception {
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    String sql = "SELECT * FROM organization WHERE org_id = ?";
    try {
      con = db.getConnection();
      stmt = con.prepareStatement(sql);
      stmt.setInt(1, orgId);
      rs = stmt.executeQuery();
      Organization organization = new Organization();
      if(rs.next()) {
        organization.setId(orgId);
        organization.setPhone(rs.getInt("phone"));
        organization.setLocation(rs.getString("location"));
        organization.setName(rs.getString("name"));
        organization.setUser(new User().setId(rs.getInt("user_org_id")));
        return organization;
      }
      throw new Exception("model organization not found for id " + orgId);
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

  public Organization findForAnimal(int anid) throws Exception {
    Connection con = null;
    PreparedStatement stmt1 = null;
    PreparedStatement stmt2 = null;
    ResultSet rs1 = null;
    ResultSet rs2 = null;
    String sql1 = "SELECT org_id FROM animal WHERE animal_id = ?";
    String sql2 = "SELECT * FROM organization WHERE org_id = ?";
    try {
      con = db.getConnection();
      stmt1 = con.prepareStatement(sql1);
      stmt1.setInt(1, anid);
      rs1 = stmt1.executeQuery();
      if (rs1.next()) {
        int organization_id = rs1.getInt("org_id");
        stmt2 = con.prepareStatement(sql2);
        stmt2.setInt(1, organization_id);
        rs2 = stmt2.executeQuery();
        Organization organization = new Organization();
        if (rs2.next()) {
          organization.setId(rs2.getInt("org_id"));
          organization.setPhone(rs2.getInt("phone"));
          organization.setLocation(rs2.getString("location"));
          organization.setName(rs2.getString("name"));
          User user = new User();
          user.setId(rs2.getInt("user_org_id"));
          organization.setUser(user);
          return organization;
        }
      }
      throw new Exception("model organization not found for animal with id " + anid);
    } catch (Exception e) {
      System.out.println("Database error when executing query.");
      throw e;
    } finally {
      if (rs1 != null) {
        rs1.close();
      }
      if (rs2 != null) {
        rs2.close();
      }
      if (stmt1 != null) {
        stmt1.close();
      }
      if (stmt2 != null) {
        stmt2.close();
      }
      try {
        db.close();
      } catch (SQLException e) {

      }
    }
  }
}