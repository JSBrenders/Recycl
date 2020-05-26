package classes.servlets;

import global.globalVariables;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "setCurrentVille", urlPatterns = "/setCurrentVille")
public class setCurrentVille extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newVille = request.getParameter("newVille");
        if(newVille.equals("Lille") || newVille.equals("Paris")) {
            globalVariables.Ville = newVille;
        }else {
            response.getWriter().write("Ville inconnue");
            response.getWriter().close();
        }
    }
}
