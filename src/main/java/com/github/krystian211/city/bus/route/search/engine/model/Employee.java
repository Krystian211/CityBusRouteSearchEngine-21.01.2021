package com.github.krystian211.city.bus.route.search.engine.model;

import javax.persistence.*;

@Entity(name = "employee")
@Table(name = "tEmployee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean administrator;

    public Employee(int id, String firstName, String lastName, String email, boolean administrator) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.administrator = administrator;
    }

    public Employee() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdministrator() {
        return this.administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

}
