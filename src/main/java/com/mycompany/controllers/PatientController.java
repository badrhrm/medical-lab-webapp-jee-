package com.mycompany.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycompany.dao.PatientDAO;
import com.mycompany.models.Patient;
import com.mycompany.utils.SecurityUtil;

/**
 * Servlet implementation class PatientController
 */
//@WebServlet("/patients")
@WebServlet({"/patients","/patients/new","/patients/delete","/patients/update"})
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
		String action = request.getServletPath();
	    switch(action) {
	        case "/patients":
	    		getPatients(request, response);
	            break;
	        case "/patients/new":
	            request.getRequestDispatcher("/WEB-INF/views/patient/addpatient.jsp").forward(request, response);
	            break;
	        case "/patients/delete":
	            deletePatient(request, response);
	            break;  
			case "/patients/update":
				String id = request.getParameter("id");
				int patientId = Integer.parseInt(id);
				Patient patient = patientDAO.getPatientById(patientId); 
				if (patient == null) {
					request.setAttribute("errorMessage", "Patient not found");
					request.getRequestDispatcher("/error.jsp").forward(request, response);
					return;
				}
				request.setAttribute("patient", patient);
				request.getRequestDispatcher("/WEB-INF/views/patient/updatepatient.jsp").forward(request, response);
				break;
	        default:
	            response.sendError(HttpServletResponse.SC_NOT_FOUND);
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();
	    switch(action) {
	        case "/patients/new":
	            addPatient(request, response);
	            break;  
	        case "/patients/update":
	            updatePatient(request, response);
	            break;      
	        default:
	            response.sendError(HttpServletResponse.SC_NOT_FOUND);
	    }
		
	}

	public void getPatients(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in doGET /patients");
		List<Patient> patients = patientDAO.getAllPatients();
		if (patients == null) {
			System.out.println("no patients");
			return;
		}
		System.out.println(patients);
		request.setAttribute("patients", patients);
		request.getRequestDispatcher("/WEB-INF/views/patient/patients.jsp").forward(request, response);
		System.out.println("end of doGET /patients");
	}
	
	public void addPatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fName = request.getParameter("fName");
        String lName = request.getParameter("lName");
        String cin = request.getParameter("cin");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String birthdateStr = request.getParameter("birthdate");

        // Validate input
        if (fName == null || lName == null || cin == null || email == null || password == null ||
            fName.isEmpty() || lName.isEmpty() || cin.isEmpty() || email.isEmpty() || password.isEmpty()) {
        	request.setAttribute("errorMessage", "Invalid input");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        // Parse birthdate string to Date object
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthdate = null;
        try {
            birthdate = simpleDateFormat.parse(birthdateStr);
        } catch (ParseException e) {
        	request.setAttribute("errorMessage", "Invalid birthdate");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }
        
        // Validate email format
	    if (!SecurityUtil.isValidEmail(email)) {
	        request.setAttribute("errorMessage", "Invalid email format.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
	        return;
	    }
	    // Validate password complexity (e.g., minimum length, special characters, etc.)
	    if (!SecurityUtil.isValidPassword(password)) {
	        request.setAttribute("errorMessage", "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
	        return;
	    }
	    

        // Create Patient object
        Patient patient = new Patient();
        patient.setFName(fName);
        patient.setLName(lName);
        patient.setCin(cin);
        patient.setEmail(email);
        patient.setPassword(password);
        patient.setPhone(phone);
        patient.setGender(gender);
        patient.setBirthdate(birthdate);

        // Save Patient object to the database
        patientDAO.createPatient(patient);

        // Redirect back to the form page or any other desired page
        response.sendRedirect(request.getContextPath() + "/patients");
        
	}
	public void deletePatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		patientDAO.deletePatientById(Integer.parseInt(id));
		response.sendRedirect(request.getContextPath() + "/patients");
	}

	public void updatePatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String fName = request.getParameter("fName");
		String lName = request.getParameter("lName");
		String cin = request.getParameter("cin");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String gender = request.getParameter("gender");
		String birthdateStr = request.getParameter("birthdate");
		
		// Print all parameters
		System.out.println("New Patient INFO TO UPDATE" + id);
		System.out.println("ID: " + id);
		System.out.println("First Name: " + fName);
		System.out.println("Last Name: " + lName);
		System.out.println("CIN: " + cin);
		System.out.println("Email: " + email);
		System.out.println("Password: " + password);
		System.out.println("Phone: " + phone);
		System.out.println("Gender: " + gender);
		System.out.println("Birthdate: " + birthdateStr);

		// Validate input
		if (fName == null || lName == null || cin == null || email == null || password == null || phone == null ||
		    gender == null || birthdateStr == null ||
		    fName.isEmpty() || lName.isEmpty() || cin.isEmpty() || email.isEmpty() || password.isEmpty()) {
		    request.setAttribute("errorMessage", "Invalid input");
		    request.getRequestDispatcher("/error.jsp").forward(request, response);
		    return;
		}

        // Parse birthdate string to Date object
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthdate = null;
        try {
            birthdate = simpleDateFormat.parse(birthdateStr);
        } catch (ParseException e) {
        	request.setAttribute("errorMessage", "Invalid birthdate");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }
        
        // Validate email format
	    if (!SecurityUtil.isValidEmail(email)) {
	        request.setAttribute("errorMessage", "Invalid email format.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
	        return;
	    }
	    // Validate password complexity (e.g., minimum length, special characters, etc.)
	    if (!SecurityUtil.isValidPassword(password)) {
	        request.setAttribute("errorMessage", "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
	        return;
	    }
	    

        // Create Patient object
        Patient patient = new Patient();
        patient.setFName(fName);
        patient.setLName(lName);
        patient.setCin(cin);
        patient.setEmail(email);
        patient.setPassword(password);
        patient.setPhone(phone);
        patient.setGender(gender);
        patient.setBirthdate(birthdate);

		// Save the updated patient
		patientDAO.updatePatient(patient);

		// Redirect back to the patients page
		response.sendRedirect(request.getContextPath() + "/patients");
	}
	

}
