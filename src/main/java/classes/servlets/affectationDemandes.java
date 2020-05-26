package classes.servlets;

import classes.HibernateUtil;
import classes.lille.demandeNonTraiteesLille;
import classes.paris.demandeNonTraiteesParis;
import dataBase.rlille.DemandeEntityLille;
import dataBase.rlille.TourneeEntityLille;
import dataBase.rparis.DemandeEntityParis;
import dataBase.rparis.TourneeEntityParis;
import global.globalVariables;
import oracle.ucp.common.waitfreepool.Tuple;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "affectationDemandes", urlPatterns = "/affectationDemandes")

public class affectationDemandes extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        demandeNonTraitees.Response reponse = new demandeNonTraitees.Response();

        if(globalVariables.Ville.equals("Lille")){
            reponse = demandeNonTraiteesLille.getDemandesNonTraitees();
            //tratiemenent des demandes non affectées de Lille

            Session session = HibernateUtil.getSessionFactory("Lille").openSession();

            for(Iterator iterator = reponse.resultatLille.iterator(); iterator.hasNext();) {
                DemandeEntityLille demande = (DemandeEntityLille)iterator.next();

                TourneeEntityLille tourneevalide = null;
                //pour chaque demande on cherche les dates des 2 tournées dans les 2 jours qui viennent

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(demande.getDateenlevement());
                calendar.add(Calendar.DATE, 1);
                Date date1 = calendar.getTime();
                calendar.add(Calendar.DATE, 1);
                Date date2 = calendar.getTime();

                List tournees = session.createQuery("FROM TourneeEntityLille t WHERE t.datetournee = :date").setParameter("date", demande.getDateenlevement()).list();

                tourneevalide = checkTourneesLille(tournees, session);

                if(tournees.isEmpty() || tourneevalide == null){
                    tournees = session.createQuery("FROM TourneeEntityLille t WHERE t.datetournee = :date2").setParameter("date2", date1).list();
                }

                tourneevalide = checkTourneesLille(tournees, session);

                if(tournees.isEmpty() || tourneevalide == null){
                    tournees = session.createQuery("FROM TourneeEntityLille t WHERE t.datetournee = :date3").setParameter("date3", date2).list();
                }

                tourneevalide = checkTourneesLille(tournees, session);

                if(tournees.isEmpty() || tourneevalide == null) {
                    //inscrire la demande dans un journal
                    response.getWriter().write("Pas de tournees valides\n");
                    response.getWriter().close();
                } else {
                    //on inscrit la demande à la tournée
                    Query insert = session.createQuery("UPDATE DemandeEntityLille d set d.notournee = :notourneevalide WHERE d.nodemande = :nodemande");
                    insert.setParameter("notourneevalide", tourneevalide.getNotournee()).setParameter("nodemande", demande.getNodemande());
                    Transaction tx = session.beginTransaction();
                    int result = insert.executeUpdate();
                    String resultat = "- Demande N"+ (char)248 + demande.getNodemande() + " affectee a la tournee N"+ (char)248 + tourneevalide.getNotournee() + ", Lignes affectees : " + result;
                    tx.commit();
                    response.getWriter().write(resultat+"\n");
                }
            }


            session.close();
            reponse.ville = "Lille";


        }else {
            reponse = demandeNonTraiteesParis.getDemandesNonTraitees();
            //tratiemenent des demandes non affectées de Paris

            Session session = HibernateUtil.getSessionFactory("Paris").openSession();

            for(Iterator iterator = reponse.resultatParis.iterator(); iterator.hasNext();) {
                DemandeEntityParis demande = (DemandeEntityParis)iterator.next();

                TourneeEntityParis tourneevalide = null;
                //pour chaque demande on cherche les dates des 2 tournées dans les 2 jours qui viennent

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(demande.getDateenlevement());
                calendar.add(Calendar.DATE, 1);
                Date date1 = calendar.getTime();
                calendar.add(Calendar.DATE, 1);
                Date date2 = calendar.getTime();

                List tournees = session.createQuery("FROM TourneeEntityParis t WHERE t.datetournee = :date1").setParameter("date1", demande.getDateenlevement()).list();

                tourneevalide = checkTourneesParis(tournees, session);

                if(tournees.isEmpty() || tourneevalide == null){
                    tournees = session.createQuery("FROM TourneeEntityParis t WHERE t.datetournee = :date1").setParameter("date1", date1).list();
                }

                tourneevalide = checkTourneesParis(tournees, session);

                if(tournees.isEmpty() || tourneevalide == null){
                    tournees = session.createQuery("FROM TourneeEntityParis t WHERE t.datetournee = :date1").setParameter("date1", date2).list();
                }

                tourneevalide = checkTourneesParis(tournees, session);

                if(tournees.isEmpty() || tourneevalide == null) {
                    //inscrire la demande dans un journal
                    response.getWriter().write("Pas de tournees libre dans les prochains jours\n");
                    response.getWriter().close();
                } else {
                    //on inscrit la demande à la tournée
                    Query insert = session.createQuery("UPDATE DemandeEntityParis d set d.notournee = :notourneevalide WHERE d.nodemande = :nodemande");
                    insert.setParameter("notourneevalide", tourneevalide.getNotournee()).setParameter("nodemande", demande.getNodemande());
                    int result = insert.executeUpdate();
                    String resultat = "- Demande N"+ (char)248 + demande.getNodemande() + " affectee a la tournee N"+ (char)248  + tourneevalide.getNotournee() + ", Lignes affectees : " + result;
                    response.getWriter().write(resultat+"\n");
                }
            }
            session.close();
            response.getWriter().close();
        }

    }

    private TourneeEntityLille checkTourneesLille (List tournees, Session session) {

        TourneeEntityLille tourneeValide = null;

        for(Iterator iterator1 = tournees.iterator(); iterator1.hasNext();){
            TourneeEntityLille tournee = (TourneeEntityLille)iterator1.next();
            long nbEnlevement = (long)session.createQuery("Select count(d) From DemandeEntityLille d WHERE d.notournee = :numtournee").setParameter("numtournee", tournee.getNotournee()).getSingleResult();

            String Imat = (String)session.createQuery("SELECT t.noimmatric FROM TourneeEntityLille t WHERE t.notournee = :notournee").setParameter("notournee", tournee.getNotournee()).getSingleResult();

            int maxElementCamion = (int)session.createQuery("SELECT c.nbenlevementmax From CamionEntityLille c Where c.noimmatric = :imat").setParameter("imat", Imat).getSingleResult();

            if(nbEnlevement < maxElementCamion) {
                tourneeValide = tournee;
                break;
            }
        }
        return tourneeValide;
    }

    private TourneeEntityParis checkTourneesParis (List tournees, Session session) {

        TourneeEntityParis tourneeValide = null;

        for(Iterator iterator1 = tournees.iterator(); iterator1.hasNext();){
            TourneeEntityParis tournee = (TourneeEntityParis) iterator1.next();

            int nbEnlevement = (int)session.createQuery("Select count(d) From DemandeEntityParis d WHERE d.notournee = :numtournee").setParameter("numtournee", tournee.getNotournee()).getSingleResult();

            String Imat = (String)session.createQuery("SELECT t.noimmatric FROM TourneeEntityParis t WHERE t.notournee = :notournee").setParameter("notournee", tournee.getNotournee()).getSingleResult();

            int maxElementCamion = (int)session.createQuery("Select c.nbenlevementmax From CamionEntityParis c Where c.noimmatric = :imat").setParameter("imat", Imat).getSingleResult();

            if(nbEnlevement < maxElementCamion) {
                tourneeValide = tournee;
                break;
            }
        }
        return tourneeValide;
    }
}



