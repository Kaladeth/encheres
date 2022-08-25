
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="fr" xmlns:mso="urn:schemas-microsoft-com:office:office" xmlns:msdt="uuid:C2F41010-65B3-11d1-A29F-00AA00C14882">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    
    <!-- Personnal CSS-->
    <link rel="stylesheet" href="css/style.css">

    <!--icons-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">

    <title>ENI-Encheres : Inscription</title>

<!--[if gte mso 9]><xml>
<mso:CustomDocumentProperties>
<mso:xd_Signature msdt:dt="string"></mso:xd_Signature>
<mso:display_urn_x003a_schemas-microsoft-com_x003a_office_x003a_office_x0023_Editor msdt:dt="string">Bruno MARTIN</mso:display_urn_x003a_schemas-microsoft-com_x003a_office_x003a_office_x0023_Editor>
<mso:Order msdt:dt="string">493300.000000000</mso:Order>
<mso:xd_ProgID msdt:dt="string"></mso:xd_ProgID>
<mso:_ExtendedDescription msdt:dt="string"></mso:_ExtendedDescription>
<mso:SharedWithUsers msdt:dt="string"></mso:SharedWithUsers>
<mso:display_urn_x003a_schemas-microsoft-com_x003a_office_x003a_office_x0023_Author msdt:dt="string">Bruno MARTIN</mso:display_urn_x003a_schemas-microsoft-com_x003a_office_x003a_office_x0023_Author>
<mso:ComplianceAssetId msdt:dt="string"></mso:ComplianceAssetId>
<mso:TemplateUrl msdt:dt="string"></mso:TemplateUrl>
<mso:ContentTypeId msdt:dt="string">0x010100263DB1995E0D954B97BE6C60AEAEE054</mso:ContentTypeId>
<mso:TriggerFlowInfo msdt:dt="string"></mso:TriggerFlowInfo>
<mso:_SourceUrl msdt:dt="string"></mso:_SourceUrl>
<mso:_SharedFileIndex msdt:dt="string"></mso:_SharedFileIndex>
<mso:MediaLengthInSeconds msdt:dt="string"></mso:MediaLengthInSeconds>
</mso:CustomDocumentProperties>
</xml><![endif]-->
</head>
<body>

<%@ include file="/WEB-INF/fragmentsJsp/entete.jspf"%>


    <div class="container-fluid">

        <!--main bloc-->
        <main>
            <!--title-->
            <div class="mx-auto text-center">
                <h1>Inscription</h1>
                <img class="mb-4 large-icon rounded-circle" src="images/user.svg" alt="">
            </div>
            <!--erreur-->

          <%@ include file="/WEB-INF/fragmentsJsp/affichageErreurs.jspf"%>          

            <!--formulaire-->
            <form class="form-register needs-validation" action="<%=request.getContextPath()%>/Register" method="post" novalidate>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="pseudo">Pseudo</label>
                        <input type="text" class="form-control" id="pseudo" name="pseudo" placeholder="" maxlength="30" required value="${param.pseudo}">
                        <div class="invalid-feedback">
                            Ce champ est invalide !
                        </div>
                    </div>
                
                    <div class="col-md-6 mb-3">
                        <label for="lastname">Nom</label>
                        <input type="text" class="form-control" id="lastname" name="lastname" placeholder=""  maxlength="30" required value="${param.lastname}">
                        <div class="invalid-feedback">
                            Ce champ est invalide !
                        </div>
                    </div>
                </div>
                
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="firstname">Prénom</label>
                        <input type="text" class="form-control" id="firstname" name="firstname" placeholder="" maxlength="30" required value="${param.firstname}">
                        <div class="invalid-feedback">
                            Ce champ est invalide !
                        </div>
                    </div>
                
                    <div class="col-md-6 mb-3">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="you@example.com" maxlength="20" required value="${param.email}">
                        <div class="invalid-feedback">
                            Ce champ est invalide !
                        </div>
                    </div>
                </div>
                
                <div class="row">
                    <div class="col-md-4 mb-3">
                        <label for="phone">Téléphone <span class="text-muted">(Optional)</span></label>
                        <input type="text" class="form-control" id="phone" name="phone" placeholder="" maxlength="15" value="${param.phone}">
                    </div>
                    <div class="col-md-8 mb-3">
                        <label for="street">Rue</label>
                        <input type="text" class="form-control" id="street" name="street" placeholder="" maxlength="30" required value="${param.street}">
                        <div class="invalid-feedback">
                            Ce champ est invalide !
                        </div>
                    </div>
                </div>
                
                <div class="row">
                    <div class="col-md-4 mb-3">
                        <label for="zipcode">Code postal</label>
                        <input type="number" class="form-control" id="zipcode" name="zipcode" placeholder="" min="01000" max="99999" required value="${param.zipcode}">
                        <div class="invalid-feedback">
                            Ce champ est invalide !
                        </div>
                    </div>
                    <div class="col-md-8 mb-3">
                        <label for="city">Ville</label>
                        <input type="text" class="form-control" id="city" name="city" placeholder="" maxlength="30" required value="${param.city}">
                        <div class="invalid-feedback">
                            Ce champ est invalide !
                        </div>
                    </div>
                </div>
                                
				<div class="row">
				    <div class="col-md-6 mb-3">
				        <label for="password">Mot de passe</label>
				        <input type="password" class="form-control" id="password" name="password" placeholder="" minlength="6" maxlength="30" value="" required>
				        <div class="invalid-feedback">
				            Ce champ est invalide !
				        </div>
				    </div>
				
				    <div class="col-md-6 mb-3">
				        <label for="confirm_password">Confirmation</label>
				        <input type="password" class="form-control" id="confirm_password" name="confirm_password" placeholder="" required>
				        <div class="invalid-feedback">
				            Ce champ est invalide ou les mots de passe sont différents !
				        </div>
				    </div>
				</div>      
				          
                <hr class="mb-4">
                <button class="btn btn-primary btn-lg btn-block" type="submit" name="creer">Créer mon compte</button>
                <button class="btn btn-danger btn-lg btn-block" type="submit" name="annuler">Annuler</button>
            </form>
        </main>
    </div>
    <%@ include file="/WEB-INF/fragmentsJsp/footer.jspf"%>
    
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script>
    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (function() {
        'use strict';

        window.addEventListener('load', function() {
        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.getElementsByClassName('needs-validation');

        // Loop over them and prevent submission
        var validation = Array.prototype.filter.call(forms, function(form) {
            form.addEventListener('submit', function(event) {
                //validation du mot de passe
                var password = document.getElementById("password")
                , confirm_password = document.getElementById("confirm_password");
                if(password.value != confirm_password.value) {
                    confirm_password.setCustomValidity("Les mots de passe sont différents");
                    event.preventDefault();
                    event.stopPropagation();
                } else {
                    confirm_password.setCustomValidity('');
                }
                form.classList.add('was-validated');
            }, false);
        });            
    }, false);
    })();
    </script>
</body>

</html>