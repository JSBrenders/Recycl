<div id="interfaceDechets">
    <%-- Sélection d'un centre --%>
    <select  name="centre" id="centre">
        <option value="" disabled selected>Veuillez choisir un centre de traitement</option>
        <%
            if(requete != null && requete.centres.size() > 0) {
                if (requete.ville.equals("Lille")) {
                    requete.centres.sort(Comparator.comparing(CentretraitementEntityLille::getNomcentre));
                    for (Iterator iterator = requete.centres.iterator(); iterator.hasNext(); ) {
                        CentretraitementEntityLille centre = (CentretraitementEntityLille) iterator.next();

                        out.print("<option value ='" + centre.getNocentre() + "'>" + centre.getNomcentre() + "</option>");

                    }
                }


                if (requete.ville.equals("Paris")) {
                    requete.centres.sort(Comparator.comparing(CentretraitementEntityParis::getNomcentre));
                    for (Iterator iterator = requete.centres.iterator(); iterator.hasNext(); ) {
                        CentretraitementEntityParis centre = (CentretraitementEntityParis) iterator.next();

                        out.print("<option value ='" + centre.getNocentre() + "'>" + centre.getNomcentre() + "</option>");

                    }
                }
            } else {
                out.print("Il n'y a pas de centres enregistrés.");
            }
        %>
    </select>

    <%--    Sélection d'un type de déchet    --%>
    <label>
        <select name="dechet" id="dechet">
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
        <input type="date" id="datestart" value="<%= date1%>"%>
    </label>
    <label>
        <input type="date" id="dateend" value="<%= date2%>"%>
    </label>


</div>