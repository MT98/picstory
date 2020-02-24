<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ajouter Utilisateurs</title>
<link rel="stylesheet" href="../assets/style.css" >
<link rel="stylesheet" href="../assets/bootstrap.min.css" >
</head>
<body>
	<c:import url="../template/entete.jsp"/>
	<main role="main">
	<div  class="container " style="background-color:#F3F8F9;">
	  <h2>Ajout d'un utilisateur</h2>
	  <form method="post" action="add" class="col-sm-8">
	    <div class="form-group">
	      <label for="nom">Nom:</label>
	      <input type="text" class="form-control" id="nom"  name="nom" value="${ requestScope.utilisateur.lastname }">
	      <span>${ messageErreurs.nom }</span><br>
	    </div>
	    <div class="form-group">
	      <label for="prenom">Prenom:</label>
	      <input type="text" class="form-control" id="prenom"  name="prenom" value="${ requestScope.utilisateur.firstname }">
	      <span>${ messageErreurs.prenom }</span><br>
	    </div>
	    <div class="form-group">
	      <label for="email">Email:</label>
	      <input type="text" class="form-control" id="email"  name="email" value="${ requestScope.utilisateur.email }">
	      <span>${ messageErreurs.email }</span><br>
	    </div>
	    <div class="form-group">
	      <label for="password">Mot de passe:</label>
	      <input type="password" class="form-control" id="password"  name="password">
	      <span>${ messageErreurs.password }</span><br>
	    </div>
	    <div class="form-group">
	      <label for="passwordBis">Mot de passe:</label>
	      <input type="password" class="form-control" id="passwordBis"  name="passwordBis">
	      <span>${ messageErreurs.passwordBis }</span><br>
	    </div>
	    <div class="form-group">
	    	<div class="form-check">
			  <label class="form-check-label">
			    <input type="checkbox" class="form-check-input" value="on" name="role"> Elever au rang d'administrateur
			  </label>
			</div>
	    </div>
	    
	
	    <button type="submit" class="btn btn-primary">Submit</button>
	    <span class="${ empty messageErreurs ? 'succes' : 'erreur'}">${ statusMessage }</span>
	  </form>
	</div>
	</main>
	
	<c:import url="../template/footer.jsp"/>
	  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0-beta1/jquery.min.js"></script>
     <script type="text/javascript" src="../assets/bootstrap.min.js"></script>
</body>
</html>