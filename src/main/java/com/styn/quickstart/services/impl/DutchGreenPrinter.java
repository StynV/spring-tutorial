package com.styn.quickstart.services.impl;

import com.styn.quickstart.services.GreenPrinter;

public class DutchGreenPrinter implements GreenPrinter {
    @Override
    public String print() {
        return "groen";
    }
}
