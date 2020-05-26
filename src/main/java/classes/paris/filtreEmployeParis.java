package classes.paris;

import classes.HibernateUtil;
import classes.servlets.filtreEmploye;
import dataBase.rparis.EmployeEntityParis;
import org.hibernate.Session;

import java.util.Iterator;
import java.util.List;

public class filtreEmployeParis {

    public static filtreEmploye.Response getEmployees (int nombreTournee){

        filtreEmploye.Response response = new filtreEmploye.Response();

        Session session = HibernateUtil.getSessionFactory("Paris").openSession();

        List employees = session.createQuery("SELECT e FROM EmployeEntityParis e WHERE (SELECT COUNT(f) FROM TourneeEntityParis f WHERE f.noemploye = e.noemploye) <= cast(:nbTournee as integer) ").setParameter("nbTournee", nombreTournee).list();


        for (Iterator iterator = employees.iterator(); iterator.hasNext(); ) {
            EmployeEntityParis employe = (EmployeEntityParis) iterator.next();

            //on recherche les informations liée à la tournée
            Object nbTourneesEmploye = session.createQuery("SELECT COUNT(f) FROM TourneeEntityParis f WHERE f.noemploye = cast(:noemploy as integer)").setParameter("noemploy", employe.getNoemploye()).getSingleResult();

            //on crée une instance de structureReponse

            filtreEmploye.StructureReponse structureReponse = new filtreEmploye.StructureReponse();
            structureReponse.nbTournee = Integer.parseInt(nbTourneesEmploye.toString());
            structureReponse.noEmploye = employe.getNoemploye();
            structureReponse.nom = employe.getNom();
            structureReponse.prenom = employe.getPrenom();

            response.nbTournée = nombreTournee;
            response.resultat.add(structureReponse);
        }

        session.close();
        return response;
    }
}
