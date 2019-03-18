package com.example.us_bill.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String contractorName;
    private String number;
    private String date;
    private BigDecimal shipingCost;
    private BigDecimal amountOfDuty;
    private Double dollarCost;
    private BigDecimal totalCostInZl;
}
