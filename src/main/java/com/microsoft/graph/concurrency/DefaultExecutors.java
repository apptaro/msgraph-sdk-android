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

package com.microsoft.graph.concurrency;

import com.microsoft.graph.logger.ILogger;
import com.microsoft.graph.core.ClientException;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * The default executors implementation for the SDK.
 */
public class DefaultExecutors implements IExecutors {

    private static IExecutors executors;

    public DefaultExecutors(ILogger logger) {
        //TODO use logger?
    }

    public static void set(IExecutors executors) {
        DefaultExecutors.executors = executors;
    }
    
    public static IExecutors get() {
        return executors;
    }

    @Override
    public void performOnBackground(Runnable runnable) {
        executors.performOnBackground(runnable);
    }

    @Override
    public <Result> void performOnForeground(Result result, ICallback<Result> callback) {
        executors.performOnForeground(result, callback);
    }

    @Override
    public <Result> void performOnForeground(int progress, int progressMax, IProgressCallback<Result> callback) {
        executors.performOnForeground(progress, progressMax, callback);
    }

    @Override
    public <Result> void performOnForeground(ClientException exception, ICallback<Result> callback) {
        executors.performOnForeground(exception, callback);
    }

}
