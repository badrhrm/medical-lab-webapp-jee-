package com.mycompany.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycompany.dao.TestDao;
import com.mycompany.models.Test;

/**
 * Servlet implementation class TestController
 */

@WebServlet({"/tests","/tests/*"})
public class TestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final TestDao testDao;

    public TestController() {
        this.testDao = new TestDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println("action:"+ action);

        try {
            switch (action) {
            	
                case "/tests/new":
                    showNewForm(request, response);
                    break;
                case "/tests/insert":
                    insertTest(request, response);
                    break;
                case "/tests/delete":
                    deleteTest(request, response);
                    break;
                case "/tests/edit":
                    showEditForm(request, response);
                    break;
                case "/tests/update":
                    updateTest(request, response);
                    break;
                default:
                    listTests(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void listTests(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	System.out.println("in doGET /tests");
        List<Test> tests = testDao.getAllTests();
        request.setAttribute("tests", tests);
        request.getRequestDispatcher("/WEB-INF/views/tests/tests.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/tests/testform.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Test test = testDao.getTestByID(id);
        request.setAttribute("test", test);
        request.getRequestDispatcher("/WEB-INF/views/tests/testform.jsp").forward(request, response);
    }

    private void insertTest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Retrieve form data
        String label = request.getParameter("label");
        double price = Double.parseDouble(request.getParameter("price"));
        int resultAfter = Integer.parseInt(request.getParameter("result_after"));

        // Create Test object
        Test test = new Test(label, price, resultAfter);

        // Save Test object to the database
        testDao.createTest(test);

        // Redirect back to the test list page
        response.sendRedirect(request.getContextPath() + "/WEB-INF/views/tests/tests");
    }

    private void updateTest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String label = request.getParameter("label");
        double price = Double.parseDouble(request.getParameter("price"));
        int resultAfter = Integer.parseInt(request.getParameter("result_after"));

        Test test = new Test(label, price, resultAfter);
        testDao.updateTest(test);
        response.sendRedirect(request.getContextPath() + "/WEB-INF/views/tests/tests");
    }

    private void deleteTest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Test test = testDao.getTestByID(id);
        testDao.deleteTest(test);
        response.sendRedirect(request.getContextPath() + "/WEB-INF/views/tests/tests");
    }
}