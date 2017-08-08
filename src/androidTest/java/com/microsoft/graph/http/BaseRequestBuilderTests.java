package com.microsoft.graph.http;

import android.test.AndroidTestCase;

/**
 * Test cases for {@see BaseRequestBuilder}
 */
public class BaseRequestBuilderTests extends AndroidTestCase {

    private String expectedRequestUrl = "https://a.b.c";
    private BaseRequestBuilder baseRequestBuilder;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        baseRequestBuilder = new BaseRequestBuilder(expectedRequestUrl,null,null){};
    }

    public void testNotNull() {
        assertNotNull(baseRequestBuilder);
    }

    public void testRequestUrlAndOptions() {
        assertEquals(expectedRequestUrl, baseRequestBuilder.getRequestUrl());
        assertEquals(0, baseRequestBuilder.getOptions().size());
        assertEquals(expectedRequestUrl+"/d", baseRequestBuilder.getRequestUrlWithAdditionalSegment("d"));
        assertEquals(expectedRequestUrl + "('version=1.0')", baseRequestBuilder.getRequestUrlWithAdditionalParameter("version=1.0"));
    }
}
