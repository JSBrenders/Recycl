package classes.servlets;

import classes.lille.filtreEmployeLille;
import classes.paris.filtreEmployeParis;
import global.globalVariables;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "filtreEmploye", urlPatterns = "/filtreEmploye")
public class filtreEmploye extends HttpServlet implements Servlet {



    public static class StructureReponse {
        public String nom;
        public String prenom;
        public int noEmploye;
        public int nbTournee;
    }

    public static class Response {
        public List<StructureReponse> resultat = new ArrayList<>();
        public int nbTournée;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Response result = search(request, response);
        request.setAttribute("result", result);
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/filtreEmploye.jsp");
        rd.forward(request, response);
    }

    protected Response search(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {


        Response response = new Response();

        String nbTournee = req.getParameter("nbTournee");



        try {
            int nombreTournee = Integer.parseInt(nbTournee);


            if(globalVariables.Ville.equals("Lille")){
                response = filtreEmployeLille.getEmployees(nombreTournee);
            } else if(globalVariables.Ville.equals("Paris")){
                response = filtreEmployeParis.getEmployees(nombreTournee);
            }

            response.nbTournée = nombreTournee;
            return response;

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }

    }
}
