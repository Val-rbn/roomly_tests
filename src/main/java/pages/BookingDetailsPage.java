package pages;

import basePage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BookingDetailsPage extends BasePage {

    public BookingDetailsPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private final By bookingDetailsTitle =
            By.xpath("//h1");


    // Getters
    public WebElement getBookingDetailsTitle() {
        return waitUntilVisible(bookingDetailsTitle);
    }


    // Methods
    public String getBookingDetailsTitleText() {
        try {
            return getBookingDetailsTitle().getText();
        } catch (StaleElementReferenceException e) {
            return getBookingDetailsTitle().getText();
        }
    }








}
