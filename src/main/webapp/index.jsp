<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page : Pharma Application</title>
<%@ include file="allcomponent/allcss.jsp"%>
<style type="text/css">
a {
	text-decoration: none;
	color: black;
}

a:hover {
	text-decoration: none;
	color: black;
}
</style>
</head>
<body style="background-color: #f0f1f2;">
	<%@ include file="allcomponent/navbar.jsp"%>


	<div class="container">
		<div class="row justify-content-center p-5">

			<!-- Add Batche Code and Medicine Code -->
			<div class="col-md-4">
				<a href="addbatch.jsp">
					<div class="card">
						<div class="card-body text-center">
							<div class="text-info">
								<i class="fa fa-heartbeat fa-2x" aria-hidden="true"></i>
							</div>
							<h4 class="text-info">Add Batches</h4>
							<p class="text-info">Medicine stored in batches</p>
						</div>
					</div>
				</a>
			</div>

			<!-- Add Medicine Code and Name -->
			<div class="col-md-4">
				<a href="addmedicine.jsp">
					<div class="card">
						<div class="card-body text-center">
							<div class="text-info">
								<i class="fa fa-medkit fa-2x" aria-hidden="true"></i>
							</div>
							<h4 class="text-info">Add Medicine Code</h4>
							<p class="text-info">Medicine Master</p>
						</div>
					</div>
				</a>
			</div>

			<!-- View Pharma Store -->
			<div class="col-md-8 mt-3">
				<a href="pharmastore.jsp">
					<div class="card">
						<div class="card-body text-center">
							<div class="text-info">
								<i class="fa fa-globe fa-2x" aria-hidden="true"></i>
							</div>
							<h4 class="text-info">Pharma Store</h4>
							<p class="text-info">Store details</p>
						</div>
					</div>
				</a>
			</div>

		</div>
	</div>



	<%@ include file="allcomponent/footer.jsp"%>
</body>
</html>