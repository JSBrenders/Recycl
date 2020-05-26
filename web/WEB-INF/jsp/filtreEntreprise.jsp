<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="classes.servlets.dateDemande" %>
<%@ page import="oracle.ucp.common.waitfreepool.Tuple" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="classes.servlets.filtreEmploye" %>
<%@ page import="classes.servlets.filtreEntreprise" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="icon" href="${pageContext.request.contextPath}/static/icone.ico"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/styles.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/switch.css">


    <title>Entreprises</title>
</head>
<body>

<div id="page-container">

    <div id="content-wrap">

        <div class="header" id="header">
            <%@ include file="common/header.jsp" %>
        </div>

        <%
            filtreEntreprise.Response requete = (filtreEntreprise.Response)request.getAttribute("result");
            String demande = "Résultat de la recherche : ";
            if(requete != null && !requete.entrepriseReference.equals("KO")){
                String entrepriseReference = requete.entrepriseReference;
                demande = demande + "Entreprises ayant plus de demande(s) que l'entreprise " + entrepriseReference + " (" + requete.nbDemandeRef + " demande(s))";
            }
            else {
                demande = "L'entreprise n'a pas été trouvée ! <a href='/welcome'>Retour</a>";
            }


        %>

        <div id="body" class="haut">
            <h3 class="haut h3 text-center"><%= demande%></h3>
            <table class="table table-striped mt-5 mx-auto table-bordered w-50">
                <tr>
                    <th class="text-center">Raison Sociale</th>
                    <th class="text-center">Siret</th>
                    <th class="text-center">Nombre de Demandes</th>
                </tr>
                <%


                    if(requete != null && requete.resultat.size() > 0) {

                        if(requete.ville.equals("Lille")) {
                            //tri de la requete
                            requete.resultat.sort(Comparator.comparing(structureReponse -> structureReponse.nbDemande));
                            for (Iterator iterator = requete.resultat.iterator(); iterator.hasNext(); ) {
                                filtreEntreprise.StructureResponse result = (filtreEntreprise.StructureResponse) iterator.next();

                                out.print("<tr>");

                                out.print("<td class='text-center' style='vertical-align: middle;'>" + result.entrepriseEntityLille.getRaisonsociale() + "</td>");
                                out.print("<td class='text-center' style='vertical-align: middle;'>" + result.entrepriseEntityLille.getSiret() + "</td>");
                                out.print("<td class='text-center' style='vertical-align: middle;'>" + result.nbDemande + "</td>");

                                out.print("</tr>");
                            }
                        } else {
                            //tri de la requete
                            requete.resultat.sort(Comparator.comparing(structureReponse -> structureReponse.nbDemande));
                            for (Iterator iterator = requete.resultat.iterator(); iterator.hasNext(); ) {
                                filtreEntreprise.StructureResponse result = (filtreEntreprise.StructureResponse) iterator.next();

                                out.print("<tr>");

                                out.print("<td class='text-center' style='vertical-align: middle;'>" + result.entrepriseEntityParis.getRaisonsociale() + "</td>");
                                out.print("<td class='text-center' style='vertical-align: middle;'>" + result.entrepriseEntityParis.getSiret() + "</td>");
                                out.print("<td class='text-center' style='vertical-align: middle;'>" + result.nbDemande + "</td>");

                                out.print("</tr>");
                            }
                        }
                    } else {
                        out.print("<tr><td class='text-center' colspan='3'>Aucun résultat pour ce nombre de tournée !</td></tr>");
                    }

                %>
            </table>
        </div>


    </div>

    <%@ include file="common/footer.jsp" %>


</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/villesswitch.js"></script>


</body>

</html>
