package com.microsoft.graph.functional;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.Suppress;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.microsoft.graph.extensions.IWorkbookNamedItemCollectionPage;
import com.microsoft.graph.extensions.WorkbookNamedItem;

import org.junit.Before;
import org.junit.Test;

@Suppress
public class ExcelTests extends AndroidTestCase {

    private TestBase testBase;
    private String fileId = "015U5LKCVICVSIQ6DKOVELGXD3WAEHMMAI";

    @Before
    public void setUp()
    {
        testBase = new TestBase();
    }

    @Test
    public void testCreateNamedItem() {
        JsonObject range = new JsonObject();
        range.addProperty("address", "Sheet1!A1");
        WorkbookNamedItem item = testBase.graphClient
                .getMe()
                .getDrive()
                .getItems(fileId)
                .getWorkbook()
                .getNames()
                .getAdd("testNamedRange", new JsonPrimitive("Sheet1!A1"), "Comment")
                .buildRequest()
                .post();
        assertNotNull(item);
        assertEquals("testNamedRange", item.name);
    }

    @Test
    public void testGetNamedItems() {
        JsonObject range = new JsonObject();
        range.addProperty("range", "A1");
        WorkbookNamedItem item = testBase.graphClient.getMe().getDrive().getItems(fileId).getWorkbook().getNames().getAdd("named-range", range, "Comment").buildRequest().post();

        IWorkbookNamedItemCollectionPage namedItems = testBase.graphClient.getMe().getDrive().getItems(fileId).getWorkbook().getNames().buildRequest().get();
        assertNotNull(namedItems);
    }
}