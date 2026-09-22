import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class TestFullForm {

    @BeforeAll
    static void beforeAll(){
        Configuration.browserSize= "1920x1080";
    }

    @Test
    void successfullRegistrationFull() {
        open("https://demoqa.com/automation-practice-form");

        $("[id=firstName]").setValue("Rimma");
        $("[id=lastName]").setValue("Giza");
        $("[id=userEmail]").setValue("test@test.ru");
        $("[id=gender-radio-2]").click();
        $("[id=userNumber]").setValue("1234567890");

        $("[id=dateOfBirthInput]").click();
        $ (".react-datepicker__month-select").selectOptionByValue("1");
        $ (".react-datepicker__year-select").selectOption("1991");
        $$(".react-datepicker__day:not(.react-datepicker__day--outside-month)").findBy(text("6")).click();

        $("[id=hobbies-checkbox-2]").click();

        File file = new File ("/Users/rimmagizatillina/Downloads/1.png");
        $x("//*[@id='uploadPicture']").uploadFile(file);

        $("[id=currentAddress]").setValue("Moscow never sleeps");
        $("[id=subjectsInput]").setValue("Computer Science").pressEnter();
        sleep(5000);

        //REGION не получается, не нашла select
        //$(".css-1xc3v61-indicatorContainer").click();
        //$(".react-select-3-live-region").click();
        //$$("div[role='option']").filter(el -> el.getText().equals("Haryana")).click();
        //$(react-select-4-live-region)

        $("[id=submit]").scrollTo().click();
        sleep(6000);

        //Проверки в итоговой таблице
        $x("//div[contains(text(), 'Thanks for submitting the form')]").shouldBe(visible);
        $x("//td[contains(text(),'Student Name')]/following-sibling::*[1]").shouldHave(text("Rimma Giza"));
        $x("//td[contains(text(),'Student Email')]/following-sibling::*[1]").shouldHave(text("test@test.ru"));
        $x("//td[contains(text(),'Gender')]/following-sibling::*[1]").shouldHave(text("Female"));
        $x("//td[contains(text(),'Mobile')]/following-sibling::*[1]").shouldHave(text("1234567890"));
        $x("//td[contains(text(),'Date of Birth')]/following-sibling::*[1]").shouldHave(text("06 February,1991"));
        $x("//td[contains(text(),'Subjects')]/following-sibling::*[1]").shouldHave(text("Computer Science"));
        $x("//td[contains(text(),'Hobbies')]/following-sibling::*[1]").shouldHave(text("Reading"));
        $x("//td[contains(text(),'Address')]/following-sibling::*[1]").shouldHave(text("Moscow never sleeps"));
        $x("//td[contains(text(),'Picture')]/following-sibling::*[1]").shouldHave(text("1.png"));

    }
    @Test
    void requiredFieldsSucess(){
        open("https://demoqa.com/automation-practice-form");

        $("[id=firstName]").setValue("Rimma");
        $("[id=lastName]").setValue("Giza");
        $("[id=gender-radio-2]").click();
        $("[id=userNumber]").setValue("1234567890");
        $("[id=submit]").scrollTo().click();

        $x("//td[contains(text(),'Student Name')]/following-sibling::*[1]").shouldHave(text("Rimma Giza"));
        $x("//td[contains(text(),'Gender')]/following-sibling::*[1]").shouldHave(text("Female"));
        $x("//td[contains(text(),'Mobile')]/following-sibling::*[1]").shouldHave(text("1234567890"));
        sleep(5000);
    }

    @Test
    void negativeEmpty(){
        open("https://demoqa.com/automation-practice-form");
        $("[id=submit]").scrollTo().click();
        $x("//div[class='modal-title h4'][contains(text(),'Thanks for submitting the form')]").shouldNotBe(visible);
        sleep(5000);
    }

    @Test
    void negativeIncorrectPhone(){
        open("https://demoqa.com/automation-practice-form");

        $("[id=firstName]").setValue("Rimma");
        $("[id=lastName]").setValue("Giza");
        $("[id=gender-radio-2]").click();
        $("[id=userNumber]").setValue("123456789");
        $("[id=submit]").scrollTo().click();
        $x("//div[contains(text(), 'Thanks for submitting the form')]").shouldNotBe(visible);
        sleep(5000);
    }

    @Test
    void negativeEmptyGender(){
        open("https://demoqa.com/automation-practice-form");

        $("[id=firstName]").setValue("Rimma");
        $("[id=lastName]").setValue("Giza");
        $("[id=userNumber]").setValue("1234567890");
        $("[id=submit]").scrollTo().click();
        $x("//div[contains(text(), 'Thanks for submitting the form')]").shouldNotBe(visible);
        sleep(5000);
    }

    @Test
    void negativeEmptyLastName(){
        open("https://demoqa.com/automation-practice-form");

        $("[id=firstName]").setValue("Rimma");
        $("[id=gender-radio-2]").click();
        $("[id=userNumber]").setValue("1234567890");
        $("[id=submit]").scrollTo().click();
        $x("//div[contains(text(), 'Thanks for submitting the form')]").shouldNotBe(visible);
        sleep(5000);
    }
}
