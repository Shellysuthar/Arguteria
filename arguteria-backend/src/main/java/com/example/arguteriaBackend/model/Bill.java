package com.example.arguteriaBackend.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serial;
import java.io.Serializable;

//@NamedQuery(name="Bill.getAllBills" , query= "select b from Bill b order by b.id desc")
//@NamedQuery(name="Bill.getBillByUsername", query =" select b from Bill b where b.createdBy=:username order by b.id desc")
@Entity
@Data
@DynamicInsert
@DynamicUpdate
@Table


public class Bill implements Serializable {
    @Serial
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;

    private String uuid;
    private String firstName;
    private String lastName;
    private String email;
    private Number totalAmount;

    @Column( columnDefinition = "json")
    private String productDetail;

    private boolean completed = false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Number getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Number totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }


    //  private Date createdAt;
}
