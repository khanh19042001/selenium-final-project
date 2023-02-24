package com.misa.kiemtra.page.page;

import com.misa.common.BasePage;
import com.misa.constants.FrameworkConst;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;


public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        webDriver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(webDriver, FrameworkConst.WAIT_EXPLICIT);
        PageFactory.initElements(factory, this);
    }

    /**
     * click button Secure Checkout
     */
    public FillInfoPage clickSecureCheckout() {
        String xpath = "(//input[@value='Secure Checkout'])[2]";
        clickElement(getByXpathDynamic(xpath), "Click button Secure Checkout");
        return new FillInfoPage(webDriver);
    }

}
