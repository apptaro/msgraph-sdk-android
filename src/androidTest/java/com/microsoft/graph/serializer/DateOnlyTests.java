package com.microsoft.graph.serializer;

import android.test.AndroidTestCase;

import com.microsoft.graph.model.DateOnly;

public class DateOnlyTests extends AndroidTestCase {

    public void testDateSerializer() throws Exception {
        String strDate = DateOnly.parse("2016-04-27").toString();
        assertEquals("2016-04-27", strDate);
    }

    public void testDateSerializerIndefinite() throws Exception {
        String strDate = DateOnly.parse("0001-01-01").toString();
        assertEquals("0001-01-01", strDate);
    }

    public void testDateDeserializer() throws Exception {
        DateOnly date = DateOnly.parse("2016-04-27");
        assertEquals(2016, date.getYear());
        assertEquals(4, date.getMonth());
        assertEquals(27, date.getDay());
    }

    public void testDateDeserializerIndefinite() throws Exception{
        DateOnly date = DateOnly.parse("0001-01-01");
        assertEquals(1, date.getYear());
        assertEquals(1, date.getMonth());
        assertEquals(1, date.getDay());
    }
}
