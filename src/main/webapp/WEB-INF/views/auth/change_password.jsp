<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change Password</title>
</head>
<body>
    <h2>Change Password</h2>
    <form action="changePassword" method="post">
        <label for="old_password">Old Password:</label>
        <input type="password" id="old_password" name="old_password" required><br><br>
        
        <label for="new_password">New Password:</label>
        <input type="password" id="new_password" name="new_password" required><br><br>
        
        <input type="submit" value="Change Password">
    </form>
</body>
</html>
