package controller;

import dao.AnimalDAO;
import dao.ReservationDAO;
import dao.UserDAO;
import model.Animal;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ShelterController extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = new PrintWriter(response.getWriter(), true);
    User loggedUser = (User) request.getSession(true).getAttribute("loggedUser");
    boolean adopted = Boolean.parseBoolean(request.getParameter("adopted"));
    int petId = Integer.parseInt(request.getParameter("petId"));
    try {
      if (loggedUser == null || loggedUser.getOrganization() == null) {
//        request.getSession(true).setAttribute("notAuthorized", true);
//        response.sendRedirect("index.jsp");
//        return;
        loggedUser = new UserDAO().getUserById(2);
        request.getSession(true).setAttribute("loggedUser", loggedUser);
      }
      AnimalDAO animalDAO = new AnimalDAO();
      ReservationDAO reservationDAO = new ReservationDAO();
      Animal pet = animalDAO.getPetById(petId);
      if(adopted) {
        animalDAO.removePet(petId);
      } else {
        animalDAO.removeReservation(petId);
      }
      reservationDAO.deleteReservation(pet.getReservation().getId());
      doGet(request, response);
    } catch (Exception ex) {
      out.println("Exception: " + ex.getMessage());
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = new PrintWriter(response.getWriter(), true);
    User loggedUser = (User) request.getSession(true).getAttribute("loggedUser");
    try {
      if (loggedUser == null || loggedUser.getOrganization() == null) {
//        request.getSession(true).setAttribute("notAuthorized", true);
//        response.sendRedirect("index.jsp");
//        return;
        loggedUser = new UserDAO().getUserById(2);
        request.getSession(true).setAttribute("loggedUser", loggedUser);
      }
      AnimalDAO andao = new AnimalDAO();
      List<Animal> reserved = andao.getReservedForOrganization(loggedUser.getOrganization().getId());
      request.getSession(true).setAttribute("reserved", reserved);
      response.sendRedirect("organizationmgmt.jsp");
    } catch (Exception ex) {
      out.println("Exception: " + ex.getMessage());
    }
  }
}
