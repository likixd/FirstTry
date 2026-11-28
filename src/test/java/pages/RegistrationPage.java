package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.CalendarComponent;
import pages.components.RegistrationTableComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static testdata.TestData.*;

public class RegistrationPage {
    //Elements
    CalendarComponent calendar = new CalendarComponent();
    //ResultTableComponent resultTable = new ResultTableComponent();
    RegistrationTableComponent outputTableResults = new RegistrationTableComponent();
    private final SelenideElement firstNameInput = $("#firstName");
    private final SelenideElement lastNameInput = $("#lastName");
    private final SelenideElement userEmailInput = $("#userEmail");
    private final SelenideElement genderContainer = $("#genterWrapper");
    private final SelenideElement userNumberInput = $("#userNumber");
    private final SelenideElement userSubjectInput = $("#subjectsInput");
    private final SelenideElement hobbiesContainer = $("#hobbiesWrapper");
    private final SelenideElement uploadContainer = $("#uploadPicture");
    private final SelenideElement currentAddressInput = $("#currentAddress");
    private final SelenideElement stateSelect = $("#state");
    private final SelenideElement citySelect = $("#city");
    private final SelenideElement stateCityContainer = $("#stateCity-wrapper");
    private final SelenideElement submitButton = $("#submit");
    private final SelenideElement modalTitle = $("#example-modal-sizes-title-lg");
    private final SelenideElement modalDialog = $(".modal-dialog");


    //Actions
    @Step("Open registration page /automation-practice-form")
    public RegistrationPage openPage() {
        open("/automation-practice-form");

        return this;
    }

    @Step("Type first name \"{value}\"")
    public RegistrationPage typeFirstName (String value) {
        firstNameInput.setValue(value);

        return this;
    }

    @Step("Type last name \"{value}\"")
    public RegistrationPage typeLastName (String value) {
        lastNameInput.setValue(value);

        return this;
    }

    @Step("Type email \"{value}\"")
    public RegistrationPage typeUserEmail (String value) {
        userEmailInput.setValue(value);

        return this;
    }

    @Step("Set gender \"{value}\"")
    public RegistrationPage setGender (String value) {
        genderContainer.$(byText(value)).click();

        return this;
    }

    @Step("Set birth date \"{date} {month} {year} \"")
    public RegistrationPage setDateOfBirth(String date, String month, String year) {
        $("#dateOfBirthInput").click();
        calendar.setDate(date, month, year);
        return this;
    }

    @Step("Type number \"{value}\"")
    public RegistrationPage typeUserNumber (String value) {
        userNumberInput.setValue(value);

        return this;
    }

    @Step("Type incorrect number")
    public RegistrationPage typeIncorrectUserNumber () {
        userNumberInput.setValue(incorrectUserNumber);

        return this;
    }

    @Step("Set subject \"{value}\"")
    public RegistrationPage setSubject (String value) {
        userSubjectInput.setValue(value).pressEnter();

        return this;
    }

    @Step("Upload file \"{value}\"")
    public RegistrationPage uploadFile (String value) {
        uploadContainer.uploadFromClasspath(value);

        return this;
    }

    @Step("Type current address \"{value}\"")
    public RegistrationPage typeCurrentAddress (String value) {
        currentAddressInput.setValue(value);

        return this;
    }

    @Step("Set hobbies \"{value}\"")
    public RegistrationPage setHobbies (String value) {
        hobbiesContainer.$(byText(value)).click();

        return this;
    }

    @Step("Set state \"{value}\"")
    public RegistrationPage setState (String value) {
        stateSelect.scrollTo().click();
        stateCityContainer.$(byText(value)).click();

        return this;
    }

    @Step("Set city \"{value}\"")
    public RegistrationPage setCity (String value) {
        citySelect.click();
        stateCityContainer.$(byText(value)).click();

        return this;
    }

    @Step("Set state - \"{state}\" and city - \"{city}\"")
    public RegistrationPage setStateAndCity (String state, String city) {
        setState(state);
        setCity(city);

        return this;
    }

    @Step("Submit form")
    public RegistrationPage submitForm () {
        submitButton.scrollTo().click();

        return this;
    }

    @Step("Check registration form results component appears")
    public RegistrationPage checkModalOpen(String value) {
        modalDialog.should(appear);
        modalTitle.shouldHave(text(value));

        return this;
    }

    @Step("Chek negative. Registration component doesn't appear ")
    public RegistrationPage checkNegative() {
        modalDialog.shouldNotBe(visible);

        return this;
    }

    @Step("Check fields \"{key}\" = \"{value}\"")
    public RegistrationPage checkField(String key, String value) {
        outputTableResults.checkField(key, value);
        return this;
    }

}
