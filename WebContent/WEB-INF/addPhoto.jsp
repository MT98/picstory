<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ajouter Photo</title>
<link rel="stylesheet" href="../assets/style.css" >
<link rel="stylesheet" href="../assets/bootstrap.min.css" >
</head>
<body>
	<c:import url="../template/entete.jsp"/>
	<main role="main">
	
	<div  class="container " style="background-color:#F3F8F9;height: 600px;">
		<form class="form-horizontal" method="post" action="add">
			<fieldset>
               <legend class="text-center header">Ajouter photo</legend>

                <div class="form-group">
                    <label>Titre </label>
                    <div class="col-md-6">
                        <input id="fname" name="title" type="text" placeholder="Nom photo" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-group">
					  <div class="input-group-prepend">
					    <span class="input-group-text" id="inputGroupFileAddon01">Upload</span>
					  </div>
					  <div class="custom-file">
					    <input type="file" class="custom-file-input" id="inputGroupFile01"
					      aria-describedby="inputGroupFileAddon01" name="photo">
					    <label class="custom-file-label" for="inputGroupFile01">Choose file</label>
					  </div>
					</div>               
				 </div>
				<div class="form-group">
                    <div class="col-md-12 text-center">
                        <button type="submit" class="btn btn-primary btn-lg">Ajouter</button>
                    </div>
                 </div>
             
           </fieldset> 
                        
		</form>
	</div>
	</main>
	 <c:import url="../template/footer.jsp"/>
	 <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0-beta1/jquery.min.js"></script>
     <script type="text/javascript" src="./assets/bootstrap.min.js"></script>
</body>
</html>