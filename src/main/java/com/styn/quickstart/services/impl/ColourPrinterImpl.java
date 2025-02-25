package com.styn.quickstart.services.impl;

import org.springframework.stereotype.Component;

import com.styn.quickstart.services.BluePrinter;
import com.styn.quickstart.services.ColourPrinter;
import com.styn.quickstart.services.GreenPrinter;
import com.styn.quickstart.services.RedPrinter;

@Component
public class ColourPrinterImpl implements ColourPrinter {
    private RedPrinter redPrinter;
    private BluePrinter bluePrinter;
    private GreenPrinter greenPrinter;

    public ColourPrinterImpl(RedPrinter redPrinter, BluePrinter bluePrinter, GreenPrinter greenPrinter) {
        this.redPrinter = redPrinter;
        this.bluePrinter = bluePrinter;
        this.greenPrinter = greenPrinter;
    }

    @Override
    public String print() {
        return String.join(", ", redPrinter.print(), bluePrinter.print(), greenPrinter.print());
    }
}