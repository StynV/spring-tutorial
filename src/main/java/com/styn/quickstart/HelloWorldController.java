package com.styn.quickstart;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.styn.quickstart.services.ColourPrinter;
import com.styn.quickstart.services.impl.ColourPrinterImpl;

@RestController
public class HelloWorldController {
    
    @GetMapping(path = "/hello")
    public String helloWorld() {
        final ColourPrinter colourPrinter = new ColourPrinterImpl();
        return colourPrinter.print();
    }
}
