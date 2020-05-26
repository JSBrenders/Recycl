package classes;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactoryLille;
    private static SessionFactory sessionFactoryParis;
    public static SessionFactory getSessionFactory(String ville)
    {
        if(ville.equals("Lille")){
            if(sessionFactoryLille == null) {
                try {
                    //Configuration seesion lille
                    Configuration configuration = new Configuration().configure("hibernate.cfg.Lille.xml");

                    sessionFactoryLille = configuration.buildSessionFactory();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return sessionFactoryLille;
            } else return sessionFactoryLille;
        } else if (ville.equals("Paris")) {
            if (sessionFactoryParis == null) {
                try {
                    //Configuration session Paris
                    Configuration configuration = new Configuration().configure("hibernate.cfg.Paris.xml");

                    sessionFactoryParis = configuration.buildSessionFactory();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return sessionFactoryParis;
            } else return sessionFactoryParis;
        }
        //si oublie d'appel ou exception on retourne La session de paris par défault (normalement pas d'oubli)
        return sessionFactoryParis;
    }
}
