<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="classes.servlets.filtreEmploye" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="icon" href="${pageContext.request.contextPath}/static/icone.ico" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <%--    <link rel="stylesheet" type="text/css" href="styles.css">--%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/switch.css">
    <style>
        <%@ include file="/static/styles.css"%>
    </style>
    <title>Employés</title>
</head>
<body>

<div id="page-container">

    <div id="content-wrap">

        <div class="header" id="header">
            <%@ include file="common/header.jsp" %>
        </div>

        <%
            filtreEmploye.Response requete = (filtreEmploye.Response)request.getAttribute("result");
            String tournee = "Résultat de la recherche : ";
            if(requete != null){
                int nbTournee = requete.nbTournée;

                tournee = tournee + "Employés ayant " + nbTournee + " Tournée ou moins à leur actif";
            }
            else {
                tournee = "Vous n'avez pas sélectionné de nombre valide ! <a href='/'>Retour</a>";
            }


        %>

        <div id="body" class="haut">
            <h3 class="haut h3 text-center"><%= tournee%></h3>
            <table class="table table-striped mt-5 mx-auto table-bordered w-50">
                <tr>
                    <th class="text-center">Nom</th>
                    <th class="text-center">Prénom</th>
                    <th class="text-center">Nombre de tournées</th>
                </tr>
                <%
                    if(requete != null && requete.resultat.size() > 0) {

                        //tri de la requete
                        requete.resultat.sort(Comparator.comparing(structureReponse -> structureReponse.nbTournee));
                        for(Iterator iterator = requete.resultat.iterator(); iterator.hasNext();){
                            filtreEmploye.StructureReponse result = (filtreEmploye.StructureReponse)iterator.next();

                            out.print("<tr>");

                            out.print("<td class='text-center' style='vertical-align: middle;'>" + result.nom + "</td>");
                            out.print("<td class='text-center' style='vertical-align: middle;'>" + result.prenom + "</td>");
                            out.print("<td class='text-center' style='vertical-align: middle;'>" + result.nbTournee + "</td>");

                            out.print("</tr>");
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
