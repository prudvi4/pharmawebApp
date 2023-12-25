<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Batch Page : PharmaApplication</title>
<%@ include file="allcomponent/allcss.jsp"%>
</head>
<body style="background-color: #f0f1f2;">
	<%@ include file="allcomponent/navbar.jsp"%>
	<c:if test="${not empty succMsg}">
		<div class="alert alert-success text-center" role="alert">${succMsg }</div>
		<c:remove var="succMsg" scope="session" />
	</c:if>
	<c:if test="${not empty failedMsg}">
		<div class="alert alert-danger text-center" role="alert">${failedMsg }</div>
		<c:remove var="failedMsg" scope="session" />
	</c:if>

	<div class="container">
		<div class="row p-5">
			<div class="col-md-6 offset-md-4">
				<div class="card">
					<div class="card-body">
						<h4 class="text-center text-dark">Add Batch</h4>
						<form action="AddBatchCodeServlet" method="post">
							<div class="form-group">
								<label for="exampleInputEmail1">Batch Code</label> <input
									type="text" name="batchCode" class="form-control"
									id="exampleInputEmail1" aria-describedby="emailHelp"
									placeholder="BTC-XXXX">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">Medicine Code</label> <input
									type="text" name="medicineCode" class="form-control"
									id="exampleInputPassword1" placeholder="MC_XXX">
							</div>

							<div class="form-group">
								<label for="exampleInputPassword1">Weight</label> <input
									type="number" name="weight" class="form-control"
									id="exampleInputPassword1" placeholder="123">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">Price</label> <input
									type="number" name="price" min="0" max="1000" step="0.01"
									class="form-control" id="exampleInputPassword1"
									placeholder="123">
							</div>
							<div class="form-group">
								<label for="inputState">Medicine Type Code</label> <select
									id="inputState" name="medicineTypeCode" class="form-control">
									<option selected>--- Select ---</option>
									<option value="S">Syrup</option>
									<option value="T">Tablet</option>
									<option value="C">Capsule</option>
								</select>
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">Refrigeration</label> <input
									type="text" name="refrigeration" class="form-control"
									id="exampleInputEmail1" aria-describedby="emailHelp"
									placeholder="Yes/No">
							</div>
							<div class="text-center">
								<button type="submit" class="btn btn-primary">Add Batch</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


	<%@ include file="allcomponent/footer.jsp"%>
</body>
</html>