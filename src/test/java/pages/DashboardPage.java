package pages;

import base.WebDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DashboardPage extends WebDriverManager {
    @FindBy(className = "oxd-userdropdown-name")
    public WebElement essUserFullName;       // <== public WebElement welcome = driver.findElement(By.id("welcome"));

    @FindBy(xpath = "//h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")
    public WebElement dashboardHeaderText;

    @FindBy(css = "div#branding a img:nth-child(1)")
    public WebElement dashboardLogo;

    @FindBy(css = "#mainMenu b")
    //public WebElement mainMenu;  // be careful, no red line error if you make mistake here
    public List<WebElement> mainMenu;

    public DashboardPage() {
        PageFactory.initElements(getDriver(), this);
    }
}
