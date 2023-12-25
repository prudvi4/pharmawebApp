<%@page import="com.excelr.pharma.vo.MedicineInfo"%>
<%@page import="com.excelr.pharma.vo.BatchInfo"%>
<%@page import="java.util.Set"%>
<%@page import="com.excelr.pharma.dao.PharmaDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pharma Store : PharmaApplication</title>
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


			<!-- View Batche Code Details -->
			<div class="col-md-4">
				<a href="viewBatch.jsp">
					<div class="card">
						<div class="card-body text-center">
							<div class="text-info">
								<i class="fa fa-heart fa-2x" aria-hidden="true"></i>
							</div>
							<h4 class="text-info">View Batches</h4>
							<p class="text-info">View/Edit/Delete Batches</p>
						</div>
					</div>
				</a>
			</div>


			<!-- View Medcine  Details -->
			<div class="col-md-4">
				<a href="viewMedicine.jsp">
					<div class="card">
						<div class="card-body text-center">
							<div class="text-info">
								<i class="fa fa-stethoscope fa-2x" aria-hidden="true"></i>
							</div>
							<h4 class="text-info">View Medicines</h4>
							<p class="text-info">View/Edit/Delete Medicines</p>
						</div>
					</div>
				</a>
			</div>


		</div>
	</div>

	<%@ include file="allcomponent/footer.jsp"%>
</body>
</html>