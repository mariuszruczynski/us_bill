package com.example.us_bill.dto;

import com.example.us_bill.model.BillPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BillPositionListDto {

    List<BillPosition> billPositions;
}
