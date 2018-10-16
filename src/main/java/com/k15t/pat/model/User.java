package com.k15t.pat.model;

import org.hibernate.validator.constraints.Email;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 985870637804997280L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private Date dateCreated;

    @Size(min = 2, max = 50, message = "Please enter the name between 2 and 50 characters!")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Please enter the name only with letters!")
    @NotNull
    @Column
    private String name;

    @Size(min = 2, max = 255, message = "Please enter the password between 2 and 50 characters!")
    @Column
    private String password;


    @Size(min = 2, max = 50, message = "Please enter the address between 2 and 50 characters!")
    @Column
    private String address;

    @Size(min = 2, max = 50, message = "Please enter the name between 2 and 50 characters!")
    @Email(message = "Please enter email address in real format!")
    @Column(unique = true)
    private String email;

    @Size(max = 30, message = "Please do not exceed 30 characters!")
    @Column
    @Pattern(regexp = "^$|(([+][(]?[0-9]{1,3}[)]?)|([(]?[0-9]{4}[)]?))\\s*[)]?[-\\s\\.]?[(]?[0-9]{1,3}[)]?([-\\s\\.]?[0-9]{3})([-\\s\\.]?[0-9]{3,4})",message = "Please enter valid phone number")
    private String phone;

    public User() {
    }

    public User(Date dateCreated, String name, String password, String address, String email, String phone) {
        this.dateCreated = dateCreated;
        this.name = name;
        this.password = password;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @PrePersist
    public void prePersist() {
        dateCreated = DateTime.now().toDate();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", dateCreated=" + dateCreated +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phone + '\'' +
                '}';
    }
}
