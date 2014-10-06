package com.lantushenko.experimental.stub.webtests.pages;

import com.lantushenko.experimental.stub.webtests.auxiliary.WebTestsContext;
import com.lantushenko.experimental.stub.webtests.controls.TopMenu;

public class IndexPage extends BasePage<IndexPage> {

    private TopMenu topMenu;

    public IndexPage(WebTestsContext webTestsContext) {
        super(webTestsContext);
        topMenu = new TopMenu(webTestsContext);
    }

    public TopMenu getTopMenu() {
        return topMenu;
    }

    @Override
    protected String getPageUrl() {
        return "/index";
    }

}
