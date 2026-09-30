package basePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
    //Ждёт, пока элемент станет выбранным.
    protected boolean waitUntilSelected(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeSelected(locator));
    }

    //Ждёт, пока элемент можно будет нажать.
    protected WebElement waitUntilClickable(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    //Ждёт, пока элемент станет видимым на странице.
    protected WebElement waitUntilVisible(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    //
    protected void waitUntilUrlNotContains(String text) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.not(
                        ExpectedConditions.urlContains(text)
                ));
    }

    //Ждёт, пока элемент станет кликабельным;
    //Кликает по нему.
    protected void click(By locator) {
        waitUntilClickable(locator).click();
    }

    //Вводит текст в поле.
    protected void type(By locator, String text) {
        WebElement element = waitUntilVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    //Получает текст элемента.
    protected String getText(By locator) {
        return waitUntilVisible(locator).getText();
    }

    //Метод нужен, когда обычный click() может не сработать,  элемент находится ниже по странице.
    protected void scrollAndClick(By locator) {
        WebElement element = waitUntilClickable(locator);

        new Actions(driver)
                .moveToElement(element)
                .perform();

        element.click();
    }
}