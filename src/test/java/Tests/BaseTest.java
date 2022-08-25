package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    protected static final String BASE_URL = "http://automationpractice.com/index.php";
    protected WebDriver driver; // komunikacja z przeglądarką
    protected WebElement element; // obsługa elementów na stronie

    //odpala się przed wszystkimi testami
    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    //odpala się przed każdym testem
    @BeforeEach
    public void setupTest() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
    }

    // odpala się po każdym teście
    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
