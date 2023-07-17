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
    //  private Date createdAt;
}
