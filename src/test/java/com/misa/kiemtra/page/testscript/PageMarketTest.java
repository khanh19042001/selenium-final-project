package com.misa.kiemtra.page.testscript;

import com.misa.annotations.MisaAnnotation;
import com.misa.common.TestBase;
import com.misa.constants.AuthorType;
import com.misa.constants.CategoryType;
import com.misa.driver.DriverManager;
import com.misa.driver.TargetFactory;
import com.misa.kiemtra.page.dataprovider.PersonDataProvider;
import com.misa.kiemtra.page.models.PersonModel;
import com.misa.kiemtra.page.page.CartPage;
import com.misa.kiemtra.page.page.FillInfoPage;
import com.misa.kiemtra.page.page.MarketPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.annotations.*;

public class PageMarketTest extends TestBase {
    MarketPage marketPage;
    CartPage cartPage;
    FillInfoPage fillInfoPage;

    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void beforeClass(@Optional("chrome") String browser) {
        WebDriver driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
        DriverManager.setDriver(driver);
    }

    @BeforeMethod
    public void beforeMethod()
    {
        marketPage = new MarketPage(DriverManager.getDriver());
        marketPage.goToPage();
    }
    @MisaAnnotation(category = {CategoryType.REGRESSION}, author = {AuthorType.Khanh}, reviewer = {AuthorType.Khanh})
    @Test(description = "Buy items with 2 user info", dataProvider = "PersonData", dataProviderClass = PersonDataProvider.class)
    public void TC1(PersonModel personModel) {
        marketPage.clickAllItem();
        marketPage.searchText("elementor");
        marketPage.sortBestSellers("Best sellers");
        marketPage.addItem(5);
        cartPage = marketPage.accessToCart();
        fillInfoPage = cartPage.clickSecureCheckout();
        fillInfoPage.FillInfoPerson(personModel);
    }

}
