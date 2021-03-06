package controller;

import dao.AnimalDAO;
import model.Animal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.StringJoiner;


public class SearchController extends HttpServlet {
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    response.setContentType("text/html; charset=UTF-8");
    HttpSession session = request.getSession(true);
    PrintWriter out = new PrintWriter(response.getWriter(), true);

    String species = request.getParameter("species");
    String race = request.getParameter("race");
    String sex = request.getParameter("sex");
    String colour = request.getParameter("colour");
    String age = request.getParameter("age");

    try {

      AnimalDAO andao = new AnimalDAO();
      List<Animal> results = andao.searchPet(species, race, sex, colour, age);
      session.setAttribute("SearchResults", results);
      response.sendRedirect("SearchResults.jsp");
    } catch (Exception ex) {
      out.println("Exception: " + ex.getMessage());
    }
  }


  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    PrintWriter out = new PrintWriter(response.getWriter(), true);
    try {
      String species = request.getParameter("species");
      if(species != null) {
        StringJoiner joiner = new StringJoiner(";");
        List<String> availableRaces = new AnimalDAO().getAvailableRaces(species);
        for (String race : availableRaces) {
          joiner.add(race);
        }
        out.println(joiner.toString());
      } else {
        response.sendRedirect("http://ism.dmst.aueb.gr/ismgroup18/petshelter/adopt.jsp");
      }
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}








