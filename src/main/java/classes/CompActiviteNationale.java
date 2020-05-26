package classes;

import global.globalVariables;
import oracle.ucp.common.waitfreepool.Tuple;
import org.hibernate.Session;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CompActiviteNationale {

    private int numdechet;
    private Tuple<Date, Date> periode;


    public CompActiviteNationale (int _numdechet, Tuple<Date, Date> _periode) {
        numdechet =_numdechet;
        periode = _periode;
    }


    public int getTotalDechets () {

        int totaldechets = 0;

        for (globalVariables.ListeVilles ville : globalVariables.ListeVilles.values()) {

            Session session = HibernateUtil.getSessionFactory(ville.toString()).openSession();


            List sites = session.createQuery(String.format("SELECT c.nocentre FROM CentretraitementEntity" + ville + " c")).list();

            for(Iterator iterator = sites.iterator(); iterator.hasNext();){
                int site = (int)iterator.next();
                CompActiviteSite compActiviteSite = new CompActiviteSite(site, numdechet, periode, ville.toString());
                totaldechets = totaldechets + compActiviteSite.getNbDechet();
            }
        }

        return totaldechets;
    }
}
