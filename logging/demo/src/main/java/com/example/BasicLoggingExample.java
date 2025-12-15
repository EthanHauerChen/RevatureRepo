package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BasicLoggingExample {
    //get logger instance (best practice: use class as the logger name, which is placed inside the getLogger() parameter)
    private static final Logger logger = LoggerFactory.getLogger(BasicLoggingExample.class);

    public void demonstrateLogging() {
        // Different Log Levels
        logger.trace("Trace Message - Most Detailed");
        logger.debug("Debug Message - for debugging");
        logger.info("Info Message - general information");
        logger.warn("Warning Message - Potential Problem");
        logger.error("Error message - Something went wrong");

        // parameterized logging (avoids string concatenation if not logged)
        String user = "John";
        int userId = 123;
        logger.info("User {} with ID logged in", user, userId);

        // exception logging
        try {
            throw new RuntimeException("Something went wrong");
        }
        catch (Exception e) {
            logger.error("An error occured while processing", e);
        }
    }

    public static void main(String[] args) {
        BasicLoggingExample example = new BasicLoggingExample();
        example.demonstrateLogging();
    }
}