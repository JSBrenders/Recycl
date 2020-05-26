package classes.paris;

import classes.HibernateUtil;
import classes.servlets.demandeNonTraitees;
import dataBase.rparis.DemandeEntityParis;
import org.hibernate.Session;

import java.util.Iterator;
import java.util.List;

public class demandeNonTraiteesParis {

    public static demandeNonTraitees.Response getDemandesNonTraitees () {

        demandeNonTraitees.Response response = new demandeNonTraitees.Response();

        Session session = HibernateUtil.getSessionFactory("Paris").openSession();

        List detailDepotDate = session.createQuery("FROM DemandeEntityParis d WHERE d.notournee = null ").list();

        for (Iterator iterator = detailDepotDate.iterator(); iterator.hasNext(); ) {
            DemandeEntityParis demandeNonTraitee = (DemandeEntityParis)iterator.next();
            response.resultatParis.add(demandeNonTraitee);
        }

        session.close();
        return response;
    }
}
