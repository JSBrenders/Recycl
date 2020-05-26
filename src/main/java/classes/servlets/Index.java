package classes.servlets;
import classes.indexLoading;
import dataBase.rlille.CentretraitementEntityLille;
import dataBase.rlille.EntrepriseEntityLille;
import dataBase.rlille.TypedechetEntityLille;
import dataBase.rparis.CentretraitementEntityParis;
import dataBase.rparis.EntrepriseEntityParis;
import dataBase.rparis.TypedechetEntityParis;
import global.globalVariables;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Index", urlPatterns = "")
public class Index extends HttpServlet {

    public static class Response {
        public List entreprises;
        public List dechets;
        public List centres;
        public String ville;

        public Response () {
            if (globalVariables.Ville.equals("Lille")){
                entreprises = new ArrayList<EntrepriseEntityLille>();
                dechets = new ArrayList<TypedechetEntityLille>();
                centres = new ArrayList<CentretraitementEntityLille>();
            } else {
                entreprises = new ArrayList<EntrepriseEntityParis>();
                dechets = new ArrayList<TypedechetEntityParis>();
                centres = new ArrayList<CentretraitementEntityParis>();
            }
        }
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String ville = request.getParameter("ville");

        if(ville != null && !ville.equals("")) {
            globalVariables.Ville = ville;
        }

        Response reponse = new Response();

        reponse = indexLoading.loadIndex(globalVariables.Ville);

        request.setAttribute("reponse", reponse);
        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
    }
}
