package com.example.us_bill.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BillPositionDto {

    private String name;
    private String catalogNumber;
    private Integer amountOfStock;
    private BigDecimal priceInDolars;
    private BigDecimal priceInZl;

}
