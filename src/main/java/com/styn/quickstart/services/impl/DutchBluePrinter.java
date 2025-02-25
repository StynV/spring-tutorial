package com.styn.quickstart.services.impl;

import org.springframework.stereotype.Component;

import com.styn.quickstart.services.BluePrinter;

@Component
public class DutchBluePrinter implements BluePrinter {
    @Override
    public String print() {
        return "blauw";
    }
}
