package classes;

import classes.servlets.Index;
import global.globalVariables;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class indexLoading {

    public static Index.Response loadIndex (String ville){

        Index.Response reponse = new Index.Response();

        if(globalVariables.Ville.equals("Lille")) {

            reponse.ville = "Lille";

            Session sessionLille = HibernateUtil.getSessionFactory("Lille").openSession();

            List entreprises = sessionLille.createQuery("from dataBase.rlille.EntrepriseEntityLille").list();
            List centres = sessionLille.createQuery("from dataBase.rlille.CentretraitementEntityLille ").list();
            List dechets = sessionLille.createQuery("from dataBase.rlille.TypedechetEntityLille ").list();

            reponse.entreprises = entreprises;
            reponse.centres = centres;
            reponse.dechets = dechets;

            sessionLille.close();

            return reponse;

        } else if(ville.equals("Paris")) {

            reponse.ville = "Paris";

            Session sessionParis = HibernateUtil.getSessionFactory("Paris").openSession();

            List entreprises = sessionParis.createQuery("from EntrepriseEntityParis").list();
            List centres = sessionParis.createQuery("from CentretraitementEntityParis ").list();
            List dechets = sessionParis.createQuery("from TypedechetEntityParis ").list();

            reponse.entreprises = entreprises;
            reponse.centres = centres;
            reponse.dechets = dechets;

            sessionParis.close();

            return reponse;

        }

        reponse.ville = "Ville inconnue";
        return reponse;
    }

}
