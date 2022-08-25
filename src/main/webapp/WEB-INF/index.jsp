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


<!--  <link href="${pageContext.request.contextPath}/CSS/styleFrag.css" rel="stylesheet">-->



<link rel="stylesheet" href="css/styleIndex.css">


<!--icons-->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
	crossorigin="anonymous">

<title>ENI-Encheres : Liste des enchères</title>

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
	<!--         <header> -->
	<%@ include file="/WEB-INF/fragmentsJsp/entete.jspf"%>
	<div class="container-fluid">

		<!--main bloc-->
		
		<main>
			<!--title-->
			<div class="mx-auto text-center">
				<h1>Enchères</h1>
			</div>
			
			 <!--erreur-->
            <%@ include file="/WEB-INF/fragmentsJsp/affichageErreurs.jspf"%>
			
			<!--filtre-->
			<form class="form-filter border mb-3" action="<%=request.getContextPath()%>/Accueil" method="post">
				<div class="row">
					<!--Partie gauche-->
					<div class="col-md-6 mb-3">
						<div class="form-group">
							<label for="filter-input">Filtre</label> <input type="text"
								class="form-control" id="filter-input" name="nomArticle"
								placeholder="articles contenant..." value="">
						</div>
						<div class="form-group">
							<label for="categories-select">Catégories</label> <select
								class="form-control" id="categories-select" name="categorie">
								<option value="Toutes" selected>Toutes</option>
								<c:forEach items="${listesCategories}" var="categorie">
								<option name="categorie" value="${categorie.getLibelle()}">${categorie.getLibelle()}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<!--  si l'utilisateur connecté afficher les chekbox  -->
					<!--Partie droite-->
					<c:if test="${connecte == true}">
					<div class="col-md-6 mb-3">
						<div class="form-check">
							<label class="form-check-label"> <input type="radio"
								class="form-check-input" checked name="type-encheres"
								value="achats" id="achats">Achats
							</label>
						</div>
						<div class="form-group">
							<div class="form-check">
								<label class="form-check-label"> 
								<input type="checkbox" class="form-check-input" name="encheres" value="CR" <c:if test="${valeurCheckbox=='CR'}">checked=checked</c:if>
									id="ouvertes">Enchères ouvertes
								</label>
							</div>
							<div class="form-check">
								<label class="form-check-label"> 
								<input type="checkbox" class="form-check-input" name="encheres" value="EC" <c:if test="${valeurCheckbox=='EC'}">checked=checked</c:if>
									id="encours">Mes enchères en cours
								</label>
							</div>
							<div class="form-check">
								<label class="form-check-label"> 
								<input type="checkbox" class="form-check-input" name="encheres" value="ER" <c:if test="${valeurCheckbox=='VD'}">checked=checked</c:if>
									id="remportees">Mes enchères remportées
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
									class="form-check-input" name="ventes" value="EC"
									id="venteencours">Mes ventes en cours
								</label>
							</div>
							<div class="form-check">
								<label class="form-check-label"> <input type="checkbox"
									class="form-check-input" name="ventes" value="CR"
									id="nondebutees">Mes ventes non débutées
								</label>
							</div>
							<div class="form-check">
								<label class="form-check-label"> <input type="checkbox"
									class="form-check-input" name="ventes" value="VD"
									id="terminees">Mes ventes terminées
								</label>
							</div>
						</div>

					</div>
					</c:if>
				</div>
				<button class="btn btn-primary btn-lg btn-block" type="submit" name="filtrer">
					<img class="small-icon" src="imgs/search.svg" alt="Filtrer">
				</button>
			</form>

			<!--enchères-->
			
			<div class="row justify-content-center border-top card-deck">
				<c:forEach var="element" items="${listeArticles}">
					<div class="col-12 col-sm-6 p-2" >
	                    <div class="card">
	                        <div class="card-header text-center">
	                            <h4 class="my-0 font-weight-normal">${element.getNomArticle()}</h4>
	                            
	                        </div>
	                        <div class="d-flex">
	                            <div class="col-3 p-2">
	                                <img class="img-fluid img-thumbnail" src="imgs/photo.png" alt="pas de photo" />
	                            </div>
	                            <ul class="col-9 list-unstyled p-2">
	                                <li>Prix : ${element.getMiseAPrix()} point(s)</li>
	                                <li>Meilleure enchère : ${element.enchere.getMontant_enchere()} point(s)</li>
	                                <li>Fin de l'enchère : ${element.getDateFinEncheres()} </li>
	                                <li>Vendeur : <a href="${pageContext.request.contextPath}/connecte/visualiser/profil?pseudo=${element.getVendeur().getPseudo()}">${element.getVendeur().getPseudo()}</a></li>
	                                	
	                            </ul>
	                        </div>
	                         <a class="mt-3 btn btn-lg btn-block btn-primary" href="<%=request.getContextPath()%>/connecte/afficher/enchere?noArticleVendu=${element.getNoArticle()}" title="faire une enchère" name="noArticleVendu"  >
	                            <img class="small-icon" src="imgs/bid.svg">
	                            
	                        </a> 
	                    </div>
	                </div>
               </c:forEach>
    	</main>


	</div>

	<%@ include file="/WEB-INF/fragmentsJsp/footer.jspf"%>

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