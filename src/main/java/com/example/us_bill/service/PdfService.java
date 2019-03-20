package com.example.us_bill.service;

import com.example.us_bill.dto.BillCreationDto;
import com.example.us_bill.model.BillPosition;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class PdfService {


    public byte[] generatePdfReport(BillCreationDto billCreationDto) {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PdfWriter pdfWriter = new PdfWriter(outputStream);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument, PageSize.A4);
        List<Paragraph> paragraphs = new ArrayList<>();

        paragraphs.add(new Paragraph("Nazwa dostawcy: "
                + billCreationDto.getBillDto().getContractorName()
                + "                                                  Data wystawienia: "
                + billCreationDto.getBillDto().getDate()
                + "\n\n"));
        paragraphs.add(new Paragraph("Numer faktury: "
                + billCreationDto.getBillDto().getNumber()
                + "\n"));
        paragraphs.add(new Paragraph("Koszt dostawy w $: "
                + billCreationDto.getBillDto().getShipingCost()
                + "\n"));
        paragraphs.add(new Paragraph("Wysokosc cla:  "
                + billCreationDto.getBillDto().getAmountOfDuty()
                + "\n"));
        paragraphs.add(new Paragraph("Wartosc dolara:  "
                + billCreationDto.getBillDto().getDollarCost()
                + "\n\n"));
        paragraphs.add(new Paragraph("Pozycje faktury:   "
                + "\n"));

        for(BillPosition bp: billCreationDto.getBillPositions()){

            paragraphs.add(new Paragraph("Nazwa: "
                    + bp.getName() + ", numer kat: " + bp.getCatalogNumber() +
                    ", ilosc: " + bp.getAmountOfStock()
                    + " cena w zl: " + bp.getPriceInZl()+
                    ", wartosc razem: " + bp.getPositionTotalCostInZl()));
        }
        paragraphs.add(new Paragraph("Suma w zl:  "
                + billCreationDto.getBillDto().getTotalCostInZl()
                ));



        for (Paragraph p : paragraphs) {
            document.add(p);
        }

        IntStream.range(1, pdfDocument.getNumberOfPages() + 1)
                .mapToObj(pdfDocument::getPage)
                .forEach(page -> page.setRotation(0));
        document.close();

        return outputStream.toByteArray();
    }
}
