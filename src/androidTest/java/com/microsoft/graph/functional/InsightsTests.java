package com.microsoft.graph.functional;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.Suppress;

import com.microsoft.graph.options.Option;
import com.microsoft.graph.options.QueryOption;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

@Suppress
public class InsightsTests extends AndroidTestCase {

    private TestBase testBase;

    @Before
    public void setUp() {
        testBase = new TestBase();
    }

    @Test
    public void testTrending() {
        //ITrendingCollectionPage trendingCollectionPage = testBase.graphClient.getMe().getInsights().getTrending().buildRequest().get();
        //assertNotNull(trendingCollectionPage);
    }

    @Test
    public void testFilterType(){
        final List<Option> options = new LinkedList<Option>();
        options.add(new QueryOption("filter", "resourceVisualization/type eq 'Word'"));
        //ITrendingCollectionPage trendingCollectionPage = testBase.graphClient.getMe().getInsights().getTrending().buildRequest(options).get();
        //assertNotNull(trendingCollectionPage);
    }

    @Test
    public void testFilterContentType(){
        final List<Option> options = new LinkedList<Option>();
        options.add(new QueryOption("filter", "resourceVisualization/contentType eq 'OneDrive'"));
        //ITrendingCollectionPage trendingCollectionPage = testBase.graphClient.getMe().getInsights().getTrending().buildRequest(options).get();
        //assertNotNull(trendingCollectionPage);
    }
}