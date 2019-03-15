package com.example.us_bill.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bill {

    @Id
    private Long id;
    private String contractorName;
    private String number;
    private LocalDate date;

    //@OneToMany(mappedBy = "bill_position")
    //List<BillPosition> billPositions;

}
