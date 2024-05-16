<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.models.Test" %>
<%@ page import="com.mycompany.controllers.TestController" %>
<%@ page import="com.mycompany.dao.TestDao" %>

<%
    TestController testController = new TestController();
    TestDao testDao = new TestDao();
    List<Test> tests = testDao.getAllTests();
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />

<title></title>

<!-- Custom fonts for this template-->
<link
	href="${pageContext.request.contextPath}/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet" />

<!-- Custom styles for this template-->
<link href="${pageContext.request.contextPath}/css/sb-admin-2.min.css"
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
								class="mr-2 d-none d-lg-inline text-gray-600 small">Douglas
									McGee</span> <img class="img-profile rounded-circle"
								src="img/undraw_profile.svg" />
						</a></li>
					</ul>
				</nav>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">
					<!-- Page Heading -->
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">Tests</h1>
					</div>

					<!-- TODO: custom content here -->
					<!-- DataTales Example -->
					<div class="row p-2">
						<div class="container d-flex justify-content-end align-items-end">

							<a href="${pageContext.request.contextPath}/tests/new"
								class="btn btn-primary btn-icon-split ml-2"> <span
								class="icon text-white-50"> <i class="fas fa-plus"></i>
							</span> <span class="text">New </span>
							</a>


						</div>
					</div>

					<div class="card shadow mb-4">
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered" id="dataTable" width="100%"
									cellspacing="0">
									<thead>
										<tr>
											<th>ID</th>
											<th>Label</th>
											<th>Days To Get Result</th>
											<th>Actions</th>
										</tr>
									</thead>
									<tbody>
										<%
										for (Test test : tests) {
										%>
										<tr>
											<td><%= test.getId() %></td>
                                        <td><%= test.getLabel() %></td>
                                        <td><%= test.getPrice() %></td>
                                        <td><%= test.getResult_after() %></td>
                                        <td>
												<div class="d-flex justify-content-end align-items-end">

													<a
														href="<%= request.getContextPath() %>/tests/edit?id=<%= test.getId() %>"
														class="btn btn-danger btn-icon-split"> <span
														class="icon text-white-50"> <i class="fas fa-trash"></i>
													</span>
													</a> <a
														href="<%= request.getContextPath() %>/tests/delete?id=<%= test.getId() %>"
														class="btn btn-warning btn-icon-split ml-2"> <span
														class="icon text-white-50"> <i class="fas fa-edit"></i>
													</span>
													</a>

												</div>
											</td>
										</tr>
										<%
										}
										%>
									</tbody>
								</table>
							</div>
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
