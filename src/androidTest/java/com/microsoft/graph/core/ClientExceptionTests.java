package com.microsoft.graph.core;

import android.test.AndroidTestCase;

/**
 * Test cases for {@see ClientException}
 */
public class ClientExceptionTests extends AndroidTestCase {

    private GraphErrorCodes graphErrorCodes;
    private ClientException clientException;
    private String expectMessage = "This is test exception message";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        graphErrorCodes = GraphErrorCodes.AccessDenied;
        clientException = new ClientException(expectMessage, null, graphErrorCodes);
    }

    public void testNotNull() {
        assertNotNull(clientException);
    }

    public void testClientException() {
        assertTrue(clientException.isError(graphErrorCodes));
        assertEquals(expectMessage, clientException.getMessage());
    }
}

