package com.example.arguteriaBackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

//@NamedQuery(name="User.findByEmailId",query="select u from User u where u.email=:email")
//@NamedQuery(name="User.getAllUser",query="select new com.example.arguteriaBackend.model.User(u.id,u.firstName,u.lastName,u.email,u.userName,u.status) from User u where u.role='user'")
//@NamedQuery(name="User.getByUsername",query="select new com.example.arguteriaBackend.model.User(u.id,u.firstName,u.lastName,u.email,u.userName,u.status) from User u where u.email=:username")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table(name="_user")
public class User implements Serializable {
    private static  final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String userName;

    private String email;
    private String password;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private String status;
    private String role;

    public Integer getId() {
        return id;
    }

}
