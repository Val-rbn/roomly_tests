package tests;

import baseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Authorization;
import pages.HomePage;
import utils.TestUsers;

public class AuthorizationTest extends BaseTest {

    @Test
    public void positiveAuthorizationEmployeeTest() {

        Authorization authorization = new Authorization(driver);

        authorization.login(TestUsers.EMPLOYEE_1);

        authorization.waitForSuccessfulLogin();

        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(
                homePage.getGreetingText().contains("Здравствуйте"),
                "После авторизации сотрудника не отображается приветствие"
        );
    }

    @Test
    public void positiveAuthorizationSecondEmployeeTest() {

        Authorization authorization = new Authorization(driver);

        authorization.login(TestUsers.EMPLOYEE_2);

        authorization.waitForSuccessfulLogin();

        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(
                homePage.getGreetingText().contains("Здравствуйте"),
                "После авторизации второго сотрудника не отображается приветствие"
        );
    }

    @Test
    public void positiveAuthorizationAdminTest() {

        Authorization authorization = new Authorization(driver);

        authorization.login(TestUsers.ADMIN);

        authorization.waitForSuccessfulLogin();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                "http://localhost:5173/",
                "Администратор не авторизовался"
        );
    }

    @Test
    public void authorizationWithInvalidCredentialsTest() {

        Authorization authorization = new Authorization(driver);

        authorization.login(
                "wrong@northstar.local",
                "WrongPassword123"
        );

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/login"),
                "Пользователь с неправильными данными не должен войти"
        );
    }

    @Test
    public void authorizationWithEmptyFieldsTest() {

        Authorization authorization = new Authorization(driver);

        authorization.login("", "");

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/login"),
                "Авторизация с пустыми полями не должна выполняться"
        );
    }
}