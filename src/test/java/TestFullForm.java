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

        $("#firstName").setValue("Rimma");
        $("#lastName").setValue("Giza");
        $("#userEmail").setValue("test@test.ru");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("1234567890");

        $("#dateOfBirthInput").click();
        $ (".react-datepicker__month-select").selectOptionByValue("1");
        $ (".react-datepicker__year-select").selectOption("1991");
        $$(".react-datepicker__day:not(.react-datepicker__day--outside-month)").findBy(text("6")).click();

        $("#hobbiesWrapper").$(byText("Reading")).click();

        $("#uploadPicture").uploadFromClasspath("1.png");

        $("#currentAddress").setValue("Moscow never sleeps");
        $("#subjectsInput").setValue("Computer Science").pressEnter();
        sleep(5000);

        $("#state").scrollTo().click();
        $("#stateCity-wrapper").$(byText("Haryana")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Panipat")).click();

        $("#submit").scrollTo().click();
        sleep(6000);

        //Проверки в итоговой таблице
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text("Rimma Giza"));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text("test@test.ru"));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text("Female"));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text("1234567890"));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text("06 February,1991"));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text("Computer Science"));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text("Reading"));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text("Moscow never sleeps"));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text("1.png"));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text("Haryana Panipat"));

    }
    @Test
    void requiredFieldsSucess(){
        open("/automation-practice-form");

        $("#firstName").setValue("Rimma");
        $("#lastName").setValue("Giza");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("1234567890");
        $("#submit").scrollTo().click();

        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text("Rimma Giza"));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text("Female"));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text("1234567890"));
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

        $("#firstName").setValue("Rimma");
        $("#lastName").setValue("Giza");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("123456789");
        $("#submit").scrollTo().click();
        $("#example-modal-sizes-title-lg").shouldNotBe(visible);
        sleep(5000);
    }

    @Test
    void negativeEmptyGender(){
        open("/automation-practice-form");

        $("#firstName").setValue("Rimma");
        $("#lastName").setValue("Giza");
        $("#userNumber").setValue("1234567890");
        $("#submit").scrollTo().click();
        $("#example-modal-sizes-title-lg").shouldNotBe(visible);
        sleep(5000);
    }

    @Test
    void negativeEmptyLastName(){
        open("/automation-practice-form");

        $("#firstName").setValue("Rimma");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("1234567890");
        $("#submit").scrollTo().click();
        $("#example-modal-sizes-title-lg").shouldNotBe(visible);
        sleep(5000);
    }
}
