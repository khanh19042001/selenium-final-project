package com.misa.common;

import com.misa.driver.DriverManager;
import com.misa.driver.TargetFactory;
import com.misa.helpers.PropertiesHelpers;
import com.misa.utils.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.Properties;


@Listeners({TestListener.class})
public class TestBase {
    public TestBase() {
        PropertiesHelpers.loadAllFiles();
    }

//    @Parameters({"browser"})
//    @BeforeSuite
//    public void beforeSuite(@Optional("chrome") String browser) {
//        Log.info("TestBase: beforeSuite");
//        WebDriver driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
//        DriverManager.setDriver(driver);
//    }


    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void createDriver(@Optional("chrome") String browser) {

    }

//    @BeforeMethod(alwaysRun = true)
//    public void addInvocation(ITestResult tr) {
//    }

    @AfterClass(alwaysRun = true)
    public void closeDriver() {
        Log.info("TestBase: Close Driver ");
        DriverManager.quit();
    }
}
