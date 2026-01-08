package com.paradise.navisale.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.ScrollIntoViewOptions.instant;
import static com.codeborne.selenide.Selenide.*;
import static com.paradise.navisale.util.LocatorsUtil.*;
import static java.util.stream.Collectors.toMap;

public class ProductPage extends BasePage {

    public String getProductDescription() {
        return $(PRODUCT_DESCRIPTION_LOCATOR).getText();
    }

    public Map<String, String> getCharacteristicsFromProductDescription() {
        Pattern pattern = Pattern.compile("(Цвет|Размер): *([^;]+)");
        Matcher matcher = pattern.matcher(getProductDescription());
        HashMap<String, String> characteristics = new HashMap<>();

        while (matcher.find()) {
            characteristics.put(matcher.group(1).trim(), matcher.group(2).trim());
        }

        return characteristics;
    }

    public Map<String, String> getColorSizeOptions() {
        return $$(COLOR_SIZE_OPTIONS_LIST_LOCATOR).stream()
                .peek(selenideElement -> selenideElement.scrollIntoView(instant()))
                .collect(toMap(colorSizeOption -> colorSizeOption.parent().find(By.tagName("span")).getText(),
                        SelenideElement::getText));
    }

    public ProductPage addToShoppingCart() {
        actions().moveToElement($(By.xpath(ADD_TO_CART_SPAN_LOCATOR)).shouldBe(visible)).click().perform();
        return this;
    }

    public ProductPage changeItemQuantity(ItemQuantityOption itemQuantityOption) {
        SelenideElement quantityOption = itemQuantityOption.getItemQuantityOption();

        quantityOption.shouldBe(Condition.interactable);
        actions().moveToElement(quantityOption).click().perform();
        return this;
    }

    public ShoppingCartPage goToShoppingCart() {
        SelenideElement goToCart = $(By.xpath(GO_TO_CART_SPAN_LOCATOR));

        goToCart.shouldBe(Condition.visible);
        actions().moveToElement(goToCart).click().perform();
        return page(ShoppingCartPage.class);
    }

    public String getPrice() {
        return $$(PRODUCT_PRICE_LOCATOR).stream()
                .map(SelenideElement::getText)
                .collect(Collectors.joining(" "));
    }

    public ProductPage openCharacteristics() {
        $(CHARACTERISTICS_BUTTON_LOCATOR).scrollIntoView(instant()).click();
        return this;
    }

    public ProductPage uncoverCharacteristics() {
        $(By.xpath(UNCOVER_BUTTON_LOCATOR)).click();
        return this;
    }

    public Map<String, String> getCharacteristics() {
        return $$(CHARACTERISTICS_LIST_LOCATOR).stream()
                .collect(toMap(characteristic -> characteristic.find(By.tagName("span")).getText(),
                        characteristic -> characteristic.find(By.tagName("dd")).getText()));
    }
}
