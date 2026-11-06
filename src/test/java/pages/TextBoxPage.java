package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxPage {
    //Elements
    private final SelenideElement userNameInput = $("#userName");
    private final SelenideElement userEmailInput = $("#userEmail");
    private final SelenideElement userCurrentAddressInput = $("#currentAddress");
    private final SelenideElement userPermanentAddressInput = $("#permanentAddress");
    private final SelenideElement submitButton = $("#submit");
    private final SelenideElement outputResults = $("#output");


    //Actions
    public TextBoxPage openPage() {
        open("/one-page-form/text-box");

        return this;
    }

    public TextBoxPage typeUserName (String value) {
        userNameInput.setValue(value);

        return this;
    }

    public TextBoxPage typeUserEmail (String value) {
        userEmailInput.setValue(value);

        return this;
    }

    public TextBoxPage typeUserCurrentAddress (String value) {
        userCurrentAddressInput.setValue(value);

        return this;
    }

    public TextBoxPage typeUserPermanentAddress (String value) {
        userPermanentAddressInput.setValue(value);

        return this;
    }

    public TextBoxPage submitForm () {
        submitButton.click();

        return this;
    }

    public TextBoxPage checkField(String key, String value) {
        outputResults.$(byId(key)).shouldHave(text(value));

        return this;
    }

//    public TextBoxPage typeIncorrectUserEmail (String value) {
//        userEmailInput.setValue(value);
//
//        return this;
//    }
//
//    public TextBoxPage checkNegative() {
//        outputResults.shouldNotBe(visible);
//
//        return this;
//    }


}
