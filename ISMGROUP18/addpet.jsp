<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="errorPage.jsp"%> 

<!DOCTYPE html>
<html lang="en">
	<head>
		<%@ include file="header.jsp" %>
		<title>Καταχώρησε Αδέσποτο </title>
		<script>
		  function onSpeciesChange(select) {
		  	var raceSelectElement = document.getElementById("petRace");
		  	while(raceSelectElement.children.length > 0) {
		  		raceSelectElement.removeChild(raceSelectElement.firstChild);
		  	}

		    var xhr = new XMLHttpRequest();
			xhr.open("GET", "http://localhost:8080/pets/races?species=" + select.value);
			xhr.onreadystatechange = function() {
				if(xhr.readyState === 4 && xhr.status === 200) {
    				var racesArray = xhr.responseText.split(";");
    				for (var i = 0; i < racesArray.length; i++) {
    					var option = document.createElement("option");
    					option.setAttribute("value", racesArray[i]);
    					option.innerHTML = racesArray[i];
    					raceSelectElement.appendChild(option);
    				}
  				}
			}
		  }
		</script>
	</head>
	<body>
		 <nav class="navbar navbar-default navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>                        
					</button>
					<a class="navbar-brand" href="index.jsp">PetShelter</a>
				</div>
				<div class="collapse navbar-collapse" id="myNavbar">
				  <ul class="nav navbar-nav navbar-right">
					<li><a href="index.jsp"><b><span class="glyphicon glyphicon-home"></span><b>Αρχική</b></b></a></li>
					<li><a href="adopt.jsp"><b><i class="fa fa-paw" style="font-size:16px;"></i><b>Υιοθέτησε</b></b></a></li>
					<li><a href="addpet.jsp"><b><i class="fas fa-edit" style="font-size:16px"></i><b>Καταχώρησε Αδέσποτο</b></b></a></li>
					<li><a href="#myModal" data-toggle="modal"><b><span class="glyphicon glyphicon-log-in"></span><b>Είσοδος</b></b></a></li>
				  </ul>
				</div>
		  </div>
		</nav>
		
		
		<div id="myModal" class="modal fade">
		<br><br>
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title">Είσοδος</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" action="" method="post">
								<div class="form-group">
									<label for="username" class="control-label col-xs-2">Όνομα</label>
									<div class="col-xs-10">
										<input type="text" class="form-control" name="Username" placeholder="Όνομα" required>
									</div>
								</div>
								<div class="form-group">
									<label for="inputpassword" class="control-label col-xs-2">Κωδικός</label>
										<div class="col-xs-10">
											<input type="password" class="form-control" name="Password" placeholder="Κωδικός" required>
										</div>
								</div>
								<div class="form-group">
									<div class="col-xs-offset-2 col-xs-10">
										<div class="checkbox">
											<label><input type="checkbox"> Remember me</label>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-xs-offset-2 col-xs-10">
										<button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-ok"></span> Είσοδος</button>
										<button type="reset" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span> Ακύρωση</button>
									</div>
								</div>
							</form>
					</div>
				</div>
			</div>
		</div>
		<br>
		
		<div class="bg-image">  
	<div class="container">
  <div class="panel panel-default" id="form3" style="max-width:500px;margin-top:100px;">
    <div class="panel-heading" style="font-size:30px;text-align:center;background-color:green;color:white;border:none;">Στοιχεία Αδέσποτου</div>
    <div class="panel-body" >
		<form class="form-horizontal"  id="form3" action="" method="post">
			<div class="form-group">
				  <label class="control-label col-xs-2 col-sm-2 col-md-2" for="pet" style="color:white;">Ζώο: </label>
				  <div class="col-xs-10 col-sm-10 col-md-10">
					<select class="form-control "name="race" onchange="onSpeciesChange(this)">
						<option value="dog">Σκύλος</option>
						<option value="cat">Γάτα</option>
					</select>
				  </div>
				</div>
				<div class="form-group">
				  <label class="control-label col-xs-2 col-sm-2 col-md-2" for="race" style="color:white;">Ράτσα: </label>
				  <div class=" col-xs-10 col-sm-10 col-md-10 ">          
					<select class="form-control" id="petRace" name="race">
					</select>
				  </div>
				</div>
				<div class="form-group">
				  <label class="control-label col-xs-2 col-sm-2 col-md-2" for="race" style="color:white;">Φύλο: </label>
				  <div class="col-sm-10 col-xs-10 col-md-10">          
					<select class="form-control " name="age">
						<option value="1">Αρσενικό</option>
						<option value="2">Θηλυκό</option>
					</select>
				  </div>
				</div>
				<div class="form-group">
				  <label class="control-label col-xs-2 col-sm-2 col-md-2" for="colour"style="color:white;">Χρώμα: </label>
				  
				  <div class="col-sm-10 col-xs-10 col-md-10">          
					<select class="form-control" name="colour">
						<option value="1">Μαύρο</option>
						<option value="2">Άσπρο</option>
						<option value="3">Καστανό</option>
						<option value="4">Τρίχρωμο</option>
					</select>
				  </div>
				</div>
				<div class="form-group">
				  <label class="control-label col-xs-2 col-sm-2 col-md-2" for="age"style="color:white;" >Ηλικία: </label>
				  <div class="col-sm-10 col-xs-10 col-md-10">
					<select class="form-control " name="age">
						<option value="0" disabled selected>Ηλικία</option>
						<option value="1">Baby</option>
						<option value="2">Junior</option>
						<option value="3">Adult</option>
						<option value="4">Senior</option>
					</select>
				  </div>
				</div>
				<div class="form-group">
				  <label class="control-label col-xs-2 col-sm-2 col-md-2" for="image" style="color:white;" >Εικόνα Αδέσποτου </label>
				  <div class="col-sm-8 col-xs-10 col-md-10">          
					<input type="file"  id="image"  name="image">
				  </div>
				</div>
				<div class="form-group">
					<div class="col-xs-offset-2 col-sm-8">
						<button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-ok"></span> Υποβολή</button>
						<button type="reset" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span> Ακύρωση</button>
					</div>
				</div>
			</form>
		</div>
		</div>
    </div>
  </div>
		<br><br><br><br><br>
		<%@ include file="footer.jsp" %>
	</body>
</html>