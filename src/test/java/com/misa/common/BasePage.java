package com.misa.common;

import com.misa.constants.FailureHandling;
import com.misa.driver.DriverManager;
import com.misa.utils.WebUI;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.Objects;

import static java.lang.Thread.sleep;

@Data
public class BasePage extends WebUI {
    protected WebDriver webDriver;

    public BasePage() {
        webDriver = DriverManager.getDriver();
    }

    /**
     * Finding element
     */
    public WebElement findElement(Object selector) {
        WebElement element;
        if (selector instanceof WebElement) element = (WebElement) selector;
        else if (selector instanceof String) {
            element = webDriver.findElement(By.xpath(String.valueOf(selector)));
        } else if (selector instanceof By) {
            element = webDriver.findElement((By) selector);
        } else {
            throw new RuntimeException("Your selector is invalid, you only user String, By or WebElement object");
        }
        return element;
    }

    /**
     * Get By locator
     */
    public By getByLocator(String locatorType) {
        Reporter.log("Get By locator by Xpath " + locatorType);
        By by;
        if (Objects.isNull(locatorType)) throw new RuntimeException("Your locator is null!");
        else {
            by = By.xpath(locatorType);
        }
        return by;
    }

    /**
     * Get Element
     *
     * @param selector
     */
    public WebElement getElement(Object selector) {
        WebElement element;
        if (selector instanceof WebElement) element = (WebElement) selector;
        else if (selector instanceof By) element = webDriver.findElement((By) selector);
        else throw new RuntimeException("Your object should be a WebElement or a by object");
        return element;
    }

    /**
     * click element button
     *
     * @param button
     */
    public void clickButton(String button) {
        By element = getByXpathDynamic(BaseConst.DYNAMIC_BUTTON_TYPE_FORM_2, button);
        clickElement(element, button);
    }

    /**
     * input text
     *
     * @param selector
     * @param label
     * @param value
     */
    public void inputText(Object selector, String label, String value) {
        WebElement element = waitForElementVisible(selector);
//        element.clear();
        inputTextTo(selector, label, value);
        assertEqualCondition(element, element.getAttribute("value"), value, FailureHandling.STOP_ON_FAILURE, "check data input ");
    }

    /**
     * verify data
     *
     * @param selector
     * @param expectedValue
     */
    public void verifyValue(Object selector, String expectedValue) {
        waitForElementVisible(selector);
        WebElement element = getElement(selector);
        assertEqualCondition(element, element.getText(), expectedValue, FailureHandling.STOP_ON_FAILURE, "verify data input");
    }

    /**
     * click element combo box
     *
     * @param title
     * @param text
     */
    public void clickElementCombo(String title, String text) {
        By menu = getByXpathDynamic(BaseConst.DYNAMIC_DIV_LABEL_FORM, title);
        clickElement(menu, text);

        By submenu = getByXpathDynamic(BaseConst.DYNAMIC_SPAN_TYPE_FORM, text);
        clickElement(submenu, text);
    }

    public void clickDelete(String xpath, String name) {
        By element = getByXpathDynamic(xpath, name);
        waitForElementClickableBy(element);
        clickElement(element);
        clickElement(getByXpathDynamic(BaseConst.DYNAMIC_BUTTON_TEXT_FORM, " Yes, Delete "));
    }

    public void checkSaveSuccess(String message) {
        String xpath = "//p[text()='Success']";
        Assert.assertTrue(getWebElement(waitForElementVisible(getByXpathDynamic(xpath))).isDisplayed(), message);
    }

    /**
     * select with text
     *
     * @param selector
     * @param textItem
     */
    public void selectItemInDefaultDropdown(Object selector, String textItem) {
        waitForElementVisible(selector);
        Select select = new Select(findElement(selector));
        select.selectByVisibleText(textItem);
    }

    /**
     * kiem tra heading có display ko
     *
     * @param selector
     */
    public void checkHeading(Object selector) {
        assertTrueCondition(selector,findElement(selector).isDisplayed(), FailureHandling.STOP_ON_FAILURE, "Check heading is display");
    }
}
