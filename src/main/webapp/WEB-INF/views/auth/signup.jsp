<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Signup</title>
</head>
<body>
    <h1>Signup</h1>
    <form action="signup" method="post">
        <label for="fname">First Name:</label>
        <input type="text" id="fname" name="fname" required><br><br>

        <label for="lname">Last Name:</label>
        <input type="text" id="lname" name="lname" required><br><br>

        <label for="cin">CIN:</label>
        <input type="text" id="cin" name="cin"><br><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>

        <label for="phone">Phone:</label>
        <input type="text" id="phone" name="phone"><br><br>

        <label for="gender">Gender:</label>
        <input type="text" id="gender" name="gender"><br><br>

        <label for="birthdate">Birthdate (DD-MM-YYYY):</label>
        <input type="text" id="birthdate" name="birthdate" required><br><br>

        <input type="submit" value="Signup">
    </form>
</body>
</html>
