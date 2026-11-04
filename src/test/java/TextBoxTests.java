import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.TextBoxPage;

import static com.codeborne.selenide.Configuration.*;
import static testdata.TestData.*;

public class TextBoxTests { //extends TestBase???
    TextBoxPage textBoxPage = new TextBoxPage();


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
        textBoxPage.openPage()
                .typeUserName()
                .typeUserEmail()
                .typeUserCurrentAddress()
                .typeUserPermanentAddress()
                .submitForm()
                .checkField("name", userName)
                .checkField("email", userEmail)
                .checkField("currentAddress", currentAddress)
                .checkField("permanentAddress", permanentAddress);
    }

    @Test
    void negativeIncorrectEmail() {

        textBoxPage.openPage()
                .typeIncorrectUserEmail()
                .submitForm()
                .checkNegative();
    }

//    @Test
//    void successfulRegistration_old() {
//
//        $("#userName").setValue(userName);
//        $("#userEmail").setValue(userEmail);
//        $("#currentAddress").setValue(currentAddress);
//        $("#permanentAddress").setValue(permanentAddress);
//        $("#submit").click();
//
//        $("#output").shouldBe(visible);
//        $("#output #name").shouldHave(text(userName));
//        $("#output #email").shouldHave(text(userEmail));
//        $("#output #currentAddress").shouldHave(text(currentAddress));
//        $("#output #permanentAddress").shouldHave(text(permanentAddress));
//        sleep(5000);
//    }

//    @Test
//    void negativeIncorrectEmail_old() {
//        open("/one-page-form/text-box");
//        $("#userName").setValue(userName);
//        $("#userEmail").setValue(incorrectUserEmail);
//        $("#submit").click();
//        $("#output").shouldNotBe(visible);
//        sleep(5000);
//    }
}
