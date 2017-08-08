package com.microsoft.graph.concurrency;

import android.os.Looper;
import android.test.AndroidTestCase;
import com.microsoft.graph.core.ClientException;
import com.microsoft.graph.core.GraphErrorCodes;
import com.microsoft.graph.logger.MockLogger;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Test cases for {@see DefaultExecutors}
 */
public class DefaultExecutorsTests extends AndroidTestCase {

    private MockLogger mLogger;
    private DefaultExecutors defaultExecutors;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mLogger = new MockLogger();
        defaultExecutors = new DefaultExecutors(mLogger);
    }

    public void testNotNull() {
        assertNotNull(mLogger);
        assertNotNull(defaultExecutors);
    }

    public void testPerformOnBackground() {
        final String expectedLogMessage = "Starting background task, current active count: 0";
        final String expectedResult = "test perform on background success";
        final ExecutorTestCallback<String> callback = new ExecutorTestCallback<>();

        defaultExecutors.performOnBackground(new Runnable() {
            @Override
            public void run() {
                callback.success(expectedResult);
            }
        });

        callback._completionWaiter.waitForSignal();
        assertEquals(CallingState.Background,callback._callingState.get());
        assertTrue(callback._successCalled.get());
        assertEquals(expectedResult, callback._successResult.get());
        assertEquals(1,mLogger.getLogMessages().size());
        assertTrue(mLogger.hasMessage(expectedLogMessage));
    }

    public void testPerformOnForegroundWithResult() {
        final String expectedResult = "result value";
        final String expectedLogMessage = "Starting foreground task, current active count:0, with result result value";
        final ExecutorTestCallback<String> callback = new ExecutorTestCallback<>();

        defaultExecutors.performOnForeground(expectedResult,callback);

        callback._completionWaiter.waitForSignal();
        assertEquals(CallingState.Foreground,callback._callingState.get());
        assertTrue(callback._successCalled.get());
        assertFalse(callback._failureCalled.get());
        assertEquals(expectedResult, callback._successResult.get());
        assertEquals(1,mLogger.getLogMessages().size());
        assertTrue(mLogger.hasMessage(expectedLogMessage));
    }

    public void testPerformOnForegroundWithProgress() throws Exception {
        final String expectedLogMessage = "Starting foreground task, current active count:0, with progress  1, max progress2";
        final ExecutorTestCallback<String> callback = new ExecutorTestCallback<>();
        final int expectedCurrentValue = 1;
        final int expectedMaxValue = 2;

        defaultExecutors.performOnForeground(expectedCurrentValue, expectedMaxValue, callback);

        callback._completionWaiter.waitForSignal();
        assertEquals(CallingState.Foreground, callback._callingState.get());
        assertFalse(callback._successCalled.get());
        assertFalse(callback._failureCalled.get());
        assertTrue(callback._progressCalled.get());
        assertEquals(expectedCurrentValue, callback._progressResultCurrent.get());
        assertEquals(expectedMaxValue, callback._progressResultMax.get());
        assertEquals(1,mLogger.getLogMessages().size());
        assertTrue(mLogger.hasMessage(expectedLogMessage));
    }

    public void testPerformOnForegroundWithClientException() {
        final String expectedExceptionMessage = "client exception message";
        final String expectedLogMessage = "Starting foreground task, current active count:0, with exception com.microsoft.graph.core.ClientException: client exception message";
        final ExecutorTestCallback<String> callback = new ExecutorTestCallback<>();

        defaultExecutors.performOnForeground(new ClientException(expectedExceptionMessage,null, GraphErrorCodes.InvalidAcceptType),
                callback);

        callback._completionWaiter.waitForSignal();
        assertEquals(CallingState.Foreground, callback._callingState.get());
        assertFalse(callback._successCalled.get());
        assertTrue(callback._failureCalled.get());
        assertEquals(expectedExceptionMessage, callback._exceptionResult.get().getMessage());
        assertTrue(callback._exceptionResult.get().isError(GraphErrorCodes.InvalidAcceptType));
        assertEquals(1,mLogger.getLogMessages().size());
        assertTrue(mLogger.hasMessage(expectedLogMessage));
    }

    private class ExecutorTestCallback<T> implements IProgressCallback<T> {
        SimpleWaiter _completionWaiter = new SimpleWaiter();
        AtomicReference<CallingState> _callingState = new AtomicReference<>(CallingState.Unknown);

        AtomicBoolean _successCalled = new AtomicBoolean(false);
        AtomicReference<T> _successResult = new AtomicReference<>();

        AtomicBoolean _failureCalled = new AtomicBoolean(false);
        AtomicReference<ClientException> _exceptionResult = new AtomicReference<>();

        AtomicBoolean _progressCalled = new AtomicBoolean(false);
        AtomicLong _progressResultCurrent = new AtomicLong(-1);
        AtomicLong _progressResultMax = new AtomicLong(-1);

        @Override
        public void success(final T result) {
            _successCalled.set(true);
            _successResult.set(result);
            _callingState.set(getCallingState());
            _completionWaiter.signal();
        }

        @Override
        public void failure(final ClientException ex) {
            _failureCalled.set(true);
            _exceptionResult.set(ex);
            _callingState.set(getCallingState());
            _completionWaiter.signal();
        }

        @Override
        public void progress(final long current, final long max) {
            _progressCalled.set(true);
            _progressResultCurrent.set(current);
            _progressResultMax.set(max);
            _callingState.set(getCallingState());
            _completionWaiter.signal();
        }

        private CallingState getCallingState(){
            return Looper.myLooper() == Looper.getMainLooper() ? CallingState.Foreground : CallingState.Background;
        }
    }

    private enum CallingState {
        Foreground, Background, Unknown;
    }
}
