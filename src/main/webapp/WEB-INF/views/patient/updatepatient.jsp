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

    <title>Update Patient</title>

    <!-- Custom fonts for this template-->
    <link href="${pageContext.request.contextPath}/vendor/fontawesome-free/css/all.min.css"
        rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
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
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">
                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow"><a class="nav-link dropdown-toggle" href="#"
                                id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true"
                                aria-expanded="false"> <span
                                    class="mr-2 d-none d-lg-inline text-gray-600 small">Douglas McGee</span> <img
                                    class="img-profile rounded-circle" src="img/undraw_profile.svg" />
                            </a>
                        </li>
                    </ul>
                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- Page Heading -->
                    <h1 class="h3 mb-4 text-gray-800">Update Patient</h1>

                    <!-- Update Patient Form -->
                    <div class="row">
                        <div class="col-lg-6">
                            <form class="user" action="${pageContext.request.contextPath}/patients/update" method="post">
                            <input type="hidden" name="id" value="${patient.getId()}">
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-user" name="fName"
                                        id="inputFName" placeholder="First Name" required value="${patient.getFName()}">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-user" name="lName"
                                        id="inputLName" placeholder="Last Name" required value="${patient.getLName()}">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-user" name="cin"
                                        id="inputCIN" placeholder="CIN" required value="${patient.getCin()}">
                                </div>
                                <div class="form-group">
                                    <input type="email" class="form-control form-control-user" name="email"
                                        id="inputEmail" placeholder="Email Address" required value="${patient.getEmail()}">
                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-control form-control-user" name="password"
                                        id="inputPaasword" placeholder="Password" required value="${patient.getPassword()}">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-user" name="phone"
                                        id="inputPhone" placeholder="Phone Number" value="${patient.getPhone()}">
                                </div>
								<div class="form-group">
									<select class="form-control form-control-user" name="gender"
										id="selectGender">
										<option value="Male"
											${patient.getGender().equals("Male") ? "selected" : ""}>Male</option>
										<option value="Female"
											${patient.getGender().equals("Female") ? "selected" : ""}>Female</option>
									</select>
								</div>
								<div class="form-group">
                                    <input type="date" class="form-control form-control-user" name="birthdate"
                                        id="inputBirthdate" placeholder="Birth Date" value="${patient.getBirthdate()}">
                                </div>
                                <button type="submit" class="btn btn-primary">Update Patient</button>
                            </form>
                        </div>
                    </div>
                    <!-- End Update Patient Form -->

                </div>
                <!-- /.container-fluid -->
            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
<%--             <jsp:include page="/WEB-INF/views/components/footer.jsp" /> --%>
            <!-- End of Footer -->
        </div>
        <!-- End of Content Wrapper -->
    </div>
    <!-- End of Page Wrapper -->

    <!-- Bootstrap core JavaScript-->
    <script src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="${pageContext.request.contextPath}/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="${pageContext.request.contextPath}/js/sb-admin-2.min.js"></script>
</body>

</html>
