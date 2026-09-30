package pages;

import basePage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    // Constructor
    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private final By greeting =
            By.xpath("//h1[contains(text(),'Здравствуйте')]");

    private final By findRoomButton  =
            By.xpath("//a[@href='/search']");


    // Getters
    public WebElement getGreeting() {
        return waitUntilVisible(greeting);
    }

    public WebElement getFindRoomButton() {
        return waitUntilVisible(findRoomButton);
    }

    // Actions
    public String getGreetingText() {
        return getGreeting().getText();
    }

    public void clickFindRoomButton() {
        getFindRoomButton().click();
    }


}