package classes;

import oracle.ucp.common.waitfreepool.Tuple;
import org.hibernate.Session;

import java.util.Date;

public class CompActiviteSite {

    public int numSite;
    public int numDechet;
    public Tuple<Date, Date> periode;
    public String ville;

    public CompActiviteSite (int _numSite, int _numDechet, Tuple<Date, Date> _periode, String _ville) {
        numSite = _numSite;
        numDechet =_numDechet;
        periode = _periode;
        ville = _ville;
    }

    public int getNbDechet () {

        Session session = HibernateUtil.getSessionFactory(ville).openSession();

        int nombreTotalDechet = 0;
        Object nbTotalDechet = null;

        if(ville.equals("Paris")) {
            nbTotalDechet = session.createQuery("SELECT SUM(d.quantitedeposee) FROM DetaildepotEntityParis d, TourneeEntityParis t WHERE " +
                    "d.notypedechet = :typeDechet AND d.nocentre = :numCentre AND d.notournee = t.notournee AND (t.datetournee BETWEEN :date1 AND :date2)")
                    .setParameter("typeDechet", numDechet).setParameter("numCentre", numSite).setParameter("date1", periode.get1()).setParameter("date2", periode.get2()).getSingleResult();
        }else if(ville.equals("Lille")) {
            nbTotalDechet = session.createQuery("SELECT SUM(d.quantitedeposee) FROM DetaildepotEntityLille d, TourneeEntityLille t WHERE " +
                    "d.notypedechet = :typeDechet AND d.nocentre = :numCentre AND d.notournee = t.notournee AND (t.datetournee BETWEEN :date1 AND :date2)")
                    .setParameter("typeDechet", numDechet).setParameter("numCentre", numSite).setParameter("date1", periode.get1()).setParameter("date2", periode.get2()).getSingleResult();
        }

        if(nbTotalDechet != null){
            nombreTotalDechet = Integer.parseInt(nbTotalDechet.toString());
        }


        session.close();
        return nombreTotalDechet;
    }
}
