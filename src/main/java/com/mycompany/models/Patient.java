package com.mycompany.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patients_jee")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "fName", nullable = false,length=25)
    private String fName;

    @Column(name = "lName", nullable = false,length=25)
    private String lName;

    @Column(name = "cin", nullable = false, unique = true, length=12)
    private String cin;

    @Column(name = "email", nullable = false, unique = true, length=50)
    private String email;
    
    @Column(name = "password", nullable = false, unique = true, length=255)
    private String password;

    @Column(name = "phone", length = 10)
    private String phone;

    @Column(name = "gender", length = 6)
    private String gender;

    //This annotation ensures that only the date portion of the Date object is stored in the database, without considering the time component.
    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;

//    @OneToMany(mappedBy = "patient")
//    private Set<Appointment> appointments = new HashSet<>();
//
//    public Set<Appointment> getAppointments() {
//        return appointments;
//    }
//
//    public void setAppointments(Set<Appointment> appointments) {
//        this.appointments = appointments;
//    }
    
    // hashset is the right datastructure for this due to unique elements and not needing order and for fast access
    @OneToMany(mappedBy = "patient")
    private Set<PatientToken> tokens = new HashSet<>();

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }
    
    public String getFullName() {
    	return fName + lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

	public Set<PatientToken> getTokens() {
		return tokens;
	}

	public void setTokens(Set<PatientToken> tokens) {
		this.tokens = tokens;
	}
    
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("ID: ").append(id).append("\n");
	    sb.append("First Name: ").append(fName).append("\n");
	    sb.append("Last Name: ").append(lName).append("\n");
	    sb.append("CIN: ").append(cin).append("\n");
	    sb.append("Email: ").append(email).append("\n");
	    sb.append("Phone: ").append(phone).append("\n");
	    sb.append("Gender: ").append(gender).append("\n");
	    sb.append("Birthdate: ").append(birthdate).append("\n");
	    return sb.toString();
	}

    
}