<!-- sidebar.jsp -->
<!-- Sidebar -->
<ul
	class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
	id="accordionSidebar">
	<!-- Sidebar - Brand -->
	<a class="sidebar-brand d-flex align-items-center justify-content-center"
		href="${pageContext.request.contextPath}/">
		<div class="sidebar-brand-icon rotate-n-15">
			<i class="fas fa-laugh-wink"></i>
		</div>
		<div class="sidebar-brand-text mx-3">SwingLab</div>
	</a>

	<!-- Divider -->
	<hr class="sidebar-divider my-0" />

	<!-- Nav Item - Dashboard -->
	<li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/dashboard">
			<i class="fas fa-fw fa-tachometer-alt"></i> <span>Dashboard</span>
	</a></li>

	<!-- Divider -->
	<hr class="sidebar-divider my-0" />

	<!-- Nav Item - Dashboard -->
	<li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/appointments">
			<i class="fas fa-fw fa-calendar"></i> <span>Appointments</span>
	</a></li>

	<!-- Divider -->
	<hr class="sidebar-divider my-0" />

	<!-- Nav Item - Dashboard -->
	<li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/patients">
			<i class="fas fa-fw fa-user"></i> <span>Patients</span>
	</a></li>

	<!-- Divider -->
	<hr class="sidebar-divider my-0" />

	<!-- Nav Item - Dashboard -->
	<li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/tests">
			<i class="fa fa-flask"></i> <span>Tests</span>
	</a></li>

	<!-- Divider -->
	<hr class="sidebar-divider my-0" />


	<!-- Nav Item - Dashboard -->
	<li class="nav-item active bottom-0"><a class="nav-link"
		href="${pageContext.request.contextPath}/logout"> <i class="fa fa-sign-out"></i> 
		<span>Log Out</span>
		</a></li>
</ul>
<!-- End of Sidebar -->