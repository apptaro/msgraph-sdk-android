package com.microsoft.graph.http;

import android.test.AndroidTestCase;

import com.google.gson.JsonObject;
import com.microsoft.graph.serializer.ISerializer;
import com.microsoft.graph.serializer.MockSerializer;

/**
 * Test cases for {@see ReferenceRequestBody}
 */
public class ReferenceRequestBodyTests extends AndroidTestCase {

    public void testRawObject() {
        ReferenceRequestBody body = new ReferenceRequestBody(null);
        ISerializer serializer = new MockSerializer(null,null);
        JsonObject jsonObject = new JsonObject();
        body.setRawObject(serializer,jsonObject);
        assertEquals(serializer,body.getSerializer());
        assertEquals(jsonObject,body.getRawObject());
    }
}
