package com.microsoft.graph.functional;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.Suppress;

//import com.microsoft.graph.extensions.AdministrativeUnit;
import com.microsoft.graph.extensions.DirectoryObject;
//import com.microsoft.graph.extensions.IAdministrativeUnitCollectionPage;
//import com.microsoft.graph.extensions.ScopedRoleMembership;
import com.microsoft.graph.extensions.User;
import com.microsoft.graph.http.GraphServiceException;

import org.junit.*;

@Suppress
public class AzureTests extends AndroidTestCase {

    private TestBase testBase;
    //private AdministrativeUnit testAdministrativeUnit;

    @Before
    public void setUp()
    {
        testBase = new TestBase();
//        AdministrativeUnit adminUnit = new AdministrativeUnit();
//        adminUnit.displayName = "Test admin unit";
//        testAdministrativeUnit = testBase.graphClient.getAdministrativeUnits().buildRequest().post(adminUnit);
    }

    @Test
    public void testGetAdministrativeUnits() {
//        IAdministrativeUnitCollectionPage administrativeUnits = testBase.graphClient.getAdministrativeUnits().buildRequest().get();
//        assertNotNull(administrativeUnits);
    }

    @Test
    public void testCreateAdministrativeUnit() {
//        AdministrativeUnit adminUnit = new AdministrativeUnit();
//        adminUnit.displayName = "Test admin unit";
//        AdministrativeUnit responseUnit = testBase.graphClient.getAdministrativeUnits().buildRequest().post(adminUnit);
//        assertEquals(adminUnit.displayName, responseUnit.displayName);
//
//        testBase.graphClient.getAdministrativeUnits(responseUnit.id).buildRequest().delete();
    }

    @Test(expected=GraphServiceException.class)
    public void testDeleteAdministrativeUnit()
    {
//        testBase.graphClient.getAdministrativeUnits(testAdministrativeUnit.id).buildRequest().delete();
//
//        try {
//            testBase.graphClient.getAdministrativeUnits(testAdministrativeUnit.id).buildRequest().get();
//        } catch (GraphServiceException ex) {
//            // Querying for deleted item should return a 404 from the service
//        }
    }

    @Test
    public void testAddUserToAdministrativeUnit()
    {
//        User me = testBase.graphClient.getMe().buildRequest().get();
//        testBase.graphClient.getAdministrativeUnits(testAdministrativeUnit.id).getMembers().references().buildRequest().post(me);
//
//        DirectoryObject member = testBase.graphClient.getAdministrativeUnits(testAdministrativeUnit.id).getMembers(me.id).buildRequest().get();
//        assertNotNull(member);
//        assertEquals(me.id, member.id);
    }

    @After
    public void tearDown()
    {
        //testBase.graphClient.getAdministrativeUnits(testAdministrativeUnit.id).buildRequest().delete();
    }
}