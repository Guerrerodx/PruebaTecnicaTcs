package cucumber.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;

public class SharedDriver {
    public ChromeDriverService service;
    public WebDriver driver;
    public SharedDriver() throws IOException {
        if (DriverFactory.getDriver() == null) {
            WebDriverManager.chromedriver().setup();
            service = new ChromeDriverService.Builder()
                    .usingAnyFreePort()
                    .build();
            service.start();

            ChromeOptions opt = new ChromeOptions();
            opt.addArguments("--incognito");
            opt.addArguments("--start-maximized");
            opt.addArguments("--enable-cookies");


            driver = new RemoteWebDriver(service.getUrl(), opt);

            DriverFactory.addDriver(driver);

        }
    }
}
