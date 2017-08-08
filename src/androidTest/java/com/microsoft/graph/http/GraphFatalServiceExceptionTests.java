package com.microsoft.graph.http;

import android.test.AndroidTestCase;

import java.util.ArrayList;

/**
 * Test cases for {@see GraphFatalServiceException}
 */
public class GraphFatalServiceExceptionTests extends AndroidTestCase {

    public void testGraphFatalServiceException() {
        GraphFatalServiceException exception = new GraphFatalServiceException(null,null,new ArrayList<String>(),null,401,"Unauthenticated",new ArrayList<String>(),null);
        String expectedMessage = "[This is an unexpected error from Graph, please report this at https://github.com/microsoftgraph/msgraph-sdk-android/issues]\n" +
                "null null\n" +
                "\n" +
                "\n" +
                "401 : Unauthenticated\n" +
                "[...]\n" +
                "\n" +
                "[Some information was truncated for brevity, enable debug logging for more details]";
        String message = exception.getMessage();
        assertEquals(expectedMessage, message);
    }
}
