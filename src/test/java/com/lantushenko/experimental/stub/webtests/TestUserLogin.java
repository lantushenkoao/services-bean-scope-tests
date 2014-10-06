package com.lantushenko.experimental.stub.webtests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lantushenko.experimental.stub.dbunit.utils.DataSets;
import com.lantushenko.experimental.stub.webtests.pages.IndexPage;
import com.lantushenko.experimental.stub.webtests.pages.LoginPage;
import com.lantushenko.experimental.stub.webtests.steps.LoginSteps;

@RunWith(SpringJUnit4ClassRunner.class)
@Category(WebTestAnnotation.class)
public class TestUserLogin extends BaseWebTest {

    private LoginSteps loginSteps;

    @Test
    public void testLogin() throws Exception {
        givenUser();
        whenLoggedInAs(DataSets.User.USER1_LOGIN, DataSets.User.USER1_PASSWORD);
        thenLoginSuccess();
    }

    @Test
    public void testLoginFail() throws Exception {
        givenUser();
        whenLoggedInAs(DataSets.User.USER1_LOGIN, DataSets.User.USER1_PASSWORD + "-wrong");
        thenLoginFailed();
    }

    private void givenUser() throws Exception {
        loginSteps.createUser();
    }

    private void whenLoggedInAs(String login, String password) {
        loginSteps.login(login, password);
    }

    private void thenLoginSuccess() {
        IndexPage page = new IndexPage(getWebTestsContext());
        assertTrue(page.isActive(true));
        assertTrue(page.getTopMenu().isLoggedIn());
    }

    private void thenLoginFailed() {
        LoginPage page = new LoginPage(getWebTestsContext());
        assertTrue(page.isActive(true));
    }

    @Override
    public void before() throws Exception {
        super.before();
        loginSteps = new LoginSteps(getWebTestsContext(), getDataSource());
    }
}
