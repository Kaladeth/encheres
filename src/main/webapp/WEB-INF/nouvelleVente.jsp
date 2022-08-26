<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/newVente.css">

<title>ENI Enchères - Nouvelle vente</title>
</head>
<body>
<!-- HEADER -->
<%@ include file="/WEB-INF/fragmentsJsp/entete.jspf"%>

<div class="container-fluid">
	<div class="row my-4">
		<div class="col-3">
		<h1>ENI Enchères</h1>
		</div>
		<div class="col-9 text-center">
		<h2>Nouvelle vente</h2>
		</div>
	</div>
	<div class="row text-center">
		<div class="col-4">
			<img class="img-fluid" src="<%=request.getContextPath()%>/imgs/chaton.jpeg" alt="bloc image">
		</div>
		  <%@ include file="/WEB-INF/fragmentsJsp/affichageErreurs.jspf"%>   
		<div class="col-8">
			<form action="<%=request.getContextPath()%>/connecte/vendre/article" method="post" >
				<!-- ARTICLE -->
				<div class="row my-2">
					<div class="col-3 text-right">
						<label class="me-5" for="article">Article:</label>
					</div>
					<div class="col-9 text-left">
						<input type="text" id="article" name="article" placeholder="nom de l'article" value="${param.article}"/>
					</div>
				</div>
				<!-- DESCRIPTION -->
				<div class="row my-2">
					<div class="col-3 text-right">
						<label class="me-5" for="description">Description:</label>
					</div>
					<div class="col-9 text-left">
						<textarea name="description" id="description" placeholder="décrivez l'article ici" rows="5" cols="53">${param.description}</textarea>
					</div>
				</div>
				<!-- CATEGORIE -->
				<div class="row my-2">
					<div class="col-3 text-right">
						<label class="me-5" for="categorie">Catégorie:</label>
					</div>
					<div class="col-9 text-left">
						<select name="categorie">
							<option value="informatique">Informatique</option>
							<option value="ameublement">Ameublement</option>
							<option value="vetement">Vêtement</option>
							<option value="sportetloisirs">Sport & Loisirs</option>
						</select>
					</div>
				</div>
				<!-- PHOTO UPLOADER -->
				<div class="row my-2">
					<div class="col-3 text-right">
						<label class="me-5" for="photo">Photo de l'article:</label>
					</div>
					<div class="col-9 text-left">
						<input type="file" id="photo" name="photo" accept=".jpg, .jpeg, .png">
					</div>
				</div>
				<!-- DEBUT ENCHERE -->
				<div class="row my-2">
					<div class="col-3 text-right">
						<label class="me-5" for="debut">Début de l'enchère:</label>
					</div>
					<div class="col-9 text-left">
						<input type="date" id="debut" name="debut" value="${param.debut}">
					</div>
				</div>
				<!-- FIN ENCHERE -->
				<div class="row my-2">
					<div class="col-3 text-right">
						<label class="me-5" for="fin" >Fin de l'enchère:</label>
					</div>
					<div class="col-9 text-left">
						<input type="date" id="fin" name="fin" value="${param.debut}">
					</div>
				</div>
				<!-- PRIX INITIAL -->
				<div class="row my-2">
					<div class="col-3 text-right">
						<label class="me-5" for="fin">Mise à prix:</label>
					</div>
					<div class="col-9 text-left">
						<input type="number" id="fin" name="map" value="150" value="${param.map}">
					</div>
				</div>
				<!-- RETRAIT -->
				<div id="retrait_block">
					<div class="row my-2">
						<div class="col-3">
							<h4 class="my-3">Retrait</h4>
						</div>
					</div>
   					
   						<div class="row my-2">
							<div class="col-3 text-right">
								<label class="me-5" for="rue">Rue:</label>
							</div>
							<div class="col-9 text-left">
								<input type="text" id="rue" name="rue" placeholder="votre rue" value="${param.rue==null? sessionScope.utilisateur.rue : param.rue}">
							</div>
						</div>
							<div class="row my-2">
							<div class="col-3 text-right">
								<label class="me-5" for="cp">Code postal:</label>
							</div>
							<div class="col-9 text-left">
								<input type="text" id="cp" name="cp" placeholder="votre code postal" value="${param.cp==null? sessionScope.utilisateur.codePostal : param.cp}">
							</div>
						</div>
							<div class="row my-2">
							<div class="col-3 text-right">
								<label class="me-5" for="ville" value="${param.ville}">Ville:</label>
							</div>
							<div class="col-9 text-left">
								<input type="text" id="ville" name="ville" placeholder="votre ville" value="${param.ville==null? sessionScope.utilisateur.ville : param.ville}">
							</div>
						</div>
   				</div>
   				<!-- BOUTONS -->
   				<div class="row my-4">
					<div class="col-3 text-right">
						<input class="boutons" type="submit" value="Enregistrer">
					</div>
					<div class="col-9 text-left">
						<input class="boutons" type="button" value="Annuler">
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- FOOTER -->
<%@ include file="/WEB-INF/fragmentsJsp/footer.jspf"%>
<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
</body>
</html>