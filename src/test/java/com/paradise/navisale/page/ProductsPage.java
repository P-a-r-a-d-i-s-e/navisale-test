package com.paradise.navisale.page;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.ScrollIntoViewOptions.instant;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.page;
import static com.paradise.navisale.util.LocatorsUtil.PRODUCTS_LIST_LOCATOR;

public class ProductsPage extends BasePage {

    public ProductPage selectProductBy(String productName) {
        $$(PRODUCTS_LIST_LOCATOR)
                .findBy(text(productName))
                .scrollIntoView(instant())
                .click();

        return page(ProductPage.class);
    }
}
