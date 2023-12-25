package com.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Cart {
    @Id
//    @Column(name="id", nullable=false)
//    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Column(length = 3)
    private String currencyCode;
    @Column(length = 20)
    private String currencyName;

    private Integer roundLevel;

    private Integer status;

}


