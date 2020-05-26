package classes.paris;

import classes.HibernateUtil;
import classes.servlets.filtreEntreprise;
import dataBase.rlille.EntrepriseEntityLille;
import dataBase.rparis.EntrepriseEntityParis;
import org.hibernate.Session;

import java.util.Iterator;
import java.util.List;

public class filtreEntrepriseParis {

    public static filtreEntreprise.Response getEntreprises (String entreprise) {

        filtreEntreprise.Response response = new filtreEntreprise.Response();
        response.entrepriseReference = entreprise;
        Session session = HibernateUtil.getSessionFactory("Paris").openSession();

        try {
            Object existe = session.createQuery("FROM EntrepriseEntityParis e WHERE e.raisonsociale = :RS").setParameter("RS", entreprise).getSingleResult();
        }catch(Exception e){
            response.entrepriseReference = "KO";
            return response;
        }

        Object nbDemande = session.createQuery("SELECT COUNT(d) FROM EntrepriseEntityParis e, DemandeEntityParis d WHERE e.raisonsociale = :RS AND d.siret = e.siret").setParameter("RS", entreprise).getSingleResult();
        int nombreDemande = Integer.parseInt(nbDemande.toString());

        response.nbDemandeRef = nombreDemande;

        List entreprisesSup = session.createQuery("SELECT e FROM EntrepriseEntityParis e WHERE (SELECT COUNT(d) FROM DemandeEntityParis d WHERE d.siret = e.siret) > CAST(:nbDemande as integer)").setParameter("nbDemande", nombreDemande).list();


        for (Iterator iterator = entreprisesSup.iterator(); iterator.hasNext(); ) {
            EntrepriseEntityParis entrepriseEntity = (EntrepriseEntityParis) iterator.next();

            //on recherche les informations liée à l'entreprise'
            Object nbDemandeEntreprise = session.createQuery("SELECT COUNT(d) FROM DemandeEntityParis d WHERE d.siret = CAST(:siret as long)").setParameter("siret", entrepriseEntity.getSiret()).getSingleResult();
            int nombreDemandeEntreprise = Integer.parseInt(nbDemandeEntreprise.toString());

            filtreEntreprise.StructureResponse structureResponse = new filtreEntreprise.StructureResponse();
            structureResponse.entrepriseEntityParis = entrepriseEntity;
            structureResponse.nbDemande = nombreDemandeEntreprise;
            response.resultat.add(structureResponse);

        }
        session.close();
        return response;
    }
}
