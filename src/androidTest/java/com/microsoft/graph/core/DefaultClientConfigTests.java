package com.microsoft.graph.core;

import android.test.AndroidTestCase;
import android.util.Log;

import com.microsoft.graph.authentication.IAuthenticationProvider;
import com.microsoft.graph.authentication.MockAuthenticationProvider;

/**
 * Test cases for {@see DefaultClientConfig}
 */
public class DefaultClientConfigTests extends AndroidTestCase {

    private IClientConfig mClientConfig;
    private IAuthenticationProvider mAuthenticationProvider;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mAuthenticationProvider = new MockAuthenticationProvider();
        mClientConfig = DefaultClientConfig.createWithAuthenticationProvider(mAuthenticationProvider);
    }

    public void testDefaultClientConfig() {
        assertNotNull(mAuthenticationProvider);
        assertNotNull(mClientConfig);
        assertNotNull(mClientConfig.getLogger());
        assertNotNull(mClientConfig.getExecutors());
        assertNotNull(mClientConfig.getSerializer());
        assertNotNull(mClientConfig.getHttpProvider());
        assertNotNull(mClientConfig.getAuthenticationProvider());
        assertEquals(mAuthenticationProvider, mClientConfig.getAuthenticationProvider());
    }
}
