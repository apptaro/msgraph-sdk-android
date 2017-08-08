package com.microsoft.graph.http;

import android.test.AndroidTestCase;

import com.microsoft.graph.core.GraphErrorCodes;

/**
 * Test cases for {@see GraphError}
 */
public class GraphErrorTests extends AndroidTestCase {

    public void testIsError(){
        String expectedMessage = "test error message";
        GraphError error = new GraphError();
        error.code = GraphErrorCodes.AccessDenied.toString();
        error.message = expectedMessage;
        assertTrue(error.isError(GraphErrorCodes.AccessDenied));
        assertEquals(expectedMessage, error.message);
    }

    public void testIsNotError() {
        GraphError error = new GraphError();
        error.code = GraphErrorCodes.AccessDenied.toString();
        assertFalse(error.isError(GraphErrorCodes.Unauthenticated));
    }
}
