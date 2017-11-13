package com.romchik.spring.mypractice.storeProduct.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "productNumber")
    private int namber;

    @Column(name = "name")
    private String name;

    @Column(name = "size")
    private String size;

    @Column(name = "weight")
    private int weight;

    @Column(name = "productLine")
    private String line;

    @Column(name = "productClass")
    private String productClass;
}
