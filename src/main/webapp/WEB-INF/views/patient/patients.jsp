<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.models.Patient"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>List of Patients</title>
</head>
<body>
    <h2>List of Patients</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>CIN</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Gender</th>
                <th>Birthdate</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<Patient> patients = (List<Patient>) request.getAttribute("patients");
                for (Patient patient : patients) {
            %>
            <tr>
                <td><%= patient.getId() %></td>
                <td><%= patient.getFName() %></td>
                <td><%= patient.getLName() %></td>
                <td><%= patient.getCin() %></td>
                <td><%= patient.getEmail() %></td>
                <td><%= patient.getPhone() %></td>
                <td><%= patient.getGender() %></td>
                <td><%= patient.getBirthdate() %></td>
            </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>
