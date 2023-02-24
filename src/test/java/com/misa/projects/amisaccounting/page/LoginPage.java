package com.misa.projects.amisaccounting.page;

import com.misa.common.BaseConst;
import com.misa.common.BasePage;
import com.misa.constants.FrameworkConst;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage extends BasePage {
    /**
     * Init a new instance
     *
     * @param driver : The WebDriver to interact with elements
     */
    public LoginPage(WebDriver driver) {
        webDriver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(webDriver, FrameworkConst.WAIT_EXPLICIT);
        PageFactory.initElements(factory, this);
    }

    public LoginPage gotoLoginPage() {
        String URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        goToURL(URL);
        verifyPageUrl(URL, "check ho to page");
        return new LoginPage(webDriver);
    }

    /**
     * Login with username and password
     */
    public HomePage login() {
        String userName = "Username";
        String passWord = "Password";
        String login = "Login";
        String url = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
        By elementLogin = getByXpathDynamic(BaseConst.DYNAMIC_BUTTON_TYPE_FORM, login);

        inputText(getByXpathDynamic(BaseConst.DYNAMIC_INPUT_TYPE_FORM, userName), userName, "Admin");
        inputText(getByXpathDynamic(BaseConst.DYNAMIC_INPUT_TYPE_FORM, passWord), passWord, "admin123");

        clickElement(elementLogin, login);
        Assert.assertEquals(webDriver.getCurrentUrl(), url, "check login success");
        return new HomePage(webDriver);
    }
}
