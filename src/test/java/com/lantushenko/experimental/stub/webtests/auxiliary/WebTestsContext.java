package com.lantushenko.experimental.stub.webtests.auxiliary;

import org.openqa.selenium.WebDriver;

public class WebTestsContext {

    private WebDriver webDriver;
    private String baseUrl;

    public void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
