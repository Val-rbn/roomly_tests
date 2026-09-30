package pages;

import basePage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AdminBookingsPage extends BasePage {

    public AdminBookingsPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private final By pageTitle =
            By.xpath("//h1[text()='Все бронирования']");

    private final By statusFilter =
            By.xpath("(//div[@role='combobox'])[3]");

    private final By scheduledStatusOption =
            By.xpath("//li[@data-value='scheduled']");

    private final By resetButton =
            By.xpath("//button[text()='Применить']");

    private final By bookingCards =
            By.xpath("//a[.//h5[text()='Командная встреча']]");






    // Getters
    public WebElement getPageTitle() {
        return waitUntilVisible(pageTitle);
    }

    public WebElement getStatusFilter() {
        return waitUntilClickable(statusFilter);
    }

    public WebElement getScheduledStatusOption() {
        return waitUntilClickable(scheduledStatusOption);
    }

    public WebElement getResetButton() {
        return waitUntilClickable(resetButton);
    }

    public WebElement getBookingCards () {
        return waitUntilClickable(bookingCards);
    }



    // Actions
    public String getPageTitleText() {
        return getPageTitle().getText();
    }

    public void clickStatusFilter() {
        getStatusFilter().click();
    }

    public void clickScheduledStatusOption() {
        getScheduledStatusOption().click();
    }

    public void clickResetButton() {
        getResetButton().click();
    }

    public void clickBookingCards() {
        getBookingCards().click();
    }









}
