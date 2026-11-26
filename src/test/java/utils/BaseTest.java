package utils;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Configuration.browserSize;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {

    @BeforeAll
    static void beforeAll(){
        browserSize= "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
//        Configuration.browser = "chrome";
//        Configuration.browserVersion = "148.0";
        Configuration.remote = "https://user1:1234@selenoid.qa.guru/wd/hub";
    }

    @AfterEach
    void afterAll() {
        closeWebDriver();
    }
}
