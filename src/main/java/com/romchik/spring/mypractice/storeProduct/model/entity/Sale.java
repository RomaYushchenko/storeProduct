package com.romchik.spring.mypractice.storeProduct.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="sale")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale implements Serializable {

    private static final long serialVersionUID = -3465813074586302847L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "price")
    private double price;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "sales")
    private Date sales;
}
