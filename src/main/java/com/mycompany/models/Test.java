package com.mycompany.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity(name="Test")
@Table(name="tests_jee")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;
    @Column(name = "label", length = 50)
    private String label;
    @Column(name = "price")
    private double price;
    @Column(name = "result_after")
    private int result_after;
    
    
    
// Constructors, getters, and setters

    public Test() {
    }

    public Test(String label, double price, int result_after) {
        this.label = label;
        this.price = price;
        this.result_after = result_after;
        
     
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

    public int getResult_after() {
        return result_after;
    }

    public void setResult_after(int result_after) {
        this.result_after = result_after;
    }

    @Override
    public String toString() {
        return "Test{" + "id=" + id + ", label=" + label + ", price=" + price + ", result_after=" + result_after + '}';
    }
}