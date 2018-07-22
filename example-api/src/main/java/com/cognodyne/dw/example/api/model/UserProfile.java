package com.cognodyne.dw.example.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user_profile", uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
public class UserProfile extends Persistent {
    private static final long serialVersionUID = 1228297771930823386L;
    private String            firstName;
    private String            middleName;
    private String            lastName;
    private String            email;

    @Column(name="fname")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Column(name="mname")
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    @Column(name="lname")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name="email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
