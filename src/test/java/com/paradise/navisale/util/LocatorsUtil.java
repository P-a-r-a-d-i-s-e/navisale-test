package com.paradise.navisale.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LocatorsUtil {

    // Header locators
    public static final String HEADER_LOCATOR = "[data-selector='page-header']";
    public static final String CATALOG_BUTTON_LOCATOR = "[data-selector='header-rubrics-toggler-desktop']";
    public static final String SHOPPING_CART_LOCATOR = "[data-selector='basket-desktop']";

    // Catalog locators
    public static final String CATALOG_MENU_LOCATOR = "[data-selector='rubrics:layout']";
    public static final String CATEGORY_LOCATOR = "[data-selector='rubrics:sidebar-link']";
    public static final String SUBCATEGORY_LIST_LOCATOR = "[data-selector='rubrics:content-block'] [class*='show'] " +
            "[data-selector='rubrics:menu-item']";
    public static final String SUBCATEGORY_MORE_BUTTON_LOCATOR = "[data-selector='rubrics:btn-more']";

    // Homepage locators
    public static final String COOKIES_BUTTON_LOCATOR = "[data-selector='gdpr-banner:btn-accept']";

    // Products page locators
    public static final String PRODUCTS_LIST_LOCATOR = "[id='page-content'] li";

    // Product page locators
    public static final String CHARACTERISTICS_BUTTON_LOCATOR = "[data-tab='properties']";
    public static final String CHARACTERISTICS_LIST_LOCATOR = "[id*='properties'] dl div";
    public static final String UNCOVER_BUTTON_LOCATOR = "//*[contains(text(), 'Раскрыть')]";
    public static final String PRODUCT_DESCRIPTION_LOCATOR = "[class^='order'] h1";
    public static final String COLOR_SIZE_OPTIONS_LIST_LOCATOR = "legend [data-selector*='options-group']";
    public static final String PRODUCT_PRICE_LOCATOR = "[data-selector='priceArea'] span span span";
    public static final String ADD_TO_CART_SPAN_LOCATOR = "//span[contains(text(),'Добавить в корзину')]";
    public static final String GO_TO_CART_SPAN_LOCATOR = "//span[contains(text(),'В корзине')]";
    public static final String PLUS_ITEM_SPAN_LOCATOR = "//span[contains(@style,'plus-outline')]";
    public static final String MINUS_ITEM_SPAN_LOCATOR = "//span[contains(@style,'minus-outline')]";
    public static final String DELETE_ITEM_SPAN_LOCATOR = "//span[contains(@style,'delete-outline')]";

    // Shopping cart page locators
    public static final String PRODUCT_ITEM_LIST_LOCATOR = "[id='basket-root'] ul:first-child li";
    public static final String TOTAL_QUANTITY_LOCATOR = "//div[@id='basket-root']//div[2]/div/div[2]/div/div";

    // Shopping cart item component locators
    public static final String PRODUCT_ITEM_DESCRIPTION_LOCATOR = ".//a";
    public static final String PRODUCT_ITEM_CHARACTERISTICS_LOCATOR = ".//a/following-sibling::div[1]";
    public static final String PRODUCT_ITEM_PRICES_LOCATOR = ".//span[@data-orientation='horizontal']";
    public static final String PRODUCT_ITEM_QUANTITY_LOCATOR = ".//div[contains(@class,'quantity')]//input";
}
