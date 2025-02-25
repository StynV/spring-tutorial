package com.styn.quickstart.services.impl;

import com.styn.quickstart.services.BluePrinter;
import com.styn.quickstart.services.ColourPrinter;
import com.styn.quickstart.services.GreenPrinter;
import com.styn.quickstart.services.RedPrinter;

public class ColourPrinterImpl implements ColourPrinter {
    private RedPrinter redPrinter;
    private BluePrinter bluePrinter;
    private GreenPrinter greenPrinter;

    public ColourPrinterImpl() {
        this.redPrinter = new EnglishRedPrinter();
        this.bluePrinter = new EnglishBluePrinter();
        this.greenPrinter = new EnglishGreenPrinter();
    }

    @Override
    public String print() {
        return String.join(", ", redPrinter.print(), bluePrinter.print(), greenPrinter.print());
    }
}