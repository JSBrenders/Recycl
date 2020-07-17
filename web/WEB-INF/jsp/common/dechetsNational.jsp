<%@ page import="dataBase.rlille.TypedechetEntityLille" %>
<%@ page import="dataBase.rparis.TypedechetEntityParis" %>
<div id="interfaceDechetsNational">

    <%--    Sélection d'un type de déchet    --%>
        <select name="dechet" id="dechetNational" class="form-control w-50  mb-3">
            <option value="" disabled selected>Veuillez choisir un type de dechet</option>
            <%
                if(requete != null && requete.dechets.size() > 0) {

                    if (requete.ville.equals("Lille")) {
                        requete.dechets.sort(Comparator.comparing(TypedechetEntityLille::getNomtypedechet));
                        for (Iterator iterator = requete.dechets.iterator(); iterator.hasNext(); ) {
                            TypedechetEntityLille dechet = (TypedechetEntityLille) iterator.next();

                            out.print("<option value ='" + dechet.getNotypedechet() + "'>" + dechet.getNomtypedechet() + "</option>");

                        }
                    }


                    if (requete.ville.equals("Paris")) {
                        requete.dechets.sort(Comparator.comparing(TypedechetEntityParis::getNomtypedechet));
                        for (Iterator iterator = requete.dechets.iterator(); iterator.hasNext(); ) {
                            TypedechetEntityParis dechet = (TypedechetEntityParis) iterator.next();

                            out.print("<option value ='" + dechet.getNotypedechet() + "'>" + dechet.getNomtypedechet() + "</option>");

                        }
                    }
                }else {
                    out.print("Il n'y a pas de types de déchets enregistrés.");
                }


            %>
        </select>

<%--    <label>--%>
<%--        <input type="date" id="datestartNational" value="<%= date3%>"%>--%>
<%--    </label>--%>
<%--    <label>--%>
<%--        <input type="date" id="dateendNational" value="<%= date4%>"%>--%>
<%--    </label>--%>

        <label class="my-2 form-row ml-1">
            Periode :
        </label>
        <label>
            <input type="date" id="datestartNational" class="form-control mr-3" value="<%= date3%>"%>
        </label>

        <label>
            <input type="date" id="dateendNational" class="form-control" value="<%= date4%>"%>
        </label>


</div>