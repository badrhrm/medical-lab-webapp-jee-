package com.mycompany.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

 
@Entity
@Table(name = "patients_tokens_jee")
public class PatientToken {

    public enum TokenType {
    	email_verification,
    	reset_password
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_patient")
    private Patient patient;
    
    @Column(name = "token", nullable = false, unique = true, length=255)
    private String token;

    @Enumerated(EnumType.STRING)
    @Column(name = "token_type", nullable = false)
    private TokenType tokenType;

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public TokenType getTokenType() {
		return tokenType;
	}

	public void setTokenType(TokenType tokenType) {
		this.tokenType = tokenType;
	}

	public int getId() {
		return id;
	}

	public LocalDateTime getExpiresAt() {
		return expiresAt;
	}
	
	public void setExpiresAt(LocalDateTime expiresAt) {
		this.expiresAt = expiresAt;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("ID: ").append(id).append("\n");
	    if (patient != null) {
	        sb.append("Patient's ID: ").append(patient.getId()).append("\n");
	    }
	    sb.append("Token: ").append(token).append("\n");
	    sb.append("TokenType: ").append(tokenType).append("\n");
	    sb.append("ExpiresAt: ").append(expiresAt).append("\n");
	    return sb.toString();
	}
    
    
}
