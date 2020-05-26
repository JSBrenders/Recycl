package classes.servlets;

import classes.lille.dateDechetsLille;
import classes.paris.dateDechetsParis;
import global.globalVariables;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(name = "filtreDateDechets", urlPatterns = "/filtreDateDechets")
public class dateDechets extends HttpServlet {


    public static class StructureReponse {
        public String nomDechet;
        public long QteTotale;
    }

    public static class Response {
        public List<StructureReponse> resultat = new ArrayList<>();
        public int month;
        public int year;
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Response result = search(request, response);
        request.setAttribute("result", result);
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/dateDechets.jsp");
        rd.forward(request, response);
    }

    private Response search(HttpServletRequest req, HttpServletResponse res) {

        String dateDechets = req.getParameter("dateDechets");


        Response response = new Response();


        try {
            Date date = new SimpleDateFormat("yyyy-MM").parse(dateDechets);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            int month = calendar.get(Calendar.MONTH) + 1;
            int year = calendar.get(Calendar.YEAR);


            if(globalVariables.Ville.equals("Lille")){
                response = dateDechetsLille.getDateDechets(month, year);
            } else if(globalVariables.Ville.equals("Paris")){
                response = dateDechetsParis.getDateDechets(month, year);
            }

            response.month = month;
            response.year = year;

            return response;

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
