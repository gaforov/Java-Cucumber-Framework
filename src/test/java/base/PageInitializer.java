package base;

import pages.*;

public class PageInitializer {
    public static LoginPage loginPage;
    public static DashboardPage dashboardPage;
    public static AddEmployeePage addEmployeePage;
    public static PIMPage pimPage;
    public static PersonalDetailsPage personalDetailsPage;
    public static EmployeeListPage employeeListPage;

    public static void initialize() {
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        addEmployeePage = new AddEmployeePage();
        pimPage = new PIMPage();
        personalDetailsPage = new PersonalDetailsPage();
        employeeListPage = new EmployeeListPage();
    }
}
