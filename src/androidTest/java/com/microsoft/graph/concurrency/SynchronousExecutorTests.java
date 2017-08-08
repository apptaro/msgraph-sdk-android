package com.microsoft.graph.concurrency;

import android.test.AndroidTestCase;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Test cases for {@see SynchronousExecutor}
 */
public class SynchronousExecutorTests extends AndroidTestCase {

    public void testExecute() throws Exception {
        final AtomicBoolean success = new AtomicBoolean(false);
        final SimpleWaiter simpleWaiter = new SimpleWaiter();
        SynchronousExecutor synchronousExecutor = new SynchronousExecutor();
        synchronousExecutor.execute(new Runnable() {
            @Override
            public void run() {
                success.set(true);
                simpleWaiter.signal();
            }
        });
        simpleWaiter.waitForSignal();
        Thread.sleep(2000);
        assertTrue(success.get());
        assertEquals(0, synchronousExecutor.getActiveCount());
    }
}
