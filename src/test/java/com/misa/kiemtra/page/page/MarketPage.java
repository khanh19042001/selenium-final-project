package com.misa.kiemtra.page.page;

import com.misa.common.BaseConst;
import com.misa.common.BasePage;
import com.misa.constants.FailureHandling;
import com.misa.constants.FrameworkConst;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MarketPage extends BasePage {
    public MarketPage(WebDriver driver) {
        webDriver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(webDriver, FrameworkConst.WAIT_EXPLICIT);
        PageFactory.initElements(factory, this);
    }

    /**
     * go to page market
     *
     * @return
     */
    public MarketPage goToPage() {
        String URL = "https://themeforest.net/";
        goToURL(URL);
        verifyPageUrl(URL, "check go to page");
        return new MarketPage(webDriver);
    }

    /**
     * click element All Item
     */
    public void clickAllItem() {
        clickElement(getByXpathDynamic(BaseConst.DYNAMIC_A_TEXT_FORM_2,"Code"),"click element code");
        clickElement(getByXpathDynamic(BaseConst.DYNAMIC_A_TEXT_FORM_2, "All Items"), "click element All item");
        WebElement element = waitForElementVisible(getByXpathDynamic(xpathHeading));
        assertTrueCondition(element, element.isDisplayed(), FailureHandling.STOP_ON_FAILURE, "check click element All Item success");
    }

    /**
     * search text
     *
     * @param text
     */
    public void searchText(String text) {
        inputText(getByXpathDynamic(xpathSearch), "Search text", text);
        waitForElementVisible(getByXpathDynamic(xpathSearch)).sendKeys(Keys.ENTER);
    }

    /**
     * verify sort Best Seller
     */
    public void verifyCheckSort() {
        String xpath = "//span[contains(@class,'starRatingCount')]/following::div[contains(@class,'sales_component__root')]";
        List<Float> obtainedList = new ArrayList<>();
        List<WebElement> elementList = getListWebElement(getByXpathDynamic(xpath));
        for (WebElement we : elementList) {
            obtainedList.add(Float.valueOf(we.getText().replace("K Sales", "")));
        }
        List<Float> sortedList = new ArrayList<>();
        for (Float s : obtainedList) {
            sortedList.add(s);
        }
        Collections.sort(sortedList);
        Collections.reverse(sortedList);
        assertEqualCondition("",sortedList,obtainedList,FailureHandling.STOP_ON_FAILURE,"verify sort best seller with like");
//        Assert.assertEquals(sortedList,obtainedList,"verify sort best seller with like");
    }

    /**
     * sort with Best Sellers
     *
     * @param text
     */
    public void sortBestSellers(String text) {
        clickElement(getByXpathDynamic(BaseConst.DYNAMIC_A_TEXT_FORM_2, text), text);
        WebElement element=waitForElementVisible(getByXpathDynamic(BaseConst.DYNAMIC_SPAN_TEXT_FORM_2,text));
        assertTrueCondition(element,element.isDisplayed(),FailureHandling.STOP_ON_FAILURE,"verify click element Best Sellers");
//        verifyCheckSort();
    }

    /**
     * add top 5 item
     *
     * @param number
     */
    public void addItem(int number) {
        for (int i = 1; i <= number; i++) {
            waitForElementClickable(getByXpathDynamic(BaseConst.DYNAMIC_A_CLASS_FORM_2, i));
            scrollToElementWithBy(getByXpathDynamic(BaseConst.DYNAMIC_A_CLASS_FORM_2,i));
            clickElement(getByXpathDynamic(BaseConst.DYNAMIC_A_CLASS_FORM_2, i), "Add to cart top 5 item");
            clickButton("Keep Browsing");
        }
    }

    /**
     * access to cart
     * @return
     */
    public CartPage accessToCart() {
        clickElement(getByXpathDynamic(xpathCart));
        return new CartPage(webDriver);
    }

    String xpathSearch = "//input[@name='term']";
    String xpathCart = "//span[@data-controller='cart-icon']";
    String xpathHeading = "//h1[text()='Plugins, Code & Scripts']";

}
