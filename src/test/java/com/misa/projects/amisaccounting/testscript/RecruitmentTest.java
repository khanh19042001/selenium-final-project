package com.misa.projects.amisaccounting.testscript;

import com.misa.annotations.MisaAnnotation;
import com.misa.common.TestBase;
import com.misa.constants.AuthorType;
import com.misa.constants.CategoryType;
import com.misa.driver.DriverManager;
import com.misa.driver.TargetFactory;
import com.misa.projects.amisaccounting.dataprovider.VacanciesDataProvider;
import com.misa.projects.amisaccounting.models.VacanciesModel;
import com.misa.projects.amisaccounting.page.HomePage;
import com.misa.projects.amisaccounting.page.LoginPage;
import com.misa.projects.amisaccounting.page.RecruitmentPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.annotations.*;

public class RecruitmentTest extends TestBase {

    LoginPage loginPage;
    HomePage homePage;
    RecruitmentPage recruitmentPage;

    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void beforeClass(@Optional("chrome") String browser) {
        WebDriver driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
        DriverManager.setDriver(driver);
        loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.gotoLoginPage();
        homePage=loginPage.login();
    }

    @BeforeMethod
    public void beforeMethod()
    {
        recruitmentPage=homePage.goToRecruitment();
        recruitmentPage.accessVacancies();
    }

    @MisaAnnotation(category = {CategoryType.REGRESSION}, author = {AuthorType.Khanh}, reviewer = {AuthorType.Khanh})
    @Test(description = "Add new Vacancies", dataProvider = "Vacancies", dataProviderClass = VacanciesDataProvider.class)
    public void TC1(VacanciesModel vacanciesModel) {
        recruitmentPage.createVacancies(vacanciesModel);
        recruitmentPage.accessVacancies();
        recruitmentPage.sortVacancies("Ascending");
        recruitmentPage.sortVacancies("Decending");
        recruitmentPage.deleteVacancies(vacanciesModel);

    }

}
