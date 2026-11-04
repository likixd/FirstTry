package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;

import java.util.Calendar;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static testdata.TestData.*;

public class RegistrationPage {
    //Elements
    CalendarComponent calendar = new CalendarComponent();
    private SelenideElement firstNameInput = $("#firstName");
    private SelenideElement lastNameInput = $("#lastName");
    private SelenideElement userEmailInput = $("#userEmail");
    private SelenideElement genderContainer = $("#genterWrapper");
    private SelenideElement userNumberInput = $("#userNumber");
    private SelenideElement userSubjectInput = $("#subjectsInput");
    private SelenideElement hobbiesContainer = $("#hobbiesWrapper");
    private SelenideElement uploadContainer = $("#uploadPicture");
    private SelenideElement currentAddressInput = $("#currentAddress");
    private SelenideElement stateSelect = $("#state");
    private SelenideElement citySelect = $("#city");
    private SelenideElement stateCityContainer = $("#stateCity-wrapper");
    private SelenideElement submitButton = $("#submit");
    private SelenideElement outputTableResults = $(".table-responsive");
    private SelenideElement modalDialog = $(".modal-dialog");
    private SelenideElement modalTitle = $("#example-modal-sizes-title-lg");



    //Actions
    public RegistrationPage openPage() {
        open("/automation-practice-form");

        return this;
    }

    public RegistrationPage typeFirstName () {
        firstNameInput.setValue(userName);

        return this;
    }

    public RegistrationPage typeLastName () {
        lastNameInput.setValue(lastName);

        return this;
    }

    public RegistrationPage typeUserEmail () {
        userEmailInput.setValue(userEmail);

        return this;
    }

    public RegistrationPage setGender () {
        genderContainer.$(byText(gender)).click();

        return this;
    }

    public RegistrationPage setDateOfBirth() {
        $("#dateOfBirthInput").click();
        calendar.setDate(dateOfBirthDate, dateOfBirthMonth, dateOfBirthYear);
        return this;
    }

    public RegistrationPage typeUserNumber () {
        userNumberInput.setValue(userNumber);

        return this;
    }

    public RegistrationPage typeIncorrectUserNumber () {
        userNumberInput.setValue(incorrectUserNumber);

        return this;
    }

    public RegistrationPage setSubject () {
        userSubjectInput.setValue(subjectsInput).pressEnter();

        return this;
    }

    public RegistrationPage uploadFile () {
        uploadContainer.uploadFromClasspath(uploadPicture);

        return this;
    }

    public RegistrationPage typeCurrentAddress () {
        currentAddressInput.setValue(currentAddress);

        return this;
    }

    public RegistrationPage setHobbies () {
        hobbiesContainer.$(byText(hobbies)).click();

        return this;
    }

    public RegistrationPage setState () {
        stateSelect.scrollTo().click();
        stateCityContainer.$(byText(state)).click();

        return this;
    }

    public RegistrationPage setCity () {
        citySelect.click();
        stateCityContainer.$(byText(city)).click();

        return this;
    }

    public RegistrationPage setStateAndCity () {
        setState();
        setCity();

        return this;
    }


    public RegistrationPage submitForm () {
        submitButton.scrollTo().click();

        return this;
    }

    public RegistrationPage checkModalOpen() {
        modalDialog.should(appear);
        modalTitle.shouldHave(text(modalTitleText));

        return this;
    }

    public RegistrationPage checkNegative() {
        modalDialog.shouldNotBe(visible);

        return this;
    }

    public RegistrationPage checkField(String key, String value) {
        outputTableResults.$(byText(key)).parent().shouldHave(text(value));

        return this;
    }

}
