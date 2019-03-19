package com.example.us_bill.service;

import com.example.us_bill.Repository.BillPositionRepository;
import com.example.us_bill.counter.BillCounter;
import com.example.us_bill.model.Bill;
import com.example.us_bill.model.BillPosition;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.isNull;

@Service
public class BillPositionService {

    private final BillPositionRepository billPositionRepository;
    private final BillCounter billCounter;

    public BillPositionService(BillPositionRepository billPositionRepository, BillCounter billCounter) {
        this.billPositionRepository = billPositionRepository;
        this.billCounter = billCounter;
    }

    public List<BillPosition> findByBillId(Long id) {
        return billPositionRepository.findByBillId(id);
    }

    public void countAndSaveBill(List<BillPosition> positions, Bill bill) {
        List<BillPosition> billPositions = removeEmptyListElements(positions);
        setBillId(billPositions, bill.getId());
        billCounter.countBill(billPositions, bill);
        billPositionRepository.saveAll(billPositions);
    }

    private void setBillId(List<BillPosition> billPositions, Long id) {
        for (BillPosition bp : billPositions) {
            bp.setIdBill(id);
        }
    }

    private List<BillPosition> removeEmptyListElements(List<BillPosition> billPositions) {
        List<BillPosition> billPositionsAfterRemove = new ArrayList<>();
        for (BillPosition bp : billPositions) {
            if (!isNull(bp.getPriceInDolars())
            && !isNull(bp.getAmountOfStock()))
                billPositionsAfterRemove.add(bp);
        }
        Collections.reverse(billPositionsAfterRemove);
        return billPositionsAfterRemove;
    }

    @Transactional
    public void deleteByBillId(Long id) {
        billPositionRepository.deleteByBillId(id);
    }
}

