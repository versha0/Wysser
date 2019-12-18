package com.stackroute.bookmydriver.domain;

import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "user") //creating table named "user"
public class User {
    //auto generate id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    //user input >> unique, compulsory email
    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "please provide valid e-mail")
    @NotEmpty(message = "please provide an e-mail")
    private String email;

    //user input >> password
    @Column(name = "password")
    @Transient
    private String password;
//    Note: the password field will be missing from the JSON output. Thats because @org.springframework.data.annotation.
//    Transient specifically states to the spring framework that the Object Mapper you are using should not
//    include this value when converting from Java Object to JSON.
//    Also note if you attempted to persist the above entity into the database,
//    it would still save it to the database because @org.springframework.data.annotation.
//    Transient only applies to Object mapping frameworks not JPA.

    @Column(name = "first_name")
    @NotEmpty(message = "please provide your first name")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "please provide your last name")
    private String lastName;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "confirmation_token")
    private String confirmationToken;

    @Column(name = "role")
    private String role;

    //getter and setters


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }
}
