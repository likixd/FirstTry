package pages;

import com.codeborne.selenide.SelenideElement;
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
    public RegistrationPage openPage() {
        open("/automation-practice-form");

        return this;
    }

    public RegistrationPage typeFirstName (String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public RegistrationPage typeLastName (String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public RegistrationPage typeUserEmail (String value) {
        userEmailInput.setValue(value);

        return this;
    }

    public RegistrationPage setGender (String value) {
        genderContainer.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setDateOfBirth(String date, String month, String year) {
        $("#dateOfBirthInput").click();
        calendar.setDate(date, month, year);
        return this;
    }

    public RegistrationPage typeUserNumber (String value) {
        userNumberInput.setValue(value);

        return this;
    }

    public RegistrationPage typeIncorrectUserNumber () {
        userNumberInput.setValue(incorrectUserNumber);

        return this;
    }

    public RegistrationPage setSubject (String value) {
        userSubjectInput.setValue(value).pressEnter();

        return this;
    }

    public RegistrationPage uploadFile (String value) {
        uploadContainer.uploadFromClasspath(value);

        return this;
    }

    public RegistrationPage typeCurrentAddress (String value) {
        currentAddressInput.setValue(value);

        return this;
    }

    public RegistrationPage setHobbies (String value) {
        hobbiesContainer.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setState (String value) {
        stateSelect.scrollTo().click();
        stateCityContainer.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setCity (String value) {
        citySelect.click();
        stateCityContainer.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setStateAndCity (String state, String city) {
        setState(state);
        setCity(city);

        return this;
    }


    public RegistrationPage submitForm () {
        submitButton.scrollTo().click();

        return this;
    }

    public RegistrationPage checkModalOpen(String value) {
        modalDialog.should(appear);
        modalTitle.shouldHave(text(value));

        return this;
    }

    public RegistrationPage checkNegative() {
        modalDialog.shouldNotBe(visible);

        return this;
    }

    public RegistrationPage checkField(String key, String value) {
        outputTableResults.checkField(key, value);
        return this;
    }

}
