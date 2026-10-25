import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static testdata.TestData.*;

public class TestFullForm {

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
    void successfullRegistrationFull() {
        open("/automation-practice-form");

        $("#firstName").setValue(userName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(userNumber);

        $("#dateOfBirthInput").click();
        $ (".react-datepicker__year-select").selectOption(dateOfBirthYear);
        $ (".react-datepicker__month-select").selectOptionByValue(dateOfBirthMonth);
        $$(".react-datepicker__day:not(.react-datepicker__day--outside-month)").findBy(text(dateOfBirthDate)).click();

        $("#hobbiesWrapper").$(byText(hobbies)).click();

        $("#uploadPicture").uploadFromClasspath(uploadPicture);

        $("#currentAddress").setValue(currentAddress);
        $("#subjectsInput").setValue(subjectsInput).pressEnter();
        sleep(5000);

        $("#state").scrollTo().click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();

        $("#submit").scrollTo().click();
        sleep(6000);

        //Проверки в итоговой таблице
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text(userName + " " + lastName));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text(userEmail));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text(gender));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(userNumber));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text("0" + dateOfBirthDate + " " + dateOfBirthMonthLetters + "," + dateOfBirthYear));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text(subjectsInput));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text(hobbies));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text(currentAddress));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text(uploadPicture));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text(state + " " + city));

    }
    @Test
    void requiredFieldsSucess(){
        open("/automation-practice-form");

        $("#firstName").setValue(userName);
        $("#lastName").setValue(lastName);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(userNumber);
        $("#submit").scrollTo().click();

        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text(userName + " " + lastName));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text(gender));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(userNumber));
        sleep(5000);
    }

    @Test
    void negativeEmpty(){
        open("/automation-practice-form");
        $("#submit").scrollTo().click();
        $("#example-modal-sizes-title-lg").shouldNotBe(visible);
        sleep(5000);
    }

    @Test
    void negativeIncorrectPhone(){
        open("/automation-practice-form");

        $("#firstName").setValue(userName);
        $("#lastName").setValue(lastName);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(incorrectUserNumber);
        $("#submit").scrollTo().click();
        $("#example-modal-sizes-title-lg").shouldNotBe(visible);
        sleep(5000);
    }

    @Test
    void negativeEmptyGender(){
        open("/automation-practice-form");

        $("#firstName").setValue(userName);
        $("#lastName").setValue(lastName);
        $("#userNumber").setValue(userNumber);
        $("#submit").scrollTo().click();
        $("#example-modal-sizes-title-lg").shouldNotBe(visible);
        sleep(5000);
    }

    @Test
    void negativeEmptyLastName(){
        open("/automation-practice-form");

        $("#firstName").setValue(userName);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(userNumber);
        $("#submit").scrollTo().click();
        $("#example-modal-sizes-title-lg").shouldNotBe(visible);
        sleep(5000);
    }
}
