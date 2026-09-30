package pages;

import basePage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPage extends BasePage {

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private final By searchPageTitle =
            By.xpath("//h1[contains(text(),'Поиск свободной переговорной')]");

    private final By dateField =
            By.xpath("//input[@type='date']");

    private final By startTimeField =
            By.xpath("(//input[@type='time'])[1]");

    private final By endTimeField =
            By.xpath("(//input[@type='time'])[2]");

    private final By capacityField =
            By.xpath("(//input[@type='number'])[1]");

    private final By floorField =
            By.xpath("(//input[@type='number'])[2]");

    private final By searchButton =
            By.xpath("//button[@type='submit']");

    // Locators
    private final By projectorCheckbox =
            By.xpath("//label[.//span[normalize-space()='Проектор']]");

    private final By tvCheckbox =
            By.xpath("//label[.//span[normalize-space()='Телевизор']]");

    private final By videoConferenceCheckbox =
            By.xpath("//label[.//span[normalize-space()='Видеоконференции']]");

    private final By whiteboardCheckbox =
            By.xpath("//label[.//span[normalize-space()='Маркерная доска']]");

    private final By roomCards =
            By.xpath("//article[starts-with(@data-testid,'room-card-')]");

    private final By openCardButton =
            By.xpath("//a[contains(text(),'Открыть карточку')]");






    // Getters
    public WebElement getSearchPageTitle() {
        return waitUntilVisible(searchPageTitle);
    }

    public WebElement getDateField() {
        return waitUntilVisible(dateField);
    }

    public WebElement getStartTimeField() {
        return waitUntilVisible(startTimeField);
    }

    public WebElement getEndTimeField() {
        return waitUntilVisible(endTimeField);
    }

    public WebElement getCapacityField() {
        return waitUntilVisible(capacityField);
    }

    public WebElement getFloorField() {
        return waitUntilVisible(floorField);
    }

    public WebElement getSearchButton() {
        return waitUntilClickable(searchButton);
    }

    public WebElement getProjectorCheckbox() {
        return waitUntilClickable(projectorCheckbox);
    }

    public WebElement getTvCheckbox() {
        return waitUntilClickable(tvCheckbox);
    }

    public WebElement getVideoConferenceCheckbox() {
        return waitUntilClickable(videoConferenceCheckbox);
    }

    public WebElement getWhiteboardCheckbox() {
        return waitUntilClickable(whiteboardCheckbox);
    }

    public List<WebElement> getRoomCards() {
        return driver.findElements(roomCards);
    }

    public WebElement getOpenCardButton() {
        return waitUntilClickable(openCardButton);
    }






    // Actions
    public String getSearchPageTitleText() {
        return getSearchPageTitle().getText();
    }

    public void inputDate(String date) {
        getDateField().sendKeys(date);
    }

    public void inputStartTime(String startTime) {
        getStartTimeField().sendKeys(startTime);
    }

    public void inputEndTime(String endTime) {
        getEndTimeField().sendKeys(endTime);
    }

    public void inputCapacity(String capacity) {
        getCapacityField().sendKeys(capacity);
    }

    public void inputFloor(String floor) {
        getFloorField().sendKeys(floor);
    }

    public void clickSearchButton() {
        getSearchButton().click();
    }
    public void clickProjectorCheckbox() {
        getProjectorCheckbox().click();
    }

    public void clickTvCheckbox() {
        getTvCheckbox().click();
    }

    public void clickVideoConferenceCheckbox() {
        getVideoConferenceCheckbox().click();
    }

    public void clickWhiteboardCheckbox() {
        getWhiteboardCheckbox().click();
    }

    public int getRoomsCount() {
        return getRoomCards().size();
    }

    public void clickOpenCardButton() {
        getOpenCardButton().click();
    }





}
