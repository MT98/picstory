<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<c:import url="../template/entete.jsp"/>
	<c:choose>
		<c:when test="${ !empty requestScope.client }">
			<div class="container col-md-5">
				 <div class="card">
		         	<div class="card-body">
						<form class="form-horizontal" method="post" action="update">
							<caption>
								<h2>
									Modifier utilisateur
								</h2>
							</caption>
							
								<div class="form-group">
								<label>Nom :</label>
								<input class="form-control" type="text" name="nom" value="${ client.nom }">
								<span>${ messageErreurs.nom }</span><br>
								</div>
								
								<div class="form-group">
								<label>Prénom :</label>
								<input class="form-control" type="text" name="prenom" value="${ client.prenom }">
								<span>${ messageErreurs.prenom }</span><br>
								</div>
								
								<div class="form-group">
									<label>Login :</label>
									<input class="form-control" type="text" name="login" value="${ client.login }">
									<span>${ messageErreurs.login }</span><br>
								</div>
								
								<div class="form-group">
									<label>Password :</label>
									<input class="form-control" type="password" name="password" value="${client.password}">
									<span>${ messageErreurs.password }</span>
								</div>
								<c:if test="${client != null}">
                          		  <input type="hidden" name="id" value="<c:out value='${client.id}' />" />
                    			 </c:if>
								
								<input class="btn btn-primary" type="submit" value="Modifier">
								<span class="${ empty messageErreurs ? 'succes' : 'erreur'}">${ statusMessage }</span>
							
						</form>
					</div>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<p>L'utilisateur que vous souhaitez modifier n'existe pas</p>"
		</c:otherwise>
	</c:choose>
	<c:import url="../template/footer.jsp"/>
</body>
</html>