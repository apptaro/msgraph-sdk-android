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
 * The class for the Base Workbook Functions Dst Dev Request Builder.
 */
public class BaseWorkbookFunctionsDstDevRequestBuilder extends BaseActionRequestBuilder {

    /**
     * The request builder for this WorkbookFunctionsDstDev
     *
     * @param requestUrl The request url
     * @param client The service client
     * @param requestOptions The options for this request
     */
    public BaseWorkbookFunctionsDstDevRequestBuilder(final String requestUrl, final IBaseClient client, final java.util.List<Option> requestOptions, final com.google.gson.JsonElement database, final com.google.gson.JsonElement field, final com.google.gson.JsonElement criteria) {
        super(requestUrl, client, requestOptions);
        mBodyParams.put("database", database);
        mBodyParams.put("field", field);
        mBodyParams.put("criteria", criteria);
    }

    /**
     * Creates the IWorkbookFunctionsDstDevRequest
     *
     * @return The IWorkbookFunctionsDstDevRequest instance
     */
    public IWorkbookFunctionsDstDevRequest buildRequest() {
        return buildRequest(getOptions());
    }

    /**
     * Creates the IWorkbookFunctionsDstDevRequest with specific requestOptions instead of the existing requestOptions
     *
     * @param requestOptions the options for the request
     * @return The IWorkbookFunctionsDstDevRequest instance
     */
    public IWorkbookFunctionsDstDevRequest buildRequest(final java.util.List<Option> requestOptions) {
        WorkbookFunctionsDstDevRequest request = new WorkbookFunctionsDstDevRequest(
                getRequestUrl(),
                getClient(),
                requestOptions
        );

        if (hasParameter("database")) {
            request.mBody.database = getParameter("database");
        }

        if (hasParameter("field")) {
            request.mBody.field = getParameter("field");
        }

        if (hasParameter("criteria")) {
            request.mBody.criteria = getParameter("criteria");
        }

        return request;
    }
}
