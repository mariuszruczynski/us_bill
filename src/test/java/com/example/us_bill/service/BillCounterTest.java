package com.example.us_bill.service;

import com.example.us_bill.counter.BillCounter;
import com.example.us_bill.model.Bill;
import com.example.us_bill.model.BillPosition;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;


class BillCounterTest {

     private BillCounter billCounter = new BillCounter();
             //mock(BillCounter.class);



    @Test
    void shouldCountPositionsValuesInBill() {

        //given
        List<BillPosition> givenBillPositionList = new ArrayList<>();
        BillPosition givenFirstBillPosition = new BillPosition();
        givenFirstBillPosition.setPriceInDolars(BigDecimal.valueOf(25));
        givenFirstBillPosition.setAmountOfStock(5);

        BillPosition givenSecondBillPosition = new BillPosition();
        givenSecondBillPosition.setPriceInDolars(BigDecimal.valueOf(10));
        givenSecondBillPosition.setAmountOfStock(2);

        BillPosition givenThirdBillPosition = new BillPosition();
        givenThirdBillPosition.setPriceInDolars(BigDecimal.valueOf(5));
        givenThirdBillPosition.setAmountOfStock(3);

        givenBillPositionList.add(givenFirstBillPosition);
        givenBillPositionList.add(givenSecondBillPosition);
        givenBillPositionList.add(givenThirdBillPosition);

        Bill givenBill = new Bill();
        givenBill.setAmountOfDuty(BigDecimal.valueOf(3.5));
        givenBill.setDollarCost(3.8989);
        givenBill.setShipingCost(BigDecimal.valueOf(65.65));

        BigDecimal firstExpectedPriceInZl = BigDecimal.valueOf(118.54);
        BigDecimal secondExpectedPriceInZl = BigDecimal.valueOf(84.50).setScale(2, RoundingMode.FLOOR);
        BigDecimal thirdExpectedPriceInZl = BigDecimal.valueOf(49.61);

        //when
        billCounter.countBill(givenBillPositionList, givenBill);

        //then
        assertEquals(firstExpectedPriceInZl, givenBillPositionList.get(0).getPriceInZl() );
        assertEquals(secondExpectedPriceInZl, givenBillPositionList.get(1).getPriceInZl() );
        assertEquals(thirdExpectedPriceInZl, givenBillPositionList.get(2).getPriceInZl() );
    }

    @Test
    void shouldCountTotalPositionValueInZl(){

        //given
        List<BillPosition> givenBillPositionList = new ArrayList<>();
        BillPosition givenFirstBillPosition = new BillPosition();
        givenFirstBillPosition.setPriceInDolars(BigDecimal.valueOf(25));
        givenFirstBillPosition.setAmountOfStock(5);

        BillPosition givenSecondBillPosition = new BillPosition();
        givenSecondBillPosition.setPriceInDolars(BigDecimal.valueOf(10));
        givenSecondBillPosition.setAmountOfStock(2);

        BillPosition givenThirdBillPosition = new BillPosition();
        givenThirdBillPosition.setPriceInDolars(BigDecimal.valueOf(5));
        givenThirdBillPosition.setAmountOfStock(3);

        givenBillPositionList.add(givenFirstBillPosition);
        givenBillPositionList.add(givenSecondBillPosition);
        givenBillPositionList.add(givenThirdBillPosition);

        Bill givenBill = new Bill();
        givenBill.setAmountOfDuty(BigDecimal.valueOf(3.5));
        givenBill.setDollarCost(3.8989);
        givenBill.setShipingCost(BigDecimal.valueOf(65.65));

        BigDecimal expectedTotalSumInFirstPosition = BigDecimal.valueOf(592.70).setScale(2, RoundingMode.FLOOR);
        BigDecimal expectedTotalSumInSecondPosition = BigDecimal.valueOf(169.00).setScale(2, RoundingMode.FLOOR);
        BigDecimal expectedTotalSumInThirdPosition = BigDecimal.valueOf(148.83);

        //when
        billCounter.countBill(givenBillPositionList, givenBill);

        //then
        assertEquals(expectedTotalSumInFirstPosition, givenBillPositionList.get(0).getPositionTotalCostInZl() );
        assertEquals(expectedTotalSumInSecondPosition, givenBillPositionList.get(1).getPositionTotalCostInZl() );
        assertEquals(expectedTotalSumInThirdPosition, givenBillPositionList.get(2).getPositionTotalCostInZl() );
    }

    @Test
    void shouldCountTotalValueInZl(){

        //given
        List<BillPosition> givenBillPositionList = new ArrayList<>();
        BillPosition givenFirstBillPosition = new BillPosition();
        givenFirstBillPosition.setPriceInDolars(BigDecimal.valueOf(25));
        givenFirstBillPosition.setAmountOfStock(5);

        BillPosition givenSecondBillPosition = new BillPosition();
        givenSecondBillPosition.setPriceInDolars(BigDecimal.valueOf(10));
        givenSecondBillPosition.setAmountOfStock(2);

        BillPosition givenThirdBillPosition = new BillPosition();
        givenThirdBillPosition.setPriceInDolars(BigDecimal.valueOf(5));
        givenThirdBillPosition.setAmountOfStock(3);

        givenBillPositionList.add(givenFirstBillPosition);
        givenBillPositionList.add(givenSecondBillPosition);
        givenBillPositionList.add(givenThirdBillPosition);

        Bill givenBill = new Bill();
        givenBill.setAmountOfDuty(BigDecimal.valueOf(3.5));
        givenBill.setDollarCost(3.8989);
        givenBill.setShipingCost(BigDecimal.valueOf(65.65));

        BigDecimal expectedTotalSumInZl = BigDecimal.valueOf(910.53);

        //when
        billCounter.countBill(givenBillPositionList, givenBill);

        //then
        assertEquals(expectedTotalSumInZl, givenBill.getTotalCostInZl() );
    }
}