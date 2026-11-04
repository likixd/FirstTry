import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import static com.codeborne.selenide.Configuration.*;
import static testdata.TestData.*;

public class RegistrationTests {
    RegistrationPage registrationPage = new RegistrationPage();

    @BeforeAll
    static void beforeAll(){
        browserSize= "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }
//оставила для себя, закомментировала потому что запускаться с ним не нравится, так как сайт открывается через раз
//    @AfterEach
//    void afterAll() {
//        closeWebDriver();
//    }

    @Test
    @DisplayName("Успешная регистрация FULL")
    void successfullRegistrationFull() {

        registrationPage.openPage()
                .typeFirstName()
                .typeLastName()
                .typeUserEmail()
                .setGender()
                .typeUserNumber()
                .setDateOfBirth()
                .setSubject()
                .setHobbies()
                .uploadFile()
                .typeCurrentAddress()
                .setStateAndCity()
                .submitForm()
                .checkModalOpen()
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
                .typeFirstName()
                .typeLastName()
                .setGender()
                .typeUserNumber()
                .submitForm()
                .checkModalOpen()
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
                .typeLastName()
                .typeFirstName()
                .setGender()
                .typeIncorrectUserNumber()
                .submitForm()
                .checkNegative();
    }

    @Test
    @DisplayName("Негативный тест. Не выбран пол")
    void negativeEmptyGender(){
        registrationPage.openPage()
                .typeFirstName()
                .typeLastName()
                .typeUserNumber()
                .submitForm()
                .checkNegative();
   }

  @Test
  @DisplayName("Негативный тест. Не введена фамилия")
    void negativeEmptyLastName(){
      registrationPage.openPage()
              .typeFirstName()
              .setGender()
              .typeUserNumber()
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
