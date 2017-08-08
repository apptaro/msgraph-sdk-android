package com.microsoft.graph.core;

import android.test.AndroidTestCase;

import com.microsoft.graph.authentication.IAuthenticationProvider;
import com.microsoft.graph.authentication.MockAuthenticationProvider;
import com.microsoft.graph.concurrency.IExecutors;
import com.microsoft.graph.concurrency.MockExecutors;
import com.microsoft.graph.http.IHttpProvider;
import com.microsoft.graph.http.MockHttpProvider;
import com.microsoft.graph.logger.ILogger;
import com.microsoft.graph.logger.MockLogger;
import com.microsoft.graph.serializer.ISerializer;
import com.microsoft.graph.serializer.MockSerializer;

/**
 * Test cases for {@see BaseClient}
 */
public class BaseClientTests extends AndroidTestCase {

    public static final String DEFAULT_GRAPH_ENDPOINT = "https://graph.microsoft.com/v1.0";
    private String mEndpoint = DEFAULT_GRAPH_ENDPOINT;
    private BaseClient baseClient;
    private IAuthenticationProvider mAuthenticationProvider;
    private IExecutors mExecutors;
    private IHttpProvider mHttpProvider;
    private ILogger mLogger;
    private ISerializer mSerializer;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        baseClient = new BaseClient() {
            @Override
            public String getServiceRoot() {
                return mEndpoint;
            }

            @Override
            public void setServiceRoot(String value) {
                mEndpoint = value;
            }
        };
        mAuthenticationProvider = new MockAuthenticationProvider();
        mExecutors = new MockExecutors();
        mHttpProvider = new MockHttpProvider();
        mLogger = new MockLogger();
        mSerializer = new MockSerializer(null, "");
    }

    public void testNotNull() {
        assertNotNull(baseClient);
        assertNotNull(mAuthenticationProvider);
        assertNotNull(mExecutors);
        assertNotNull(mHttpProvider);
        assertNotNull(mLogger);
        assertNotNull(mSerializer);
    }

    public void testValidateThrowException() {
        Boolean success = false;
        try {
            baseClient.validate();
        }catch (NullPointerException nEx){
            success = true;
        }
        assertTrue(success);
    }

    public void testValidateSuccess() throws Exception {
        baseClient.setAuthenticationProvider(mAuthenticationProvider);
        baseClient.setExecutors(mExecutors);
        baseClient.setHttpProvider(mHttpProvider);
        baseClient.setSerializer(mSerializer);
        baseClient.validate();
    }

    public void testEndPoint() {
        assertEquals(DEFAULT_GRAPH_ENDPOINT, baseClient.getServiceRoot());
        String expectedServiceRoot = "https://foo.bar";
        baseClient.setServiceRoot(expectedServiceRoot);
        assertEquals(expectedServiceRoot, baseClient.getServiceRoot());
    }

    public void testAuthenticationProvider() {
        assertNull(baseClient.getAuthenticationProvider());
        baseClient.setAuthenticationProvider(mAuthenticationProvider);
        assertEquals(mAuthenticationProvider, baseClient.getAuthenticationProvider());
    }

    public void testExecutors() {
        assertNull(baseClient.getExecutors());
        baseClient.setExecutors(mExecutors);
        assertEquals(mExecutors, baseClient.getExecutors());
    }

    public void testHttpProvider() {
        assertNull(baseClient.getHttpProvider());
        baseClient.setHttpProvider(mHttpProvider);
        assertEquals(mHttpProvider, baseClient.getHttpProvider());
    }

    public void testLogger() {
        assertNull(baseClient.getLogger());
        baseClient.setLogger(mLogger);
        assertEquals(mLogger, baseClient.getLogger());
    }

    public void testSerializer() {
        assertNull(baseClient.getSerializer());
        baseClient.setSerializer(mSerializer);
        assertEquals(mSerializer, baseClient.getSerializer());
    }
}
