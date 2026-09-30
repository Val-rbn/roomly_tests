package tests;

import baseTest.BaseTest;
import factory.BrowserFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import utils.TestUsers;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;




public class BookingTest extends BaseTest {


    @Test(priority = 0)
    public void employeeCanSearchRoomWithoutFiltersTest() {

        Authorization authorization = new Authorization(driver);
        authorization.login(TestUsers.EMPLOYEE_1);
        authorization.waitForSuccessfulLogin();

        HomePage homePage = new HomePage(driver);
        homePage.clickFindRoomButton();

        SearchPage searchPage = new SearchPage(driver);

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

        searchPage.inputDate(formattedDate);
        searchPage.inputStartTime("10:00");
        searchPage.inputEndTime("11:00");
        searchPage.inputCapacity("4");
        searchPage.inputFloor("2");

        searchPage.clickSearchButton();

        Assert.assertEquals(
                searchPage.getRoomsCount(),
                1,
                "Ожидалось 1 переговорные"
        );
    }





    @Test(priority = 1)
    public void employeeCanBookMeetingRoomTest() {

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

        // Авторизация
        Authorization authorization = new Authorization(driver);

        authorization.login(TestUsers.EMPLOYEE_1);
        authorization.waitForSuccessfulLogin();

        // Главная страница
        HomePage homePage = new HomePage(driver);

        homePage.clickFindRoomButton();

        //SearchPage

        SearchPage searchPage = new SearchPage(driver);

        Assert.assertEquals(
                searchPage.getSearchPageTitleText(),
                "Поиск свободной переговорной",
                "Страница поиска переговорной не открылась"
        );

        // 5. Заполняем параметры поиска
        searchPage.inputDate(formattedDate);
        searchPage.inputStartTime("10:00");
        searchPage.inputEndTime("11:00");
        searchPage.inputCapacity("4");
        searchPage.inputFloor("2");

        // 6. Нажимаем кнопку Найти
        searchPage.clickSearchButton();

        searchPage.clickSearchButton();


        Assert.assertEquals(
                searchPage.getRoomsCount(),
                1,
                "Без фильтров должна быть найдена 1 комната"
        );
    }


    @DataProvider(name = "roomFilters")
    public Object[][] roomFilters() {
        return new Object[][]{
                {"Проектор", 0},
                {"Телевизор", 1},
                {"Видеоконференции", 0},
                {"Маркерная доска", 1}
        };
    }

    @Test(dataProvider = "roomFilters", priority = 2)
    public void employeeCanSearchRoomWithFilterTest(
            String filterName,
            int expectedRoomsCount)
    {

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

        Authorization authorization = new Authorization(driver);
        authorization.login(TestUsers.EMPLOYEE_1);
        authorization.waitForSuccessfulLogin();

        HomePage homePage = new HomePage(driver);
        homePage.clickFindRoomButton();

        SearchPage searchPage = new SearchPage(driver);

        searchPage.inputDate(formattedDate);
        searchPage.inputStartTime("10:00");
        searchPage.inputEndTime("11:00");
        searchPage.inputCapacity("4");
        searchPage.inputFloor("2");

        switch (filterName) {

            case "Проектор":
                searchPage.clickProjectorCheckbox();
                break;

            case "Телевизор":
                searchPage.clickTvCheckbox();
                break;

            case "Видеоконференции":
                searchPage.clickVideoConferenceCheckbox();
                break;

            case "Маркерная доска":
                searchPage.clickWhiteboardCheckbox();
                break;
        }

        searchPage.clickSearchButton();

        Assert.assertEquals(
                searchPage.getRoomsCount(),
                expectedRoomsCount,
                "Неверное количество комнат для фильтра: " + filterName
        );
    }

