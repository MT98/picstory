<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Partager Album</title>
<link rel="stylesheet" href="../assets/style.css" >
<link rel="stylesheet" href="../assets/bootstrap.min.css" >
</head>
<body>
	<c:import url="../template/entete.jsp"/>
	<main role="main">
	<div  class="container " style="background-color:#F3F8F9;">
	  <h2>Ajout d'un utilisateur</h2>
	  <form method="post" action="share" class="col-sm-8">
	    <div class="form-group">
	      <label for="nom">Album:</label>
	      <input type="text" class="form-control" id="nom"  name="album" value="${ album.title }" readonly="readonly">
	      <input type="hidden" class="form-control" id="nom"  name="albumId" value="${ album.id }" >
	      
	    </div>
	    <div class="form-group">
			<label>Album </label>
			<div class="col-md-6">
                 <select class="browser-default custom-select" name="userId" required>
                      <option value="0" selected>Open this select menu</option>
                       <c:forEach items="${utilisateurs}" var="utilisateur">
                        	<option value="${utilisateur.id}">${utilisateur.firstname}  ${utilisateur.lastname}</option>                   	 	
                       </c:forEach>				
				</select>
            </div>
		</div>
	    <button type="submit" class="btn btn-primary">Submit</button>	   
	  </form>
	</div>
	</main>
	
	<c:import url="../template/footer.jsp"/>
	  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0-beta1/jquery.min.js"></script>
     <script type="text/javascript" src="../assets/bootstrap.min.js"></script>
</body>
</html>