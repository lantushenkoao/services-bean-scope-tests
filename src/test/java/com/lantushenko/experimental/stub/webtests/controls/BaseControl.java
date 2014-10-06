package com.lantushenko.experimental.stub.webtests.controls;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.lantushenko.experimental.stub.webtests.Settings;
import com.lantushenko.experimental.stub.webtests.auxiliary.WebTestsContext;

/**
 * Base class for all page elements including page itself
 */
public class BaseControl {

    private Log log = LogFactory.getLog(this.getClass());
    private static final String SCRIPT_JQUERY_AJAX_COMPLETED = "return jQuery.active == 0";
    private final WebTestsContext context;

    public BaseControl(WebTestsContext context) {
        this.context = context;
    }

    public WebDriver getWebDriver() {
        return this.context.getWebDriver();
    }

    public WebTestsContext getWebTestsContext() {
        return this.context;
    }

    public Log getLog() {
        return log;
    }

    protected void setInputText(By by, String text) {
        WebElement input = getWebDriver().findElement(by);
        input.sendKeys(Keys.chord(Keys.CONTROL, "a"), text);
    }

    protected boolean isElementVisible(By by) {
        setWebDriverImplicityWait(Settings.SHORT_IMPLICITY_WAIT);
        try {
            WebElement element = getWebDriver().findElement(by);
            return element.isDisplayed();
        } catch (NoSuchElementException nse) {
            return false;
        } finally {
            setWebDriverImplicityWait(Settings.DEFAULT_IMPLICITY_WAIT);
        }

    }

    public void setWebDriverImplicityWait(int timeoutMs) {
        getWebDriver().manage().timeouts().implicitlyWait(timeoutMs, TimeUnit.MILLISECONDS);
    }

    protected boolean isAjaxRequestsComleted(final String script) {
        boolean comleted = true;
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) getWebDriver();
        try {
            comleted = (Boolean) javascriptExecutor.executeScript(script);
        } catch (WebDriverException ex) {
            // if library based on the script isn't found we return true
        }
        return comleted;
    }
}
