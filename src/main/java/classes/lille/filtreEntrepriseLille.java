package classes.lille;

import classes.HibernateUtil;
import classes.servlets.filtreEntreprise;
import dataBase.rlille.EntrepriseEntityLille;
import org.hibernate.Session;

import java.util.Iterator;
import java.util.List;

public class filtreEntrepriseLille {

    public static filtreEntreprise.Response getEntreprises (String entreprise) {

        filtreEntreprise.Response response = new filtreEntreprise.Response();
        response.entrepriseReference = entreprise;
        Session session = HibernateUtil.getSessionFactory("Lille").openSession();

        try {
            Object existe = session.createQuery("FROM EntrepriseEntityLille e WHERE e.raisonsociale = :RS").setParameter("RS", entreprise).getSingleResult();
        }catch(Exception e){
            response.entrepriseReference = "KO";
            return response;
        }

        Object nbDemande = session.createQuery("SELECT COUNT(d) FROM EntrepriseEntityLille e, DemandeEntityLille d WHERE e.raisonsociale = :RS AND d.siret = e.siret").setParameter("RS", entreprise).getSingleResult();
        int nombreDemande = Integer.parseInt(nbDemande.toString());

        response.nbDemandeRef = nombreDemande;

        List entreprisesSup = session.createQuery("SELECT e FROM EntrepriseEntityLille e WHERE (SELECT COUNT(d) FROM DemandeEntityLille d WHERE d.siret = e.siret) > CAST(:nbDemande as integer)").setParameter("nbDemande", nombreDemande).list();


        for (Iterator iterator = entreprisesSup.iterator(); iterator.hasNext(); ) {
            EntrepriseEntityLille entrepriseEntity = (EntrepriseEntityLille) iterator.next();

            //on recherche les informations liée à l'entreprise'
            Object nbDemandeEntreprise = session.createQuery("SELECT COUNT(d) FROM DemandeEntityLille d WHERE d.siret = CAST(:siret as long)").setParameter("siret", entrepriseEntity.getSiret()).getSingleResult();
            int nombreDemandeEntreprise = Integer.parseInt(nbDemandeEntreprise.toString());

            filtreEntreprise.StructureResponse structureResponse = new filtreEntreprise.StructureResponse();
            structureResponse.entrepriseEntityLille = entrepriseEntity;
            structureResponse.nbDemande = nombreDemandeEntreprise;
            response.resultat.add(structureResponse);

        }
        session.close();
        return response;
    }
}
