<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="classes.servlets.Index" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="dataBase.rlille.EntrepriseEntityLille" %>
<%@ page import="dataBase.rparis.EntrepriseEntityParis" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<%--    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/static/icone.ico" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/switch.css">



    <title>Accueil Recycl</title>
</head>
<body>

<div id="page-container">

    <div id="content-wrap">

        <div class="header" id="header">
            <%@ include file="WEB-INF/jsp/common/header.jsp" %>
        </div>


        <%
            Index.Response requete = (Index.Response)request.getAttribute("reponse");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String date1 = dtf.format(LocalDate.now());
            String date2 = dtf.format(LocalDate.now());
            String date3 = dtf.format(LocalDate.now());
            String date4 = dtf.format(LocalDate.now());
        %>

        <div id="row" class="row">
            <div id="column1" class="column">
                <div class="haut">
                    <div class="form-group row ml-3">
                        <div class="col-sm-6">
                            <h3 class="haut mb-4">Recherche par critère</h3>
                        </div>
                    </div>

                    <form id="formVille" action="${pageContext.request.contextPath}/" method="post">
                    </form>


                    <form action="${pageContext.request.contextPath}/filtreDateDemandes?" method="get">
                        <div class="form-group row ml-3">
                            <label for="dateDemande" class="col-sm-6 col-form-label font-weight-bold">Rechercher toutes les demandes à partir d'une date</label>
                            <div class="col-3">
                                <input id="dateDemande" type="date" name="dateDemande" class="form-control"/>
                            </div>
                            <button type="submit" class="btn btn-primary">Rechercher</button>
                        </div>
                    </form>

                    <form class="haut" action="${pageContext.request.contextPath}/filtreDateDechets?" method="get">
                        <%--   Format yyyy-MM   --%>
                        <div class="form-group row ml-3">
                            <label for="dateDechet" class="col-sm-6 col-form-label font-weight-bold">Rechercher sur un mois le total des déchets prélevés</label>
                            <div class="col-3">
                                <input id="dateDechet" type="month" name="dateDechets" class="form-control"/>
                            </div>
                            <button type="submit" class="btn btn-primary">Rechercher</button>
                        </div>
                    </form>

                    <form class="haut" action="${pageContext.request.contextPath}/filtreEmploye?" method="get">
                        <div class="form-group row ml-3">
                            <label for="nbTournee" class="col-sm-6 col-form-label font-weight-bold">Rechercher les employés ayant moins de x tournées</label>
                            <div class="col-3">
                                <input id="nbTournee" type="number" min="0" name="nbTournee" class="form-control"/>
                            </div>
                            <button type="submit" class="btn btn-primary">Rechercher</button>
                        </div>
                    </form>

                    <form class="haut" action="${pageContext.request.contextPath}/filtreEntreprise?" method="get">
                        <%--      Select avec la liste des entreprises--%>
                        <div class="form-group row ml-3">
                            <label for="entreprise" class="col-sm-6 col-form-label font-weight-bold">Rechercher les entreprises ayant davantage de demandes que l'entreprise sélectionnée</label>
                            <div class="col-3">
                                <select id="entreprise" name="entreprise" class="form-control">
                                    <option value = "" disabled selected>Veuillez choisir une entreprise</option>
                                    <%
                                        if(requete != null && requete.entreprises.size() > 0) {

                                            if(requete.ville.equals("Lille")){
                                                requete.entreprises.sort(Comparator.comparing(EntrepriseEntityLille::getRaisonsociale));
                                                for(Iterator iterator = requete.entreprises.iterator(); iterator.hasNext();){
                                                    EntrepriseEntityLille entreprise = (EntrepriseEntityLille)iterator.next();

                                                    out.print("<option value ='" + entreprise.getRaisonsociale() +"'>" + entreprise.getRaisonsociale() + "</option>");

                                                }
                                            }


                                            if(requete.ville.equals("Paris")){
                                                requete.entreprises.sort(Comparator.comparing(EntrepriseEntityParis::getRaisonsociale));
                                                for(Iterator iterator = requete.entreprises.iterator(); iterator.hasNext();){
                                                    EntrepriseEntityParis entreprise = (EntrepriseEntityParis)iterator.next();

                                                    out.print("<option value ='" + entreprise.getRaisonsociale() +"'>" + entreprise.getRaisonsociale() + "</option>");

                                                }
                                            }

                                        } else {
                                            out.print("Il n'y a pas d'entreprise enregistrées.");
                                        }
                                    %>

                                </select>
                            </div>
                            <div >
                                <button type="submit" class="btn btn-primary">Rechercher</button>
                            </div>
                        </div>
                    </form>

                    <form class="haut" action="${pageContext.request.contextPath}/demandeNonTraitees" method="get">
                        <div class="form-group row ml-3">
                            <label for="demandeNonAffectes" class="col-auto col-form-label font-weight-bold">Rechercher les demandes non affectées</label>
                            <div class="col-auto">
                                <button id="demandeNonAffectes" type="submit" class="btn btn-primary">Rechercher</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <%--      fin div colonne 1      --%>
            <div id="column2" class="column haut">
                <%--  Interface --%>


                <div id="interface" >
                    <h3 class="haut mb-5">Recherche par centre de traitement pour un type de déchet</h3>
                    <%@ include file="WEB-INF/jsp/common/dechetParSite.jsp" %>
                    <div id="resultatDechetSite" hidden>Quantite totale : <span id="resultQte"></span></div>
                    <div class="mb-5"></div>
                    <h3 class="mt-3 mb-5">Recherche nationale pour un type de déchet</h3>
                    <%@ include file="WEB-INF/jsp/common/dechetsNational.jsp" %>
                    <div id="resultatDechetNational" hidden>Quantité totale : <span id="resultQteNational"></span></div>
                </div>
            </div>
            <%--      fin div Row      --%>
        </div>






    </div>
    <%@ include file="WEB-INF/jsp/common/footer.jsp" %>
</div>




<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="static/scripts.js"></script>
<script src="${pageContext.request.contextPath}/static/villesswitch.js"></script>

</body>
</html>
