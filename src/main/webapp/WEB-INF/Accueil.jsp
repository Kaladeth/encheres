<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ACCUEIL</title>
</head>
<body>
	<h1> Bienvenue </h1>
<form action="<%=request.getContextPath()%>/AccueilServlet" method="post"></form>
	<input type="submit" class="btn btn-primary" name="connecter">Connecter</button>
</body>
</html>