import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import pages.WorkWithSiteMine;

import java.util.List;
import java.util.stream.Stream;

public class RequestTrialAndSearchTest extends TestBase {
    WorkWithSiteMine workWithSiteMine = new WorkWithSiteMine();

    @ValueSource(strings = {"alex1@gmail.com", "alex2@gmail.com", "alex3@gmail.com"})
    @ParameterizedTest(name = "Request Start Trial for {0}")
    @Tags({@Tag("Regress"), @Tag("Smoke")})
    void successfulRequestStartTrial(String email) {
        workWithSiteMine.openPage()
                        .setEmail(email)
                        .submit()
                        .checkTitle("Sign up for a free trial");
    }

    @CsvSource(value = {
            "https://www.signnow.com/features/create-templates-of-your-documents, template",
            "https://www.signnow.com/, signature",
            "https://www.signnow.com/developers/features/add-magic-fields-using-api, magic"
    })
    @ParameterizedTest(name = "Look for link {0} for query {1}")
    @Tag("Smoke")
    void checkSearchFromSource(String expectedLink, String searchQuery) {
        workWithSiteMine.openPage()
                        .doSearch(searchQuery)
                        .checkLink(expectedLink);
    }

   @CsvFileSource(resources = "/searchData.csv")
   @ParameterizedTest(name = "Look for link {0} for query {1}")
   @Tag("Regress")
   void checkSearchFromFile(String expectedLink, String searchQuery) {
       workWithSiteMine.openPage()
               .doSearch(searchQuery)
               .checkLink(expectedLink);
   }

    static Stream<Arguments> checkSearchFromStream(){
        return Stream.of(
                Arguments.of("import", List.of(
                        "Learn how to import fillable fields to your document | signNow",
                        "Import Signature | airSlate SignNow",
                        "Import Initial Royalty Agreement Template | signNow")),
                Arguments.of("magic", List.of(
                        "Add magic fields on a document to be eSigned using API | signNow",
                        "Native Wyvern Magic eSignature | signNow",
                        "Trigonometric Identities Magic Square Answer Key Form - Fill Out ..."))
        );
    }
    @MethodSource
    @ParameterizedTest(name = "Check results {1} for query {0}")
    @Tag("Regress")
    void checkSearchFromStream(String searchQuery, List<String> expectedLinks) {
        workWithSiteMine.openPage()
                .doSearch(searchQuery)
                .checkSearch(expectedLinks);
    }
}
