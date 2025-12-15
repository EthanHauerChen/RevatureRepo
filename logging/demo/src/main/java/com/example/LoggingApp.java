package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingApp {
    //create logger instance
    private static final Logger logger = LoggerFactory.getLogger(LoggingApp.class);
    
    public static void main(String[] args) {
        logger.info("===Application Started===");
        logger.info("Logging to the file with rolling");

        //simulate some work
        processOrder("001");
        processOrder("002");
    }

    private static void processOrder(String orderId) {
        logger.info("Processing order: {}", orderId);

        //simulate some work
        try {
            Thread.sleep(100);
            throw new InterruptedException();
            //logger.debug("Order {} processed successfully", orderId);
        } catch (InterruptedException e) {
            logger.error("Failed to process order {}", orderId, e);
            Thread.currentThread().interrupt();
        }
    }
}
