import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class FirstTest {


    @BeforeAll
    static void beforeAll(){
        Configuration.browserSize= "1920x1080";
    }


    @Test
    void successfulRegistration() {
        open("https://qa-guru.github.io/one-page-form/text-box.html");

        $("[id=userName]").setValue("Rimma");
        $("[id=userEmail]").setValue("wow@bk.ru");
        $("[id=currentAddress]").setValue("test 123");
        $("[id=permanentAddress]").setValue("test 456,.789!");
        $("[id=submit]").click();

        $("[id=output] [id=name]").shouldHave(text("Rimma"));
        $("[id=output] [id=email]").shouldHave(text("wow@bk.ru"));
        $("[id=output] [id=currentAddress]").shouldHave(text("test 123"));
        $("[id=output] [id=permanentAddress]").shouldHave(text("test 456,.789!"));
    }

//    @Test
//    void negativeTestNullName() {
//        open("https://qa-guru.github.io/one-page-form/text-box.html");
//
//        //$("[id=userName]").setValue("Rimma");
//        $("[id=userEmail]").setValue("wow@bk.ru");
//        $("[id=currentAddress]").setValue("test 123");
//        $("[id=permanentAddress]").setValue("test 456,.789!");
//        $("[id=submit]").click();
//
//        $("[id=output] [id=name]").shouldHave(text("Rimma"));
//        $("[id=output] [id=email]").shouldHave(text("wow@bk.ru"));
//        $("[id=output] [id=currentAddress]").shouldHave(text("test 123"));
//        $("[id=output] [id=permanentAddress]").shouldHave(text("test 456,.789!"));
//    }
}
