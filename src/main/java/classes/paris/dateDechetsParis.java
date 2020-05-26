package classes.paris;

import classes.HibernateUtil;
import classes.servlets.dateDechets;
import dataBase.rparis.TypedechetEntityParis;
import org.hibernate.Session;

import java.util.Iterator;
import java.util.List;

public class dateDechetsParis {

    public static dateDechets.Response getDateDechets (int month, int year) {

        dateDechets.Response response = new dateDechets.Response();

        Session session = HibernateUtil.getSessionFactory("Paris").openSession();

        List detailDepotDate = session.createQuery("SELECT SUM(d.quantitedeposee), d.notypedechet FROM DetaildepotEntityParis as d, TourneeEntityParis as t " +
                "WHERE d.notournee = t.notournee AND month(t.datetournee) = :month AND year(t.datetournee) = :year GROUP BY d.notypedechet").setParameter("month", month).setParameter("year", year).list();

        for (Iterator iterator = detailDepotDate.iterator(); iterator.hasNext(); ) {
            Object[] ligne = (Object[]) iterator.next();
            TypedechetEntityParis nd = (TypedechetEntityParis) session.createQuery("FROM TypedechetEntityParis e WHERE e.notypedechet = :notypedechet").setParameter("notypedechet", ligne[1]).getSingleResult();;
            String nomDechet = nd.getNomtypedechet();
            dateDechets.StructureReponse structureReponse = new dateDechets.StructureReponse();
            structureReponse.nomDechet = nomDechet;
            structureReponse.QteTotale = (long)ligne[0];
            response.resultat.add(structureReponse);
        }
        session.close();
        return response;
    }
}
