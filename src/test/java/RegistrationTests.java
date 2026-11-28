import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import testdata.RandomizedTestData;
import utils.BaseTest;
import static io.qameta.allure.Allure.step;
import static testdata.TestData.*;



public class RegistrationTests extends BaseTest {

    RandomizedTestData randomData = new RandomizedTestData();
    RegistrationPage registrationPage = new RegistrationPage();


    @Test
    @DisplayName("Успешная регистрация FULL с генерацией случайных данных")
    @Owner("Gizatullina Rimma")
    void successfullRegistrationFull_withFaker() {
        step("Open registration page", () ->
            registrationPage.openPage());
        step("Fill registration form", () -> {
            registrationPage.typeFirstName(randomData.firstName)
                    .typeLastName(randomData.lastName)
                    .typeUserEmail(randomData.userEmail)
                    .setGender(randomData.gender)
                    .typeUserNumber(randomData.userNumber)
                    .setDateOfBirth(randomData.dateOfBirthDate, randomData.dateOfBirthMonth, randomData.dateOfBirthYear)
                    .setSubject(randomData.subjects)
                    .setHobbies(randomData.hobbies)
                    .uploadFile(uploadPicture)
                    .typeCurrentAddress(randomData.currentAddress)
                    .setStateAndCity(randomData.state, randomData.city)
                    .submitForm();
                });
        step("Check registration form results",() -> {
            registrationPage.checkModalOpen(modalTitleText)
                    .checkField("Student Name", randomData.firstName + " " + randomData.lastName)
                    .checkField("Student Email", randomData.userEmail)
                    .checkField("Gender", randomData.gender)
                    .checkField("Mobile", randomData.userNumber)
                    .checkField("Date of Birth", randomData.dateOfBirthDate + " " + randomData.dateOfBirthMonth + "," + randomData.dateOfBirthYear)
                    .checkField("Subjects", randomData.subjects)
                    .checkField("Hobbies", randomData.hobbies)
                    .checkField("Address", randomData.currentAddress)
                    .checkField("Picture", uploadPicture)
                    .checkField("State and City", randomData.state + " " + randomData.city);
//        sleep(4000);
        });
    }

    @Test
    @DisplayName("Успешная регистрация FULL с заданными данными")
    void successfullRegistrationFull() {

        registrationPage.openPage()
                .typeFirstName(userName)
                .typeLastName(lastName)
                .typeUserEmail(userEmail)
                .setGender(gender)
                .typeUserNumber(userNumber)
                .setDateOfBirth(dateOfBirthDate, dateOfBirthMonthLetters, dateOfBirthYear)
                .setSubject(subjectsInput)
                .setHobbies(hobbies)
                .uploadFile(uploadPicture)
                .typeCurrentAddress(currentAddress)
                .setStateAndCity(state, city)
                .submitForm();

        registrationPage.checkModalOpen(modalTitleText)
                .checkField("Student Name", userName + " " + lastName)
                .checkField("Student Email", userEmail)
                .checkField("Gender", gender)
                .checkField("Mobile", userNumber)
                .checkField("Date of Birth", dateOfBirthDate + " " + dateOfBirthMonthLetters + "," + dateOfBirthYear )
                .checkField("Subjects", subjectsInput)
                .checkField("Hobbies", hobbies)
                .checkField("Address", currentAddress)
                .checkField("Picture", uploadPicture)
                .checkField("State and City", state + " " + city );

    }

    @Test
    @DisplayName("Успешная регистрация REQUIRED")
    void requiredFieldsSucess(){
        registrationPage.openPage()
                .typeFirstName(userName)
                .typeLastName(lastName)
                .setGender(gender)
                .typeUserNumber(userNumber)
                .submitForm()
                .checkModalOpen(modalTitleText)
                .checkField("Student Name", userName + " " + lastName)
                .checkField("Gender", gender)
                .checkField("Mobile", userNumber);
    }

    @Test
    @DisplayName("Негативный тест. Все пустые")
    void negativeEmpty(){
        registrationPage.openPage()
                .submitForm()
                .checkNegative();
    }

    @Test
    @DisplayName("Негативный тест. Некорректный телефон")
    void negativeIncorrectPhone(){
        registrationPage.openPage()
                .typeLastName(lastName)
                .typeFirstName(userName)
                .setGender(gender)
                .typeIncorrectUserNumber()
                .submitForm()
                .checkNegative();
    }

    @Test
    @DisplayName("Негативный тест. Не выбран пол")
    void negativeEmptyGender(){
        registrationPage.openPage()
                .typeFirstName(userName)
                .typeLastName(lastName)
                .typeUserNumber(userNumber)
                .submitForm()
                .checkNegative();
   }

  @Test
  @DisplayName("Негативный тест. Не введена фамилия")
    void negativeEmptyLastName(){
      registrationPage.openPage()
              .typeFirstName(userName)
              .setGender(gender)
              .typeUserNumber(userNumber)
              .submitForm()
              .checkNegative();
  }

//    @Test
//    void successfullRegistrationFull_old() {
//        open("/automation-practice-form");
//
//        $("#firstName").setValue(userName);
//        $("#lastName").setValue(lastName);
//        $("#userEmail").setValue(userEmail);
//        $("#genterWrapper").$(byText(gender)).click();
//        $("#userNumber").setValue(userNumber);
//
//        $("#dateOfBirthInput").click();
//        $ (".react-datepicker__year-select").selectOption(dateOfBirthYear);
//        $ (".react-datepicker__month-select").selectOptionByValue(dateOfBirthMonth);
//        $$(".react-datepicker__day:not(.react-datepicker__day--outside-month)").findBy(text(dateOfBirthDate)).click();
//
//        $("#hobbiesWrapper").$(byText(hobbies)).click();
//
//        $("#uploadPicture").uploadFromClasspath(uploadPicture);
//
//        $("#currentAddress").setValue(currentAddress);
//        $("#subjectsInput").setValue(subjectsInput).pressEnter();
//        sleep(5000);
//
//        $("#state").scrollTo().click();
//        $("#stateCity-wrapper").$(byText(state)).click();
//        $("#city").click();
//        $("#stateCity-wrapper").$(byText(city)).click();
//
//        $("#submit").scrollTo().click();
//        sleep(6000);
//
//        //Проверки в итоговой таблице
//        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
//        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text(userName + " " + lastName));
//        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text(userEmail));
//        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text(gender));
//        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(userNumber));
//        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text("0" + dateOfBirthDate + " " + dateOfBirthMonthLetters + "," + dateOfBirthYear));
//        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text(subjectsInput));
//        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text(hobbies));
//        $(".table-responsive").$(byText("Address")).parent().shouldHave(text(currentAddress));
//        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text(uploadPicture));
//        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text(state + " " + city));
//
//    }
}
