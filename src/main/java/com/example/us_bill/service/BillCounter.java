package com.example.us_bill.service;

import com.example.us_bill.model.Bill;
import com.example.us_bill.model.BillPosition;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

@Component
public class BillCounter {

    public void countBill(List<BillPosition> billPositions, Bill bill) {

        BigDecimal totalCostInZl = BigDecimal.ZERO;
        BigDecimal positionTotalCostInZl;
        BigDecimal shipingCostOneProduct;
        BigDecimal totalCostOneProduct;
        BigDecimal shipingCost = bill.getShipingCost();
        BigDecimal dolarCost = BigDecimal.valueOf(bill.getDollarCost());
        BigDecimal shipingCostInZl = (shipingCost.multiply(dolarCost));
        BigDecimal billPositionSize = BigDecimal.valueOf(billPositions.size());
        shipingCostInZl = shipingCostInZl.divide(billPositionSize, 2, RoundingMode.FLOOR);

        for (int n = 0; billPositions.size() > n; n++) {
            shipingCostOneProduct = shipingCostInZl.divide(BigDecimal.valueOf(billPositions.get(n).getAmountOfStock()), 2, RoundingMode.FLOOR);
            totalCostOneProduct = billPositions.get(n).getPriceInDolars().multiply(dolarCost);
            totalCostOneProduct = totalCostOneProduct.add(shipingCostOneProduct);

            totalCostOneProduct = totalCostOneProduct.add(totalCostOneProduct.multiply(bill.getAmountOfDuty().divide(BigDecimal.valueOf(100))));
            totalCostOneProduct = totalCostOneProduct.setScale(2, RoundingMode.FLOOR);
            positionTotalCostInZl = BigDecimal.valueOf(billPositions.get(n).getAmountOfStock()).multiply(totalCostOneProduct);
            totalCostInZl = totalCostInZl.add(positionTotalCostInZl);
            billPositions.get(n).setPositionTotalCostInZl(positionTotalCostInZl);
            billPositions.get(n).setPriceInZl(totalCostOneProduct);
        }
        bill.setTotalCostInZl(totalCostInZl);
    }
}
