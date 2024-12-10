package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utils.CommonMethods;
import utils.ConfigsReader;
import utils.Constants;

import java.time.Duration;

// NOTE: THIS CLASS IS USED TO LAUNCH AND QUIT THE BROWSER

public class WebDriverManager extends CommonMethods {
    // ThreadLocal instance to ensure thread safety
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        // Get the WebDriver instance for the current thread
        return driver.get();
    }

    public static void setUp() {
        ConfigsReader.loadProperties(Constants.CONFIGURATION_FILEPATH);
        String headless = ConfigsReader.getProperties("headless");

        WebDriver webDriver;
        switch (ConfigsReader.getProperties("browser").toLowerCase()) {
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();
                if (headless.equalsIgnoreCase("true")) {
                    options.addArguments("--headless", "--log-level=3");
                }
                webDriver = new ChromeDriver(options);
            }
            case "firefox" -> {
                FirefoxOptions options = new FirefoxOptions();
                if (headless.equalsIgnoreCase("true")) {
                    options.addArguments("--headless");
                }
                webDriver = new FirefoxDriver(options);
            }
            case "edge" -> {
                EdgeOptions options = new EdgeOptions();
                if (headless.equalsIgnoreCase("true")) {
                    options.addArguments("--headless");
                }
                webDriver = new EdgeDriver(options);
            }
            default -> throw new RuntimeException("Browser is not supported");
        }

        // Set the WebDriver instance in ThreadLocal
        driver.set(webDriver);

        // Configure the WebDriver instance
        getDriver().get(ConfigsReader.getProperties("url"));
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT_TIME));

        initialize();
    }

    public static void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove(); // Remove WebDriver instance from ThreadLocal
        }
    }

}
