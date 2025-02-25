package com.styn.quickstart.services.impl;

import com.styn.quickstart.services.GreenPrinter;

public class EnglishGreenPrinter implements GreenPrinter {
    @Override
    public String print() {
        return "green";
    }
}
