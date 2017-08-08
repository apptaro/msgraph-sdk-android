package com.microsoft.graph.functional;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.Suppress;

import com.microsoft.graph.extensions.Subscription;

import org.junit.Test;

import java.util.Calendar;

@Suppress
public class WebhooksTests extends AndroidTestCase {

    @Test
    public void testCreateSubscription() {
        TestBase testBase = new TestBase();
        Subscription sub = new Subscription();
        sub.changeType = "created,updated";
        sub.notificationUrl = "https://webhook-sub-test.azurewebsites.net/api/WebhookTrigger";
        sub.resource = "/me/mailfolders('inbox')/messages";
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 1);
        sub.expirationDateTime = cal;
        Subscription subscription = testBase.graphClient.getSubscriptions().buildRequest().post(sub);
        assertNotNull(subscription);
        assertEquals(sub.resource, subscription.resource);
    }
}