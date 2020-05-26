package classes.servlets;

import classes.lille.filtreEntrepriseLille;
import classes.paris.filtreEntrepriseParis;
import dataBase.rlille.EntrepriseEntityLille;
import dataBase.rparis.EntrepriseEntityParis;
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
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "filtreEntreprise", urlPatterns = "/filtreEntreprise")
public class filtreEntreprise extends HttpServlet implements Servlet {


    public static class Response {
        public List<StructureResponse> resultat = new ArrayList<>();
        public String entrepriseReference;
        public String ville;
        public int nbDemandeRef;
    }

    public static class StructureResponse {
        public EntrepriseEntityParis entrepriseEntityParis;
        public EntrepriseEntityLille entrepriseEntityLille;
        public int nbDemande;
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String entreprise = request.getParameter("entreprise");

        if (!entreprise.equals("")) {

            Response result = search(request, response, entreprise);
            request.setAttribute("result", result);
        }
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/filtreEntreprise.jsp");
        rd.forward(request, response);
    }

    protected Response search(HttpServletRequest req, HttpServletResponse res, String entreprise)
            throws ServletException, IOException {


        Response response = new Response();



        if(globalVariables.Ville.equals("Lille")){
            response = filtreEntrepriseLille.getEntreprises(entreprise);
        } else if(globalVariables.Ville.equals("Paris")){
            response = filtreEntrepriseParis.getEntreprises(entreprise);
        }


        response.ville = globalVariables.Ville;

        return response;
    }
}
