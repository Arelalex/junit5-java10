package pages;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class SearchPage {

    private SelenideElement
            searchResult = $$x("//a[@class='search-list__item-link']").first();

    public SearchPage checkLink(String link) {
        searchResult.shouldHave(text(link));

        return this;
    }

    public SearchPage checkSearch(List<String> expectedLinks) {
        String resultSelector = "//a[contains(text(),'%s')]";
        expectedLinks.forEach(someText ->
                $x(String.format(resultSelector, someText)).shouldBe(visible));

        return this;
    }
}



