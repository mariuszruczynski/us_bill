package com.example.us_bill.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BillDto {

    private Long id;
    private String contractorName;
    private String number;
    private String date;
    private BigDecimal shipingCost;
    private BigDecimal amountOfDuty;
    private Double dollarCost;
    private BigDecimal totalCostInZl;

}
