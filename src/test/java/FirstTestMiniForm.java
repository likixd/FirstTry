import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.*;

public class FirstTestMiniForm {


    @BeforeAll
    static void beforeAll(){
        browserSize= "1920x1080";
        baseUrl = "https://qa-guru.github.io";
    }
//оставила для себя, закомментировала потому что запускаться с ним не нравится, так как сайт открывается через раз
//    @AfterEach
//    void afterAll() {
//        closeWebDriver();
//    }

    @Test
    void successfulRegistration() {
        open("/one-page-form/text-box");

        $("#userName").setValue("Rimma");
        $("#userEmail").setValue("test@qagu.ru");
        $("#currentAddress").setValue("test 123");
        $("#permanentAddress").setValue("test 456,.789!");
        $("#submit").click();

        $("#output").shouldBe(visible);
        $("#output #name").shouldHave(text("Rimma"));
        $("#output #email").shouldHave(text("test@qagu.ru"));
        $("#output #currentAddress").shouldHave(text("test 123"));
        $("#output #permanentAddress").shouldHave(text("test 456,.789!"));
        sleep(5000);
    }

    @Test
    void negativeIncorrectEmail() {
        open("/one-page-form/text-box");

        $("#userName").setValue("Rimma");
        $("#userEmail").setValue("testqa");
        $("#submit").click();

        $("#output").shouldNotBe(visible);
        sleep(5000);
    }


}
