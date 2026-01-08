package com.paradise.navisale.page;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class HomePage extends BasePage {

    public HomePage open() {
        Selenide.open("https://navisale.ru");
        getWebDriver().manage().window().maximize();
        return this;
    }
}
