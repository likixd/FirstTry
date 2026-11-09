import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.sleep;
import static testdata.TestData.*;
import static utils.RandomUtils.*;
import com.github.javafaker.Faker;
import java.util.Locale;

public class RegistrationTests {
    RegistrationPage registrationPage = new RegistrationPage();
    Faker fakerEn = new Faker();
    Faker fakerRu = new Faker(new Locale("ru"));

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
    @DisplayName("Успешная регистрация FULL с FAKER")
    void successfullRegistrationFull_withFaker() {

        String userName = fakerRu.name().firstName();
        String lastName = fakerRu.name().lastName();
        String userEmail = getRandomEmail();
        String gender = getRandomGender();
        String currentAddress = fakerEn.address().fullAddress();
        String userNumber = getRandomPhone();
        String hobbies = getRandomHobby();
        String subjects = getRandomSubjects();
        String state = fakerEn.options().option(
                STATE_AND_CITIES.keySet().toArray(new String[0]));
        String city = fakerEn.options().option(
                STATE_AND_CITIES.get(state));
        String[] birthDate = generateBirthDate();
        String dateOfBirthDate = birthDate[0];
        String dateOfBirthMonth = birthDate[1];
        String dateOfBirthYear = birthDate[2];


        registrationPage.openPage()
                .typeFirstName(userName)
                .typeLastName(lastName)
                .typeUserEmail(userEmail)
                .setGender(gender)
                .typeUserNumber(userNumber)
                .setDateOfBirth(dateOfBirthDate, dateOfBirthMonth, dateOfBirthYear)
                .setSubject(subjects)
                .setHobbies(hobbies)
                .uploadFile(uploadPicture)
                .typeCurrentAddress(currentAddress)
                .setStateAndCity(state, city)
                .submitForm();
        sleep(4000);

        registrationPage.checkModalOpen(modalTitleText)
                .checkField("Student Name", userName + " " + lastName)
                .checkField("Student Email", userEmail)
                .checkField("Gender", gender)
                .checkField("Mobile", userNumber)
                .checkField("Date of Birth", dateOfBirthDate + " " + dateOfBirthMonth + "," + dateOfBirthYear )
                .checkField("Subjects", subjects)
                .checkField("Hobbies", hobbies)
                .checkField("Address", currentAddress)
                .checkField("Picture", uploadPicture)
                .checkField("State and City", state + " " + city );
        sleep(4000);




    }

    @Test
    @DisplayName("Успешная регистрация FULL")
    void successfullRegistrationFull() {

        registrationPage.openPage()
                .typeFirstName(userName)
                .typeLastName(lastName)
                .typeUserEmail(userEmail)
                .setGender(gender)
                .typeUserNumber(userNumber)
                .setDateOfBirth(dateOfBirthDate, dateOfBirthMonth, dateOfBirthYear)
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
