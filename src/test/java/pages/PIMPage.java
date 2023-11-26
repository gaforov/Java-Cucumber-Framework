package pages;

import base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PIMPage extends BaseClass {
    @FindBy(id = "menu_pim_viewPimModule")
    public WebElement PIM;
    @FindBy(id = "menu_pim_addEmployee")
    public WebElement addEmployee;
    @FindBy(id = "menu_pim_viewEmployeeList")
    public WebElement employeeList;

    public PIMPage() {
        PageFactory.initElements(driver, this);
    }

    public void navigateToAddEmployee() {
        click(PIM);
        click(addEmployee);
    }

    public void navigateToEmployeeList() {
        click(PIM);
        click(employeeList);
    }



}
