package com.mycompany.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycompany.dao.AdminDAO;
import com.mycompany.models.Admin;
import com.mycompany.utils.SecurityUtil;

/**
 * Servlet implementation class AdminAuthController
 */
@WebServlet({ "/adminlogin", "/adminlogout" }) 
public class AdminAuthController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AdminDAO adminDAO = new AdminDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAuthController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();
	    switch(action) {
	        case "/adminlogin":
	        	request.getRequestDispatcher("/WEB-INF/views/auth/adminlogin.jsp").forward(request, response);
	            break;
	        case "/adminlogout":
	        	System.out.println("Log Out is successful");
	    		// remove admin from session
	    	    request.getSession().invalidate();
	    	    // send admin home
	    	    response.sendRedirect("home.jsp");
	            break;
	        default:
	            response.sendError(HttpServletResponse.SC_NOT_FOUND);
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String action = request.getServletPath();
	    switch(action) {
	        case "/adminlogin":
	        	adminLogin(request, response);
	            break;
	        default:
	            response.sendError(HttpServletResponse.SC_NOT_FOUND);
	    }
	}

	
	private void adminLogin(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		String email = request.getParameter("email");
	    String password = request.getParameter("password");
	    
	    // Input validation
	    if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
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
		
		// retrieving admin from db by email
        Admin admin = adminDAO.getAdminByEmail(email);
	     
        // Checking if the admin object is null
        if (admin == null) {
            request.setAttribute("errorMessage", "admin with email " + email + " not found.");
            // Forward the request to a JSP page to display the error message
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }
        
        
	    if ( password.equals(admin.getPassword()) ) {
	        System.out.println("Login successful");
	        
	        // create a session for the verified admin
		    request.getSession().setAttribute("admin", admin);

		    // redirect to the homepage
		    response.sendRedirect(request.getContextPath() + "/dashboard");
	    } else {
	        System.out.println("Login failed");
	        response.getWriter().append("Login failed");
	        //request.setAttribute("error", "Invalid username or password");
	        // Redirect to a different URL using GET method 
	        // (since using dispatcher does refresh page so i end it up resending previous data)
	        //response.sendRedirect(request.getContextPath() + "/login");
	    }
	}
 

}
