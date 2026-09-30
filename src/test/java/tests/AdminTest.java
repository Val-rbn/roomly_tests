package tests;

import baseTest.BaseTest;
import factory.BrowserFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.TestUsers;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class AdminTest extends BaseTest {

    @Test(priority = 6)

    public void adminCanCancelBookingTest() {
        LocalDate searchDate = LocalDate.now().plusDays(1);

        if (searchDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
            searchDate = searchDate.plusDays(2);
        }

        if (searchDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            searchDate = searchDate.plusDays(1);
        }

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd.MM.yyyy");

        String formattedDate = searchDate.format(formatter);

        Authorization employeeAuthorization  = new Authorization(driver);
        employeeAuthorization .login(TestUsers.EMPLOYEE_1);
        employeeAuthorization .waitForSuccessfulLogin();

        HomePage homePage = new HomePage(driver);
        homePage.clickFindRoomButton();

        SearchPage searchPage = new SearchPage(driver);

        searchPage.inputDate(formattedDate);
        searchPage.inputStartTime("11:00");
        searchPage.inputEndTime("12:00");
        searchPage.inputCapacity("4");
        searchPage.inputFloor("2");

        searchPage.clickSearchButton();

        Assert.assertEquals(
                searchPage.getRoomsCount(),
                1,
                "Ожидалась 1 переговорная"
        );

        searchPage.clickOpenCardButton();

        CardPage cardPage = new CardPage(driver);

        Assert.assertEquals(
                cardPage.getRoomTitleText(),
                "Атлас",
                "Открылась не та карточка комнаты"
        );

        cardPage.clickBookingButton();

        BookingFormPage bookingFormPage = new BookingFormPage(driver);

        Assert.assertEquals(
                bookingFormPage.getFormTitleText(),
                "Новое бронирование",
                "Форма не открылась"
        );

        bookingFormPage.clickRoomSelect();
        bookingFormPage.clickAtlasRoomOption();
        bookingFormPage.inputTopic("Командная встреча");
        bookingFormPage.inputDescription("Тестовое бронирование переговорной");
        bookingFormPage.inputParticipants("4");
        bookingFormPage.inputDate(formattedDate);
        bookingFormPage.inputStartTime("11:00");
        bookingFormPage.inputEndTime("12:00");
        bookingFormPage.clickCreateBookingButton();

        BookingDetailsPage bookingDetailsPage = new BookingDetailsPage(driver);

        Assert.assertEquals(
                bookingDetailsPage.getBookingDetailsTitleText(),
                "Командная встреча",
                "Бронирование не создалось"
        );

        driver.quit();
        driver = BrowserFactory.getDriver("chrome");
        driver.get("http://localhost:5173/login");



        Authorization adminAuthorization = new Authorization(driver);
        adminAuthorization.login(TestUsers.ADMIN);
        adminAuthorization.waitForSuccessfulLogin();

        AdminPage adminPage = new AdminPage(driver);
        Assert.assertTrue(
                adminPage.getPageTitleText().contains("Здравствуйте"),
                "Главная страница администратора не открылась"
        );

        adminPage.clickAllBookingsButton();

        AdminBookingsPage adminBookingsPage = new AdminBookingsPage(driver);

        Assert.assertEquals(
                adminBookingsPage.getPageTitleText(),
                "Все бронирования",
                "Страница всех бронирований не открылась"
        );

        adminBookingsPage.clickStatusFilter();
        adminBookingsPage.clickScheduledStatusOption();
        adminBookingsPage.clickResetButton();


            adminBookingsPage.clickBookingCards();

            AdminBookingDetailsPage adminBookingDetailsPage =
                    new AdminBookingDetailsPage(driver);

            Assert.assertFalse(
                    adminBookingDetailsPage.getBookingDetailsTitleText().isBlank(),
                    "Страница бронирования не открылась"
            );

            adminBookingDetailsPage.clickCancelBookingButton();

            adminBookingDetailsPage.inputCancelReason(
                    "Отмена тестового бронирования"
            );

            adminBookingDetailsPage.clickConfirmCancelButton();
            driver.get("http://localhost:5173/admin/bookings");

        }

    }
