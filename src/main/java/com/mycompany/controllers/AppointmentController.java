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
@WebServlet(urlPatterns = { "/appointments", "/appointments/new", "/appointments/add", "/appointments/update",
		"/appointments/edit", "/appointments/delete" })
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
		String id;
		switch (action) {
		case "/appointments/new":
			// get all tests
			List<Test> tests = tdao.getAllTests();
			request.setAttribute("tests", tests);
			request.getRequestDispatcher("/WEB-INF/views/appointments/appointmentform.jsp").forward(request, response);

			break;
		case "/appointments/update":
			// retrieve id from params
			id = request.getParameter("id");
			// get apt to be updated.. if not exits redirect to 404
			
			if (id == null && id.isEmpty()) {
				request.setAttribute("errors", new ArrayList().add("No patient with the given id"));
				aptHome(request, response);
			} 
			
			Patient p = pdao.getPatientById(Integer.parseInt(id));
			if (p == null) {
				request.setAttribute("errors", new ArrayList().add("No patient with the given id"));
				aptHome(request, response);
			}
			
			request.setAttribute("patient", p);
			request.getRequestDispatcher("/WEB-INF/views/appointments/appointmentform.jsp").forward(request,
					response);
			// send it with the response to fill the form inputs

			break;
		case "/appointments/delete":
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
		System.out.println("----------------------------------");
		System.out.println(action);
		System.out.println("----------------------------------");
		switch (action) {
		case "/appointments/add":
			// get all tests
			String time = request.getParameter("from");
			String day = request.getParameter("day");
			String cin = request.getParameter("cin");
			int test_id = Integer.parseInt(request.getParameter("test"));

			Patient p = pdao.getPatientById(test_id);
			if (p == null) {
				List<String> errors = new ArrayList<String>();
				errors.add("no patient with the given cin");
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("/WEB-INF/views/appointments/appointmentform.jsp").forward(request,
						response);
				break;
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
				request.getRequestDispatcher("/WEB-INF/views/appointments/appointmentform.jsp").forward(request,
						response);
			} else {
				request.setAttribute("errors", new ArrayList().add("Appointment was not saved"));
				request.getRequestDispatcher("/WEB-INF/views/appointments/appointmentform.jsp").forward(request,
						response);
			}

			break;
		case "/appointments/edit":
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
		System.out.println("----------------------------------");
		System.out.println(id);
		System.out.println("----------------------------------");
		// get apt to be deleted.. if not exits redirect to 404
		if (id == null && id.isEmpty()) {
			request.setAttribute("errors", new ArrayList().add("No patient with the given id"));
			aptHome(request, response);
		} 
		
		Patient p = pdao.getPatientById(Integer.parseInt(id));
		if (p == null) {
			request.setAttribute("errors", new ArrayList().add("No patient with the given id"));
			aptHome(request, response);
		}
		
		pdao.deletePatient(p);
		response.sendRedirect(request.getContextPath() + "/apts");
	}

}
