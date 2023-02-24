package com.misa.kiemtra.page.testscript;

import com.misa.annotations.MisaAnnotation;
import com.misa.common.TestBase;
import com.misa.constants.AuthorType;
import com.misa.constants.CategoryType;
import com.misa.driver.DriverManager;
import com.misa.driver.TargetFactory;
import com.misa.kiemtra.page.dataprovider.ComputerDataProvider;
import com.misa.kiemtra.page.models.ComputerModel;
import com.misa.kiemtra.page.page.ComputerPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PageComputerTest extends TestBase {
    ComputerPage computerPage;

    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void beforeClass(@Optional("chrome") String browser) {
        WebDriver driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
        DriverManager.setDriver(driver);
        computerPage = new ComputerPage(DriverManager.getDriver());
        computerPage.goToPage();
    }

    @MisaAnnotation(category = {CategoryType.REGRESSION}, author = {AuthorType.Khanh}, reviewer = {AuthorType.Khanh})
    @Test(description = "Add new computer", dataProvider = "ComputerData", dataProviderClass = ComputerDataProvider.class)
    public void TC1(ComputerModel computerModel) {
       computerPage.addNewComputer(computerModel);
    }
}
