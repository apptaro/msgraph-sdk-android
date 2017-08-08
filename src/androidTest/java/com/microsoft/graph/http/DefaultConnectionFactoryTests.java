package com.microsoft.graph.http;

import android.test.AndroidTestCase;

import java.io.IOException;

/**
 * Test cases for {@see DefaultConnectionFactory}
 */
public class DefaultConnectionFactoryTests extends AndroidTestCase {

    private IConnection mConnection;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mConnection = new DefaultConnectionFactory().createFromRequest(new MockHttpRequest());
    }

    public void testNotNull() {
        assertNotNull(mConnection);
    }

    public void testDefaultRequestMethod() {
        assertEquals("GET", mConnection.getRequestMethod());
    }

}
