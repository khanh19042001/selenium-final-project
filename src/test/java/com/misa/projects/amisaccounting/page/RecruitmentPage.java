package com.misa.projects.amisaccounting.page;

import com.misa.common.BaseConst;
import com.misa.common.BasePage;
import com.misa.constants.FrameworkConst;
import com.misa.projects.amisaccounting.models.VacanciesModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecruitmentPage extends BasePage {

    /**
     * Init a new instance
     *
     * @param driver : The WebDriver to interact with elements
     */
    public RecruitmentPage(WebDriver driver) {
        webDriver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(webDriver, FrameworkConst.WAIT_EXPLICIT);
        PageFactory.initElements(factory, this);
    }

    /**
     * Click vào Vacancies
     */

    public void accessVacancies() {
        String vacancies = "Vacancies";
        By xpath = getByXpathDynamic(BaseConst.DYNAMIC_A_TEXT_FORM, vacancies);
        clickElement(xpath, vacancies);
    }

    /**
     * input vào Hiring Manager khi chỉ nhập 1 chữ cái
     *
     * @param text
     */
    public void inputInHiringManager(String text) {
        String xpath = "(//div[@role='listbox']//span)[1]";
        inputText(getByXpathDynamic(BaseConst.DYNAMIC_LABEL_TEXT_INPUT_FORM, "Hiring Manager"), "Hiring Manager", text);
        waitForElementVisible(getByXpathDynamic(xpath, ""));
        clickElement(findElement(xpath), "Hiring Manager");
    }

    /**
     * click vào icon Sort
     */
    public void clickIconSort() {
        String xpath = "//div[text()='Job Title']//i";
        clickElement(getByXpathDynamic(xpath), "Icon Sort");
    }

    /**
     * Click sort DESC và ASC
     *
     * @param text
     */
    public void clickSort(String text) {
        String xpath = String.format(BaseConst.DYNAMIC_DIV_SPAN_TEXT_FORM, text);
        clickElement(getByXpathDynamic(xpath), text);
    }

    /**
     * kiểm tra sort
     */

    public void verifyCheckSort(String text) {
        String xpath = "//div[@class='oxd-table-card']/child::div/child::div[3]";
        List<String> obtainedList = new ArrayList<>();
        List<WebElement> elementList = getListWebElement(getByXpathDynamic(xpath));
        for (WebElement we : elementList) {
            obtainedList.add(we.getText());
        }
        List<String> sortedList = new ArrayList<>();
        for (String s : obtainedList) {
            sortedList.add(s);
        }
        if (text.equals("Ascending")) {
            Collections.sort(sortedList);
        } else {
            Collections.sort(sortedList);
            Collections.reverse(sortedList);
        }
        Assert.assertEquals(sortedList, obtainedList, "verify sort list");
    }

    public RecruitmentPage createVacancies(VacanciesModel vacanciesModel) {
        clickButton("Add");
        inputText(getByXpathDynamic(BaseConst.DYNAMIC_LABEL_TEXT_INPUT_FORM, "Vacancy Name"), "Vacancy Name", vacanciesModel.getVacanciesName());
        clickElementCombo("Job Title", vacanciesModel.getJob());
        inputInHiringManager(vacanciesModel.getHiringManager());
        inputText(getByXpathDynamic(BaseConst.DYNAMIC_LABEL_TEXT_INPUT_FORM, "Number"), "Number", vacanciesModel.getNumber());
        clickButton("Save");
        //thêm verify
//        checkSaveSuccess("Save success");

        return new RecruitmentPage(webDriver);
    }

    public RecruitmentPage sortVacancies(String text) {
        clickIconSort();
        clickSort(text);
        verifyCheckSort(text);
        return new RecruitmentPage(webDriver);
    }

    public RecruitmentPage deleteVacancies(VacanciesModel vacanciesModel) {
        clickDelete(BaseConst.XPATH_ICON_DELETE, vacanciesModel.getVacanciesName());
        return new RecruitmentPage(webDriver);
    }
}
