package com.styn.quickstart.services.impl;

import org.springframework.stereotype.Component;

import com.styn.quickstart.services.RedPrinter;

@Component
public class DutchRedPrinter implements RedPrinter {
    @Override
    public String print() {
        return "rood";
    }
}
