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

    <title>Add Patient</title>

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
                                    class="mr-2 d-none d-lg-inline text-gray-600 small">Admin</span> <img
                                    class="${pageContext.request.contextPath}/img-profile rounded-circle" src="img/undraw_profile.svg" />
                            </a>
                        </li>
                    </ul>
                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- Page Heading -->
                    <h1 class="h3 mb-4 text-gray-800">Add New Patient</h1>

                    <!-- Add Patient Form -->
                    <div class="row">
                        <div class="col-lg-6">
                            <form class="user" action="${pageContext.request.contextPath}/patients/new" method="post">
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-user" name="fName"
                                        id="inputFName" placeholder="First Name" required>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-user" name="lName"
                                        id="inputLName" placeholder="Last Name" required>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-user" name="cin"
                                        id="inputCIN" placeholder="CIN" required>
                                </div>
                                <div class="form-group">
                                    <input type="email" class="form-control form-control-user" name="email"
                                        id="inputEmail" placeholder="Email Address" required>
                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-control form-control-user" name="password"
                                        id="inputPassword" placeholder="Password" required>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-user" name="phone"
                                        id="inputPhone" placeholder="Phone Number">
                                </div>
                                <div class="form-group">
                                    <select class="form-control form-control-user" name="gender" id="selectGender">
                                        <option value="Male">Male</option>
                                        <option value="Female">Female</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <input type="date" class="form-control form-control-user" name="birthdate"
                                        id="inputBirthdate" placeholder="Birth Date">
                                </div>
                                <button type="submit" class="btn btn-primary">Save Patient</button>
                            </form>
                        </div>
                    </div>
                    <!-- End Add Patient Form -->

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
