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

public class BaseClass extends CommonMethods {
    public static WebDriver driver;

    public static void setUp() {
        //System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
        //System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "true");
        ConfigsReader.loadProperties(Constants.CONFIGURATION_FILEPATH);
        String headless = ConfigsReader.getProperties("headless");

        switch (ConfigsReader.getProperties("browser").toLowerCase()) {
            case "chrome" -> {
//                WebDriverManager.chromedriver().setup();  // As of Selenium 4.12 and after not needed anymore
                if (headless.equalsIgnoreCase("true")) {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless", "--log-level=3");          // <== Run in headless mode
                    driver = new ChromeDriver(options);
                } else {
                    driver = new ChromeDriver();               // <== if headless=false this line will run
                }
            }
            case "firefox" -> {
//                WebDriverManager.firefoxdriver().setup();    // <=== This line will replace local geckodriver for firefox.
                if (headless.equalsIgnoreCase("true")) {
                    FirefoxOptions options = new FirefoxOptions();
                    options.addArguments("--headless");
                    driver = new FirefoxDriver(options);
                } else {
                    driver = new FirefoxDriver();              // <== if headless=false this line will run
                }
            }
            case "edge" -> {
//                WebDriverManager.edgedriver().setup();
                if (headless.equalsIgnoreCase("true")) {
                    EdgeOptions options = new EdgeOptions();
                    options.addArguments("--headless");
                    driver = new EdgeDriver(options);
                } else {
                    driver = new EdgeDriver();           // <== if headless=false this line will run
                }
            }
            default -> throw new RuntimeException("Browser is not supported");
        }

        driver.get(ConfigsReader.getProperties("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT_TIME));
        initialize();
    }

    public static void tearDown() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.getStackTrace();
        }
        if (driver != null) {     // This line is optional. We only use it to prevent NullPointerException.
            driver.quit();
        }
    }

}
