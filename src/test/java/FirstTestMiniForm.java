import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class FirstTestMiniForm {


    @BeforeAll
    static void beforeAll(){
        Configuration.browserSize= "1920x1080";
    }


    @Test
    void successfulRegistration() {
        open("https://qa-guru.github.io/one-page-form/text-box.html");

        $("[id=userName]").setValue("Rimma");
        $("[id=userEmail]").setValue("test@qagu.ru");
        $("[id=currentAddress]").setValue("test 123");
        $("[id=permanentAddress]").setValue("test 456,.789!");
        $("[id=submit]").click();

        $x("//div[@id='output']").shouldBe(visible);
        $("[id=output] [id=name]").shouldHave(text("Rimma"));
        $("[id=output] [id=email]").shouldHave(text("test@qagu.ru"));
        $("[id=output] [id=currentAddress]").shouldHave(text("test 123"));
        $("[id=output] [id=permanentAddress]").shouldHave(text("test 456,.789!"));
        sleep(5000);
    }

    @Test
    void negativeIncorrectEmail() {
        open("https://qa-guru.github.io/one-page-form/text-box.html");

        $("[id=userName]").setValue("Rimma");
        $("[id=userEmail]").setValue("testqa");
        $("[id=submit]").click();

        $x("//div[@id='output']").shouldNotBe(visible);
        sleep(5000);
    }


}
