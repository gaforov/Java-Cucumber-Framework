package pages;

import base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PersonalDetailsPage extends BaseClass {
    @FindBy(id = "personal_txtEmployeeId")
    public WebElement employeeId;

    @FindBy(css = "#pdMainContainer h1")
    public WebElement personalDetailsHeader;

    @FindBy(css = "#profile-pic h1")
    public WebElement employeeFullName;

    public PersonalDetailsPage() {
        PageFactory.initElements(driver, this);
    }

}
