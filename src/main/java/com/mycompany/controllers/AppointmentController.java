package com.mycompany.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycompany.dao.AppointmentDAO;
import com.mycompany.dao.PatientDAO;
import com.mycompany.dao.TestDAO;
import com.mycompany.models.Appointment;
import com.mycompany.models.Patient;
import com.mycompany.models.Test;
import com.mycompany.utils.AptState;

/**
 * Servlet implementation class AppointmentController
 */
@WebServlet({ "/appointments", "/appointments_new", "/appointments_add", "/appointments_update",
		"/appointments_edit", "/appointments_delete" })
public class AppointmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AppointmentDAO dao = null;
	private TestDAO tdao = null;
	private PatientDAO pdao = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AppointmentController() {
		super();
		dao = new AppointmentDAO();
		tdao = new TestDAO();
		pdao = new PatientDAO();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();
		switch (action) {
		case "/appointments_new":
			// get all tests
			List<Test> tests = tdao.getAllTests();
			request.setAttribute("tests", tests);
			request.getRequestDispatcher("/WEB-INF/views/appointments/appointmentform.jsp").forward(request, response);

			break;
		case "/appointments_update":
			// retrieve id from params
			handleUpdate(request, response);
			// send it with the response to fill the form inputs

			break;
		case "/appointments_delete":
			// retrieve id from params
			handleDelete(request, response);
			// redirect to /appointments
			break;
		default:
			aptHome(request, response);
			break;

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();

		switch (action) {
		case "/appointments_add":
			// get all tests
			handleAdd(request, response);
			break;
			
		case "/appointments_edit":
			// retrieve id from params
			// get apt to be updated.. if not exits redirect to 404
			// send it with the response to fill the form inputs
			request.getRequestDispatcher("/WEB-INF/views/appointments/appointmentform.jsp").forward(request, response);
			break;

		default:
			aptHome(request, response);
			break;

		}
	}

	private void aptHome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Appointment> apts = dao.getFuturApt();
		if (apts != null) {
			request.setAttribute("apts", apts);
		} else {
			request.setAttribute("error", "Oops! Something went wong!");
		}
		// return responce with data
		request.getRequestDispatcher("/WEB-INF/views/appointments/appointments.jsp").forward(request, response);
	}

	private void handleDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		// get apt to be deleted.. if not exits redirect to 404
		if (id == null && id.isEmpty()) {
			request.setAttribute("errors", "no id given");
			aptHome(request, response);
			return;
		}

		if (dao.getAppointById(Integer.parseInt(id)) == null) {
			request.setAttribute("errors", "no appointment ::: with given id");
			aptHome(request, response);
			return;
		}
		dao.deleteAppoint(Integer.parseInt(id));
		response.sendRedirect(request.getContextPath() + "/appointments");
	}

	private void handleUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		// get apt to be updated.. if not exits redirect to 404

		if (id == null && id.isEmpty()) {
			request.setAttribute("errors", new ArrayList().add("No appointment with the given id"));
			aptHome(request, response);
			return;
		}

		Appointment apt = dao.getAppointById(Integer.parseInt(id));
		if (apt == null) {
			request.setAttribute("errors", new ArrayList().add("No appointment with the given id"));
			aptHome(request, response);
			return;
		}

		request.setAttribute("apt", apt);
		request.getRequestDispatcher("/WEB-INF/views/appointments/appointmentform.jsp").forward(request, response);
	}

	private void handleAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String time = request.getParameter("from");
		String day = request.getParameter("day");
		String cin = request.getParameter("cin");
		int test_id = Integer.parseInt(request.getParameter("test"));

		Patient p = pdao.getPatientById(test_id);
		if (p == null) {
			List<String> errors = new ArrayList<String>();
			errors.add("no patient with the given cin");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/WEB-INF/views/appointments/appointmentform.jsp").forward(request, response);
			return;
		}

		// get test
		Test t = tdao.getTestById(test_id);

		// check if apt availabe (later)

		// save apt
		Appointment newapt = new Appointment();
		newapt.setPatient(p);
		newapt.setTest(t);
		newapt.setDay(LocalDate.parse(day));
		newapt.setHour(LocalTime.parse(time));
		newapt.setState(AptState.PENDING);

		if (dao.saveAppoint(newapt)) {
			request.setAttribute("success", "Appointment added successfully");
			request.getRequestDispatcher("/WEB-INF/views/appointments/appointmentform.jsp").forward(request, response);
		} else {
			request.setAttribute("errors", new ArrayList().add("Appointment was not saved"));
			request.getRequestDispatcher("/WEB-INF/views/appointments/appointmentform.jsp").forward(request, response);
		}
	}

}
