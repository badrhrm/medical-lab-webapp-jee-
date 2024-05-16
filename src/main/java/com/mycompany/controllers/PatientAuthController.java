package com.mycompany.controllers;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycompany.dao.PatientDAO;
import com.mycompany.dao.PatientTokenDAO;
import com.mycompany.models.Patient;
import com.mycompany.models.PatientToken;
import com.mycompany.models.PatientToken.TokenType;
import com.mycompany.utils.EmailUtil;
import com.mycompany.utils.SecurityUtil;

/**
 * Servlet implementation class PatientAuthController
 */
@WebServlet({"/login", "/logout", "/signup", "/signupVerification", "/forget_password", 
	"/sendPasswordResetEmail","/resetPasswordVerification", "/changePassword"})
public class PatientAuthController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PatientDAO patientDAO = new PatientDAO();
	private PatientTokenDAO patientTokenDAO = new PatientTokenDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientAuthController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath())
		//.append("\n send post request with email and password to login");
		String action = request.getServletPath();
	    switch(action) {
	        case "/login":
	            request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
	            break;
	        case "/logout":
	        	System.out.println("Log Out is successful");
	    		// remove patient from session
	    	    request.getSession().invalidate();
	    	    // send patient home
	    	    response.sendRedirect("home.jsp");
	            break;
	        case "/signup":
	            request.getRequestDispatcher("/WEB-INF/views/auth/signup.jsp").forward(request, response);
	            break;
	        case "/signupVerification":
	        	System.out.println("GET request received for signupVerification");
	        	System.out.println("sending token for post request");
	            String signupToken = request.getParameter("token");
	            System.out.println("Token: " + signupToken);
	            doPost(request, response);
	            break;
	        case "/forget_password":
	            request.getRequestDispatcher("/WEB-INF/views/auth/forget_password.jsp").forward(request, response);
	            break;
	        case "/resetPasswordVerification":
	        	System.out.println("GET request received for resetPasswordVerification");
	        	System.out.println("sending forget password token for post request");
	            String resetPasswordToken = request.getParameter("token");
	            System.out.println("Token: " + resetPasswordToken);
	            doPost(request, response);
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
		//doGet(request, response);
		String action = request.getServletPath();
        switch(action) {
            case "/login":
            	login(request, response);
                break;
            case "/signup":
            	signup(request, response);
                break;
            case "/signupVerification":
            	signupVerification(request, response);
                break;
            case "/sendPasswordResetEmail":
            	sendPasswordResetEmail(request, response);
            	break;
            case "/resetPasswordVerification":
            	resetPasswordVerification(request, response);
            	break;	
            case "/changePassword":
            	changePassword(request, response);
            	break;	
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
	}
	
	private void signup(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		String fname = request.getParameter("fname");
	    String lname = request.getParameter("lname");
	    String cin = request.getParameter("cin");
	    String email = request.getParameter("email");
	    String password = request.getParameter("password");
	    String phone = request.getParameter("phone");
	    String gender = request.getParameter("gender");
	    String birthdateStr = request.getParameter("birthdate");
	    Date birthdate = null;

	    // Check for null or empty values and perform input validation
	    if (fname == null || lname == null || email == null || password == null || birthdateStr == null ||
	        fname.isEmpty() || lname.isEmpty() || email.isEmpty() || password.isEmpty() || birthdateStr.isEmpty()) {
	        response.getWriter().append("Invalid input parameters");
	        return;
	    }
	    
	    // Validate email format
	    if (!SecurityUtil.isValidEmail(email)) {
	        response.getWriter().append("Invalid email format.");
	        return;
	    }
	    // Validate password complexity (e.g., minimum length, special characters, etc.)
	    if (!SecurityUtil.isValidPassword(password)) {
	        response.getWriter().append("Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.");
	        return;
	    }
	    
	    // parsing string date to date data type 
	    try {
	        birthdate = new SimpleDateFormat("dd-MM-yyyy").parse(birthdateStr);
	    } catch (ParseException e) {
	        response.getWriter().append("Invalid birthdate format");
	        return;
	    }
	    
	    // hashing the password before storing in db
	    String hashedpassword = null;
		try {
			hashedpassword = SecurityUtil.hashPassword(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

	    // Create a Patient object and set its properties
	    Patient patient = new Patient();
	    patient.setFName(fname);
	    patient.setLName(lname);
	    patient.setEmail(email);
	    patient.setPassword(hashedpassword);
	    patient.setCin(cin);
	    patient.setPhone(phone);
	    patient.setGender(gender);
	    patient.setBirthdate(birthdate);
	    
	    // creating the patient in db
	    patientDAO.createPatient(patient);
	    
	    // Create a verification code 
	    PatientToken patientToken = new PatientToken();
	    patientToken.setPatient(patient);
	    patientToken.setToken(SecurityUtil.generateToken());
	    patientToken.setTokenType(TokenType.email_verification);
	    patientToken.setExpiresAt(SecurityUtil.calculateTokenExpirationDateTime());
	    
	    System.out.println(patientToken);
	    patientTokenDAO.createPatientToken(patientToken);
	    
	    // create verification link :
	    // not necessary but good for compatibility, so this is why i use URLEncode for special characters
	    //String encodedToken = URLEncoder.encode(patientToken.getToken(), StandardCharsets.UTF_8);
	    String verificationLink = "http://localhost:8080" + request.getContextPath() + "/signupVerification?token=" + patientToken.getToken(); //encodedToken;
	    System.out.println(verificationLink);
	    
	    // Send email for verification
//	    try {
//	        EmailUtil.sendEmail(patient.getEmail(), "Email Verification", verificationLink);
//	        System.out.println("email is sent");
//	    } catch (MessagingException e) {
//	        System.out.println("Failed to send verification email.");
//	        e.printStackTrace();
//	    }
	    System.out.println("Email is sent (disabled it for now since it works):");

//	    // Create patient and handle response
//	    Boolean isPatientCreated = patientDAO.createPatient(patient);
//	    
//	    if (isPatientCreated) {
//	        System.out.println("Account is created successfully");
//	        response.getWriter().append("Account is created successfully");
//	        //request.getSession().setAttribute("user", user);
//	    } else {
//
//	        System.out.println("Account creation failed");
//	        response.getWriter().append("Account creation failed");
//	        //request.setAttribute("error", "Invalid username or password");
//	        // Redirect to a different URL using GET method 
//	        // (since using dispatcher does refresh page so i end it up resending previous data)
//	        //response.sendRedirect(request.getContextPath() + "/login");
//	    }
	}
	private void signupVerification(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		// get token token from the request parameter
		
	    String token = request.getParameter("token");
	    System.out.println("received token in post:" + token);
	    
	    if (token == null || token.isEmpty()) {
	        response.getWriter().append("token is null or empty");
	        return;
	    }

	    // get patientToken from db using the token
	    PatientToken patientToken = patientTokenDAO.getPatientTokenByToken(token);

	    if (patientToken == null) {
	        response.getWriter().append("PatientToken not found in db");
	        return;
	    }

	    // checking if the token is expired
	    LocalDateTime tokenFromDBExperationDateTime = patientToken.getExpiresAt();
	    Boolean isTokenExpired = SecurityUtil.isTokenExpired(tokenFromDBExperationDateTime);
	    if (isTokenExpired) {
	        response.getWriter().append("Verification token has expired");
	        return;
	    }

	    // get the associated patient from the patient token
	    Patient patient = patientToken.getPatient();
	    // make patient verified 
	    //patient.setVerified(true); 
	    //patientDAO.updatePatient(patient);

	    // remove patient token from db
	    if(patientTokenDAO.deletePatientToken(patientToken)) {
	    	System.out.println("token deleted");
	    }else { 
	    	System.out.println("token could not get deleted");
	    }
		
	    // create a session for the verified patient
	    request.getSession().setAttribute("patient", patient);

	    // redirect to the homepage
	    response.sendRedirect(request.getContextPath() + "/home.jsp");
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		String email = request.getParameter("email");
	    String password = request.getParameter("password");
	    System.out.println("email: "+ email + "password:" + password);

	    // Check for null or empty values and perform input validation
	    if (email == null || password == null || email.isEmpty() || password.isEmpty() ) {
	        response.getWriter().append("Invalid input parameters");
	        return;
	    }
	    
	    // Validate email format
	    if (!SecurityUtil.isValidEmail(email)) {
	        response.getWriter().append("Invalid email format.");
	        return;
	    }
	    // Validate password complexity (e.g., minimum length, special characters, etc.)
	    if (!SecurityUtil.isValidPassword(password)) {
	        response.getWriter().append("Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.");
	        return;
	    }
	    
        System.out.println("email: " + email);
        System.out.println("password: " + password);

        Patient patient = patientDAO.getPatientByEmail(email);
        // Checking if the patient object is null
        if (patient == null) {
            request.setAttribute("errorMessage", "Patient with email " + email + " not found.");
            // Forward the request to a JSP page to display the error message
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }
	    
        // verifying if inputed password is the same as the stored hashed password
        Boolean isPasswordCorrect = false;
        String StoredHashedPassword = patient.getPassword();
        try {
        	isPasswordCorrect = SecurityUtil.verifyPassword(password, StoredHashedPassword);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        
	    if (isPasswordCorrect) {
	        System.out.println("Login successful");
	        
	        // create a session for the verified patient
		    request.getSession().setAttribute("patient", patient);

		    // redirect to the homepage
		    response.sendRedirect("home.jsp");
	    } else {
	        System.out.println("Login failed");
	        response.getWriter().append("Login failed");
	        //request.setAttribute("error", "Invalid username or password");
	        // Redirect to a different URL using GET method 
	        // (since using dispatcher does refresh page so i end it up resending previous data)
	        //response.sendRedirect(request.getContextPath() + "/login");
	    }
	}
	

	private void sendPasswordResetEmail(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		String email = request.getParameter("email");

	    // Check for null or empty values and perform input validation
	    if (email == null || email.isEmpty() ) {
	        response.getWriter().append("Invalid input parameters");
	        return;
	    }
	    
	    // Validate email format
	    if (!SecurityUtil.isValidEmail(email)) {
	        response.getWriter().append("Invalid email format.");
	        return;
	    }
	    
        System.out.println("email: " + email);
        
        Patient patient = patientDAO.getPatientByEmail(email);
        // Checking if the patient object is null
        if (patient == null) {
            request.setAttribute("errorMessage", "Patient with email " + email + " not found.");
            // Forward the request to a JSP page to display the error message
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }
        
     // Create a verification code 
	    PatientToken patientToken = new PatientToken();
	    patientToken.setPatient(patient);
	    patientToken.setToken(SecurityUtil.generateToken());
	    patientToken.setTokenType(TokenType.reset_password);
	    patientToken.setExpiresAt(SecurityUtil.calculateTokenExpirationDateTime());
	    
	    System.out.println(patientToken);
	    patientTokenDAO.createPatientToken(patientToken);
	    
	    // create verification link :
	    // not necessary but good for compatibility, so this is why i use URLEncode for special characters
	    //String encodedToken = URLEncoder.encode(patientToken.getToken(), StandardCharsets.UTF_8);
	    String verificationLink = "http://localhost:8080" + request.getContextPath() + "/resetPasswordVerification?token=" + patientToken.getToken(); //encodedToken;
	    System.out.println(verificationLink);
	    
	    // Send email for verification
//	    try {
//	        EmailUtil.sendEmail(patient.getEmail(), "Email Verification", verificationLink);
//	        System.out.println("email is sent");
//	    } catch (MessagingException e) {
//	        System.out.println("Failed to send verification email.");
//	        e.printStackTrace();
//	    }
	    System.out.println("Email is sent (disabled it for now since it works):");
        
	}
	
	public void resetPasswordVerification(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		// get token token from the request parameter
		
	    String token = request.getParameter("token");
	    System.out.println("received token in post:" + token);
	    
	    if (token == null || token.isEmpty()) {
	        response.getWriter().append("token is null or empty");
	        return;
	    }

	    // get patientToken from db using the token
	    PatientToken patientToken = patientTokenDAO.getPatientTokenByToken(token);

	    if (patientToken == null) {
	        response.getWriter().append("PatientToken not found in db");
	        return;
	    }

	    // checking if the token is expired
	    LocalDateTime tokenFromDBExperationDateTime = patientToken.getExpiresAt();
	    Boolean isTokenExpired = SecurityUtil.isTokenExpired(tokenFromDBExperationDateTime);
	    if (isTokenExpired) {
	        response.getWriter().append("Reset password token has expired");
	        return;
	    }
	    
	    Patient patient = patientToken.getPatient();
	    request.getSession().setAttribute("patient", patient);
	    
	    // remove patient token from db
	    if(patientTokenDAO.deletePatientToken(patientToken)) {
	    	System.out.println("token deleted");
	    }else { 
	    	System.out.println("token could not get deleted");
	    }
        request.getRequestDispatcher("/WEB-INF/views/auth/change_password.jsp").forward(request, response);
	    
	}
	
	public void changePassword(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		
        Patient patient = (Patient) request.getSession().getAttribute("patient");

        // Checking if the patient object is null
        if (patient == null) {
        	request.setAttribute("errorMessage", "Error cant get Patient from session");
            // Forward the request to a JSP page to display the error message
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return; 
        } 

        String old_password = request.getParameter("old_password");
	    String new_password = request.getParameter("new_password");

	    // Check for null or empty values and perform input validation
	    if (old_password == null || old_password == null || new_password.isEmpty() || new_password.isEmpty() ) {
	        response.getWriter().append("Invalid input parameters");
	        return;
	    }
	    // Validate password complexity (e.g., minimum length, special characters, etc.)
	    if (!SecurityUtil.isValidPassword(old_password)) {
	        response.getWriter().append("Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.");
	        return;
	    }
	    // Validate password complexity (e.g., minimum length, special characters, etc.)
	    if (!SecurityUtil.isValidPassword(new_password)) {
	        response.getWriter().append("Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.");
	        return;
	    }
	    
        System.out.println("old_password: " + old_password);
        System.out.println("new_password: " + new_password);
	    
        // verifying if inputed password is the same as the stored hashed password
        Boolean isOldPasswordCorrect = false;
        String StoredHashedPassword = patient.getPassword();
        try {
        	isOldPasswordCorrect = SecurityUtil.verifyPassword(old_password, StoredHashedPassword);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        
	    if (!isOldPasswordCorrect) {
        	request.setAttribute("errorMessage", "Password not correct");
            // Forward the request to a JSP page to display the error message
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return; 
	    } 
	    
	    String HashedNewPassword = null;
	    try {
			HashedNewPassword = SecurityUtil.hashPassword(new_password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	    if (HashedNewPassword == null) {
	    	System.out.print("hashed new password is not hashed");
	    	return;
	    }
	    patientDAO.updatePatientPassword(patient, HashedNewPassword);
	    
	}
}
