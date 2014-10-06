package com.lantushenko.experimental.stub.webtests.pages;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.lantushenko.experimental.stub.webtests.auxiliary.WebTestsContext;
import com.lantushenko.experimental.stub.webtests.controls.BaseControl;

/**
 * Base class for all application pages. Every page should contain all
 * information related to HTML controls that it holds. Web tests should
 * communicate with HTML elements only through appropriate page implementation
 */
public abstract class BasePage<T extends BasePage<T>> extends BaseControl {

    protected boolean usePageUrlAsRegexp;

    public BasePage(WebTestsContext webTestsContext) {
        super(webTestsContext);
    }

    public String getPageFullUrl() {
        return getWebTestsContext().getBaseUrl() + getPageUrl();
    }

    public T open() {
        getWebDriver().get(getPageFullUrl());
        return (T) this;
    }

    public boolean isActive(boolean ignoreUrlParams) {
        String decodedUrl = decodeUrl(getWebDriver().getCurrentUrl());

        if (ignoreUrlParams && decodedUrl.contains("?")) {
            decodedUrl = decodedUrl.substring(0, decodedUrl.indexOf("?"));
        }

        boolean isActive = false;
        if (usePageUrlAsRegexp) {
            isActive = decodedUrl.matches(getPageUrl());
        } else {
            isActive = decodedUrl.equalsIgnoreCase(getPageFullUrl());
        }

        return isActive;
    }

    public String decodeUrl(final String url) {
        String decodedUrl = url;
        try {
            decodedUrl = URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            getLog().error(decodedUrl, ex);
        }
        return decodedUrl;
    }

    protected abstract String getPageUrl();
}
