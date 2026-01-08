package com.paradise.navisale.component;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.visible;
import static com.paradise.navisale.util.LocatorsUtil.*;
import static java.util.stream.Collectors.toMap;

public class ShoppingCartItemComponent extends BaseComponent {

    public ShoppingCartItemComponent(SelenideElement root) {
        super(root);
    }

    public String getItemDescription() {
        return root.find(By.xpath(PRODUCT_ITEM_DESCRIPTION_LOCATOR)).getText();
    }

    public Map<String, String> getItemCharacteristics() {
        return Arrays.stream(root.find(By.xpath(PRODUCT_ITEM_CHARACTERISTICS_LOCATOR)).getText().split(","))
                .map(characteristic -> characteristic.trim().split(" "))
                .collect(toMap(characteristics -> characteristics[0],
                        characteristics -> characteristics[1]));
    }

    public String getItemPrice() {
        return root.find(By.xpath(PRODUCT_ITEM_PRICES_LOCATOR)).lastChild().findAll(By.tagName("span"))
                .stream()
                .map(SelenideElement::getText)
                .collect(Collectors.joining(" "));
    }

    public int getItemQuantity() {
        return Integer.parseInt(Objects.requireNonNull(root.find(By.xpath(PRODUCT_ITEM_QUANTITY_LOCATOR))
                .shouldBe(visible).getValue()));
    }
}
