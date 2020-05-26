<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="classes.servlets.dateDemande" %>
<%@ page import="oracle.ucp.common.waitfreepool.Tuple" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DateFormat" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="icon" href="${pageContext.request.contextPath}/static/icone.ico" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/styles.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/switch.css">

    <title>Recherche Demandes</title>
</head>
<body>

<div id="page-container">

    <div id="content-wrap" class="haut">

        <div class="header m" id="header">
            <%@ include file="common/header.jsp" %>
        </div>


        <%
            dateDemande.Response requete = (dateDemande.Response)request.getAttribute("result");
            String date = "Résultat de la recherche pour la date du ";
            if(requete != null){
                Date dateDemande = requete.dateRequete;

                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                date = date + dateFormat.format(dateDemande);
            }
            else {
                date = "Vous n'avez pas sélectionné de date valide ! <a href='/'>Retour</a>";
            }


        %>



        <h3 class="haut h3 text-center"><%= date%></h3>
        <table class="table mt-5 mx-auto table-bordered w-50">
            <tr class="bg-secondary">
                <th>Numéro de Demande</th>
                <th>Date de la Demande</th>
                <th>Entreprise</th>
                <th>Numéro de tournée</th>
                <th>Type de Déchet</th>
                <th>Quantité</th>
            </tr>
            <%
                if(requete != null && requete.resultat.size() > 0) {
                    requete.resultat.sort(Comparator.comparing(structureReponse -> structureReponse.noDemande));
                    for(Iterator iterator = requete.resultat.iterator(); iterator.hasNext();){
                        dateDemande.StructureReponse result = (dateDemande.StructureReponse)iterator.next();
                        int rowspan = result.QteParDechet.size();
                        if(requete.resultat.indexOf(result)%2 == 0){
                            out.print("<tr class='bg-light'>");
                        }else {
                            out.print("<tr class='bg-secondary'>");
                        }
                        out.print("<td class='text-center' style='vertical-align: middle;' rowspan='"+ rowspan + "'>" + result.noDemande + "</td>");
                        out.print("<td class='text-center' style='vertical-align: middle;' rowspan='"+ rowspan + "'>" + result.dateDemande + "</td>");
                        out.print("<td class='text-center' style='vertical-align: middle;' rowspan='"+ rowspan + "'>" + result.RaisonSociale + "</td>");
                        if(result.notournee != 0) {
                            out.print("<td class='text-center' style='vertical-align: middle;' rowspan='" + rowspan + "'>" + result.notournee + "</td>");
                        }

                        if(result.notournee != 0){

                            for (Iterator iterator1 = result.QteParDechet.iterator(); iterator1.hasNext();){
                                Tuple<String, Integer> tuple = (Tuple<String, Integer>)iterator1.next();
                                if(result.QteParDechet.indexOf(tuple) > 0){
                                    if(requete.resultat.indexOf(result)%2 == 0){
                                        out.print("<tr class='bg-light'>");
                                    }else {
                                        out.print("<tr class='bg-secondary'>");
                                    }
                                }
                                out.print("<td>" + tuple.get1() + "\n" + "</td>");
                                out.print("<td>" + tuple.get2() + "\n" + "</td>");
                                if(result.QteParDechet.indexOf(tuple) > 0){
                                    out.print("</tr>");
                                }
                            }
                        } else {
                            out.print("<td class='text-center' colspan='3'>Pas de tournée assignée</td>");
                        }
                        out.print("</tr>");
                    }
                } else {
                    out.print("<tr><td class='text-center' colspan='6'>Aucun résultat pour cette date !</td></tr>");
                }

            %>
        </table>
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

