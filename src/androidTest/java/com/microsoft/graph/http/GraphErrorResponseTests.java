package com.microsoft.graph.http;

import android.test.AndroidTestCase;

import com.google.gson.JsonObject;

/**
 * Test cases for {@see GraphErrorResponse}
 */
public class GraphErrorResponseTests extends AndroidTestCase {

    public void testSetRawObject() {
        JsonObject expectedJson = new JsonObject();
        GraphErrorResponse errorResponse = new GraphErrorResponse();
        errorResponse.setRawObject(null,expectedJson);
        assertEquals(expectedJson, errorResponse.rawObject);
    }
}
