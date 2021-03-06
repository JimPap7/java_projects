<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="errorPage.jsp"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<%@ include file="header.jsp" %>
		<title>Πληροφορίες Κράτησης</title>
	</head>
	<body>
		<nav class="navbar navbar-default">
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
		<br>
		
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
										<input id="username" type="text" class="form-control" name="Username" placeholder="Όνομα" required>
									</div>
								</div>
								<div class="form-group">
									<label for="inputpassword" class="control-label col-xs-2">Κωδικός</label>
										<div class="col-xs-10">
											<input id="inputpassword" type="password" class="form-control" name="Password" placeholder="Κωδικός" required>
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
		<div class='container theme-showcase' role='main'>
			<h2>Πληροφορίες Κράτησης</h2>
			<div class="alert">
				<b>Όνομα Φιλοζωικής:</b> Ζωοφιλική Ένωση Ηλιούπολης<br><b>Τηλ.Επικοινωνίας:</b> 2103008100<br><b>Διεύθυνση:</b> Πλατεία Μαβίλη, Αθήνα
			</div>
		</div>
		
		<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
		
		<%@ include file="footer.jsp" %>
	</body>
</html>