package classes.servlets;

import classes.CompActiviteNationale;
import classes.CompActiviteSite;
import oracle.ucp.common.waitfreepool.Tuple;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

@WebServlet(name = "dechetParSite", urlPatterns = "/dechetParSite")
public class dechetParSite extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String ville = request.getParameter("ville");
        String noCentre = request.getParameter("noCentre");
        String noDechet = request.getParameter("noDechet");
        String dateStart = request.getParameter("dateStart");
        String dateEnd = request.getParameter("dateEnd");
        boolean national = Boolean.parseBoolean(request.getParameter("national"));

        int nocentre = 0;
        int nodechet = 0;

        int qteDechet = 0;

        Date date1F;
        Date date2F;
        try {
            date1F = new SimpleDateFormat("yyyy-MM-dd").parse(dateStart);
            date2F = new SimpleDateFormat("yyyy-MM-dd").parse(dateEnd);

            Tuple<Date, Date> tuple = new Tuple<>(date1F, date2F);

            if( noCentre != null && !noCentre.equals("")){
                nocentre = Integer.parseInt(noCentre);
            }
            if(noDechet != null && !noDechet.equals("")){
                nodechet = Integer.parseInt(noDechet);
            }

            //Traitement des données nationales
            if(national) {
                CompActiviteNationale compActiviteNationale = new CompActiviteNationale(nodechet, tuple);
                qteDechet = compActiviteNationale.getTotalDechets();
            }
            //Traitement des données par ville
            else {
                if (nocentre != 0 && nodechet != 0) {
                    CompActiviteSite compActiviteSite = new CompActiviteSite(nocentre, nodechet, tuple, ville);
                    qteDechet = compActiviteSite.getNbDechet();
                }
            }

            response.setContentType("text/plain");
            if(qteDechet > -1){
                String qte = String.valueOf(qteDechet);
                response.getWriter().write(qte);
            } else {
                response.getWriter().write("Pas de déchet de ce type");
            }

            response.getWriter().close();

        } catch (ParseException e) {
            e.printStackTrace();
            response.getWriter().write("Pas de déchet de ce type");
        }



    }
}
