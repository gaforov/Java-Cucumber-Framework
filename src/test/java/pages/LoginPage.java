package pages;

import base.WebDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigsReader;

// Note: This is just a template for LoginPage where we store everything related to Login page here, in this class.
//  In Page Object Model (Design Pattern), we organize our code by pages. Each web page will have their own class.
public class LoginPage extends WebDriverManager {

    // LoginPage using PageFactory
    //@FindBy() == driver.findElement()

    @FindBy(name = "username")                 //  locating by ID
    public WebElement username;

    @FindBy(name = "password")               // locating by Name
    public WebElement password;

    @FindBy(xpath = "//button[@type='submit']") // Locating by xPath
    public WebElement loginBtn;

    @FindBy(css = "#divLogo img")
    public WebElement homepageLogo;
    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")
    public WebElement loginErrorMessage;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    public WebElement usernameBlankErrorMessage;
    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    public WebElement passwordBlankErrorMessage;

    public LoginPage(){
        PageFactory.initElements(getDriver(), this);
    }

    public void loginToWebsite(String user, String pswd) {
        username.clear();
        password.clear();
        sendText(username, user);
        sendText(password, pswd);
        clickButWaitForClickability(loginBtn);
    }

    public void loginToWebsiteViaConfigs(String user, String pswd) {
        sendText(username, ConfigsReader.getProperties(user));
        sendText(password, ConfigsReader.getProperties(pswd));
        clickButWaitForClickability(loginBtn);
    }


}
