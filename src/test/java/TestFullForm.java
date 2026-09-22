import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TestFullForm {

    @BeforeAll
    static void beforeAll(){
        Configuration.browserSize= "1920x1080";
    }

    @Test
    void successfullRegistratioonFull() {
        open("https://demoqa.com/automation-practice-form");

        $("[id=firstName]").setValue("Rimma");
        $("[id=lastName]").setValue("Giza");
        $("[id=userEmail]").setValue("test@test.ru");
        $("[id=gender-radio-2]").click();
        $("[id=userNumber]").setValue("1234567890");

        $("[id=dateOfBirthInput]").click();
        $ (".react-datepicker__month-select").selectOptionByValue("2");
        $ (".react-datepicker__year-select").selectOption("1991");
        $$(".react-datepicker__day:not(.react-datepicker__day--outside-month)").findBy(text("10")).click();

        $("[id=hobbies-checkbox-2]").click();
        $("[id=currentAddress]").setValue("Moscow never sleeps");
        $("[id=subjectsInput]").setValue("TESTING123");
        sleep(5000);

        //REGION не получается
        //$(".css-1xc3v61-indicatorContainer").click();
        //$(".react-select-3-live-region").click();
        //$$("div[role='option']").filter(el -> el.getText().equals("Haryana")).click();
        //$(react-select-4-live-region)

        sleep(10000);
        $("[id=submit]").click();
        sleep(6000);


//
//
//        $("[id=output] [id=firstName]").shouldHave(text("Rimma"));
//        $("[id=output] [id=lastName]").shouldHave(text("Giza"));
//        $("[id=output] [id=userEmail]").shouldHave(text("test@test.ru"));
//        $("[id=output] [id=userNumber]").shouldHave(text("1234567890"));
//        $("[id=output] [id=subjectsInput]").shouldHave(text("Testing"));
//        $("[id=output] [id=currentAddress]").shouldHave(text("Moscow never sleeps"));

    }
}
