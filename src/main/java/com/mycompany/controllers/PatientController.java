package com.mycompany.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycompany.dao.PatientDAO;
import com.mycompany.models.Patient;

/**
 * Servlet implementation class PatientController
 */
@WebServlet("/patients")
public class PatientController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PatientDAO patientDAO = new PatientDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("in doGET /patients");
		List<Patient> patients = patientDAO.getAllPatients();
		if (patients == null) {
			System.out.println("no patients");
			return;
		}
		request.setAttribute("patients", patients);
		request.getRequestDispatcher("/WEB-INF/views/patient/patients.jsp").forward(request, response);
		System.out.println("end of doGET /patients");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
