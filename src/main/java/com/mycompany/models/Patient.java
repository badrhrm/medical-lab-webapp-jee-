package com.mycompany.models;

<<<<<<< HEAD
=======
import jakarta.persistence.*;

>>>>>>> e2d34e74cec251b54b770e32835065f6871d0fa5
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

<<<<<<< HEAD
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "patients")
=======
@Entity
@Table(name = "patients_jee")
>>>>>>> e2d34e74cec251b54b770e32835065f6871d0fa5
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

<<<<<<< HEAD
    @Column(name = "fName", nullable = false, length=25)
    private String fName;

    @Column(name = "lName", nullable = false, length=25)
=======
    @Column(name = "fName", nullable = false,length=25)
    private String fName;

    @Column(name = "lName", nullable = false,length=25)
>>>>>>> e2d34e74cec251b54b770e32835065f6871d0fa5
    private String lName;

    @Column(name = "cin", nullable = false, unique = true, length=12)
    private String cin;

    @Column(name = "email", nullable = false, unique = true, length=50)
    private String email;
<<<<<<< HEAD
=======
    
    @Column(name = "password", nullable = false, unique = true, length=255)
    private String password;
>>>>>>> e2d34e74cec251b54b770e32835065f6871d0fa5

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
<<<<<<< HEAD

    // Getters and setters
    @Override
    public String toString() {
        return "Patient{" + "id=" + id + ", fName=" + fName + ", lName=" + lName + ", cin=" + cin + ", email=" + email + ", phone=" + phone + ", gender=" + gender + ", birthdate=" + birthdate + '}';
    }
=======
    
    // hashset is the right datastructure for this due to unique elements and not needing order and for fast access
    @OneToMany(mappedBy = "patient")
    private Set<PatientToken> tokens = new HashSet<>();

    // Getters and setters
>>>>>>> e2d34e74cec251b54b770e32835065f6871d0fa5

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

<<<<<<< HEAD
    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
=======
    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
>>>>>>> e2d34e74cec251b54b770e32835065f6871d0fa5
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
<<<<<<< HEAD

    public String getPhone() {
=======
    
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
>>>>>>> e2d34e74cec251b54b770e32835065f6871d0fa5
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
<<<<<<< HEAD
}
=======

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
>>>>>>> e2d34e74cec251b54b770e32835065f6871d0fa5
