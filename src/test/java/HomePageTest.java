import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;


public class HomePageTest {

    private WebDriver driver; // komunikacja z przeglądarką
    private WebElement element; // obsługa elementów na stronie
    private String x;

    //odpala się przed wszystkimi testami
    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    //odpala się przed każdym testem
    @BeforeEach
    public void setupTest() {
        driver = new ChromeDriver();
    }

    // odpala się po każdym teście
    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void shouldReturnCorrectPageTitle() {
        driver.get("http://automationpractice.pl/index.php");
        System.out.println(driver.getTitle());
        Assertions.assertThat(driver.getTitle()).isEqualTo("My Store");
    }

    @Test
    public void shouldSeeBestSellerItemsOnHomePAge(){
        driver.get("http://automationpractice.com/index.php");

//        List<WebElement> productNamesByCssSelector = driver.findElements(By.cssSelector("#blockbestsellers .product-name"));

        List<WebElement> productNamesByCssSelector = driver.findElements(By.cssSelector("#homefeatured .product-name"));

//        List<WebElement> productNamesByXpath = driver.findElements(By.xpath("//*[@id='blockbestsellers']//*[@class='product-name']"));

        
    for (WebElement productName : productNamesByCssSelector){
        System.out.println("productName.getText = " + productName.getText());
    }

    //Pierwszy sposób na sprawdzenie, czy są puste nazwy
//        boolean anyProductHasEmptyName = productNamesByCssSelector.stream()
//                .anyMatch(el -> el.getText().isEmpty());
//
//        Assertions.assertThat(anyProductHasEmptyName).isFalse();

    //Drugi sposób na sprawdzenie, czy są puste nazwy
        List<WebElement> productsWithEmptyName = productNamesByCssSelector.stream()
                .filter(el -> el.getText().isEmpty())
                .collect(Collectors.toList());

    Assertions.assertThat(productsWithEmptyName).isEmpty();


    }

}
