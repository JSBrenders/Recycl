package classes.servlets;

import classes.lille.demandeNonTraiteesLille;
import classes.paris.demandeNonTraiteesParis;
import dataBase.rlille.DemandeEntityLille;
import dataBase.rparis.DemandeEntityParis;
import global.globalVariables;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "demandeNonTraitees", urlPatterns = "/demandeNonTraitees")
public class demandeNonTraitees extends HttpServlet {




    public static class Response {
        public List<DemandeEntityParis> resultatParis = new ArrayList<>();
        public List<DemandeEntityLille> resultatLille = new ArrayList<>();
        public String ville;
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Response result = search(request, response);
        request.setAttribute("result", result);
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/demandeNonTraitees.jsp");
        rd.forward(request, response);
    }

    private Response search(HttpServletRequest req, HttpServletResponse res) {


        Response response = new Response();

        if(globalVariables.Ville.equals("Lille")){
            response = demandeNonTraiteesLille.getDemandesNonTraitees();
        } else if(globalVariables.Ville.equals("Paris")){
            response = demandeNonTraiteesParis.getDemandesNonTraitees();
        }
        response.ville = globalVariables.Ville;

        return response;


    }
}
