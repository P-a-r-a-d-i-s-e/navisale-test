package com.paradise.navisale.page;

import com.paradise.navisale.component.ShoppingCartItemComponent;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.paradise.navisale.util.LocatorsUtil.PRODUCT_ITEM_LIST_LOCATOR;
import static com.paradise.navisale.util.LocatorsUtil.TOTAL_QUANTITY_LOCATOR;

public class ShoppingCartPage extends BasePage {

    public List<ShoppingCartItemComponent> getAddedProductItems() {
        return $$(PRODUCT_ITEM_LIST_LOCATOR).stream()
                .map(ShoppingCartItemComponent::new)
                .toList();
    }

    public ShoppingCartItemComponent getAddedItemWhenVisible() {
        $(PRODUCT_ITEM_LIST_LOCATOR).shouldBe(visible);
        return getAddedProductItems().getFirst();
    }

    public int getTotalQuantity() {
        return Integer.parseInt($(By.xpath(TOTAL_QUANTITY_LOCATOR)).shouldBe(visible).getText().split(" ")[0]);
    }
}
