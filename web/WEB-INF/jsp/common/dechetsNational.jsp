<div id="interfaceDechetsNational">

    <%--    Sélection d'un type de déchet    --%>
    <label>
        <select name="dechet" id="dechetNational">
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
    </label>

    <label>
        <input type="date" id="datestartNational" value="<%= date3%>"%>
    </label>
    <label>
        <input type="date" id="dateendNational" value="<%= date4%>"%>
    </label>


</div>