// ------------------------------------------------------------------------------
// Copyright (c) 2015 Microsoft Corporation
// 
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
// 
// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
// 
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.
// ------------------------------------------------------------------------------

package com.microsoft.graph.serializer;

import android.test.AndroidTestCase;

import com.microsoft.graph.extensions.Drive;
import com.microsoft.graph.extensions.RecurrenceRangeType;
import com.microsoft.graph.generated.BaseRecurrenceRange;
import com.microsoft.graph.logger.DefaultLogger;
import com.microsoft.graph.model.DateOnly;

/**
 * Test cases for the {@see DefaultSerializer}
 */
public class DefaultSerializerTests extends AndroidTestCase {

    /**
     * Make sure that deserializing a Drive also returns members from BaseDrive
     *
     * @throws Exception If there is an exception during the test
     */
    public void testDriveDeserialization() throws Exception {
        final DefaultSerializer serializer = new DefaultSerializer(new DefaultLogger());
        String source = "{\"@odata.context\":\"https://graph.microsoft.com/v1.0/$metadata#drives/$entity\",\"id\":\"8bf6ae90006c4a4c\",\"driveType\":\"personal\",\"owner\":{\"user\":{\"displayName\":\"Peter\",\"id\":\"8bf6ae90006c4a4c\"}},\"quota\":{\"deleted\":1485718314,\"remaining\":983887466461,\"state\":\"normal\",\"total\":1142461300736,\"used\":158573834275}}";
        Drive result = serializer.deserializeObject(source, Drive.class);
        assertNotNull(result);
        assertEquals("personal", result.driveType);
        assertEquals(Long.valueOf(983887466461L), result.quota.remaining);
        assertEquals("8bf6ae90006c4a4c", result.id);

    }

    public void testRecurrenceRangeDeserialization() throws Exception {
        final DefaultSerializer serializer = new DefaultSerializer(new DefaultLogger());
        String source = "{\n" +
                "    \"type\": \"noEnd\",\n" +
                "    \"startDate\": \"2016-04-27\",\n" +
                "    \"endDate\": \"0001-01-01\",\n" +
                "    \"recurrenceTimeZone\": \"China Standard Time\",\n" +
                "    \"numberOfOccurrences\": 0\n" +
                "}";
        BaseRecurrenceRange baseRecurrenceRange = serializer.deserializeObject(source, BaseRecurrenceRange.class);
        assertNotNull(source);
        assertEquals(baseRecurrenceRange.type, RecurrenceRangeType.noEnd);
        assertEquals("2016-04-27", baseRecurrenceRange.startDate.toString());
        assertEquals("0001-01-01", baseRecurrenceRange.endDate.toString());
        assertEquals("China Standard Time", baseRecurrenceRange.recurrenceTimeZone);
        assertEquals(Integer.valueOf(0), baseRecurrenceRange.numberOfOccurrences);
    }

    public void testRecurrenceRangeSerialization() throws Exception {
        final String expected = "{\"endDate\":\"2016-05-25\",\"numberOfOccurrences\":4,\"recurrenceTimeZone\":\"PST\",\"startDate\":\"2016-04-25\",\"type\":\"endDate\"}";
        final DefaultSerializer serializer = new DefaultSerializer(new DefaultLogger());
        BaseRecurrenceRange brr = new BaseRecurrenceRange();
        brr.type = RecurrenceRangeType.endDate;
        brr.startDate = new DateOnly(2016, 4, 25);
        brr.endDate = new DateOnly(2016, 5, 25);
        brr.recurrenceTimeZone = "PST";
        brr.numberOfOccurrences = 4;
        String jsonOut = serializer.serializeObject(brr);
        assertNotNull(jsonOut);
        assertEquals(jsonOut, expected);
    }
}
