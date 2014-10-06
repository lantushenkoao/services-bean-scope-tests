package com.lantushenko.experimental.stub.webtests.steps;

import com.lantushenko.experimental.stub.dbunit.utils.DataSets;
import com.lantushenko.experimental.stub.dbunit.utils.DbUnitDataSource;
import com.lantushenko.experimental.stub.webtests.auxiliary.WebTestsContext;
import com.lantushenko.experimental.stub.webtests.pages.LoginPage;

public class LoginSteps {

    private WebTestsContext context;
    private DbUnitDataSource dbUnitDataSource;
    private LoginPage loginPage;

    public LoginSteps(WebTestsContext context, DbUnitDataSource dbUnitDataSource) {
        this.context = context;
        this.dbUnitDataSource = dbUnitDataSource;
    }

    public void createUser() throws Exception {
        dbUnitDataSource.insert(DataSets.User.DATASET_INSERT_USER1);
    }

    public void login(String login, String password) {
        loginPage = new LoginPage(context).open();
        loginPage.setLogin(login).setPassword(password).submit();
    }
}
