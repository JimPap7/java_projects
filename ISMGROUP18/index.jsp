<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<%@ include file="header.jsp" %>
		<title>Βρες και υιοθέτησε ένα αδέσποτο ζώο </title>
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

		<div id="myCarousel" class="carousel slide" data-ride="carousel">
			<!-- Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
				<li data-target="#myCarousel" data-slide-to="3"></li>
			</ol>

			<!-- Wrapper for slides -->
			<div class="carousel-inner">
				<div class="item active">
					<img src="<%=request.getContextPath()%>/images/wallpaper.jpg" alt="Wallpaper">
				</div>
				<div class="item">
					<a href="adopt.jsp"><img src="<%=request.getContextPath()%>/images/gask.jpg"  alt="gask"></a>
					<div class="carousel-caption">
						<h1 style="font-size:32px;">Υιοθέτησε</h1>
					</div>
				</div>
				<div class="item">
					<a href="addpet.jsp"><img src="<%=request.getContextPath()%>/images/adopt.jpg"  alt="adopt"></a>
					<div class="carousel-caption">
						<h1 style="font-size:32px;">Καταχώρησε Αδέσποτο</h1>
					</div>
				</div>
				<div class="item">
					<img src="<%=request.getContextPath()%>/images/asoee.jpg"  alt="asoee">
					<div class="carousel-caption">
						<h3 style="font-size:32px;">Εργασία στο μάθημα "Ανάπτυξη και Αρχιτεκτονικές Πληροφοριακών Συστημάτων"</h3>	
					</div>
				</div>
			</div>
			<!-- Left and right controls -->
			<a class="left carousel-control" href="#myCarousel" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left"></span>
				<span class="sr-only">Previous</span>
			</a>
			<a class="right carousel-control" href="#myCarousel" data-slide="next">
				<span class="glyphicon glyphicon-chevron-right"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>
			
		<br><br><br><br><br><br><br><br><br>
		<%@ include file="footer.jsp" %>
	</body>
</html>