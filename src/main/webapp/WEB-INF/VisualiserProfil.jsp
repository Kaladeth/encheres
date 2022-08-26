
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
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/styleIndex.css">

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
	

		<!--main bloc-->
		
		<main>
			<div class="container-fluid">
				<!--title-->
				<div class="mx-auto text-center">
				<br/>
					<h1>Détails de mon profil</h1>
					<br/><br/>
				</div>
				
				 <!--erreur-->
	            <%@ include file="/WEB-INF/fragmentsJsp/affichageErreurs.jspf"%>
				
				<!--filtre-->
					<div class=container-fluid>
										<!--Partie droite-->
						<div class="d-flex">
							<div class="col-2 offset-1">
								
							</div>						
							<div class="col-8">						
															
								<div class="row">
									<label class="col-3 offset-1">Pseudo : </label>
									<p class="col-8 "> ${utilisateur.pseudo}</p>
								</div>
								<div class="row">
									<label class="col-3 offset-1">Nom : </label>
									<p class="col-8"> ${utilisateur.nom}</p>
								</div>
								<div class="row">
									<label class="col-3 offset-1">Email : </label>
									<p class="col-8"> ${utilisateur.email} </p>
								</div>					
								<div class="row">
									<label class="col-3 offset-1"> Teléphone : </label>
									<p class="col-8">${utilisateur.telephone}</p>
								</div>
								
								<div class="row">
									<label class="col-3 offset-1">Rue : </label>
									<p class="col-8 "> ${utilisateur.rue}</p>
								</div>
								<div class="row">
									<label class="col-3 offset-1">Code postale : </label>
									<p class="col-8"> ${utilisateur.codePostal}</p>
								</div>
								<div class="row">
									<label class="col-3 offset-1">Ville : </label>
									<p class="col-8"> ${utilisateur.ville}</p>
								</div>
								
	                       </div>	
						</div>
					</div>		
			</div>
		</main>
		
		
		<footer>
	<br/><br/><br/><br/><br/><br/><br/>
	<!-- Pied de page -->	
	
	<%@ include file="/WEB-INF/fragmentsJsp/footer.jspf"%>

	</footer>
</body>
</html>