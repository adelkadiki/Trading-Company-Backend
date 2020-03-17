package com.system.trade.model;

import lombok.Data;

import javax.persistence.*;
import com.system.trade.model.Product;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "contact")
    private String contact;

    @Column(name = "email")
    private String email;

    @Column(name = "website")
    private String website;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

//    @ElementCollection
//    @CollectionTable(name = "products_list")
//    @Column(name = "product")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="vendor_id", referencedColumnName = "id")
    Set<Product> products = new HashSet<>();


}
