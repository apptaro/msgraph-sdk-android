// ------------------------------------------------------------------------------
// Copyright (c) Microsoft Corporation.  All Rights Reserved.  Licensed under the MIT License.  See License in the project root for license information.
// ------------------------------------------------------------------------------

package com.microsoft.graph.generated;

import com.microsoft.graph.concurrency.*;
import com.microsoft.graph.core.*;
import com.microsoft.graph.extensions.*;
import com.microsoft.graph.http.*;
import com.microsoft.graph.generated.*;
import com.microsoft.graph.options.*;
import com.microsoft.graph.serializer.*;

import java.util.Arrays;
import java.util.EnumSet;

// **NOTE** This file was generated by a tool and any changes will be overwritten.

/**
 * The interface for the Base Workbook Application Request.
 */
public interface IBaseWorkbookApplicationRequest extends IHttpRequest {

    /**
     * Gets the WorkbookApplication from the service
     * @param callback The callback to be called after success or failure.
     */
    void get(final ICallback<WorkbookApplication> callback);

    /**
     * Gets the WorkbookApplication from the service
     * @return The WorkbookApplication from the request.
     * @throws ClientException This exception occurs if the request was unable to complete for any reason.
     */
    WorkbookApplication get() throws ClientException;

    /**
     * Delete this item from the service.
     * @param callback The callback when the deletion action has completed
     */
    void delete(final ICallback<Void> callback);

    /**
     * Delete this item from the service.
     * @throws ClientException if there was an exception during the delete operation
     */
    void delete() throws ClientException;

    /**
     * Patches this WorkbookApplication with a source
     * @param sourceWorkbookApplication The source object with updates
     * @param callback The callback to be called after success or failure.
     */
    void patch(final WorkbookApplication sourceWorkbookApplication, final ICallback<WorkbookApplication> callback);

    /**
     * Patches this WorkbookApplication with a source
     * @param sourceWorkbookApplication The source object with updates
     * @return The updated WorkbookApplication
     * @throws ClientException This exception occurs if the request was unable to complete for any reason.
     */
    WorkbookApplication patch(final WorkbookApplication sourceWorkbookApplication) throws ClientException;

    /**
     * Posts a WorkbookApplication with a new object
     * @param newWorkbookApplication The new object to create
     * @param callback The callback to be called after success or failure.
     */
    void post(final WorkbookApplication newWorkbookApplication, final ICallback<WorkbookApplication> callback);

    /**
     * Posts a WorkbookApplication with a new object
     * @param newWorkbookApplication The new object to create
     * @return The created WorkbookApplication
     * @throws ClientException This exception occurs if the request was unable to complete for any reason.
     */
    WorkbookApplication post(final WorkbookApplication newWorkbookApplication) throws ClientException;

    /**
     * Sets the select clause for the request
     *
     * @param value The select clause
     * @return The updated request
     */
    IBaseWorkbookApplicationRequest select(final String value);

    /**
     * Sets the expand clause for the request
     *
     * @param value The expand clause
     * @return The updated request
     */
    IBaseWorkbookApplicationRequest expand(final String value);

}
