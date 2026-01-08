package com.paradise.navisale;

import com.codeborne.selenide.Selenide;
import com.paradise.navisale.component.HeaderComponent;
import com.paradise.navisale.component.ShoppingCartItemComponent;
import com.paradise.navisale.page.HomePage;
import com.paradise.navisale.page.ProductPage;
import com.paradise.navisale.page.ShoppingCartPage;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertAll;

@TestMethodOrder(MethodOrderer.Random.class)
public class NavisaleTest {

    private HeaderComponent header;

    @BeforeEach
    void setUp() {
        HomePage homepage = new HomePage();
        homepage.open().agreeWithCookies();
        header = homepage.getHeader();
    }

    @Test
    void descriptionAndCharacteristicsSelectedProductMatchDescriptionAndCharacteristicsInShoppingCart() {
        ProductPage productPage = header.openCatalog()
                .selectCategoryBy("Женщинам", "Верхняя одежда",
                        "Пальто", false)
                .selectProductBy("Пальто женское для холодного времени года Tommy Hilfiger 76J4706-200");
        String productDescription = productPage.getProductDescription();
        Map<String, String> characteristicsFromProductDescription = productPage.getCharacteristicsFromProductDescription();

        ShoppingCartPage shoppingCartPage = productPage.addToShoppingCart().goToShoppingCart();
        ShoppingCartItemComponent addedItem = shoppingCartPage.getAddedItemWhenVisible();
        String itemDescription = addedItem.getItemDescription();
        Map<String, String> itemCharacteristics = addedItem.getItemCharacteristics();

        assertAll(
                () -> assertThat(productDescription).contains(itemDescription),
                () -> MatcherAssert.assertThat(characteristicsFromProductDescription.entrySet(),
                        equalTo(itemCharacteristics.entrySet()))
        );
    }

    @Disabled
    @Test
    void totalQuantityAddedProductSameAsTotalQuantityInShoppingCart() {
        header.openCatalog()
                .selectCategoryBy("Мужчинам", "Футболки и майки",
                        "Футболки", false)
                .selectProductBy("Футболка повседневная унисекс The Simpsons SPCTE232F15UA")
                .addToShoppingCart();

        header.openCatalog()
                .selectCategoryBy("Женщинам", "Верхняя одежда",
                        "Пальто", false)
                .selectProductBy("Пальто женское для холодного времени года Tommy Hilfiger 76J4706-200")
                .addToShoppingCart()
                .plusItemToShoppingCart();

        ShoppingCartPage shoppingCartPage = header.openCatalog()
                .selectCategoryBy("Обувь", "Мужская",
                        "Ботинки", false)
                .selectProductBy("Ботинки универсальные унисекс для повседневной носки Native Shoes 31106800-1001")
                .addToShoppingCart()
                .goToShoppingCart();

        int totalQuantityAddedProducts = shoppingCartPage.getAddedProductItems().stream()
                .mapToInt(ShoppingCartItemComponent::getItemQuantity)
                .sum();
        int totalQuantityInShoppingCart = shoppingCartPage.getTotalQuantity();

        assertThat(totalQuantityAddedProducts).isEqualTo(totalQuantityInShoppingCart);
    }

    @Disabled
    @Test
    void forEachProductPriceOnProductPageMatchToPriceInShoppingCartPage() {
        String item1Price = header.openCatalog()
                .selectCategoryBy("Мужчинам", "Брюки",
                        "Карго", false)
                .selectProductBy("Брюки карго мужские повседневные Uniqlo 462318-57")
                .addToShoppingCart()
                .plusItemToShoppingCart()
                .getPrice();

        String item2Price = header.openCatalog()
                .selectCategoryBy("Сумки и чемоданы", "Рюкзаки")
                .selectProductBy("Рюкзак мужской городской кожаный TUMI 147053-1041")
                .addToShoppingCart()
                .getPrice();

        String item3Price = header.openCatalog()
                .selectCategoryBy("Женщинам", "Спортивная одежда",
                        "Футболки и топы", false)
                .selectProductBy("Спортивный жилет женский New Balance WT41280-BK")
                .addToShoppingCart()
                .getPrice();

        ShoppingCartPage shoppingCartPage = header.goToShoppingCart();
        List<String> itemPrices = shoppingCartPage.getAddedProductItems().stream()
                .map(ShoppingCartItemComponent::getItemPrice)
                .toList();

        assertThat(itemPrices).contains(item3Price, item2Price, item1Price);
    }

    @Disabled
    @Test
    void descriptionProductOnProductPageMatchCharacteristicsOnProductPage() {
        ProductPage productPage = header.openCatalog()
                .selectCategoryBy("Мужчинам", "Верхняя одежда",
                        "Пальто", false)
                .selectProductBy("Плащ мужской для повседневной носки Evisu 2ESHTM1CT705FF");

        String productDescription = productPage.getProductDescription();
        Map<String, String> characteristicsFromProductDescription = productPage.getCharacteristicsFromProductDescription();
        Map<String, String> characteristics = productPage.openCharacteristics()
                .uncoverCharacteristics()
                .getCharacteristics();

        assertAll(
                () -> assertThat(productDescription)
                        .contains(String.format("%s %s", characteristics.get("Бренд"), characteristics.get("Артикул"))),
                () -> MatcherAssert.assertThat(new HashSet<>(characteristics.entrySet()),
                        hasItems(characteristicsFromProductDescription.entrySet().toArray()))
        );
    }

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }
}
