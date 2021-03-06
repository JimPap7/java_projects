package controller;

import dao.*;
import model.Organization;
import model.Reservation;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class AdoptController extends HttpServlet {

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = new PrintWriter(response.getWriter(), true);
    try {
      if (request.getSession(true).getAttribute("loggedUser") == null) {
        //request.getSession(true).setAttribute("notAuthorized", true);
        //response.sendRedirect("index.jsp");
        //return;
        User loggedUser = new UserDAO().getUserById(1);
        request.getSession(true).setAttribute("loggedUser", loggedUser);
      } //else {
        int animalId = Integer.parseInt(request.getParameter("petId"));
        AnimalDAO andao = new AnimalDAO();
        boolean available = andao.isAvailable(animalId);
        if (!available) {
          response.sendRedirect("notAvailable.jsp");
        } else {
          Reservation reservation = new Reservation();
          ReservationDAO resdao = new ReservationDAO();
          reservation.setDate(new Date());
          reservation.setCustomer(new CustomerDAO().getCustomerByUserId((User) request.getSession(true).getAttribute("loggedUser")));
          int reservationId = resdao.insertReservation(reservation);
          andao.reserveAnimal(animalId, reservationId);
        }
        OrganizationDAO orgDao = new OrganizationDAO();
        Organization org = orgDao.findForAnimal(animalId);
        request.getSession(true).setAttribute("organization", org);
        response.sendRedirect("AdoptInfo.jsp");
      //}
    } catch (Exception ex) {
      out.println("Exception: " + ex.getMessage());
    }
  }
}