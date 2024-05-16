package com.mycompany.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.regex.Pattern;

import com.mycompany.models.PatientToken.TokenType;

public class SecurityUtil {
	
	private SecurityUtil() {
		
	}
	
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
    	// Base64 is for Compatibility and data integrity while Byte is for security instead of using char 
    	
        // generating a 16 character salt for the pwd
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16]; 
        random.nextBytes(salt);

        // instanciating the SHA-256 algorithm
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        // adding the salt 
        digest.update(salt);
        // adding the password as Bytes and completing the digest computation
        byte[] hashedPassword = digest.digest(password.getBytes());

        // mix salt and hashed password into a single string of encoded Base64 for compatibility 
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String encodedHashedPassword = Base64.getEncoder().encodeToString(hashedPassword);
        return encodedSalt + ":" + encodedHashedPassword;
    }
    
    // verifying input password against the stored hashed password using the stored hashed salt
    public static boolean verifyPassword(String password, String hashedPassword) throws NoSuchAlgorithmException {
        // Split the hashed password into salt and password
        String[] parts = hashedPassword.split(":");
        byte[] salt = Base64.getDecoder().decode(parts[0]); 
        byte[] storedHashedPassword = Base64.getDecoder().decode(parts[1]); 

        // hashing the inputed password with the stored salt
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(salt); 
        byte[] inputHashedPassword = digest.digest(password.getBytes());

        // Comparison for the hashed passwords
        return MessageDigest.isEqual(storedHashedPassword, inputHashedPassword);
    }
    
    // Validate email format
    public static boolean isValidEmail(String email) {
 	    // Regular expression for email validation
 	    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
 	    Pattern pattern = Pattern.compile(emailRegex);
 	    return pattern.matcher(email).matches();
 	}

 	// Validate password complexity
 	public static boolean isValidPassword(String password) {
 	    // Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character
 	    String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
 	    return password.matches(passwordRegex);
 	}
 	
 	public static String generateToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] tokenBytes = new byte[10];
        secureRandom.nextBytes(tokenBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    }
 	
 	
 	public static LocalDateTime calculateTokenExpirationDateTime() {
 		// Get the current time
        LocalDateTime currentDateTime = LocalDateTime.now();
        
        // Add 30 minutes to the current time
        LocalDateTime expiryTime = currentDateTime.plus(30, ChronoUnit.MINUTES);
        
        return expiryTime;
    }
    
    public static boolean isTokenExpired(LocalDateTime expirationDateTime) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return currentDateTime.isAfter(expirationDateTime);
    }
}
