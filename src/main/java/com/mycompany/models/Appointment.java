package com.mycompany.models;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author yusef
 */
@Entity
@Table(name = "appointments_jee")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Test test;

    @Column(name = "day", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate day;

    @Column(name = "hour", nullable = false)
    @Temporal(TemporalType.TIME)
    private LocalTime hour;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "created_at", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp updatedAt;
    
    /**
     * Getters & Setter
     * 
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public void setTest(Test test) {
        this.test = test;
    }

    public Test getTest() {
        return test;
    }
    
    
    /**
     * toString
     * Display appointments details
     * @return 
     */
    @Override
    public String toString() {
        return "Appointment{" + "id=" + id + ", patient=" + patient + ", test=" + test + ", day=" + day + ", hour=" + hour + ", state=" + state + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }

}