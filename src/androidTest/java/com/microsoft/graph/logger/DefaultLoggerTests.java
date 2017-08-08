package com.microsoft.graph.logger;

import android.test.AndroidTestCase;
import android.util.Log;

/**
 * Test cases for {@see DefaultLogger}
 */
public class DefaultLoggerTests extends AndroidTestCase {

    public void testLoggerLevel(){
        ILogger logger = new DefaultLogger();
        assertEquals(LoggerLevel.Error, logger.getLoggingLevel());
        logger.setLoggingLevel(LoggerLevel.Debug);
        assertEquals(LoggerLevel.Debug, logger.getLoggingLevel());
    }
}
