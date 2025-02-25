package com.styn.quickstart.services.impl;

import org.springframework.stereotype.Service;

import com.styn.quickstart.services.RedPrinter;

@Service
public class DutchRedPrinter implements RedPrinter {
    @Override
    public String print() {
        return "rood";
    }
}
