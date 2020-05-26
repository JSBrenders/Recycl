
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="classes.servlets.demandeNonTraitees" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="dataBase.rlille.DemandeEntityLille" %>
<%@ page import="dataBase.rparis.DemandeEntityParis" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="icon" href="${pageContext.request.contextPath}/static/icone.ico"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/switch.css">

    <title>Demandes non Traitées</title>
</head>
<body>

<div id="page-container">

    <div id="content-wrap">

        <div class="header" id="header">
            <%@ include file="common/header.jsp" %>
        </div>

        <%
            demandeNonTraitees.Response requete = (demandeNonTraitees.Response)request.getAttribute("result");
            String demande = "Résultat de la recherche : Demandes n'ayant pas de tournée affectée";
        %>

        <div class="haut">
            <h3 class="haut h3 text-center"><%= demande%></h3>
            <table class="table table-striped mt-5 mx-auto table-bordered w-50">
                <tr>
                    <th class="text-center">Numéro de la demande</th>
                    <th class="text-center">Date de la demande</th>
                    <th class="text-center">Siret</th>
                </tr>
                <%


                    if(requete.ville.equals("Lille")){
                        if(requete != null && requete.resultatLille.size() > 0) {
                            //tri de la requete
                            requete.resultatLille.sort(Comparator.comparing(DemandeEntityLille::getDatedemande));
                            for(Iterator iterator = requete.resultatLille.iterator(); iterator.hasNext();){
                                DemandeEntityLille result = (DemandeEntityLille)iterator.next();

                                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                String dateFormated = dateFormat.format(result.getDatedemande());

                                out.print("<tr>");

                                out.print("<td class='text-center' style='vertical-align: middle;'>" + result.getNodemande() + "</td>");
                                out.print("<td class='text-center' style='vertical-align: middle;'>" + dateFormated + "</td>");
                                out.print("<td class='text-center' style='vertical-align: middle;'>" + result.getSiret() + "</td>");

                                out.print("</tr>");
                            }
                        } else {
                            out.print("<tr><td class='text-center' colspan='3'>Toutes les demandes ont été affectées !</td></tr>");
                        }
                    }else {
                        if (requete != null && requete.resultatParis.size() > 0) {
                            //tri de la requete
                            requete.resultatParis.sort(Comparator.comparing(DemandeEntityParis::getDatedemande));
                            for (Iterator iterator = requete.resultatParis.iterator(); iterator.hasNext(); ) {
                                DemandeEntityParis result = (DemandeEntityParis) iterator.next();

                                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                String dateFormated = dateFormat.format(result.getDatedemande());

                                out.print("<tr>");

                                out.print("<td class='text-center' style='vertical-align: middle;'>" + result.getNodemande() + "</td>");
                                out.print("<td class='text-center' style='vertical-align: middle;'>" + dateFormated + "</td>");
                                out.print("<td class='text-center' style='vertical-align: middle;'>" + result.getSiret() + "</td>");

                                out.print("</tr>");
                            }
                        } else {
                            out.print("<tr><td class='text-center' colspan='3'>Toutes les demandes ont été affectées !</td></tr>");
                        }
                    }

                %>
            </table>
        </div>

        <div class="d-flex justify-content-center">
            <button id="affectation" type="button" class="btn btn-primary">Affecter ces demandes</button>
        </div>


        <div class="d-flex justify-content-center pt-1">
            <div id="result"></div>
        </div>

    </div>

    <%@ include file="common/footer.jsp" %>



</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/villesswitch.js"></script>


<script>

    jQuery(document).ready(function() {
        jQuery("#affectation").click(function() {

            let promise = jQuery.get('/affectationDemandes')
                .then(function(response) {
                    var results = response.toString().split("\n");
                    jQuery.each(results, function (result) {
                        jQuery('#result').append('<p>' + results[result] + '</p>');
                    });
                });
        });
    });

    jQuery(document).ready(function() {
        jQuery("#test").click(function() {
            var string = "long texte par ci \nlong texte par là\net aussi par là !"
            var results = string.split("\n");
            jQuery.each(results, function (result) {
                jQuery('#result').append('<p>' + results[result] + '</p><p>test</p>');
            });
        });
    });

</script>

</body>

</html>
