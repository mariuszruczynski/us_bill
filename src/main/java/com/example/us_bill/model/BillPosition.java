package com.example.us_bill.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class BillPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String catalogNumber;
    private Integer amountOfStock;
    private BigDecimal priceInDolars;
    private BigDecimal priceInZl;
    private Long idBill;
    private BigDecimal positionTotalCostInZl;

}
