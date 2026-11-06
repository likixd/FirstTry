package pages.components;

import com.codeborne.selenide.SelenideElement;
import pages.RegistrationPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationTableComponent {

    private final SelenideElement outputTableResults = $(".table-responsive");



    public RegistrationTableComponent checkField(String key, String value) {
        outputTableResults.$(byText(key)).parent().shouldHave(text(value));

        return this;
    }

}
