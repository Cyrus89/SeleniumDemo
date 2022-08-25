package Tests;

import Pages.PopularItemsPage;
import Utils.PageTitleUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;


public class HomePageTest extends BaseTest {

    private PopularItemsPage popularItemsPage;


    @Test
    public void shouldReturnCorrectPageTitle() {
        System.out.println(driver.getTitle());
        Assertions.assertThat(driver.getTitle()).isEqualTo(PageTitleUtils.HOME_PAGE_TITLE);
    }

    @Test
    public void shouldSeeBestSellerItemsOnHomePage() {
        //Nadpisuję driver tylko dlatego, że na wersji pl nie działają Best Seller Items
//        driver.get("http://automationpractice.com/index.php");

        List<WebElement> productNamesByCssSelector = driver.findElements(By.cssSelector("#homefeatured .product-name"));
//        List<WebElement> productNamesByXpath = driver.findElements(By.xpath("//*[@id='blockbestsellers']//*[@class='product-name']"));


        for (WebElement productName : productNamesByCssSelector) {
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

    @Test
    public void shouldSeePopularItemsOnHomePage(){

        //TO SAMO CO shouldSeeBestSellerItemsOnHomePage(), ALE Z ZASTOSOWANIEM PAGEOBJECT
        popularItemsPage = new PopularItemsPage(driver);
        List<String> productNames = popularItemsPage.getProductNames();

        List<String> productsWithEmptyName = productNames.stream()
                .filter(el -> el.isEmpty())
                .collect(Collectors.toList());

        Assertions.assertThat(productsWithEmptyName).isEmpty();

    }

}
