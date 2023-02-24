package com.misa.kiemtra.page.page;

import com.misa.common.BaseConst;
import com.misa.common.BasePage;
import com.misa.constants.FrameworkConst;
import com.misa.kiemtra.page.models.ComputerModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ComputerPage extends BasePage {

    public ComputerPage(WebDriver driver) {
        webDriver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(webDriver, FrameworkConst.WAIT_EXPLICIT);
        PageFactory.initElements(factory, this);
    }

    /**
     * go to page computer
     *
     * @return
     */
    public ComputerPage goToPage() {
        String URL = "https://computer-database.gatling.io/computers";
        goToURL(URL);
        verifyPageUrl(URL, "check go to page");
        return new ComputerPage(webDriver);
    }


    /**
     * add new computer
     *
     * @param computerModel
     * @return
     */
    public ComputerPage addNewComputer(ComputerModel computerModel) {
        clickElement(getByXpathDynamic(BaseConst.DYNAMIC_A_TEXT_FORM_2,"Add"), "Click button Add");
        inputText(getByXpathDynamic(BaseConst.DYNAMIC_LABEL_TEXT_INPUT_FORM_2, "Computer name"), "Computer name", computerModel.getName());
        inputText(getByXpathDynamic(BaseConst.DYNAMIC_LABEL_TEXT_INPUT_FORM_2, "Introduced"), "Introduced", computerModel.getIntroduced());
        inputText(getByXpathDynamic(BaseConst.DYNAMIC_LABEL_TEXT_INPUT_FORM_2, "Discontinued"), "Discontinued", computerModel.getDiscontinued());
        selectItemInDefaultDropdown(getByXpathDynamic(selectXpath), computerModel.getCompany());
        clickElement(getByXpathDynamic(BaseConst.DYNAMIC_LABEL_TEXT_INPUT_FORM_2, "Company"), "Click button Create");
        checkHeading(getByXpathDynamic(BaseConst.DYNAMIC_DIV_CLASS_FORM_2, "message"));
        return new ComputerPage(webDriver);
    }

    String selectXpath = "//select[@name='company']";
}
