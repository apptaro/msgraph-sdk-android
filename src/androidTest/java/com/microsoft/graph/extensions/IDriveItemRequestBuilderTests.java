// ------------------------------------------------------------------------------
// Copyright (c) Microsoft Corporation.  All Rights Reserved.  Licensed under the MIT License.  See License in the project root for license information.
// ------------------------------------------------------------------------------

package com.microsoft.graph.extensions;

import android.test.AndroidTestCase;

import java.lang.reflect.Method;

/**
 * Created by pnied on 8/9/2016.
 */
public class IDriveItemRequestBuilderTests extends AndroidTestCase {

    public void testGetItemWithPath() throws Exception {
        final Method getItemWithPath = IDriveItemRequestBuilder.class.getDeclaredMethod("getItemWithPath", String.class);
        assertNotNull(getItemWithPath);
        assertEquals(IDriveItemRequestBuilder.class, getItemWithPath.getReturnType());
    }
}
