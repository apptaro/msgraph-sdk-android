package com.microsoft.graph.http;

import android.test.AndroidTestCase;

import com.google.gson.JsonObject;
import com.microsoft.graph.core.IBaseClient;
import com.microsoft.graph.core.MockBaseClient;
import com.microsoft.graph.serializer.ISerializer;
import com.microsoft.graph.serializer.MockSerializer;

import java.util.ArrayList;

/**
 * Test cases for {@see BaseCollectionPage}
 */
public class BaseCollectionPageTests extends AndroidTestCase {

    private IRequestBuilder mRequestBuilder;
    private static ArrayList<String> list;
    private BaseCollectionPage baseCollectionPage;
    private String requestUrl = "https://a.b.c";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        list = new ArrayList<String>();
        list.add("Object1");
        list.add("Object2");
        list.add("Object3");
        IBaseClient mBaseClient = new MockBaseClient();
        mRequestBuilder = new MockRequestBuilder(mBaseClient,requestUrl);
        baseCollectionPage = new BaseCollectionPage<String, IRequestBuilder>(list,mRequestBuilder) {};
    }

    public void testNotNull() {
        assertNotNull(baseCollectionPage);
    }

    public void testCurrentPage() {
        assertEquals(3,baseCollectionPage.getCurrentPage().size());
        assertEquals("Object2", baseCollectionPage.getCurrentPage().get(1));
        Boolean success = false;
        try{
            baseCollectionPage.getCurrentPage().remove(1);
        }catch (UnsupportedOperationException uEx){
            success = true;
        }
        assertTrue(success);
    }

    public void testNextPage() {
        assertEquals(mRequestBuilder, baseCollectionPage.getNextPage());
    }

    public void testRawObject() {
        ISerializer serializer = new MockSerializer(null, null);
        JsonObject jsonObject = new JsonObject();
        assertNull(baseCollectionPage.getRawObject());
        assertNull(baseCollectionPage.getSerializer());
        baseCollectionPage.setRawObject(serializer,jsonObject);
        assertNotNull(baseCollectionPage.getRawObject());
        assertNotNull(baseCollectionPage.getSerializer());
        assertEquals(serializer, baseCollectionPage.getSerializer());
        assertEquals(jsonObject, baseCollectionPage.getRawObject());
    }

}
