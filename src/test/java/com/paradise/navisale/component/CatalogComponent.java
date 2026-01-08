package com.paradise.navisale.component;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.paradise.navisale.page.ProductsPage;
import lombok.Getter;
import org.openqa.selenium.By;

import java.util.Map;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.ScrollIntoViewOptions.instant;
import static com.codeborne.selenide.Selenide.*;
import static com.paradise.navisale.util.LocatorsUtil.*;
import static java.util.stream.Collectors.toMap;

public class CatalogComponent extends BaseComponent {

    @Getter
    private static final CatalogComponent catalogMenu = new CatalogComponent($(CATALOG_MENU_LOCATOR));

    private final ElementsCollection categories = $$(CATEGORY_LOCATOR);
    private final ElementsCollection subcategories = $$(SUBCATEGORY_LIST_LOCATOR);

    private CatalogComponent(SelenideElement root) {
        super(root);
    }

    public ProductsPage selectCategoryBy(String categoryName, String subcategoryName) {
        hoverMouseOverCategory(categoryName);

        Map<String, SelenideElement> subcategoriesMap = subcategories.stream()
                .collect(toMap(subcategory -> subcategory.find(By.tagName("a")).getText(),
                        subcategory -> subcategory.find(By.tagName("a"))));

        subcategoriesMap.get(subcategoryName)
                .scrollIntoView(instant())
                .click();

        return page(ProductsPage.class);
    }

    public ProductsPage selectCategoryBy(String categoryName, String subcategoryName, String itemName) {
        hoverMouseOverCategory(categoryName);

        Map<String, ElementsCollection> subcategoriesMap = subcategories.stream()
                .peek(this::checkMoreButtonForSubcategoryAndClickIfExists)
                .collect(toMap(subcategory -> subcategory.find(By.tagName("a")).getText(),
                        subcategory -> subcategory.findAll("ul a")));

        SelenideElement item = subcategoriesMap.get(subcategoryName)
                .findBy(attribute("title", itemName));
        item.scrollIntoView(instant()).click();

        return page(ProductsPage.class);
    }

    private void checkMoreButtonForSubcategoryAndClickIfExists(SelenideElement subcategory) {
        if (subcategory.$(SUBCATEGORY_MORE_BUTTON_LOCATOR).exists()) {
            subcategory.$(SUBCATEGORY_MORE_BUTTON_LOCATOR)
                    .scrollIntoView(instant());
            subcategory.$(SUBCATEGORY_MORE_BUTTON_LOCATOR).click();
        }
    }

    private void hoverMouseOverCategory(String categoryName) {
        SelenideElement selectedCategory = categories.findBy(attribute("title", categoryName));
        actions().moveToElement(selectedCategory.shouldBe(visible)).perform();
    }
}
