package com.example.us_bill.Repository;

import com.example.us_bill.model.BillPosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillPositionRepository extends JpaRepository<BillPosition, Long> {
}
