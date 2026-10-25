import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.*;
import static testdata.TestData.*;

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

        $("#userName").setValue(userName);
        $("#userEmail").setValue(userEmail);
        $("#currentAddress").setValue(currentAddress);
        $("#permanentAddress").setValue(permanentAddress);
        $("#submit").click();

        $("#output").shouldBe(visible);
        $("#output #name").shouldHave(text(userName));
        $("#output #email").shouldHave(text(userEmail));
        $("#output #currentAddress").shouldHave(text(currentAddress));
        $("#output #permanentAddress").shouldHave(text(permanentAddress));
        sleep(5000);
    }

    @Test
    void negativeIncorrectEmail() {
        open("/one-page-form/text-box");

        $("#userName").setValue(userName);
        $("#userEmail").setValue(incorrectUserEmail);
        $("#submit").click();

        $("#output").shouldNotBe(visible);
        sleep(5000);
    }


}
