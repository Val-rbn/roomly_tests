package baseTest;

import factory.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BaseTest {

    protected WebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser) throws Exception {

        resetDatabase();

        driver = BrowserFactory.getDriver(browser);
        driver.get("http://localhost:5173/login");
    }

    private void resetDatabase() throws Exception {

        System.out.println("===== RESET DATABASE =====");

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:3000/api/v1/test/reset"))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        System.out.println("RESET STATUS: " + response.statusCode());
        System.out.println("RESET RESPONSE: " + response.body());

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new RuntimeException(
                    "Не удалось сбросить БД. Status code: "
                            + response.statusCode()
                            + ", response: "
                            + response.body()
            );
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}