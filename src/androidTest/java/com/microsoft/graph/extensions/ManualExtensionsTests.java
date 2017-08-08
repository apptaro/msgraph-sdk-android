package com.microsoft.graph.extensions;

import android.test.AndroidTestCase;

import junit.framework.Assert;

/**
 * These tests verify that manually-written classes are preserved from version-to-version.
 *
 * @author brianmel
 */
public class ManualExtensionsTests extends AndroidTestCase {

    public void testChunkedUploadRequestPreserved() {
        final String className = "com.microsoft.graph.extensions.ChunkedUploadRequest";
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            Assert.fail("Extension [" + className + "] not found.");
        }
    }

    public void testChunkedUploadResultPreserved() {
        final String className = "com.microsoft.graph.extensions.ChunkedUploadResult";
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            Assert.fail("Extension [" + className + "] not found.");
        }
    }
}
