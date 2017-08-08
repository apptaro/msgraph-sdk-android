package com.microsoft.graph.serializer;

import android.test.AndroidTestCase;

import com.microsoft.graph.model.TimeOfDay;

public class TimeOfDayTests extends AndroidTestCase {

    public void testTimeOfDaySerializer() throws Exception {
        String strDate = TimeOfDay.parse("12:30:44").toString();
        assertEquals("12:30:44", strDate);
    }

    public void testTimeOfDaySerializerIndefinite() throws Exception {
        String strDate = TimeOfDay.parse("01:01:01").toString();
        assertEquals("01:01:01", strDate);
    }

    public void testTimeOfDayDeserializer() throws Exception {
        TimeOfDay time = TimeOfDay.parse("12:30:44");
        assertEquals(12, time.getHour());
        assertEquals(30, time.getMinute());
        assertEquals(44, time.getSecond());
    }

    public void testTimeOfDayDeserializerIndefinite() throws Exception{
        TimeOfDay time = TimeOfDay.parse("01:01:01");
        assertEquals(1, time.getHour());
        assertEquals(1, time.getMinute());
        assertEquals(1, time.getSecond());
    }
}
