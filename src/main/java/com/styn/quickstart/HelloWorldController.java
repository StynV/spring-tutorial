package com.styn.quickstart;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.styn.quickstart.services.ColourPrinter;

@RestController
public class HelloWorldController {
    
    private ColourPrinter colourPrinter;

    public HelloWorldController(ColourPrinter colourPrinter) {
        this.colourPrinter = colourPrinter;
    }

    @GetMapping(path = "/hello")
    public String helloWorld() {
        return colourPrinter.print();
    }
}
