package com.example.us_bill.Repository;

import com.example.us_bill.model.BillPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BillPositionRepository extends JpaRepository<BillPosition, Long> {

    @Query (value = "SELECT * FROM bill_position WHERE id_bill =:idBill", nativeQuery = true)
    List<BillPosition> findByBillId(@Param("idBill") Long id);


    @Modifying
    @Query  (value = "DELETE FROM BillPosition where idBill=:idBill")
    void deleteByBillId(@Param("idBill") Long id);
}
