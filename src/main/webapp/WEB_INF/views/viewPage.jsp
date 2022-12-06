<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.min.css">
<link
	rel="https://cdnjs.datatables/1.12.1/css/dataTables.bootstrap4.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script
	src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap4.min.js"></script>
	<!-- <--excel link-->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<!-- pdf link -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body style="background-color: plum;">
 <a href="http://localhost:8080/downl/tutorial.xlsx" ><i class="fa fa-file-excel-o" style="font-size:48px;color:red"></i></a> 
 <a href="http://localhost:8080/users/export/pdf" ><i class="fa fa-file-pdf-o" style="font-size:48px;color:blue;"></i></a>
	<div class="container">
		<div>
			<ul class="nav nav-tabs btn-succcess">
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="./test">Add</a></li>
				<li class="nav-item"><a class="nav-link"
					href="./allData">View</a></li>
			</ul>
		</div>
		<h2 class="display-4 text-danger" align="center" id="h3">Registration
			Details</h2>
			
		<div class="container mt-5" id="menu1">
		<form action="./search" >
				<fieldset>
					<legend>Filter:</legend>
					
						<div class="row">
							<div class="form-group col-md-4">
								<label>Library Name :<span class="text-danger">*</span></label>
								<select name="lName" class="form-control">
									<option value="0">-select-</option>
									<c:forEach items="${allLibraryList}" var="library">
										<option value='${library.libraryId}'>${library.libraryName}</option>
									</c:forEach>
								</select>
							</div>

							<div class="form-group col-md-4">
								<label>Subscription  Type :<span class="text-danger">*</span></label>
								<select name="sName" class="form-control">
									<option value="0">-select-</option>
									<c:forEach items="${subscriptionList}" var="subscription">
										<option value='${subscription.subscriptionId}'>${subscription.subscriptionType}</option>
									</c:forEach>
								</select>
							</div>
							<div>

								<button style="margin-top: 15px;" type="submit"
									class="btn btn-lg btn-success btn-submit">
									<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
									Search
								</button>
							</div>
						</div>
						</fieldset>
					
				
</form>
			<table class="table table-stripped" id="BookingTable" border="1">
				<thead>
					<tr>
						<th>Sl#</th>
						<th>Name</th>
						<th>Email</th>
						<th>Mobile No</th>
						
						<th>Age</th>
						<th>Course</th>
						<th>Image</th>
						<th>College Name</th>
						<th>Branch Type</th>
						<th>Action</th>
						<th>Download</th>
					</tr>
				</thead>
				<tbody>


					<c:forEach items="${allBookingList}" var="rDetails"
						varStatus="count">
						<tr>
							<td>${count.count}</td>
							<td>${rDetails.applicantName}</td>
							<td>${rDetails.email}</td>
							<td>${rDetails.mobileNo}</td>

							<td>${rDetails.age}</td>
							<td>${rDetails.course }</td>
							<td><a
									href="/downloadFile?id=${rDetails.registrationId}">${rDetails.idProof}</a></td>
							<td>${rDetails.college.collegeName}</td>
							<td>${rDetails.branch.branchName}</td>
                            <td><a href="./deleteUser?registrationId=${rDetails.registrationId }"  class="btn btn-danger">DELETE</a></td>
                           <td><a href="http://localhost:8080/download/tut/rid=${rDetails.registrationId }" ><i class="fa fa-file-excel-o" style="font-size:48px;color:red"></i></a>&nbsp;&nbsp; <a href="http://localhost:8080/use/export/pdf/pId=${rDetails.registrationId }" ><i class="fa fa-file-pdf-o" style="font-size:48px;color:blue;"></i></a></td>
						
						</tr>
					</c:forEach>

				</tbody>
			</table>

		</div>
	</div>

</body>
<script>
       $(document).ready(function () {
    $('#BookingTable').DataTable();
    $('#h3').select2();
});
   </script>
</html>