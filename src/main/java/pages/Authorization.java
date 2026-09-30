package pages;

import basePage.BasePage;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class Authorization extends BasePage {

    public Authorization(WebDriver driver) {
        super(driver);
    }

    //Locators(Локаторы)
    private final By email =
            By.xpath("//input[@id='email']");

    private final By password =
            By.xpath("//input[@id='password']");

    private final By loginButton =
            By.xpath("//button[normalize-space()='Войти']");

    private final By errorMessage =
            By.xpath("//*[contains(text(),'Неверный')]");



    // Getters
    public WebElement getEmailField() {
        return waitUntilVisible(email);
    }

    public WebElement getPasswordField() {
        return waitUntilVisible(password);
    }

    public WebElement getLoginButton() {
        return waitUntilClickable(loginButton);
    }

    public WebElement getErrorMessage() {
        return waitUntilVisible(errorMessage);
    }





    // Actions
    public void inputEmail(String userEmail) {
        getEmailField().clear();
        getEmailField().sendKeys(userEmail);
        System.out.println("Input email");
    }

    public void inputPassword(String userPassword) {
        getPasswordField().clear();
        getPasswordField().sendKeys(userPassword);
        System.out.println("Input password");
    }

    public void clickLoginButton() {
        getLoginButton().click();
        System.out.println("Click login button");
    }

    public String getErrorMessageText() {
        return getErrorMessage().getText();
    }









    // Methods
    public Authorization login(String userEmail, String userPassword) {
        inputEmail(userEmail);
        inputPassword(userPassword);
        clickLoginButton();

        return this;
    }

    public void waitForSuccessfulLogin() {
        waitUntilUrlNotContains("/login");
    }

    public Authorization login(User user) {
        inputEmail(user.getEmail());
        inputPassword(user.getPassword());
        clickLoginButton();

        return this;
    }
}












