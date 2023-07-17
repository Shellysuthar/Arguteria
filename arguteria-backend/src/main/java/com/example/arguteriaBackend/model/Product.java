package com.example.arguteriaBackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table
public class Product implements Serializable {
    public static final long serialVersionUid=123456L;
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Integer price;
    private String status;
    public Product(Integer id,String name){
        this.id=id;
        this.name=name;
    }
    public Product(Integer id,String name,String description,Integer price){
        this.id=id;
        this.name=name;
        this.description=description;
        this.price=price;
    }
}
