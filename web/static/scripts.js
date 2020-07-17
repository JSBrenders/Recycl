(function($) {
    jQuery(document).ready(function() {
        jQuery("#interfaceDechets > *").change(function() {
            let ville;
            if(jQuery("#check"))
            {
                ville = "Lille"
            }
            else
            {
                ville = "Paris"
            }
            let promise = jQuery.get('/dechetParSite?ville=' + ville + '&noCentre=' + $("#centre option:selected").val() + '&noDechet=' + $("#dechet option:selected").val() + '&dateStart=' + $("#datestart").val() + '&dateEnd=' + $("#dateend").val())
                .then(function(response) {

                    if(response !== "") {
                        document.getElementById("resultatDechetSite").hidden = false
                        var date1 = new Date($("#datestart").val());
                        var date2 = new Date($("#dateend").val());
                        const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
                        date1 = date1.toLocaleDateString('fr-FR', options);
                        date2 = date2.toLocaleDateString('fr-FR', options);
                        jQuery('#resultQte').text(response);

                    } else {
                        document.getElementById("resultatDechetSite").hidden = true
                    }
                })
                .catch(function(){
                    jQuery('#resultQte').text("Pas de déchet de ce type");
                });
        });
    });
})(jQuery);

(function($) {
    jQuery(document).ready(function() {
        jQuery("#interfaceDechetsNational > *").change(function() {
            let promise = jQuery.get('/dechetParSite?noDechet=' + $("#dechetNational option:selected").val() + '&dateStart=' + $("#datestartNational").val() + '&dateEnd=' + $("#dateendNational").val() + '&national=true')
                .then(function(response) {

                    if(response !== "") {
                        document.getElementById("resultatDechetNational").hidden = false

                        jQuery('#resultQteNational').text(response);

                        var date3 = new Date($("#datestartNational").val());
                        var date4 = new Date($("#dateendNational").val());
                        const options = {weekday: 'long', year: 'numeric', month: 'long', day: 'numeric'};
                        date3 = date3.toLocaleDateString('fr-FR', options);
                        date4 = date4.toLocaleDateString('fr-FR', options);


                        if ($("#dechetNational option:selected").val() != "") {
                            jQuery('#resultDechet').text($("#dechet option:selected").text())
                        }

                        jQuery('#resultDateSNational').text(date3)
                        jQuery('#resultDateENational').text(date4)
                    }else {
                        document.getElementById("resultatDechetNational").hidden = true
                    }
                })
                .catch(function(){
                    jQuery('#resultQteNational').text("Pas de déchet de ce type");
                });
        });
    });
})(jQuery);



// document.getElementById("check").addEventListener("change", function () {
//
//         if (jQuery('#check').is(':checked')) {
//             jQuery('#formVille').append('<input type="hidden" name="ville" value="Lille" /> ');
//             document.getElementById("formVille").submit();
//         }else {
//             jQuery('#formVille').append('<input type="hidden" name="ville" value="Paris" /> ');
//             document.getElementById("formVille").submit();
//         }
//
// });

// jQuery(document).ready(function() {
//     jQuery("#check").change(function() {
//         //true = lille false = Paris
//         if (jQuery('#check').is(':checked')) {
//             window.location.href = '/ville=Lille'
//
//         } else {
//             window.location.href = '/ville=Paris';
//         }
//     });
// });


