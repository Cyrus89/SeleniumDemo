package Tests;

import Enums.MessageSubject;
import Model.Message;
import Pages.ContactUsFormPage;
import Pages.TopMenuPage;
import org.assertj.core.api.Assertions;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.*;

public class ContactUsTest extends BaseTest {

    private TopMenuPage topMenuPage;
    private ContactUsFormPage contactUsFormPage;

    @BeforeEach
    public void setupTest() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);

        topMenuPage = new TopMenuPage(driver);
        contactUsFormPage = new ContactUsFormPage(driver);

    }

    @Test
    public void shouldNotAllowToSendEmptyContactUsForm() {

// CZEKANIE x SEKUND NA SPRAWDZENIE KAŻDEGO WEBELEMENTU W TEŚCIE (np. findElement). NATYCHMIASTOWE WYKONANIE, JEŚLI ELEMENT JEST ZNALEZIONY
//    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

//        WebElement contactUsLink = driver.findElement(By.linkText("Contact us"));
//        contactUsLink.click();
        topMenuPage.clickOnContactUsLink();

//        WebElement sendButton = driver.findElement(By.id("submitMessage"));
//        sendButton.click();
        contactUsFormPage.clickOnSendButton();

//ZAWSZE ZATRZYMUJEMY WĄTEK - NAJGORSZE ROZWIĄZANIE NA CZEKANIE (TESTY BĘDĄ DŁUŻSZE)
//    try {
//        Thread.sleep(5000);
//    } catch (InterruptedException e) {
//        e.printStackTrace();
//    }


//        WebElement redAlertBox = driver.findElement(By.className("alert-danger"));

// CZEKAMY TYLKO TAM GDZIE WYWOŁAMY WAIT. NATYCHMIASTOWE WYKONANIE, JEŚLI WARUNEK (ExpectedCondition) JEST SPEŁNIONY
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        wait.until(ExpectedConditions.visibilityOf(redAlertBox));
//
//        assertThat(redAlertBox.isDisplayed()).isTrue();

        Assertions.assertThat(contactUsFormPage.isRedAlertBoxDisplayed()).isTrue();
    }

    @Test
    public void shouldNotAllowToSendContactUsFormWithEmailOnly() {

//        WebElement contactUsLink = driver.findElement(By.linkText("Contact us"));
//        contactUsLink.click();
        topMenuPage.clickOnContactUsLink();

//        WebElement emailInput = driver.findElement(By.id("email"));
//        emailInput.sendKeys("test@test.com");
        contactUsFormPage.enterEmail("test@test.com");

//        WebElement sendButton = driver.findElement(By.id("submitMessage"));
//        sendButton.click();
        contactUsFormPage.clickOnSendButton();

//        WebElement redAlertBox = driver.findElement(By.className("alert-danger"));

// CZEKAMY TYLKO TAM GDZIE WYWOŁAMY WAIT. NATYCHMIASTOWE WYKONANIE, JEŚLI WARUNEK (ExpectedCondition) JEST SPEŁNIONY
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        wait.until(ExpectedConditions.visibilityOf(redAlertBox));
//
//        assertThat(redAlertBox.isDisplayed()).isTrue();

        Assertions.assertThat(contactUsFormPage.isRedAlertBoxDisplayed()).isTrue();
    }

    @Test
    public void shouldSendContactUsFormWithValidData(){
        topMenuPage.clickOnContactUsLink();

        Message message = new Message();
        message.setSubject(MessageSubject.WEBMASTER);
        message.setEmail("test@test.com");
        message.setOrderReference("123");
        message.setMessage("Testowa wiadomość");

        contactUsFormPage.sendContactUsForm(message);

        Assertions.assertThat(contactUsFormPage.isGreenAlertBoxDisplayed()).isTrue();



    }


}
