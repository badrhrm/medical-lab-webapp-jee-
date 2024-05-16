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
	List<String> errors;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AppointmentController() {
		super();
		dao = new AppointmentDAO();
		tdao = new TestDAO();
		pdao = new PatientDAO();
		errors = new ArrayList<String>();
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
				errors.add("No patient with the given id");
				request.setAttribute("errors", errors);
				aptHome(request, response);
			} 
			
			Appointment appoint = dao.getAppointById(Integer.parseInt(id));
			if (appoint == null) {
				errors.add("No appoint with the given id");
				request.setAttribute("errors", errors);
				aptHome(request, response);
			}
			
			request.setAttribute("appointment", appoint);
			System.out.println("appoint inside update recovered :\n" + appoint);
			request.getRequestDispatcher("/WEB-INF/views/appointments/appointmentedit.jsp").forward(request,
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
				//List<String> errors = new ArrayList<String>();
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
				errors.add("Appointment was not saved");
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("/WEB-INF/views/appointments/appointmentform.jsp").forward(request,
						response);
			}

			break;
		case "/appointments/edit":
			editAppoint(request, response);
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
			errors.add("No patient with the given id");
			request.setAttribute("errors", errors);
			aptHome(request, response);
		} 
		
		Appointment appoint = dao.getAppointById(Integer.parseInt(id));
		if (appoint == null) {
			errors.add("No appoint with the given id");
			request.setAttribute("errors", errors);
			aptHome(request, response);
		}
		System.out.println("appoint id is : "+ id);
		System.out.println("appoint is : \n"+appoint);
		dao.deleteAppoint(appoint);
		response.sendRedirect(request.getContextPath() + "/appointments");
	}
	private void  editAppoint(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String appoint_id = request.getParameter("id");
		Appointment appointment = dao.getAppointById(Integer.parseInt(appoint_id));
		System.out.println("////////////////////////////////");
		System.out.println("appointment object before update: \n"+appointment);
		System.out.println("////////////////////////////////");
		
		String from = request.getParameter("from");
		String day = request.getParameter("day");
		String cin = request.getParameter("cin");
		int test_id = Integer.parseInt(request.getParameter("test"));
		
//		System.out.println("appoint_id is : \n"+appoint_id);
//		System.out.println("starting_from_time is : \n"+from);
//		System.out.println("day is : \n"+day);
//		System.out.println("cin is : \n"+cin);
//		System.out.println("test_id is : \n"+test_id);
		
		LocalTime startTime = LocalTime.parse(from);
  		LocalDate appointmentDay = LocalDate.parse(day);
		Patient patient = pdao.getPatientByCin(cin);
		if(patient == null) {
			System.out.println("patient retrieved in edit is null");
			return;
		}
		Test test = tdao.getTestById(test_id);
		if(test == null) {
			System.out.println("test retrieved in edit is null");
			return;
		}

  		appointment.setHour(startTime);
  		appointment.setDay(appointmentDay);
  		appointment.setPatient(patient);
  		appointment.setTest(test);
  		System.out.println("////////////////////////////////");
  		System.out.println("appointment object after update :\n"+appointment);
  		System.out.println("////////////////////////////////");
  		
  		dao.updateAppoint(appointment);
  		response.sendRedirect(request.getContextPath() + "/appointments");


//		appointment.setDay(day);
//		appointment.setHour(starting_from_time);


//		Patient p = pdao.getPatientById(test_id);
//		if (p == null) {
//			//List<String> errors = new ArrayList<String>();
//			errors.add("no patient with the given cin");
//			request.setAttribute("errors", errors);
//			request.getRequestDispatcher("/WEB-INF/views/appointments/appointmentform.jsp").forward(request,
//					response);
//			return;
//		}
//
//		// get test
//		Test t = tdao.getTestById(test_id);
//
//		// check if apt availabe (later)
//
//		// save apt
//		Appointment newapt = new Appointment();
//		newapt.setPatient(p);
//		newapt.setTest(t);
//		newapt.setDay(LocalDate.parse(day));
//		newapt.setHour(LocalTime.parse(time));
//		newapt.setState(AptState.PENDING);
//
//		if (dao.saveAppoint(newapt)) {
//			request.setAttribute("success", "Appointment added successfully");
//			request.getRequestDispatcher("/WEB-INF/views/appointments/appointmentform.jsp").forward(request,
//					response);
//		} else {
//			errors.add("Appointment was not saved");
//			request.setAttribute("errors", errors);
//			request.getRequestDispatcher("/WEB-INF/views/appointments/appointmentform.jsp").forward(request,
//					response);
//		}

	}

}
