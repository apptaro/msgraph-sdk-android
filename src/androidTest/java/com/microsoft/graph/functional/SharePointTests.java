package com.microsoft.graph.functional;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.Suppress;
import android.util.Base64;

import com.microsoft.graph.extensions.IDriveCollectionPage;
import com.microsoft.graph.extensions.IDriveItemCollectionPage;
import com.microsoft.graph.extensions.ISiteCollectionPage;
import com.microsoft.graph.extensions.Site;
import com.microsoft.graph.options.Option;
import com.microsoft.graph.options.QueryOption;

import org.junit.*;

import java.util.LinkedList;
import java.util.List;

@Suppress
public class SharePointTests extends AndroidTestCase {

    private TestBase testBase;
    private Site testSite;

    @Before
    public void setUp() {
        testBase = new TestBase();
        testBase.graphClient.setServiceRoot("https://graph.microsoft.com/stagingv1.0");

        List<Option> requestOptions = new LinkedList<Option>();
        requestOptions.add(new QueryOption("search", "Contoso"));
        ISiteCollectionPage sites = testBase.graphClient.getSites().buildRequest(requestOptions).get();
        testSite = sites.getCurrentPage().get(0);
    }

    @Test
    public void testAccessRootSite() {
//        ISiteCollectionPage sites = testBase.graphClient.getSites().buildRequest().get();
//        assertNotNull(sites);
    }

    @Test
    public void testSearch() {
        List<Option> requestOptions = new LinkedList<Option>();
        requestOptions.add(new QueryOption("search", "Contoso"));
        ISiteCollectionPage sites = testBase.graphClient.getSites().buildRequest(requestOptions).get();

        assertNotNull(sites);
    }

    @Test
    public void testDrives() {
        IDriveCollectionPage drives = testBase.graphClient.getSites(testSite.id).getDrives().buildRequest().get();
        assertNotNull(drives);
    }

    @Test
    public void testNonDefaultLibrary() {
        IDriveCollectionPage drives = testBase.graphClient.getSites(testSite.id).getDrives().buildRequest().get();
        IDriveItemCollectionPage driveItems = testBase.graphClient.getSites(testSite.id).getDrives(drives.getCurrentPage().get(0).id).getRoot().getChildren().buildRequest().get();

        assertNotNull(driveItems);
    }

    @Test
    public void accessSiteViaUrl() {
        String url = "u!" + Base64.encodeToString(testSite.webUrl.getBytes(), Base64.URL_SAFE);
        url = url.replace("=", "").replace("\n", "");
        Site site = testBase.graphClient.getShares(url).getSite().buildRequest().get();
    }
}