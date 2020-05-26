<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="classes.servlets.dateDemande" %>
<%@ page import="oracle.ucp.common.waitfreepool.Tuple" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="classes.servlets.dateDechets" %>
<%@ page import="java.text.DateFormatSymbols" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="icon" href="${pageContext.request.contextPath}/static/icone.ico" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/styles.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/switch.css">

    <title>Recherche Déchets</title>
</head>
<body>

<div id="page-container">

    <div id="content-wrap">

        <div class="header m" id="header">
            <%@ include file="common/header.jsp" %>
        </div>


        <%
            dateDechets.Response requete = (dateDechets.Response)request.getAttribute("result");
            String date = "Résultat de la recherche : ";
            if(requete != null){
                int monthRequete = requete.month;
                int yearRequete = requete.year;

                String month = new DateFormatSymbols().getMonths()[monthRequete - 1];
                String Month = Character.toUpperCase(month.charAt(0)) + month.substring(1);

                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                date = date + Month + " " + yearRequete;
            }
            else {
                date = "Vous n'avez pas sélectionné de date valide ! <a href='/'>Retour</a>";
            }


        %>

        <div id="body">

            <h3 class="haut h3 text-center"><%= date%></h3>
            <table class="table table-striped mt-5 mx-auto table-bordered w-50">
                <tr>
                    <th class="text-center">Nom déchet</th>
                    <th class="text-center">Quantité totale</th>
                </tr>
                <%
                    if(requete != null && requete.resultat.size() > 0) {

                        for(Iterator iterator = requete.resultat.iterator(); iterator.hasNext();){
                            dateDechets.StructureReponse result = (dateDechets.StructureReponse)iterator.next();

                            out.print("<tr>");

                            out.print("<td class='text-center' style='vertical-align: middle;'>" + result.nomDechet + "</td>");
                            out.print("<td class='text-center' style='vertical-align: middle;'>" + result.QteTotale + "</td>");

                            out.print("</tr>");
                        }
                    } else {
                        out.print("<tr><td class='text-center' colspan='2'>Aucun résultat pour cette date !</td></tr>");
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

