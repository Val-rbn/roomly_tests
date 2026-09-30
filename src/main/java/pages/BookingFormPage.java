package pages;

import basePage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BookingFormPage extends BasePage {

    public BookingFormPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private final By formTitle =
            By.xpath("//h1");

    private final By roomSelect =
            By.xpath("//div[@role='combobox']");

    private final By atlasRoomOption =
            By.xpath("//li[@data-value='room-atlas']");

    private final By topicField =
            By.xpath("//input[@name='subject']");

    private final By descriptionField =
            By.xpath("//textarea[@name='description']");

    private final By participantsField =
            By.name("participants");

    private final By dateField =
            By.xpath("//input[@name='date']");

    private final By timeFieldStart =
            By.xpath("//input[@name='start']");

    private final By timeFieldEnd =
            By.xpath("//input[@name='end']");

    private final By createBookingButton =
            By.xpath("//button[@type='submit' and contains(text(),'Создать бронирование')]");





    // Getters
    public WebElement getFormTitle() {
        return waitUntilVisible(formTitle);
    }

    public WebElement getRoomSelect() {
        return waitUntilClickable(roomSelect);
    }


    public WebElement getAtlasRoomOption() {
        return waitUntilClickable(atlasRoomOption);
    }

    public WebElement getTopicField() {
        return waitUntilVisible(topicField);
    }

    public WebElement getDescriptionField() {
        return waitUntilVisible(descriptionField);
    }

    public WebElement getParticipantsField() {
        return waitUntilVisible(participantsField);
    }

    public WebElement getDateField() {
        return waitUntilVisible(dateField);
    }

    public WebElement getTimeFieldStart() {
        return waitUntilVisible(timeFieldStart);
    }

    public WebElement getTimeFieldEnd() {
        return waitUntilVisible(timeFieldEnd);
    }

    public WebElement getCreateBookingButton() {
        return waitUntilClickable(createBookingButton);
    }







    // Methods
    public String getFormTitleText() {
        return getFormTitle().getText();
    }

    public void clickRoomSelect() {
        getRoomSelect().click();
    }

    public void clickAtlasRoomOption() {
        getAtlasRoomOption().click();
    }

    public void inputTopic(String topic) {
        getTopicField().sendKeys(topic);
    }

    public void inputDescription(String description) {
        getDescriptionField().sendKeys(description);
    }

    public void inputParticipants(String participants) {
        getParticipantsField().clear();
        getParticipantsField().sendKeys(participants);
    }

    public void inputDate(String date) {
        getDateField().clear();
        getDateField().sendKeys(date);
    }

    public void inputStartTime(String time) {
        getTimeFieldStart().clear();
        getTimeFieldStart().sendKeys(time);
    }

    public void inputEndTime(String time) {
        getTimeFieldEnd().clear();
        getTimeFieldEnd().sendKeys(time);
    }

    public void clickCreateBookingButton() {
        getCreateBookingButton().click();
    }







}
