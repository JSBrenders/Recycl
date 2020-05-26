package classes.servlets;

import classes.lille.dateDemandeLille;
import classes.paris.dateDemandeParis;
import global.globalVariables;
import oracle.ucp.common.waitfreepool.Tuple;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "filtreDateDemandes", urlPatterns = "/filtreDateDemandes")
public class dateDemande extends HttpServlet implements Servlet {


    public static class StructureReponse {
        public String RaisonSociale;
        public int noDemande;
        public int notournee;
        public String dateDemande;
        //liste clé déchet : quantité
        public List<Tuple<String, Integer>> QteParDechet = new ArrayList<>();
    }

    public static class Response {
        public List<StructureReponse> resultat = new ArrayList<>();
        public Date dateRequete;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Response result = search(request, response);
        request.setAttribute("result", result);
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/dateDemande.jsp");
        rd.forward(request, response);
    }

    protected Response search(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {


        String dateDemande = req.getParameter("dateDemande");
        Response response = new Response();

        if(globalVariables.Ville.equals("Lille")){
            response = dateDemandeLille.getDateDemande(dateDemande);
        } else if (globalVariables.Ville.equals("Paris")) {
            response = dateDemandeParis.getDateDemande(dateDemande);
        }

        return response;


    }
}
