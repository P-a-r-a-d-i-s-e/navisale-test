package com.paradise.navisale.component;

import com.codeborne.selenide.SelenideElement;
import com.paradise.navisale.page.ShoppingCartPage;
import com.paradise.navisale.util.LocatorsUtil;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static com.paradise.navisale.util.LocatorsUtil.CATALOG_BUTTON_LOCATOR;
import static com.paradise.navisale.util.LocatorsUtil.SHOPPING_CART_LOCATOR;

public class HeaderComponent extends BaseComponent {

    @Getter
    private static final HeaderComponent header = new HeaderComponent($(LocatorsUtil.HEADER_LOCATOR));

    private HeaderComponent(SelenideElement root) {
        super(root);
    }

    public CatalogComponent openCatalog() {
        scrollIfComponentNotView();
        root.$(CATALOG_BUTTON_LOCATOR).click();
        return CatalogComponent.getCatalogMenu();
    }

    public ShoppingCartPage goToShoppingCart() {
        scrollIfComponentNotView();
        root.$(SHOPPING_CART_LOCATOR).click();
        return page(ShoppingCartPage.class);
    }
}
