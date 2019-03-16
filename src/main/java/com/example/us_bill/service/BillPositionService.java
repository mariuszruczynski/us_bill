package com.example.us_bill.service;

import com.example.us_bill.Repository.BillPositionRepository;
import com.example.us_bill.model.Bill;
import com.example.us_bill.model.BillPosition;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Service
public class BillPositionService {

    private final BillPositionRepository billPositionRepository;

    public BillPositionService(BillPositionRepository billPositionRepository) {
        this.billPositionRepository = billPositionRepository;
    }

    public List<BillPosition> findById(Long id) {
        return billPositionRepository.findByBillId(id);
    }

    public void countAndSaveBill(List<BillPosition> billPositions, Bill bill) {

        List<BillPosition> billPositions1 = removeEmptyListElements(billPositions);
        setBillId(billPositions1, bill.getId());
        countBill(billPositions1, bill);
        billPositionRepository.saveAll(billPositions1);
    }

    private void setBillId(List<BillPosition> billPositions, Long id) {
        for (BillPosition bp : billPositions) {
            bp.setIdBill(id);
        }
    }

    private List<BillPosition> removeEmptyListElements(List<BillPosition> billPositions) {
        List<BillPosition> billPositions1 = new ArrayList<>();
        for (BillPosition bp : billPositions) {
            if (!isNull(bp.getPriceInDolars()))
                billPositions1.add(bp);
        }
        return billPositions1;
    }

    private void countBill(List<BillPosition> billPositions, Bill bill) {

        BigDecimal shipingCostOneProduct;
        BigDecimal totalCost;
        BigDecimal shipingCost = bill.getShipingCost();
        Double dolarCost = bill.getDollarCost();
        BigDecimal shipingCostInZl = shipingCost.multiply(BigDecimal.valueOf(dolarCost).divide(BigDecimal.valueOf(billPositions.size())));

        for (int n = 0; billPositions.size() > n; n++) {
            System.out.println("-----------------------------------------------------------------------------------");
            shipingCostOneProduct = shipingCostInZl.divide(BigDecimal.valueOf(billPositions.get(n).getAmountOfStock()));
            totalCost = billPositions.get(n).getPriceInDolars().multiply((BigDecimal.valueOf(dolarCost)))
                    .add(shipingCostOneProduct);
            totalCost = totalCost.add(totalCost.multiply(bill.getAmountOfDuty().divide(BigDecimal.valueOf(100))));
            billPositions.get(n).setPriceInZl(totalCost);
        }
    }
}

