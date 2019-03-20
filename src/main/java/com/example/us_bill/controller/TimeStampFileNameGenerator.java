package com.example.us_bill.controller;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TimeStampFileNameGenerator {

    private final static String PFD_ARGUMENT = ".pdf";


    public String generateTimeStampFileName() {
        String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + PFD_ARGUMENT;
        return fileName;

    }
}
