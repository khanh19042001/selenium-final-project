package com.misa.kiemtra.page.page;

import com.misa.common.BaseConst;
import com.misa.common.BasePage;
import com.misa.constants.FailureHandling;
import com.misa.constants.FrameworkConst;
import com.misa.constants.LogType;
import com.misa.kiemtra.page.models.PersonModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.ArrayList;
import java.util.List;

public class FillInfoPage extends BasePage {

    public FillInfoPage(WebDriver driver) {
        webDriver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(webDriver, FrameworkConst.WAIT_EXPLICIT);
        PageFactory.initElements(factory, this);
    }

    /**
     * Điền thông tin user
     */
    public void FillInfoPerson(PersonModel personModel)
    {
        inputText(getByXpathDynamic(BaseConst.DYNAMIC_LABEL_TEXT_INPUT_FORM_2,"First Name"),"First Name",personModel.getFirstName());
        inputText(getByXpathDynamic(BaseConst.DYNAMIC_LABEL_TEXT_INPUT_FORM_2,"Last Name"),"Last Name",personModel.getLastName());
        inputText(getByXpathDynamic(BaseConst.DYNAMIC_LABEL_TEXT_INPUT_FORM_2,"Email"),"Email",personModel.getEmail());
        inputText(getByXpathDynamic(BaseConst.DYNAMIC_LABEL_TEXT_INPUT_FORM_2,"Username"),"Username",personModel.getUserName());
        inputText(getByXpathDynamic(BaseConst.DYNAMIC_LABEL_TEXT_INPUT_FORM_2,"Password"),"Password",personModel.getPassWord());
        if(verifyPrice()==true)
        {
            addReportInfo(LogType.VERIFY,"sum price success", "backToPage", null);

        }
    }

    /**
     * verify sum price
     * @return
     */
    public boolean verifyPrice() {
        String xpath = "//div[@class='order-summary__price']";
        List<Double> listPriceProduct = new ArrayList<>();
        List<WebElement> priceProducts = getListWebElement(getByLocator(xpath));
        for (int i=0;i<priceProducts.size();i++) {
            listPriceProduct.add(Double.valueOf(priceProducts.get(i).getText().replace("$", "")));
        }
        int sum = 0;
        for(int i=0;i<listPriceProduct.size();i++)
        {
            sum +=listPriceProduct.get(i);
        }

        String price = waitForElementVisible(getByXpathDynamic(xpathSumPrice)).getText();
        Double sumPrice= Double.valueOf(price.replace("US$",""));
        if(sum==sumPrice)
        {
            return true;
        }
        return false;
    }
    String xpathSumPrice = "//div[contains(@class,'total')]//h4";
}
