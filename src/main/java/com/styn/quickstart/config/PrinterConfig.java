package com.styn.quickstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.styn.quickstart.services.BluePrinter;
import com.styn.quickstart.services.ColourPrinter;
import com.styn.quickstart.services.GreenPrinter;
import com.styn.quickstart.services.RedPrinter;
import com.styn.quickstart.services.impl.ColourPrinterImpl;
import com.styn.quickstart.services.impl.EnglishBluePrinter;
import com.styn.quickstart.services.impl.EnglishGreenPrinter;
import com.styn.quickstart.services.impl.EnglishRedPrinter;

@Configuration
public class PrinterConfig {
    
    @Bean
    public BluePrinter bluePrinter() {
        return new EnglishBluePrinter();
    }

    @Bean
    public RedPrinter redPrinter() {
        return new EnglishRedPrinter();
    }

    @Bean
    public GreenPrinter greenPrinter() {
        return new EnglishGreenPrinter();
    }

    @Bean
    public ColourPrinter colourPrinter(BluePrinter bluePrinter, RedPrinter redPrinter, GreenPrinter greenPrinter) {
        return new ColourPrinterImpl(redPrinter, bluePrinter, greenPrinter);
    }
}
