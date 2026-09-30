package pages;

import basePage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminPage extends BasePage {

    public AdminPage(WebDriver driver) {
        super(driver);
    }
    // Locators
    private final By pageTitle =
            By.xpath("//h1");

    private final By allBookingsButton =
            By.xpath("//a[@href='/admin/bookings']");




    // Getters
    public WebElement getPageTitle() {
        return waitUntilVisible(pageTitle);
    }

    public WebElement getAllBookingsButton() {
        return waitUntilClickable(allBookingsButton);
    }



    // Actions
    public String getPageTitleText() {
        return getPageTitle().getText();
    }

    public void clickAllBookingsButton() {
        getAllBookingsButton().click();
    }








}
