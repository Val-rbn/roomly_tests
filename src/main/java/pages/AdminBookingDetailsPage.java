package pages;

import basePage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminBookingDetailsPage extends BasePage {

    public AdminBookingDetailsPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private final By bookingDetailsTitle =
            By.xpath("//h1");

    private final By cancelBookingButton =
            By.xpath("//button[@type='button' and text()='Отменить']");

    private final By cancelReasonField =
            By.xpath("//textarea[@required]");

    private final By confirmCancelButton =
            By.xpath("//button[@type='button' and text()='Подтвердить отмену']");




    // Getters
    public WebElement getBookingDetailsTitle() {
        return waitUntilVisible(bookingDetailsTitle);
    }

    public WebElement getCancelBookingButton() {
        return waitUntilClickable(cancelBookingButton);
    }

    public WebElement getCancelReasonField() {
        return waitUntilVisible(cancelReasonField);
    }

    public WebElement getConfirmCancelButton() {
        return waitUntilClickable(confirmCancelButton);
    }



    // Actions
    public void clickCancelBookingButton() {
        getCancelBookingButton().click();
    }

    public void inputCancelReason(String reason) {
        getCancelReasonField().clear();
        getCancelReasonField().sendKeys(reason);
    }

    public void clickConfirmCancelButton() {
        getConfirmCancelButton().click();
    }


    // Method
    public String getBookingDetailsTitleText() {
        return getBookingDetailsTitle().getText();
    }

}
