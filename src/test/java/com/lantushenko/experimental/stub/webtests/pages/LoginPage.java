package com.lantushenko.experimental.stub.webtests.pages;

import org.openqa.selenium.By;

import com.lantushenko.experimental.stub.webtests.auxiliary.WebTestsContext;

public class LoginPage extends BasePage<LoginPage> {

    private final String INPUT_NAME_LOGIN = "uname";
    private final String INPUT_NAME_PASSWORD = "upwd";
    private final String BUTTON_NAME_SUBMIT = "submit";

    public LoginPage(WebTestsContext webTestsContext) {
        super(webTestsContext);
    }

    public LoginPage setLogin(String name) {
        setInputText(By.name(INPUT_NAME_LOGIN), name);
        return this;
    }

    public LoginPage setPassword(String password) {
        setInputText(By.name(INPUT_NAME_PASSWORD), password);
        return this;
    }

    public void submit() {
        getWebDriver().findElement(By.name(BUTTON_NAME_SUBMIT)).click();
    }

    @Override
    protected String getPageUrl() {
        return "/login";
    }

}
