package pages;

import basePage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CardPage extends BasePage {

    public CardPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private final By roomTitle =
            By.xpath("//h1");

    private final By bookingButton =
            By.xpath("//a[contains(text(),'Забронировать комнату')]");



    // Getters
    public WebElement getRoomTitle() {
        return waitUntilVisible(roomTitle);
    }

    public WebElement getBookingButton() {
        return waitUntilClickable(bookingButton);
    }



    // Methods
    public String getRoomTitleText() {
        return getRoomTitle().getText();
    }

    public void clickBookingButton() {
        getBookingButton().click();
    }




}

