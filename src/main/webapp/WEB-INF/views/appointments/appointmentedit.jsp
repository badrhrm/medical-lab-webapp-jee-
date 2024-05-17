<%@page import="com.mycompany.models.Test"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />

<title>Edit Appointment</title>

<!-- Custom fonts for this template-->
<link
	href="${pageContext.request.contextPath}/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet" />

<!-- Custom styles for this template-->
<link href="${pageContext.request.contextPath}/css/sb-admin-2.css"
	rel="stylesheet" />
</head>

<body id="page-top">
	<!-- Page Wrapper -->
	<div id="wrapper">
		<!-- Sidebar -->
		<jsp:include page="/WEB-INF/views/components/sidebar.jsp" />
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
			<!-- Main Content -->
			<div id="content">
				<!-- Topbar -->
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
					<!-- Sidebar Toggle (Topbar) -->
					<button id="sidebarToggleTop"
						class="btn btn-link d-md-none rounded-circle mr-3">
						<i class="fa fa-bars"></i>
					</button>

					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">


						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow"><a
							class="nav-link dropdown-toggle" href="#" id="userDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <span
								class="mr-2 d-none d-lg-inline text-gray-600 small">Admin</span> <img class="img-profile rounded-circle"
								src="${pageContext.request.contextPath}/img/undraw_profile.svg" />
						</a></li>
					</ul>
				</nav>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">
					<!-- Page Heading -->
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">New Appointment</h1>
					</div>

					<!-- TODO: custom content here -->
					<div class="row">
						<div class="col-12">
							<form class="user " action="${pageContext.request.contextPath}/appointments/edit" method="POST">
								<div class="form-group row">
									<div class="col-sm-6 mb-3 mb-sm-0">
										<label for="from">Starting form</label>
										<select class="form-control text-dark form-control-user" name="from"
											id="from">
											<%-- Add options for time intervals --%>
											<%-- Time interval from 08:00 to 12:00 --%>
											<%
											for (int hour = 8; hour <= 12; hour++) {
											%>
											<%
											for (int minute = 0; minute <= 45; minute += 15) {
											%>
											<%-- Format hour and minute to two digits --%>
											<%
											String time = String.format("%02d:%02d", hour, minute);
											%>
											<option value="<%=time%>"><%=time%></option>
											<%
											}
											%>
											<%
											}
											%>

											<%-- Time interval from 14:00 to 17:00 --%>
											<%
											for (int hour = 14; hour <= 17; hour++) {
											%>
											<%
											for (int minute = 0; minute <= 45; minute += 15) {
											%>
											<%-- Format hour and minute to two digits --%>
											<%
											String time = String.format("%02d:%02d", hour, minute);
											%>
											<option value="<%=time%>"><%=time%></option>
											<%
											}
											%>
											<%
											}
											%>
										</select>
									</div>
									
									
									<input type="hidden" name="id" value="${appointment.getId()}">
								
									
									<div class="col-sm-6">
										<label for="day">Day of the appointment</label> 
										<input
											type="date" name="day" class="form-control form-control-user"
											id="day" placeholder="Appointment day"
											value="${appointment.getDay()}">
									</div>
								</div>
								<div class="form-group row">
									<div class="col-sm-6 mb-3 mb-sm-0">
										<label for="cin">Patient Cin</label>
   										<input type="text" name="cin" class="form-control form-control-user" 
   											id="cin" placeholder="cin" value="${appointment.getPatient().getCin()}">
									</div>
									<div class="col-sm-6">
									<label for="test">Test to pass</label>
										<select class="form-control form-control-user" name="test"
											id="test">
											<option value="1">Blood test</option>
											<option value="2">Diagnostique</option>
											<%
											List<Test> tests = (List<Test>) request.getAttribute("tests");
											if( tests != null ) {
											for(Test test: tests) { 
											%>
											<option value="${test.getId()}">${test.getLabel()}</option>
											<%}}%>
										</select>
									</div>
								</div>
								<button type="submit" class="btn btn-primary px-4">
									Save Appointment</button>

								<!-- if there are errors -->
								<%
								List<String> errors = (List<String>) request.getAttribute("errors");
								if (errors != null) {
								%>
								<div class="row mt-5">
									<ul class="text-danger">
										<%
										for (String err : errors) {
										%>
										<li>${ err }</li>
										<%
										}
										%>
									</ul>
								</div>
								<%
								}
								%>
								<!--  -->
								<% 
								String success = (String) request.getAttribute("success");
								if(success != null) {
								%>
								<div class="row mt-5">
									<ul class="text-success">
										<li><%=success %></li>
									</ul>
								</div>
								<%}%>
							</form>
						</div>
					</div>
					<!-- TODO: custom content here -->

				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright &copy; SwingLab 2024</span>
					</div>
				</div>
			</footer>
			<!-- End of Footer -->
		</div>
		<!-- End of Content Wrapper -->
	</div>
	<!-- End of Page Wrapper -->

	<!-- Bootstrap core JavaScript-->
	<script
		src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script
		src="${pageContext.request.contextPath}/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="${pageContext.request.contextPath}/js/sb-admin-2.min.js"></script>

	<!-- Page level plugins -->
	<script
		src="${pageContext.request.contextPath}/vendor/chart.js/Chart.min.js"></script>

	<!-- Page level custom scripts -->
	<script
		src="${pageContext.request.contextPath}/js/demo/chart-area-demo.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/demo/chart-pie-demo.js"></script>
</body>

</html>