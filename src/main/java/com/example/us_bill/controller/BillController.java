package com.example.us_bill.controller;

import com.example.us_bill.dto.BillCreationDto;
import com.example.us_bill.model.BillPosition;
import com.example.us_bill.service.BillPositionService;
import com.example.us_bill.service.BillService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BillController {

    private final BillService billService;
    private final BillPositionService billPositionService;

    public BillController(BillService billService, BillPositionService billPositionService) {
        this.billService = billService;
        this.billPositionService = billPositionService;
    }

    @GetMapping("allBills")
    public String showAll(Model model) {
        model.addAttribute("bills", billService.findAll());
        return "allBills";
    }

    @GetMapping("{id}/bill")
    public String showOne(@PathVariable Long id, Model model) {
        model.addAttribute("bills", billService.findById(id));
        model.addAttribute("billPositions", billPositionService.findById(id));
        return "bill";
    }


    @GetMapping("/create")
    public String showCreateForm(Model model) {
        BillCreationDto billForm = new BillCreationDto();

        List<BillPosition> billPositions = new ArrayList<>();
        BillPosition billPosition = new BillPosition();

        for (int i = 0; i < 14; i++) {
            billPositions.add(billPosition);
        }

        billForm.setBillPositions(billPositions);

        model.addAttribute("positionForm", billForm);
        model.addAttribute("billForm", billForm);

        return "create";
    }


    @PostMapping("create")
    public String saveBooks(@ModelAttribute BillCreationDto form) {
        billService.saveBill(form.getBill(), form.getBillPositions());
        return "redirect:/allBills";
    }



}