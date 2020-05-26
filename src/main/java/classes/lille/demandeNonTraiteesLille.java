package classes.lille;

import classes.HibernateUtil;
import classes.servlets.demandeNonTraitees;
import dataBase.rlille.DemandeEntityLille;
import dataBase.rparis.DemandeEntityParis;
import org.hibernate.Session;

import java.util.Iterator;
import java.util.List;

public class demandeNonTraiteesLille {

    public static demandeNonTraitees.Response getDemandesNonTraitees () {

        demandeNonTraitees.Response response = new demandeNonTraitees.Response();

        Session session = HibernateUtil.getSessionFactory("Lille").openSession();

        List detailDepotDate = session.createQuery("FROM DemandeEntityLille d WHERE d.notournee = null ").list();

        for (Iterator iterator = detailDepotDate.iterator(); iterator.hasNext(); ) {
            DemandeEntityLille demandeNonTraitee = (DemandeEntityLille) iterator.next();
            response.resultatLille.add(demandeNonTraitee);
        }

        session.close();
        return response;
    }
}
