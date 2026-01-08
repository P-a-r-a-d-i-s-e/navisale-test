package com.paradise.navisale.page;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.paradise.navisale.util.LocatorsUtil.*;

public enum ItemQuantityOption {

    PLUS($(By.xpath(PLUS_ITEM_SPAN_LOCATOR))),
    MINUS($(By.xpath(MINUS_ITEM_SPAN_LOCATOR))),
    DELETE($(By.xpath(DELETE_ITEM_SPAN_LOCATOR)));

    @Getter
    private final SelenideElement itemQuantityOption;

    ItemQuantityOption(SelenideElement selenideElement) {
        this.itemQuantityOption = selenideElement;
    }
}
