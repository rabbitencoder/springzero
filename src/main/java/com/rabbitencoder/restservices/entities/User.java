package com.rabbitencoder.restservices.entities;

import jakarta.persistence.*;

/**
 * @author rahul
 * @date 5/16/2024 12:57 PM
 * -
 */
//Entity

@Entity
@Table(name ="userdetails")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userdetails_seq")
    @SequenceGenerator(name = "userdetails_seq", sequenceName = "USERDETAILS_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "user_name", length = 15, nullable = false, unique = true)
    private String username;
    @Column(name = "first_name", length = 15, nullable = false)
    private String firstname;
    @Column(name = "last_name", length = 15, nullable = false)
    private String lastname;
    @Column(name = "email_address", length = 15, nullable = false)
    private String email;
    @Column(name = "role", length = 15, nullable = false)
    private String role;
    @Column(name = "ssn", length = 15, nullable = false, unique = true)
    private String ssn;

    public User() {
    }

    public User(Long id, String username, String firstname, String lastname, String email, String role, String ssn) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.role = role;
        this.ssn = ssn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", ssn='" + ssn + '\'' +
                '}';
    }
}

    