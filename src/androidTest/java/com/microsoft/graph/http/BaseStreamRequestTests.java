package com.microsoft.graph.http;

import android.test.AndroidTestCase;

import com.microsoft.graph.authentication.MockAuthenticationProvider;
import com.microsoft.graph.concurrency.ICallback;
import com.microsoft.graph.concurrency.MockExecutors;
import com.microsoft.graph.core.ClientException;
import com.microsoft.graph.core.MockBaseClient;
import com.microsoft.graph.logger.MockLogger;
import com.microsoft.graph.serializer.MockSerializer;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Test cases for {@see BaseStreamRequest}
 */
public class BaseStreamRequestTests extends AndroidTestCase {

    private MockAuthenticationProvider mAuthenticationProvider;
    private MockBaseClient mBaseClient;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mAuthenticationProvider = new MockAuthenticationProvider();
        mBaseClient = new MockBaseClient();
    }

    public void testSend() {
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
                map.put("Content-Type", "application/octet-stream");
                return map;
            }
        };
        DefaultHttpProvider mProvider = new DefaultHttpProvider(new MockSerializer(null, ""),
                mAuthenticationProvider,
                new MockExecutors(),
                new MockLogger());
        mProvider.setConnectionFactory(new MockConnectionFactory(new MockConnection(data)));
        mBaseClient.setHttpProvider(mProvider);
        final BaseStreamRequest request = new BaseStreamRequest<String>("https://a.b.c", mBaseClient,null, null){};
        request.send();
        assertEquals(1, mAuthenticationProvider.getInterceptionCount());
    }

    public void testSendWithCallback() {
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
        DefaultHttpProvider mProvider = new DefaultHttpProvider(new MockSerializer(null, ""),
                mAuthenticationProvider,
                new MockExecutors(),
                new MockLogger());
        mProvider.setConnectionFactory(new MockConnectionFactory(new MockConnection(data)));
        mBaseClient.setHttpProvider(mProvider);
        final AtomicBoolean success = new AtomicBoolean(false);
        final AtomicBoolean failure = new AtomicBoolean(false);
        final ICallback<InputStream> callback = new ICallback<InputStream>() {
            @Override
            public void success(InputStream inputStream) {
                success.set(true);
            }

            @Override
            public void failure(ClientException ex) {
                failure.set(true);
            }
        };
        final BaseStreamRequest request = new BaseStreamRequest<InputStream>("https://a.b.c", mBaseClient,null, InputStream.class){};
        request.send(callback);
        assertTrue(success.get());
        assertFalse(failure.get());
        assertEquals(1, mAuthenticationProvider.getInterceptionCount());
    }

    public void testSendWithContentAndCallback() {
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
        DefaultHttpProvider mProvider = new DefaultHttpProvider(new MockSerializer(null, ""),
                mAuthenticationProvider,
                new MockExecutors(),
                new MockLogger());
        mProvider.setConnectionFactory(new MockConnectionFactory(new MockConnection(data)));
        mBaseClient.setHttpProvider(mProvider);
        final AtomicBoolean success = new AtomicBoolean(false);
        final AtomicBoolean failure = new AtomicBoolean(false);
        final ICallback<InputStream> callback = new ICallback<InputStream>() {
            @Override
            public void success(InputStream inputStream) {
                success.set(true);
            }

            @Override
            public void failure(ClientException ex) {
                failure.set(true);
            }
        };
        final BaseStreamRequest request = new BaseStreamRequest<InputStream>("https://a.b.c", mBaseClient,null, InputStream.class){};
        request.send(new byte[]{1, 2, 3, 4},callback);
        assertTrue(success.get());
        assertFalse(failure.get());
        assertEquals(1, mAuthenticationProvider.getInterceptionCount());
    }

    public void testSendWithContent() {
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
        DefaultHttpProvider mProvider = new DefaultHttpProvider(new MockSerializer(null, ""),
                mAuthenticationProvider,
                new MockExecutors(),
                new MockLogger());
        mProvider.setConnectionFactory(new MockConnectionFactory(new MockConnection(data)));
        mBaseClient.setHttpProvider(mProvider);
        final BaseStreamRequest request = new BaseStreamRequest<InputStream>("https://a.b.c", mBaseClient,null, InputStream.class){};
        request.send(new byte[]{1, 2, 3, 4});
        assertEquals(1, mAuthenticationProvider.getInterceptionCount());
    }

    public void testBaseMethod() {
        final BaseStreamRequest request = new BaseStreamRequest<InputStream>("https://a.b.c", mBaseClient,null, InputStream.class){};
        assertEquals("https://a.b.c", request.getRequestUrl().toString());
        request.addHeader("header key", "header value");
        assertEquals(2,request.getHeaders().size());
        assertNull(request.getHttpMethod());
        assertEquals(2, request.getOptions().size());
    }

}
