package com.paradise.navisale.component;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.ScrollIntoViewOptions.instant;

public abstract class BaseComponent {

    protected final SelenideElement root;

    public BaseComponent(SelenideElement root) {
        this.root = root;
    }

    public void scrollIfComponentNotView() {
        if (!root.isDisplayed()) {
            root.scrollIntoView(instant());
        }
    }
}
