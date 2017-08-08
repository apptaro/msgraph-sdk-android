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
 * The interface for the Base Group Request Builder.
 */
public interface IBaseGroupRequestBuilder extends IRequestBuilder {
    /**
     * Creates the request
     */
    IGroupRequest buildRequest();

    /**
     * Creates the request with specific options instead of the existing options
     */
    IGroupRequest buildRequest(final java.util.List<Option> requestOptions);

    IDirectoryObjectCollectionWithReferencesRequestBuilder getMembers();

    IDirectoryObjectWithReferenceRequestBuilder getMembers(final String id);

    IDirectoryObjectCollectionWithReferencesRequestBuilder getMemberOf();

    IDirectoryObjectWithReferenceRequestBuilder getMemberOf(final String id);

    /**
     * Gets the request builder for DirectoryObject.
     */
    IDirectoryObjectWithReferenceRequestBuilder getCreatedOnBehalfOf();

    IDirectoryObjectCollectionWithReferencesRequestBuilder getOwners();

    IDirectoryObjectWithReferenceRequestBuilder getOwners(final String id);

    IExtensionCollectionRequestBuilder getExtensions();

    IExtensionRequestBuilder getExtensions(final String id);

    IConversationThreadCollectionRequestBuilder getThreads();

    IConversationThreadRequestBuilder getThreads(final String id);

    /**
     * Gets the request builder for Calendar.
     */
    ICalendarRequestBuilder getCalendar();

    IEventCollectionRequestBuilder getCalendarView();

    IEventRequestBuilder getCalendarView(final String id);

    IEventCollectionRequestBuilder getEvents();

    IEventRequestBuilder getEvents(final String id);

    IConversationCollectionRequestBuilder getConversations();

    IConversationRequestBuilder getConversations(final String id);

    /**
     * Gets the request builder for ProfilePhoto.
     */
    IProfilePhotoRequestBuilder getPhoto();

    IProfilePhotoCollectionRequestBuilder getPhotos();

    IProfilePhotoRequestBuilder getPhotos(final String id);

    IDirectoryObjectCollectionRequestBuilder getAcceptedSenders();

    IDirectoryObjectRequestBuilder getAcceptedSenders(final String id);

    IDirectoryObjectCollectionRequestBuilder getRejectedSenders();

    IDirectoryObjectRequestBuilder getRejectedSenders(final String id);

    /**
     * Gets the request builder for Drive.
     */
    IDriveRequestBuilder getDrive();

    IDriveCollectionRequestBuilder getDrives();

    IDriveRequestBuilder getDrives(final String id);

    ISiteCollectionRequestBuilder getSites();

    ISiteRequestBuilder getSites(final String id);

    /**
     * Gets the request builder for PlannerGroup.
     */
    IPlannerGroupRequestBuilder getPlanner();

    /**
     * Gets the request builder for Onenote.
     */
    IOnenoteRequestBuilder getOnenote();
    IGroupSubscribeByMailRequestBuilder getSubscribeByMail();
    IGroupUnsubscribeByMailRequestBuilder getUnsubscribeByMail();
    IGroupAddFavoriteRequestBuilder getAddFavorite();
    IGroupRemoveFavoriteRequestBuilder getRemoveFavorite();
    IGroupResetUnseenCountRequestBuilder getResetUnseenCount();

}