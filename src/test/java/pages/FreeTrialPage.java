package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;


public class FreeTrialPage {
    private SelenideElement
            checkLabel = $x("//h2[text()='Sign up for a free trial']");

    public FreeTrialPage checkTitle(String value) {
        checkLabel.shouldHave(text(value));

        return this;
    }
}