package com.styn.quickstart.services.impl;

import org.springframework.stereotype.Component;

import com.styn.quickstart.services.GreenPrinter;

@Component
public class DutchGreenPrinter implements GreenPrinter {
    @Override
    public String print() {
        return "groen";
    }
}
