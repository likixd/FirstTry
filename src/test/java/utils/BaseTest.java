package utils;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Map;

import static com.codeborne.selenide.Configuration.browserSize;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {

    @BeforeEach
    void addListener (){
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeAll
    static void beforeAll(){

        Configuration.baseUrl = "https://demoqa.com";
//        Configuration.browser = "chrome";
//        Configuration.browserVersion = "148.0";
//        Configuration.remote = "https://user1:1234@selenoid.qa.guru/wd/hub";

        browserSize= "1920x1080";
        Configuration.browser = "chrome";
        Configuration.browserVersion = "151.0";
        Configuration.timeout = 15000;
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-dev-shm-usage", "--no-sandbox");
        chromeOptions.setCapability("se:cdpEnabled", false);
        chromeOptions.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = chromeOptions;
        Configuration.remote = "https://user1:1234@selenoid.qa.guru/wd/hub";

    }

    @AfterEach
    void afterAll() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.attachAsText("Some file", "Some content");
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}
