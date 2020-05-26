jQuery(document).ready(function () {
    jQuery.get('/getCurrentVille')
        .then(function(response) {
            if(response.toString() === "Lille"){
                jQuery('#check').prop("checked", true);
                document.getElementById("Lille").classList.replace("text-dark", "text-light");
                document.getElementById("Paris").classList.replace("text-light", "text-dark");

            }else {
                jQuery('#check').prop("checked", false);
                document.getElementById("Paris").classList.replace("text-dark", "text-light");
                document.getElementById("Lille").classList.replace("text-light", "text-dark");
            }
        });
});


jQuery(document).ready(function() {
    jQuery("#check").change(function() {
        //true = lille false = Paris
        if (jQuery('#check').is(':checked')) {
            let promise = jQuery.get('/setCurrentVille?newVille=Lille')
                .then(function (response) {
                    //change to Lille
                    document.getElementById("Lille").classList.replace("text-dark", "text-light");
                    document.getElementById("Paris").classList.replace("text-light", "text-dark");
                    document.location.reload(true);
                });
        } else {
            let promise = jQuery.get('/setCurrentVille?newVille=Paris')
                .then(function (response) {
                    //change to Paris
                    document.getElementById("Paris").classList.replace("text-dark", "text-light");
                    document.getElementById("Lille").classList.replace("text-light", "text-dark");
                    document.location.reload(true);
                })
        }
    });
});
