<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="../assets/style.css" >
<link rel="stylesheet" href="../assets/bootstrap.min.css" >
<title>	Album </title>
</head>
<body>
	<c:import url="../template/entete.jsp"/>
	
	<main role="main">

      <section class="jumbotron text-center">
        <div class="container">
          <h1 class="jumbotron-heading">Album example</h1>
          <p class="lead text-muted">Something short and leading about the collection below—its contents, the creator, etc. Make it short and sweet, but not too short so folks don't simply skip over it entirely.</p>
          <p>
            <a href="<c:url value='/albums/add'/>" class="btn btn-primary my-2">Ajouter Album</a>
            <a href="#" class="btn btn-secondary my-2">Voir</a>
          </p>
        </div>
      </section>
      <div class=" container">
	  <div class=" ">
	  	 <div class="card" style="width: 20rem;">
			  <div class="card-body">
			    <h5 class="font-weight-bold mb-3">${ album.title }</h5>
			    <p class="mb-0">${ album.description}</p>
			  </div>
			  <ul class="list-group list-group-flush">
			    <li class="list-group-item">Nombre Photo: </li>
			    <li class="list-group-item">Date Creation</li>
			    <li class="list-group-item">Proprietaire : </li>
			  </ul>
			  <div class="card-body">
			    <a href="#!" class="btn btn-success">Partager</a>
			    <a href="<c:url value='/images/add'/>" class="btn btn-primary">Ajouter Photo</a>
			  </div>
		</div>
	  </div>
      <div class="album py-5 bg-light right" style="width:70%;">
        <div class="container">

          <c:forEach items="${ photos }" var="photo">
	          <div class="row">
	            <div class="col-md-4">
	              	<img src=""/>
	              	
	            </div>          
	          </div>
          </c:forEach>
        </div>
      </div>
      </div>

    </main>
	<c:import url="../template/footer.jsp"/>
</body>
</html>