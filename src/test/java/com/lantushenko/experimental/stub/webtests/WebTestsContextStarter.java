package com.lantushenko.experimental.stub.webtests;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.springframework.util.StringUtils;

import com.lantushenko.experimental.stub.webtests.auxiliary.WebTestsContext;

/**
 * Responsible for running browser and initializing selenium web driver
 */
public class WebTestsContextStarter {

    private WebTestsContext webTestsContext;
    private Properties properties;
    private final String PROPERTY_BASE_URL = "webapp.url";
    private final String PROPERTY_BROWSER_CLASS_NAME = "browser.className";
    private final String PROPERTY_CHROME_BINARIES_PATH = "webdriver.chrome.driver";

    public WebTestsContext getWebTestContext() {
        return webTestsContext;
    }

    public Properties getProperties() {
        return properties;
    }

    public void start() throws Exception {
        properties = loadWebtestProperties();
        String browserClassName = properties.getProperty(PROPERTY_BROWSER_CLASS_NAME);

        if (StringUtils.hasText(properties.getProperty(PROPERTY_CHROME_BINARIES_PATH))) {
            System.setProperty(PROPERTY_CHROME_BINARIES_PATH, properties.getProperty(PROPERTY_CHROME_BINARIES_PATH));
        }

        WebDriver webDriver = (WebDriver) Class.forName(browserClassName).newInstance();
        webDriver.manage().timeouts().implicitlyWait(Settings.DEFAULT_IMPLICITY_WAIT, TimeUnit.MILLISECONDS);
        webDriver.manage().window().setPosition(new Point(0, 0));

        String webappBaseUrl = properties.getProperty(PROPERTY_BASE_URL);

        webTestsContext = new WebTestsContext();
        webTestsContext.setBaseUrl(webappBaseUrl);
        webTestsContext.setWebDriver(webDriver);
    }

    public void stop() throws Exception {
        webTestsContext.getWebDriver().quit();
    }

    private Properties loadWebtestProperties() throws Exception {
        Properties properties = new Properties();
        InputStream resourceAsStream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream("webtests.properties");

        properties.load(resourceAsStream);
        return properties;
    }
}
