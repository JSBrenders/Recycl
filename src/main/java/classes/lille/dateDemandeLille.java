package classes.lille;

import classes.HibernateUtil;
import classes.servlets.dateDemande;
import dataBase.rlille.DetaildemandeEntityLille;
import dataBase.rlille.EntrepriseEntityLille;
import dataBase.rlille.TypedechetEntityLille;
import dataBase.rlille.DemandeEntityLille;
import oracle.ucp.common.waitfreepool.Tuple;
import org.hibernate.Session;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class dateDemandeLille {



    public static dateDemande.Response getDateDemande(String date) {



        dateDemande.Response response = new dateDemande.Response();

        Date dateF = null;
        try {

            dateF = new SimpleDateFormat("yyyy-MM-dd").parse(date);

            response.dateRequete = dateF;

            Session session = HibernateUtil.getSessionFactory("Lille").openSession();

            List result = session.createQuery("FROM DemandeEntityLille d WHERE d.datedemande >= :dateDemande").setParameter("dateDemande", dateF).list();

            for (Iterator iterator = result.iterator(); iterator.hasNext(); ) {


                DemandeEntityLille demande = (DemandeEntityLille) iterator.next();

                //on recherche les informations liée à la tournée
                EntrepriseEntityLille rs = (EntrepriseEntityLille) session.createQuery("FROM EntrepriseEntityLille e WHERE e.siret = :SIRET").setParameter("SIRET", demande.getSiret()).getSingleResult();

                String raisonSociale = rs.getRaisonsociale();
                //on crée une instance de structureReponse

                dateDemande.StructureReponse structureReponse = new dateDemande.StructureReponse();
                structureReponse.RaisonSociale = raisonSociale;
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String dateFormated = dateFormat.format(demande.getDatedemande());
                structureReponse.dateDemande = dateFormated;
                if(demande.getNotournee() == null)
                {
                    structureReponse.notournee = 0;
                    structureReponse.noDemande = demande.getNodemande();
                    structureReponse.QteParDechet.add(new Tuple<>("", 0));
                    response.resultat.add(structureReponse);
                    continue;
                }
                structureReponse.noDemande = demande.getNodemande();
                structureReponse.notournee = demande.getNotournee();


                List detailDemandes = session.createQuery("FROM DetaildemandeEntityLille d WHERE d.nodemande = :noDemande").setParameter("noDemande", demande.getNodemande()).list();
                for (Iterator iteratorDetailDemande = detailDemandes.iterator(); iteratorDetailDemande.hasNext(); ) {
                    DetaildemandeEntityLille detailDemande = (DetaildemandeEntityLille)iteratorDetailDemande.next();

                    //on liste les déchets de la tournée et leur qte
                    TypedechetEntityLille nd = (TypedechetEntityLille) session.createQuery("FROM TypedechetEntityLille e WHERE e.notypedechet = :notypedechet").setParameter("notypedechet", detailDemande.getNotypedechet()).getSingleResult();;
                    String nomDechet = nd.getNomtypedechet();

                    //on ajoute le tout à la class structreponse dans la liste result
                    Tuple<String, Integer> tuple = new Tuple<>(nomDechet, detailDemande.getQuantiteenlevee());
                    structureReponse.QteParDechet.add(tuple);
                }
                response.resultat.add(structureReponse);
            }
            session.close();
        } catch (ParseException e) {
            return null;
        }

        return response;
    }
}
