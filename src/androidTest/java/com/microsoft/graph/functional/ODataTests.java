package com.microsoft.graph.functional;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.Suppress;

import com.google.gson.JsonPrimitive;
import com.microsoft.graph.extensions.ExtensionSchemaProperty;
import com.microsoft.graph.extensions.Extension;
import com.microsoft.graph.extensions.SchemaExtension;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

@Suppress
public class ODataTests extends AndroidTestCase {

    private TestBase testBase;

    @Before
    public void setUp() {
       testBase = new TestBase();

        // There's a current limitation of two extensions per user. If there's two extensions in the collection,
        // delete the latest one
        List<Extension> extensions = testBase.graphClient.getMe().getExtensions().buildRequest().get().getCurrentPage();
        if (extensions.size() >= 2) {
            testBase.graphClient.getMe().getExtensions(extensions.get(1).id).buildRequest().delete();
        }
    }

    @Test
    public void testOpenExtensions() {
        Extension extension = new Extension();

        extension.getAdditionalDataManager().put("theme", new JsonPrimitive("dark"));
        extension.getAdditionalDataManager().put("extensionName", new JsonPrimitive("Extension 1"));
        Extension newExtension = testBase.graphClient.getMe().getExtensions().buildRequest().post(extension);
        assertEquals(extension.getAdditionalDataManager().get("theme"), newExtension.getAdditionalDataManager().get("theme"));

        testBase.graphClient.getMe().getExtensions(newExtension.id).buildRequest().delete();
    }

    @Test
    public void testSchemaExtensions() {
        SchemaExtension extension = new SchemaExtension();
        extension.id = "schematest";
        extension.description = "Android Graph SDK test";
        List<String> targets = new ArrayList<>();
        targets.add("Group");
        extension.targetTypes = targets;

        ExtensionSchemaProperty prop = new ExtensionSchemaProperty();
        prop.name = "courseId";
        prop.type = "Integer";

        ExtensionSchemaProperty prop2 = new ExtensionSchemaProperty();
        prop2.name = "courseName";
        prop2.type = "String";

        List<ExtensionSchemaProperty> properties = new ArrayList<>();
        properties.add(prop);
        properties.add(prop2);
        extension.properties = properties;

        SchemaExtension newExtension = testBase.graphClient.getSchemaExtensions().buildRequest().post(extension);
        assertEquals(extension.description, newExtension.description);

        try {
            SchemaExtension patchExtension = new SchemaExtension();
            List<ExtensionSchemaProperty> patchProperties = new ArrayList<>();
            ExtensionSchemaProperty patchProperty = new ExtensionSchemaProperty();
            patchProperty.name = "newItem";
            patchProperty.type = "String";
            patchProperties.add(prop);
            patchProperties.add(prop2);
            patchProperties.add(patchProperty);

            patchExtension.properties = patchProperties;

            testBase.graphClient.getSchemaExtensions(newExtension.id).buildRequest().patch(patchExtension);
            SchemaExtension updatedExtension = testBase.graphClient.getSchemaExtensions(newExtension.id).buildRequest().get();

            boolean foundUpdatedProperty = false;
            for (ExtensionSchemaProperty updatedProperty : updatedExtension.properties) {
                if (updatedProperty.name.equals(patchProperty.name)) {
                    assertEquals(patchProperty.type, updatedProperty.type);
                    foundUpdatedProperty = true;
                    break;
                }
            }
            if (!foundUpdatedProperty) {
                Assert.fail("Patch failed on Schema Extension");
            }
        } catch (Exception e) {
            Assert.fail("Patch failed on Schema Extension");
        } finally {
            testBase.graphClient.getSchemaExtensions(newExtension.id).buildRequest().delete();
        }
    }

    @Test
    public void testEnumFlags() {
//        EnumSet<MailTipsType> mailTips = EnumSet.noneOf(MailTipsType.class);
//        mailTips.add(MailTipsType.automaticReplies);
//        mailTips.add(MailTipsType.customMailTip);
//        mailTips.add(MailTipsType.recipientScope);
//        List<String> emailAddresses = new ArrayList<String>();
//        emailAddresses.add("katiej@mod810997.onmicrosoft.com");
//        emailAddresses.add("garthf@mod810997.onmicrosoft.com");
//        emailAddresses.add("admin@mod810997.onmicrosoft.com");
//
//        IUserGetMailTipsCollectionPage mailTipsCollection = testBase.graphClient.getMe().getGetMailTips(emailAddresses, mailTips).buildRequest().post();
//
//        assertNotNull(mailTipsCollection);
//
//        List<MailTips> mailTipsPage = mailTipsCollection.getCurrentPage();
//        EnumSet<RecipientScopeType> recipientScopeTypes = EnumSet.allOf(RecipientScopeType.class);
//        for (int i = 0; i< mailTipsPage.size(); i++) {
//            if (mailTipsPage.get(i).recipientScope != null) {
//                assertTrue(mailTipsPage.get(i).recipientScope.getClass().isInstance(mailTips));
//            }
//        }
    }

    @Test
    public void testDeltaQuery() {
//        testBase.graphClient.setServiceRoot("https://graph.microsoft.com/beta");
//        IGroupDeltaCollectionPage deltas = testBase.graphClient.getGroups().getDelta().buildRequest().get();
//
//        assertNotNull(deltas.getCurrentPage());
//        for (int i = 0; i < deltas.getCurrentPage().size(); i++) {
//            Group group = deltas.getCurrentPage().get(i);
//            String s = group.description;
//        }
//
//        while(deltas.getNextPage() != null) {
//            deltas = deltas.getNextPage().buildRequest().get();
//            assertNotNull(deltas.getCurrentPage());
//        }
//
//        IGroupDeltaCollectionPage deltas2 = testBase.graphClient.getGroups().getDelta(deltas.getDeltaLink()).buildRequest().get();
//        assertNotNull(deltas2);
    }

    @Test
    public void testDeletedItem() {
    }
}
