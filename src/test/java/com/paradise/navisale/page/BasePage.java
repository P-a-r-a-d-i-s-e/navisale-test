package com.paradise.navisale.page;

import com.paradise.navisale.component.HeaderComponent;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;
import static com.paradise.navisale.util.LocatorsUtil.COOKIES_BUTTON_LOCATOR;

public abstract class BasePage {

    @Getter
    private final HeaderComponent header;

    public BasePage() {
        this.header = HeaderComponent.getHeader();
    }

    public void agreeWithCookies() {
        $(COOKIES_BUTTON_LOCATOR).click();
    }
}
