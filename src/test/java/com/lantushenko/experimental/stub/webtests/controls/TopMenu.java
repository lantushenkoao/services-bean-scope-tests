package com.lantushenko.experimental.stub.webtests.controls;

import org.openqa.selenium.By;

import com.lantushenko.experimental.stub.webtests.auxiliary.WebTestsContext;

public class TopMenu extends BaseControl {

    private final String LOGOUT_BUTTON_ID = "logOut";

    public TopMenu(WebTestsContext context) {
        super(context);
    }

    public boolean isLoggedIn() {
        return isElementVisible(By.id(LOGOUT_BUTTON_ID));
    }

    public void clickLogoutIfLoggedIn() {
        if (isElementVisible(By.id(LOGOUT_BUTTON_ID))) {
            getWebDriver().findElement(By.id(LOGOUT_BUTTON_ID)).click();
        }
    }

}
