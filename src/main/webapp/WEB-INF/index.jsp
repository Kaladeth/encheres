<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="fr" xmlns:mso="urn:schemas-microsoft-com:office:office"
	xmlns:msdt="uuid:C2F41010-65B3-11d1-A29F-00AA00C14882">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<!-- Personnal CSS-->
<link rel="stylesheet" href="css/style.css">

<!--icons-->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
	crossorigin="anonymous">

<title>ENI-Encheres : Liste des ench�res</title>

<!--[if gte mso 9]><xml>
<mso:CustomDocumentProperties>
<mso:xd_Signature msdt:dt="string"></mso:xd_Signature>
<mso:display_urn_x003a_schemas-microsoft-com_x003a_office_x003a_office_x0023_Editor msdt:dt="string">Bruno MARTIN</mso:display_urn_x003a_schemas-microsoft-com_x003a_office_x003a_office_x0023_Editor>
<mso:Order msdt:dt="string">493100.000000000</mso:Order>
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
		<!--fullHeader-->

		<!--         <header> -->

		<!--   <nav class="pr-5 navbar navbar-expand-sm bg-dark navbar-dark align-top justify-content-between">
                Brand/logo
                <a class="navbar-brand" href="./index.jsp">
                    <img class="small-icon" src="images/trocenchere.svg" alt="Accueil ENI-Encheres">
                    <strong>ENI-Encheres</strong>
                </a>

                <a class="navbar-brand" href="#" alt="G�rer mon profil" title="G�rer mon profil">
                    <img class="small-icon" src="images/user.svg">
                    <span class="align-middle text-muted">XXXXX xxx, 0 cr�dit(s)</span>
                </a>

                <ul class="navbar-nav ml-auto">
                    Dropdown for small screen
                    <li class="nav-item dropdown d-lg-none">
                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                            <img class="small-icon" src="images/menu.svg" alt="Menu ENI-Encheres">
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="#" alt="Administrer le site">Administrer</a> 
                            <a class="dropdown-item" href="#" alt="Vendre un article">Vendre un article</a>
                            <a class="dropdown-item" href="#" alt="Me d�connecter">Me d�connecter</a>
                            <a class="dropdown-item" href="./Register" alt="S'inscrire � ENI-Encheres">M'inscrire</a>
                            <a class="dropdown-item" href="./Connecter" alt="Se connecter � ENI-Encheres">Me connecter</a>
                        </div>
                    </li>  
                    Links for medium screen
                    <li class="nav-item d-none d-lg-block">
                        <a class="nav-link" href="#" alt="Administrer le site">Administrer</a>
                    </li> 
                    <li class="nav-item d-none d-lg-block">
                        <a class="nav-link" href="#" alt="Vendre un article">Vendre un article</a>
                    </li>
                    <li class="nav-item d-none d-lg-block">
                        <a class="nav-link" href="#" alt="Me d�connecter">Me d�connecter</a>
                    </li>
                    <li class="nav-item d-none d-lg-block">
                        <a class="nav-link" href="./Register" alt="S'inscrire � ENI-Encheres">M'inscrire</a>
                    </li>
                    <li class="nav-item d-none d-lg-block">
                        <a class="nav-link" href="./Connecter" alt="Se connecter � ENI-Encheres">Me connecter</a>
                    </li>
                </ul>
            </nav> -->
		<!--      </header> -->
		<!--main bloc-->
		<main>
			<!--title-->
			<div class="mx-auto text-center">
				<h1>Ench�res</h1>
			</div>
			<!--erreur-->
			 <c:if test="${not empty requestScope.erreurs}">
            <div class="d-flex alert-danger">
                <div class="col-3 p-2">
                    <img class="small-icon" src="images/error.svg">
                </div>
        
                <ul class="col-9 list-unstyled p-2">
                    
                   	${erreurs.toString() }                   
                    
                </ul>
            </div>
            </c:if>
			
			<!--filtre-->
			<form class="form-filter border mb-3" action="#" method="">
				<div class="row">
					<!--Partie gauche-->
					<div class="col-md-6 mb-3">
						<div class="form-group">
							<label for="filter-input">Filtre</label> <input type="text"
								class="form-control" id="filter-input" name="q"
								placeholder="articles contenant...">
						</div>
						<div class="form-group">
							<label for="categories-select">Cat�gories</label> <select
								class="form-control" id="categories-select" name="categorie">
								<option selected>Toutes</option>
								<option name="categorie" value="">Informatique</option>
								<option name="categorie" value="">Ameublement</option>
								<option name="categorie" value="">V�tement</option>
								<option name="categorie" value="">Sport & Loisirs</option>
							</select>
						</div>
					</div>
					<!--Partie droite-->
					<div class="col-md-6 mb-3">
						<div class="form-check">
							<label class="form-check-label"> <input type="radio"
								class="form-check-input" checked name="type-encheres"
								value="achats" id="achats">Achats
							</label>
						</div>
						<div class="form-group">
							<div class="form-check">
								<label class="form-check-label"> <input type="checkbox"
									class="form-check-input" checked name="encheres"
									value="ouvertes" id="ouvertes">Ench�res ouvertes
								</label>
							</div>
							<div class="form-check">
								<label class="form-check-label"> <input type="checkbox"
									class="form-check-input" name="encheres" value="encours"
									id="encours">Mes ench�res en cours
								</label>
							</div>
							<div class="form-check">
								<label class="form-check-label"> <input type="checkbox"
									class="form-check-input" name="encheres" value="remportees"
									id="remportees">Mes ench�res remport�es
								</label>
							</div>
						</div>
						<div class="form-check">
							<label class="form-check-label"> <input type="radio"
								class="form-check-input" name="type-encheres" value="ventes"
								id="ventes">Ventes
							</label>
						</div>
						<div class="form-group">
							<div class="form-check">
								<label class="form-check-label"> <input type="checkbox"
									class="form-check-input" name="ventes" value="venteencours"
									id="venteencours">Mes ventes en cours
								</label>
							</div>
							<div class="form-check">
								<label class="form-check-label"> <input type="checkbox"
									class="form-check-input" name="ventes" value="nondebutees"
									id="nondebutees">Mes ventes non d�but�es
								</label>
							</div>
							<div class="form-check">
								<label class="form-check-label"> <input type="checkbox"
									class="form-check-input" name="ventes" value="terminees"
									id="terminees">Mes ventes termin�es
								</label>
							</div>
						</div>

					</div>
				</div>
				<button class="btn btn-primary btn-lg btn-block" type="submit">
					<img class="small-icon" src="images/search.svg" alt="Eni Ecole">
				</button>
			</form>

			<!--ench�res-->
			<div class="row justify-content-center border-top card-deck">
				<div class="col-12 col-sm-6 p-2">
					<div class="card">
						<div class="card-header text-center">
							<h4 class="my-0 font-weight-normal">Article 1</h4>
						</div>
						<div class="d-flex">
							<div class="col-3 p-2">
								<img class="img-fluid img-thumbnail" src="images/photo.svg"
									alt="pas de photo" />
							</div>
							<ul class="col-9 list-unstyled p-2">
								<li>Prix : 0 point(s)</li>
								<li>Meilleure ench�re : 0 point(s)</li>
								<li>Fin de l'ench�re : dd-MM-yyyy HH:mm</li>
								<li>Vendeur : xxxxxxxxx</li>
							</ul>
						</div>
						<a class="mt-3 btn btn-lg btn-block btn-primary" href="#"
							title="faire une ench�re"> <img class="small-icon"
							src="images/bid.svg">
						</a>
					</div>
				</div>
				<div class="col-12 col-sm-6 p-2">
					<div class="card">
						<div class="card-header text-center">
							<h4 class="my-0 font-weight-normal">Article 2</h4>
						</div>
						<div class="d-flex">
							<div class="col-3 p-2">
								<img class="img-fluid img-thumbnail" src="images/photo.svg"
									alt="pas de photo" />
							</div>
							<ul class="col-9 list-unstyled p-2">
								<li>Prix : 0 point(s)</li>
								<li>Meilleure ench�re : 0 point(s)</li>
								<li>Fin de l'ench�re : dd-MM-yyyy HH:mm</li>
								<li>Vendeur : xxxxxxxxx</li>
							</ul>
						</div>
						<a class="mt-3 btn btn-lg btn-block btn-primary" href="#"
							title="faire une ench�re"> <img class="small-icon"
							src="images/bid.svg">
						</a>
					</div>
				</div>
				<div class="col-12 col-sm-6 p-2">
					<div class="card">
						<div class="card-header text-center">
							<h4 class="my-0 font-weight-normal">Article 3</h4>
						</div>
						<div class="d-flex">
							<div class="col-3 p-2">
								<img class="img-fluid img-thumbnail" src="images/photo.svg"
									alt="pas de photo" />
							</div>
							<ul class="col-9 list-unstyled p-2">
								<li>Prix : 0 point(s)</li>
								<li>Meilleure ench�re : 0 point(s)</li>
								<li>Fin de l'ench�re : dd-MM-yyyy HH:mm</li>
								<li>Vendeur : xxxxxxxxx</li>
							</ul>
						</div>
						<a class="mt-3 btn btn-lg btn-block btn-primary" href="#"
							title="faire une ench�re"> <img class="small-icon"
							src="images/bid.svg">
						</a>
					</div>
				</div>
		</main>

		<!--footer-->
		<footer class="border-top text-center align-bottom">
			<div class="mt-3">
				<img class="small-icon" src="images/ateni.svg" alt="Eni Ecole">
				<small class="d-block text-muted">&copy; ENI Ecole 2020</small>
				<div>
					Icons made by <a href="https://www.flaticon.com/authors/freepik"
						title="Freepik">Freepik</a> from <a
						href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a>
				</div>
			</div>
		</footer>
	</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
	<script>
		// Example starter JavaScript for disabling form submissions if there are invalid fields
		(function() {
			'use strict';

			window
					.addEventListener(
							'load',
							function() {
								checkAchats();
								checkVentes();
								achats.addEventListener('change', function(
										event) {
									checkAchats();
								}, false);
								ventes.addEventListener('change', function(
										event) {
									checkVentes();
								}, false);

								function checkAchats() {
									//id radio button achats
									var achats = document
											.getElementById('achats');
									if (achats.checked) {
										//id des checkbox
										document.getElementById('venteencours').disabled = true;
										document.getElementById('nondebutees').disabled = true;
										document.getElementById('terminees').disabled = true;
										document.getElementById('encours').disabled = false;
										document.getElementById('ouvertes').disabled = false;
										document.getElementById('remportees').disabled = false;
									}
								}
								function checkVentes() {
									//id radio button ventes
									var ventes = document
											.getElementById('ventes');
									if (ventes.checked) {
										//id des checkbox
										document.getElementById('venteencours').disabled = false;
										document.getElementById('nondebutees').disabled = false;
										document.getElementById('terminees').disabled = false;
										document.getElementById('encours').disabled = true;
										document.getElementById('ouvertes').disabled = true;
										document.getElementById('remportees').disabled = true;
									}
								}
							}, false);
		})();
	</script>
</body>
</html>