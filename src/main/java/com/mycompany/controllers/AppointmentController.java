package com.mycompany.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycompany.dao.AppointmentDAO;
import com.mycompany.models.Appointment;

/**
 * Servlet implementation class AppointmentController
 */
@WebServlet({"/appointments", "/appointments/new", "/appointments/update/*", "/appointments/delete/*"})
public class AppointmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AppointmentDAO dao = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppointmentController() {
        super();
        dao = new AppointmentDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();
		switch (action) {
			case "/appointments/new" :
				request.getRequestDispatcher("/WEB-INF/views/appointments/appointmentform.jsp").forward(request, response);
				break;
			case "/appointments/update" :
				// retrieve id from params
				// get apt to be updated.. if not exits redirect to 404
				// send it with the response to fill the form inputs
				request.getRequestDispatcher("/WEB-INF/views/appointments/appointmentform.jsp").forward(request, response);
				break;
			case "/appointments/delete" :
				// retrieve id from params
				// get apt to be deleted.. if not exits redirect to 404
				// redirect to /appointments
				request.getRequestDispatcher("/WEB-INF/views/appointments/appointments.jsp").forward(request, response);
				break;
			default:
				// retrieve  available apts
				List<Appointment> apts = dao.getFuturApt();
				if( apts != null ) {
					request.setAttribute("apts", apts);
				} else {
					request.setAttribute("error", "Oops! Something went wong!");
				}
				// return responce with data
				request.getRequestDispatcher("/WEB-INF/views/appointments/appointments.jsp").forward(request, response);
				break;
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
