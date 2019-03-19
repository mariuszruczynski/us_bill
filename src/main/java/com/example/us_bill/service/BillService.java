package com.example.us_bill.service;

import com.example.us_bill.Repository.BillRepository;
import com.example.us_bill.dto.BillDto;
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

    public void saveBill(BillDto billDto, List<BillPosition> billPositions) {
        Bill newBill = new Bill();
        newBill.setId(billDto.getId());
        newBill.setContractorName(billDto.getContractorName());
        newBill.setNumber(billDto.getNumber());
        newBill.setDate(billDto.getDate());
        newBill.setShipingCost(billDto.getShipingCost());
        newBill.setAmountOfDuty(billDto.getAmountOfDuty());
        newBill.setDollarCost(billDto.getDollarCost());

        billRepository.save(newBill);
        billPositionService.countAndSaveBill(billPositions, newBill);
    }




    public void saveEditBill(BillDto billDto, List<BillPosition> billPositions) {
        Bill editBill = new Bill();
        editBill.setId(billDto.getId());
        editBill.setContractorName(billDto.getContractorName());
        editBill.setNumber(billDto.getNumber());
        editBill.setDate(billDto.getDate());
        editBill.setShipingCost(billDto.getShipingCost());
        editBill.setAmountOfDuty(billDto.getAmountOfDuty());
        editBill.setDollarCost(billDto.getDollarCost());

       billPositionService.countAndSaveBill(billPositions, editBill);
        billRepository.save(editBill);
    }

    public void deleteById(Long id) {
        billRepository.deleteById(id);
    }

}
