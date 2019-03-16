package com.example.us_bill.service;

import com.example.us_bill.Repository.BillRepository;
import com.example.us_bill.model.Bill;
import com.example.us_bill.model.BillPosition;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BillService {

    private final BillPositionService billPositionService;
    private final BillRepository billRepository;

    public BillService(BillPositionService billPositionService, BillRepository billRepository) {
        this.billPositionService = billPositionService;
        this.billRepository = billRepository;
    }

    public List<Bill> findAll() {
        return billRepository.findAll();
    }

    public Bill findById(Long id) {
    return billRepository.getOne(id);
    }

    public void saveBill(Bill bill, List<BillPosition> billPositions) {
        billRepository.save(bill);
        billPositionService.countAndSaveBill(billPositions, bill);

    }
}
