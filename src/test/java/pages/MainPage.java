package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;


public class MainPage {
    private SelenideElement
            inputEmail = $$x("//input[@placeholder='Your email']").first(),
            submitTrial = $$x("//button[text()='Start free trial']").first(),
            searchButton = $x("//a[@title='Search']"),
            searchLine = $$x("//input[@placeholder='Find anything (ie. integration, solutions, etc.)']").first();

    public MainPage openPage() {
        open("");

        return this;
    }

    public MainPage setEmail(String value) {
        inputEmail.setValue(value);

        return this;
    }

    public MainPage submit() {
        submitTrial.click();

        return this;
    }

    public MainPage doSearch(String query) {
        int count = 0;
        while (searchLine.is(Condition.hidden) && count < 20) {
            searchButton.click();
            count++;
        }

        searchLine.setValue(query).sendKeys(Keys.ENTER);

        return this;
    }
}
