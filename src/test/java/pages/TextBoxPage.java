package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static testdata.TestData.*;

public class TextBoxPage {
    //Elements
    private SelenideElement userNameInput = $("#userName");
    private SelenideElement userEmailInput = $("#userEmail");
    private SelenideElement userCurrentAddressInput = $("#currentAddress");
    private SelenideElement userPermanentAddressInput = $("#permanentAddress");
    private SelenideElement submitButton = $("#submit");
    private SelenideElement outputResults = $("#output");


    //Actions
    public TextBoxPage openPage() {
        open("/one-page-form/text-box");

        return this;
    }

    public TextBoxPage typeUserName () {
        userNameInput.setValue(userName);

        return this;
    }

    public TextBoxPage typeUserEmail () {
        userEmailInput.setValue(userEmail);

        return this;
    }

    public TextBoxPage typeIncorrectUserEmail () {
        userEmailInput.setValue(incorrectUserEmail);

        return this;
    }

    public TextBoxPage typeUserCurrentAddress () {
        userCurrentAddressInput.setValue(currentAddress);

        return this;
    }

    public TextBoxPage typeUserPermanentAddress () {
        userPermanentAddressInput.setValue(permanentAddress);

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

    public TextBoxPage checkNegative() {
        outputResults.shouldNotBe(visible);

        return this;
    }


}
