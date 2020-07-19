package classes.servlets;

import classes.HibernateUtil;
import org.hibernate.Session;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DatabaseStart", urlPatterns = "/DatabaseStart")
public class DatabaseStart extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String ville = request.getParameter("ville");

        Session sessionLille = HibernateUtil.getSessionFactory(ville).openSession();

        boolean reponse;

        List dechets = sessionLille.createQuery("from TypedechetEntity" + ville).list();
        if (dechets.isEmpty())
        {
            reponse = true;
        }
        else
        {
            reponse = false;
        }

        response.getWriter().write(String.valueOf(reponse));

    }
}
