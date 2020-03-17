package com.system.trade.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Entity
@Table
public class Item {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "product")
    private String product;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "expiration")
    private LocalDate expiration ;

    @Column(name = "price")
    private float price;
}
