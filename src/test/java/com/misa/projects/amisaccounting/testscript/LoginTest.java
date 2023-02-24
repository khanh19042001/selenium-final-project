package com.misa.projects.amisaccounting.testscript;

import com.misa.annotations.MisaAnnotation;
import com.misa.common.TestBase;
import com.misa.constants.AuthorType;
import com.misa.constants.CategoryType;
import com.misa.driver.DriverManager;
import com.misa.driver.TargetFactory;
import com.misa.projects.amisaccounting.dataprovider.CustomDataProvider;
import com.misa.projects.amisaccounting.dataprovider.EmployeeDataProvider;
import com.misa.projects.amisaccounting.models.CustomFieldModel;
import com.misa.projects.amisaccounting.models.EmployeeModel;
import com.misa.projects.amisaccounting.page.HomePage;
import com.misa.projects.amisaccounting.page.LoginPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.annotations.*;

@Epic("Regression Test")
@Feature("Login Function")
public class LoginTest extends TestBase {

    LoginPage loginPage;
    HomePage homePage;

    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void beforeClass(@Optional("chrome") String browser) {
        WebDriver driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
        DriverManager.setDriver(driver);
        loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.gotoLoginPage();
        homePage = loginPage.login();
    }

    @MisaAnnotation(category = {CategoryType.REGRESSION}, author = {AuthorType.Khanh}, reviewer = {AuthorType.Khanh})
    @Test(description = "Access with PIM")
    public void TC1() {
        homePage.goToPIM();
    }
    @MisaAnnotation(category = {CategoryType.REGRESSION}, author = {AuthorType.Khanh}, reviewer = {AuthorType.Khanh})
    @Test(description = "Create custom field", dataProvider = "CustomField", dataProviderClass = CustomDataProvider.class)
    public void TC2(CustomFieldModel customFieldModel) {
        homePage.goToPIM();
        homePage.clickConfiguration();
        homePage.clickCustomFields();
        homePage.inputInfoCustomField(customFieldModel);
    }
    @MisaAnnotation(category = {CategoryType.REGRESSION}, author = {AuthorType.Khanh}, reviewer = {AuthorType.Khanh})
    @Test(description = "Add new Employee", dataProvider = "Employee", dataProviderClass = EmployeeDataProvider.class)
    public void TC3(EmployeeModel employeeModel) {
        homePage.goToPIM();
        homePage.createEmployee(employeeModel);
    }

    @MisaAnnotation(category = {CategoryType.REGRESSION}, author = {AuthorType.Khanh}, reviewer = {AuthorType.Khanh})
    @Test(description = "Search for the employee", dataProvider = "Employee", dataProviderClass = EmployeeDataProvider.class)
    public void TC4(EmployeeModel employeeModel) {
        homePage.goToPIM();
        homePage.searchEmployee(employeeModel);
    }
    @MisaAnnotation(category = {CategoryType.REGRESSION}, author = {AuthorType.Khanh}, reviewer = {AuthorType.Khanh})
    @Test(description = "Report")
    public void TC5() {
        homePage.goToPIM();
        homePage.reportEmployee();
    }
    @AfterClass(alwaysRun = true)
    public void closeDriver() {
        DriverManager.quit();
    }

}
