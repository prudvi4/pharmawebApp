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
<title>Medicine Details: PharmaApplication</title>
<%@ include file="allcomponent/allcss.jsp"%>
</head>
<body style="background-color: #f0f1f2;">
	<%@ include file="allcomponent/navbar.jsp"%>

	<div class="container p-4">
		<!-- Medicine Table -->
		<h4 class="text-center text-dark">View All Medicines</h4>
		<table class="table mt-3">
			<thead class="thead-dark">
				<tr>
					<th scope="col">Medicine Code</th>
					<th scope="col">Medicine Name</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody>

				<%
				PharmaDaoImpl dao1 = new PharmaDaoImpl();
				Set<MedicineInfo> values1 = dao1.allMedicineInfo();
				for (MedicineInfo medicine : values1) {
				%>
				<tr>
					<td> <%= medicine.getMedicineCode() %> </td>
					<td> <%= medicine.getMedicineName() %> </td>
					<td><a href="" class="btn btn-sm btn-warning"><i
							class="fa fa-pencil-square-o m-1" aria-hidden="true"></i>Edit</a> <a
						href="" class="btn btn-sm btn-danger m-1"><i
							class="fa fa-trash" aria-hidden="true"></i>Delete</a></td>
				</tr>
				<%
				}
				%>

			</tbody>
		</table>

		<hr>
	</div>

	<%@ include file="allcomponent/footer.jsp"%>
</body>
</html>