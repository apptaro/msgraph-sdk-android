// ------------------------------------------------------------------------------
// Copyright (c) Microsoft Corporation.  All Rights Reserved.  Licensed under the MIT License.  See License in the project root for license information.
// ------------------------------------------------------------------------------

package com.microsoft.graph.extensions;

import android.test.AndroidTestCase;

import java.lang.reflect.Method;

/**
 * Created by pnied on 8/9/2016.
 */
public class IThumbnailSetRequestBuilderTests extends AndroidTestCase {

    public void testGetThumbnailSize() throws Exception {
        final Method getThumbnailSize = IThumbnailSetRequestBuilder.class.getDeclaredMethod("getThumbnailSize", String.class);
        assertNotNull(getThumbnailSize);
        assertEquals(IThumbnailRequestBuilder.class, getThumbnailSize.getReturnType());
    }
}
