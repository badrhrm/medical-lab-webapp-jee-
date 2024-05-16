package com.mycompany.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;



@Entity(name = "Test") 
@Table(name = "tests_jee")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "label", length = 50)
    private String label;

    @Column(name = "price")
    private double price;
    
    @Column(name = "duration")
    private int duration;

    @Column(name = "days_to_get_result")
    private int daysToGetResult;

    @OneToMany(mappedBy = "test")
    private Set<Appointment> appointments = new HashSet<>();

    @Column(name = "description", length = 255)
    private String description;


    // Constructors, getters, and setters

    public Test() {
    }

    public Test(String label, double price,int duration, int daysToGetResult, String description) {
        this.label = label;
        this.price = price;
        this.duration= duration;
        this.daysToGetResult = daysToGetResult;
        this.description = description;
     
    }

    public int getId() {
        return id;
    }

    
    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDaysToGetResult() {
        return daysToGetResult;
    }

    public void setDaysToGetResult(int daysToGetResult) {
        this.daysToGetResult = daysToGetResult;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
