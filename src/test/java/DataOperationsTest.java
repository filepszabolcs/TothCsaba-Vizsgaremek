import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.example.AboutPage;
import org.example.LandingPage;
import org.example.LoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


//import org.junit.Test;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DataOperationsTest {

    WebDriver driver;
    LoginPage loginPage;
    LandingPage landingPage;
    AboutPage aboutPage;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = BaseTest.getWebDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //loginPage = new LoginPage(driver);
    }

    @Test
    @Order(1)
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Read Expertise Data On About Page Test")
    public void DataReadTest1 () {
        loginPage = new LoginPage(driver);
        landingPage = new LandingPage(driver);
        aboutPage = new AboutPage(driver);
        String validUserName = "beckz";
        String validPassword = "30y123";
        loginPage.Navigate();
        loginPage.AcceptTermsAndConditions();
        loginPage.LoginFunction(validUserName, validPassword);
        Assertions.assertEquals("https://lennertamas.github.io/roxo/landing.html", landingPage.GetURL());
        landingPage.GoToAbout();
        String actual = aboutPage.getExpertiseData();
        String expected = "Customer Experience Design, Digital Products, Development, Campaign & Content, " +
                "Employer Branding, Animation & Motion Graphics, Packaging & Product Design, " +
                "Retail & Spacial, Print & Editorial Design, Concept/Text, Information Design";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Order(2)
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Read Members Data On About Page Test")
    public void DataReadTest2 () throws InterruptedException {
        loginPage = new LoginPage(driver);
        landingPage = new LandingPage(driver);
        aboutPage = new AboutPage(driver);
        String validUserName = "beckz";
        String validPassword = "30y123";
        loginPage.Navigate();
        loginPage.AcceptTermsAndConditions();
        loginPage.LoginFunction(validUserName, validPassword);
        Assertions.assertEquals("https://lennertamas.github.io/roxo/landing.html", landingPage.GetURL());
        landingPage.GoToAbout();
        List<String> actual = aboutPage.getMembers();
        List<String> expected = new ArrayList<>();
        expected.add("PABLO ESCOBAR - Creative Director");
        expected.add("MONTINO RIAU - Product Manager");
        expected.add("ALEX NAASRI - Chief Design Officer");
        expected.add("HONGMAN CHIOA - UX Researcher");
        expected.add("SANTIO ANDRESS - Content Researcher");
        expected.add("RAMESH PAUL - Creative Designer");
        Assertions.assertEquals(expected, actual);
    }
    @AfterEach
    public void TearDown () {
        driver.quit();
    }
}