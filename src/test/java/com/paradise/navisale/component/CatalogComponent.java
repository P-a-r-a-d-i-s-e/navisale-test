package com.paradise.navisale.component;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.paradise.navisale.page.ProductsPage;
import lombok.Getter;
import org.openqa.selenium.By;

import java.util.Optional;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.ScrollIntoViewOptions.instant;
import static com.codeborne.selenide.Selenide.*;
import static com.paradise.navisale.util.LocatorsUtil.*;

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

        Optional<SelenideElement> maybeSubcategory = subcategories.stream()
                .filter(subcategory -> subcategory.find(By.tagName("a")).getText().equals(subcategoryName))
                .map(subcategory -> subcategory.find(By.tagName("a")))
                .findFirst();

        maybeSubcategory.ifPresent(subcategory -> subcategory.scrollIntoView(instant()).click());

        return page(ProductsPage.class);
    }

    public ProductsPage selectCategoryBy(String categoryName, String subcategoryName,
                                         String itemName, boolean openMore) {
        hoverMouseOverCategory(categoryName);

        Optional<SelenideElement> maybeItem = subcategories.stream()
                .filter(subcategory -> subcategory.find(By.tagName("a")).getText().equals(subcategoryName))
                .peek(it -> this.checkMoreButtonForSubcategoryAndClickIfExists(it, openMore))
                .map(subcategory -> subcategory.findAll("ul a"))
                .map(items -> items.findBy(attribute("title", itemName)))
                .findFirst();

        maybeItem.ifPresent(item -> item.scrollIntoView(instant()).click());

        return page(ProductsPage.class);
    }

    private void checkMoreButtonForSubcategoryAndClickIfExists(SelenideElement subcategory, boolean openMore) {
        SelenideElement moreButton = subcategory.$(SUBCATEGORY_MORE_BUTTON_LOCATOR);
        if (openMore && moreButton.exists()) {
            moreButton.scrollIntoView(instant()).click();
        }
    }

    private void hoverMouseOverCategory(String categoryName) {
        SelenideElement selectedCategory = categories.findBy(attribute("title", categoryName));
        actions().moveToElement(selectedCategory.shouldBe(visible)).perform();
    }
}
