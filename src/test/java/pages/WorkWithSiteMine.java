package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class WorkWithSiteMine {
    SelenideElement
            inputEmail = $$x("//input[@placeholder='Your email']").first(),
            submitTrial = $$x("//button[text()='Start free trial']").first(),
            checkLabel = $x("//h2[text()='Sign up for a free trial']"),
            searchButton = $x("//a[@title='Search']"),
            searchLine = $$x("//input[@placeholder='Find anything (ie. integration, solutions, etc.)']").first(),
            searchResult = $$x("//a[@class='search-list__item-link']").first();

    public WorkWithSiteMine openPage() {
        open("");

        return this;
    }

    public WorkWithSiteMine setEmail(String value) {
        inputEmail.setValue(value);

        return this;
    }

    public WorkWithSiteMine submit() {
        submitTrial.click();

        return this;
    }

    public WorkWithSiteMine checkTitle(String value) {
        checkLabel.shouldHave(text(value));

        return this;
    }

    public WorkWithSiteMine doSearch(String query) {
        int count = 0;
        while (searchLine.is(Condition.hidden) && count < 20) {
            searchButton.click();
            count++;
        }

        searchLine.setValue(query).sendKeys(Keys.ENTER);

        return this;
    }

    public WorkWithSiteMine checkLink(String link) {
        searchResult.shouldHave(text(link));

        return this;
    }

    public WorkWithSiteMine checkSearch(List<String> expectedLinks) {
        String resultSelector = "//a[contains(text(),'%s')]";
        expectedLinks.forEach(someText ->
                $x(String.format(resultSelector, someText)).shouldBe(visible));

        return this;
    }
}
