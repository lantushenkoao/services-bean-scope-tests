package com.lantushenko.experimental.stub.webtests;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.lantushenko.experimental.stub.dbunit.utils.DbUnitDataSource;
import com.lantushenko.experimental.stub.webtests.auxiliary.WebTestsContext;
import com.lantushenko.experimental.stub.webtests.pages.IndexPage;

/**
 * Abstract class for all web tests. Responsible for starting/stopping browser,
 * initializing database connections and auxiliary tasks related to creating web tests
 * context.
 */
@ContextConfiguration(locations = {"/test-settings.xml", "/test-utils.xml"})
public class BaseWebTest {

    private static WebTestsContextStarter starter;

    private Log log = LogFactory.getLog(this.getClass());
    @Autowired
    private DbUnitDataSource dbUnitDataSource;

    @BeforeClass
    public static void beforeClass() throws Exception {
        starter = new WebTestsContextStarter();
        starter.start();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        starter.stop();
    }

    @Before
    public void before() throws Exception {
        dbUnitDataSource.deleteAll("clean-database");
        logout();
    }

    @After
    public void after() throws Exception {
    }

    protected void logout() {
        getWebTestsContext().getWebDriver().switchTo().defaultContent();
        //we can't just navigate to logout URL because of enabled CSRF protection 
        new IndexPage(getWebTestsContext()).open()
                .getTopMenu().clickLogoutIfLoggedIn();
    }

    public WebTestsContext getWebTestsContext() {
        return starter.getWebTestContext();
    }

    protected Log getLog() {
        return log;
    }

    protected DbUnitDataSource getDataSource() {
        return dbUnitDataSource;
    }
}
