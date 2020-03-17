package com.system.trade.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "client")
    private String client;

    @Column(name = "paid")
    private boolean paid;

    @Column(name = "notices")
    private String notices;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "total")
    private float total;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="sales_id", referencedColumnName = "id")
    Set<Item> items = new HashSet<>();
}
