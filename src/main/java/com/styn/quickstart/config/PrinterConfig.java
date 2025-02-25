package com.styn.quickstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.styn.quickstart.services.BluePrinter;
import com.styn.quickstart.services.ColourPrinter;
import com.styn.quickstart.services.GreenPrinter;
import com.styn.quickstart.services.RedPrinter;
import com.styn.quickstart.services.impl.ColourPrinterImpl;
// import com.styn.quickstart.services.impl.EnglishBluePrinter;
// import com.styn.quickstart.services.impl.EnglishGreenPrinter;
// import com.styn.quickstart.services.impl.EnglishRedPrinter;
import com.styn.quickstart.services.impl.DutchBluePrinter;
import com.styn.quickstart.services.impl.DutchGreenPrinter;
import com.styn.quickstart.services.impl.DutchRedPrinter;

@Configuration
public class PrinterConfig {
    
    @Bean
    public BluePrinter bluePrinter() {
        // return new EnglishBluePrinter();
        return new DutchBluePrinter();
    }

    @Bean
    public RedPrinter redPrinter() {
        // return new EnglishRedPrinter();
        return new DutchRedPrinter();
    }

    @Bean
    public GreenPrinter greenPrinter() {
        // return new EnglishGreenPrinter();
        return new DutchGreenPrinter();
    }

    @Bean
    public ColourPrinter colourPrinter(BluePrinter bluePrinter, RedPrinter redPrinter, GreenPrinter greenPrinter) {
        return new ColourPrinterImpl(redPrinter, bluePrinter, greenPrinter);
    }
}