    @Test(priority = 3)
    public void employeeCanSearchRoomWithAllFiltersTest() {

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

        Authorization authorization = new Authorization(driver);
        authorization.login(TestUsers.EMPLOYEE_1);
        authorization.waitForSuccessfulLogin();

        HomePage homePage = new HomePage(driver);
        homePage.clickFindRoomButton();

        SearchPage searchPage = new SearchPage(driver);

        searchPage.inputDate(formattedDate);
        searchPage.inputStartTime("10:00");
        searchPage.inputEndTime("11:00");
        searchPage.inputCapacity("4");
        searchPage.inputFloor("2");

        searchPage.clickProjectorCheckbox();
        searchPage.clickTvCheckbox();
        searchPage.clickVideoConferenceCheckbox();
        searchPage.clickWhiteboardCheckbox();

        searchPage.clickSearchButton();

        Assert.assertEquals(
                searchPage.getRoomsCount(),
                0,
                "При выборе всех фильтров комнаты не должны быть найдены"
        );

    }










    @Test(priority = 4)
    public void employeeCanOpenRoomCardTest() {

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

        Authorization authorization = new Authorization(driver);
        authorization.login(TestUsers.EMPLOYEE_1);
        authorization.waitForSuccessfulLogin();

        HomePage homePage = new HomePage(driver);
        homePage.clickFindRoomButton();

        SearchPage searchPage = new SearchPage(driver);

        searchPage.inputDate(formattedDate);
        searchPage.inputStartTime("10:00");
        searchPage.inputEndTime("11:00");
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
        bookingFormPage.inputStartTime("10:00");
        bookingFormPage.inputEndTime("11:00");
        bookingFormPage.clickCreateBookingButton();

        BookingDetailsPage bookingDetailsPage = new BookingDetailsPage(driver);

        Assert.assertEquals(
                bookingDetailsPage.getBookingDetailsTitleText(),
                "Командная встреча",
                "Бронирование не создалось"
        );
    }


    @Test(priority = 5)
    public void secondEmployeeCannotFindOccupiedRoomTest() {
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

        Authorization authorization = new Authorization(driver);
        authorization.login(TestUsers.EMPLOYEE_1);
        authorization.waitForSuccessfulLogin();

        HomePage homePage = new HomePage(driver);
        homePage.clickFindRoomButton();

        SearchPage searchPage = new SearchPage(driver);

        searchPage.inputDate(formattedDate);
        searchPage.inputStartTime("15:00");
        searchPage.inputEndTime("16:00");
        searchPage.inputCapacity("3");
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
        bookingFormPage.inputTopic("Планирование спринта");
        bookingFormPage.inputDescription("Тестовое бронирование переговорной");
        bookingFormPage.inputParticipants("3");
        bookingFormPage.inputDate(formattedDate);
        bookingFormPage.inputStartTime("13:00");
        bookingFormPage.inputEndTime("14:00");
        bookingFormPage.clickCreateBookingButton();

        BookingDetailsPage bookingDetailsPage = new BookingDetailsPage(driver);

        Assert.assertEquals(
                bookingDetailsPage.getBookingDetailsTitleText(),
                "Планирование спринта",
                "Бронирование не создалось"
        );

        driver.quit();

        driver = BrowserFactory.getDriver("chrome");
        driver.get("http://localhost:5173/login");

        Authorization authorization2 = new Authorization(driver);
        authorization2.login(TestUsers.EMPLOYEE_2);
        authorization2.waitForSuccessfulLogin();

        HomePage homePage2 = new HomePage(driver);
        homePage2.clickFindRoomButton();

        SearchPage searchPage2 = new SearchPage(driver);

        searchPage2.inputDate(formattedDate);
        searchPage2.inputStartTime("13:00");
        searchPage2.inputEndTime("14:00");
        searchPage2.inputCapacity("3");
        searchPage2.inputFloor("2");

        searchPage2.clickSearchButton();

        Assert.assertEquals(
                searchPage2.getRoomsCount(),
                0,
                "Занятая переговорная не должна отображаться для второго сотрудника"
        );

    }


}





