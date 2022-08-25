package Pages;

import Model.Message;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactUsFormPage extends BasePage {

    public ContactUsFormPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "submitMessage")
    WebElement sendButton;

    @FindBy(className = "alert-danger")
    WebElement redAlertBox;

    @FindBy(id = "email")
    WebElement emailInput;

    @FindBy(id = "id_contact")
    WebElement subjectSelect;

    @FindBy(id = "id_order")
    WebElement orderReferenceInput;

    @FindBy(id = "message")
    WebElement messageTextArea;

    @FindBy(className = "alert-success")
    WebElement greenAlertBox;

    public void clickOnSendButton() {
        sendButton.click();
    }

    public boolean isRedAlertBoxDisplayed() {
        return isAlertBoxDisplayed(redAlertBox);
    }

    public void enterEmail(String email) {
        emailInput.sendKeys(email);

    }

    public void sendContactUsForm(Message message) {
        Select subject = new Select(subjectSelect);
        subject.selectByVisibleText(message.getSubject().getValue());
        emailInput.sendKeys(message.getEmail());
        orderReferenceInput.sendKeys(message.getOrderReference());
        messageTextArea.sendKeys(message.getMessage());

        sendButton.click();

    }

    public boolean isGreenAlertBoxDisplayed() {
        return isAlertBoxDisplayed(greenAlertBox);
    }

    private boolean isAlertBoxDisplayed(WebElement box) {
        wait.until(ExpectedConditions.visibilityOf(box));

        boolean isDisplayed = false;
        try {
            isDisplayed = box.isDisplayed();
        } catch (NoSuchElementException e) {

        }
        return isDisplayed;
    }
}
