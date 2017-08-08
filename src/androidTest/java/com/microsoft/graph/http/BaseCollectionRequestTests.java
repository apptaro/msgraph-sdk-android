package com.microsoft.graph.http;

import android.test.AndroidTestCase;

import com.google.gson.JsonObject;
import com.microsoft.graph.authentication.MockAuthenticationProvider;
import com.microsoft.graph.concurrency.MockExecutors;
import com.microsoft.graph.core.MockBaseClient;
import com.microsoft.graph.logger.MockLogger;
import com.microsoft.graph.options.FunctionOption;
import com.microsoft.graph.options.Option;
import com.microsoft.graph.options.QueryOption;
import com.microsoft.graph.serializer.MockSerializer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Test cases for {@see BaseCollectionRequest}
 */
public class BaseCollectionRequestTests extends AndroidTestCase {

    private MockAuthenticationProvider mAuthenticationProvider;
    private MockBaseClient mBaseClient;
    private BaseCollectionRequest request;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mAuthenticationProvider = new MockAuthenticationProvider();
        mBaseClient = new MockBaseClient();
        final ITestConnectionData data = new ITestConnectionData() {
            @Override
            public int getRequestCode() {
                return 200;
            }

            @Override
            public String getJsonResponse() {
                return "{ \"id\": \"zzz\" }";
            }

            @Override
            public Map<String, String> getHeaders() {
                final HashMap<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/json");
                return map;
            }
        };

        MockHttpProvider mProvider = new MockHttpProvider(new MockSerializer(null, ""),
                mAuthenticationProvider,
                new MockExecutors(),
                new MockLogger());
        mProvider.setConnectionFactory(new MockConnectionFactory(new MockConnection(data)));
        mBaseClient.setHttpProvider(mProvider);
        request = new BaseCollectionRequest<JsonObject,String>("https://a.b.c", mBaseClient, null, JsonObject.class,null){};
    }

    public void testSend() {
        JsonObject result = (JsonObject)request.send();
        assertNotNull(result);
        assertEquals("zzz", result.get("id").getAsString());
    }

    public void testPost() {
        JsonObject result = (JsonObject)request.post(null);
        assertNotNull(result);
        assertEquals("zzz", result.get("id").getAsString());
    }

    public void testFunctionParameters() {
        final Option f1 = new FunctionOption("1", "one");
        final Option f2 = new FunctionOption("2", null);
        final BaseCollectionRequest request = new BaseCollectionRequest<String,String>("https://a.b.c", null, Arrays.asList(f1, f2), null,null){};
        assertEquals("https://a.b.c(1='one',2=null)", request.getRequestUrl().toString());
        request.addFunctionOption(new FunctionOption("3","two"));;
        assertEquals("https://a.b.c(1='one',2=null,3='two')", request.getRequestUrl().toString());
        assertEquals(4, request.getOptions().size());
    }

    public void testQueryParameters() {
        final Option q1 = new QueryOption("q1","option1 ");
        final Option q2 = new QueryOption("q2","option2");
        final BaseCollectionRequest request = new BaseCollectionRequest<String,String>("https://a.b.c", null, Arrays.asList(q1, q2), null,null){};
        assertEquals("https://a.b.c?q1=option1%20&q2=option2", request.getRequestUrl().toString());
        request.addQueryOption(new QueryOption("q3","option3"));
        assertEquals("https://a.b.c?q1=option1%20&q2=option2&q3=option3", request.getRequestUrl().toString());
        assertEquals(4,request.getOptions().size());
    }

    public void testFunctionAndQueryParameters() {
        final Option f1 = new FunctionOption("f1", "fun1");
        final Option f2 = new FunctionOption("f2", null);
        final Option q1 = new QueryOption("q1","option1 ");
        final Option q2 = new QueryOption("q2","option2");
        final BaseCollectionRequest request = new BaseCollectionRequest<String,String>("https://a.b.c", null, Arrays.asList(f1, f2, q1, q2), null,null){};
        assertEquals("https://a.b.c(f1='fun1',f2=null)?q1=option1%20&q2=option2", request.getRequestUrl().toString());
        assertEquals(5, request.getOptions().size());
    }

    public void testHttpMethod() {
        assertNull(request.getHttpMethod());
        request.send();
        assertEquals(HttpMethod.GET, request.getHttpMethod());
        request.post(null);
        assertEquals(HttpMethod.POST, request.getHttpMethod());
    }

    public void testHeader() {
        String expectedHeader = "header key";
        String expectedValue = "header value";
        final BaseCollectionRequest request = new BaseCollectionRequest<String,String>("https://a.b.c", null, null, null,null){};
        assertEquals(1, request.getHeaders().size());
        request.addHeader(expectedHeader,expectedValue);
        assertEquals(2,request.getHeaders().size());
    }
}
