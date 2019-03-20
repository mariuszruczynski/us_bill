package com.example.us_bill.controller;

import com.example.us_bill.dto.BillCreationDto;
import com.example.us_bill.dto.BillDto;
import com.example.us_bill.model.Bill;
import com.example.us_bill.model.BillPosition;
import com.example.us_bill.service.BillPositionService;
import com.example.us_bill.service.BillService;
import com.example.us_bill.service.PdfService;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BillController {

    private final BillService billService;
    private final BillPositionService billPositionService;
    private final TimeStampFileNameGenerator timeStampFileNameGenerator;
    private final PdfService pdfService;



    public BillController(BillService billService, BillPositionService billPositionService, TimeStampFileNameGenerator timeStampFileNameGenerator, PdfService pdfService) {
        this.billService = billService;
        this.billPositionService = billPositionService;
        this.timeStampFileNameGenerator = timeStampFileNameGenerator;
        this.pdfService = pdfService;
    }

    @GetMapping("allBills")
    public String showAll(Model model) {
        model.addAttribute("bills", billService.findAll());
        return "allBills";
    }

    @GetMapping("{id}/bill")
    public String showOne(@PathVariable Long id, Model model) {
        model.addAttribute("bills", billService.findById(id));
        model.addAttribute("billPositions", billPositionService.findByBillId(id));
        return "bill";
    }


    @GetMapping("/create")
    public String showCreateForm(Model model) {
        BillCreationDto billForm = new BillCreationDto();
        List<BillPosition> billPositions = new ArrayList<>();
        BillPosition billPosition = new BillPosition();
        BillDto billDto = new BillDto();
        for (int i = 0; i < 14; i++) {
            billPositions.add(billPosition);
        }

        billForm.setBillPositions(billPositions);
        billForm.setBillDto(billDto);

        model.addAttribute("positionForm", billForm);
        model.addAttribute("billForm", billForm);

        return "create";
    }


    @PostMapping("create")
    public String saveBill(@ModelAttribute BillCreationDto form) {

        billService.saveBill(form.getBillDto(), form.getBillPositions());
        return "redirect:/allBills";
    }

    @GetMapping("/{id}/editBill")
    public String showEditForm(@PathVariable Long id, Model model) {
        Bill bill = billService.findById(id);
        BillDto billDto = new BillDto(bill.getId(),
                bill.getContractorName(),
                bill.getNumber(),
                bill.getDate(),
                bill.getShipingCost(),
                bill.getAmountOfDuty(),
                bill.getDollarCost(),
                bill.getTotalCostInZl());

        List<BillPosition> billPositions = billPositionService.findByBillId(id);
        BillCreationDto billCreationDto = new BillCreationDto(billPositions, billDto);


        model.addAttribute("positionForm", billCreationDto);
        model.addAttribute("billForm", billCreationDto);

        return "editBill";
    }

    @PostMapping("editBill")
    public String saveEditBill(@ModelAttribute BillCreationDto form) {
        if (form.getBillPositions().isEmpty()) {
            return "redirect:/emptyList";
        }
        billService.saveEditBill(form.getBillDto(), form.getBillPositions());
        return "redirect:/allBills";
    }

    @GetMapping(path = "/{id}/deleteBill")
    public String deleteDoctor(@PathVariable Long id) {
        billService.deleteById(id);
        billPositionService.deleteByBillId(id);
        return "redirect:/allBills";
    }

    @GetMapping(value = {"/{id}/toPdf"}, produces = "application/pdf")
    public @ResponseBody
    void getByDateAndExportToPdf(@PathVariable Long id, Model model, HttpServletResponse response) throws IOException, DocumentException {

        BillDto billDto = billService.findDtoById(id);
        List<BillPosition> billPosition = billPositionService.findByBillId(id);
        BillCreationDto billCreationDto = new BillCreationDto(billPosition, billDto);


        byte[] pdfReport = pdfService.generatePdfReport(billCreationDto);
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=" + timeStampFileNameGenerator.generateTimeStampFileName());
        response.setHeader("Content-Length", String.valueOf(pdfReport.length));
        FileCopyUtils.copy(pdfReport, response.getOutputStream());

    }

}