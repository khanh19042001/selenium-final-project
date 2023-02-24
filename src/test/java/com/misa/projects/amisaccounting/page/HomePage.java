package com.misa.projects.amisaccounting.page;

import com.misa.common.BaseConst;
import com.misa.common.BasePage;
import com.misa.constants.FailureHandling;
import com.misa.constants.FrameworkConst;
import com.misa.projects.amisaccounting.models.CustomFieldModel;
import com.misa.projects.amisaccounting.models.EmployeeModel;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;


public class HomePage extends BasePage {

    /**
     * Init a new instance
     *
     * @param driver : The WebDriver to interact with elements
     */
    public HomePage(WebDriver driver) {
        webDriver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(webDriver, FrameworkConst.WAIT_EXPLICIT);
        PageFactory.initElements(factory, this);
    }

    /**
     * Sau khi truy cập thành công vào page, click vào  Recruitment
     * @return
     */
    public RecruitmentPage goToRecruitment()
    {
        String recruitment="Recruitment";
        By xpath=getByXpathDynamic(BaseConst.DYNAMIC_SPAN_TYPE_FORM,recruitment);
        clickElement(xpath,recruitment);

        return new RecruitmentPage(webDriver);
    }

    /**
     * go to PIM
     */
    public void goToPIM() {
        String PIM = "PIM";
        String url = "https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList";
        By element = getByXpathDynamic(BaseConst.DYNAMIC_SPAN_TYPE_FORM, PIM);
        clickElement(element, PIM);
        verifyPageUrl(url, "check go to PIM");
    }

    /**
     * click element Configuration
     */
    public void clickConfiguration() {
        String configuration = "Configuration ";
        By element = getByXpathDynamic(BaseConst.DYNAMIC_SPAN_TYPE_FORM, configuration);
        clickElement(element, "Configuration ");
    }

    /**
     * click element Custom Fields
     */
    public void clickCustomFields() {
        String customField="Custom Fields";
        By element = getByXpathDynamic(BaseConst.DYNAMIC_UL_A_FORM,customField);
        clickElement(element, customField);
    }

    /**
     * click title
     */
    public void clickTitle(String title) {
        By element = getByXpathDynamic(BaseConst.DYNAMIC_A_TEXT_FORM, title);
        clickElement(element, title);
    }

    /**
     * click button delete
     *
     * @param name
     */
    /**
     * verify data input custom field model
     *
     * @param customFieldModel
     */
    public void verifyDataInputCustomFileModel(CustomFieldModel customFieldModel) {
        verifyValue(getByXpathDynamic(BaseConst.DYNAMIC_DIV_TEXT_FORM, customFieldModel.getName()), customFieldModel.getName());
        verifyValue(getByXpathDynamic(BaseConst.DYNAMIC_DIV_TEXT_FORM, customFieldModel.getScreen()), customFieldModel.getScreen());
        verifyValue(getByXpathDynamic(BaseConst.DYNAMIC_DIV_TEXT_FORM, customFieldModel.getType()), customFieldModel.getType());
    }

    /**
     * click select day
     *
     * @param day
     */

    public void selectDay(String day) {
        By element = getByXpathDynamic(BaseConst.DYNAMIC_DIV_TEXT_FORM, day);
        waitForElementClickableBy(element);
        clickElement(element, "select chose: " + day);
    }

    /**
     * click select month
     *
     * @param month
     */
    public void selectMonth(String month) {
        String classMonth = "month";
        By monthXpath = getByXpathDynamic(BaseConst.DYNAMIC_LI_ClASS_FORM, classMonth);
        clickElement(monthXpath, classMonth);

        By elementMonth = getByXpathDynamic(BaseConst.DYNAMIC_LI_TEXT_FORM, month);
        clickElement(elementMonth, month);
    }

    /**
     * click select year
     *
     * @param year
     */
    public void selectYear(String year) {
        String classYear = "year";
        By monthXpath = getByXpathDynamic(BaseConst.DYNAMIC_LI_ClASS_FORM, classYear);

        clickElement(monthXpath, classYear);

        By elementMonth = getByXpathDynamic(BaseConst.DYNAMIC_LI_TEXT_FORM, year);
        clickElement(elementMonth, year);
    }

    /**
     * select day, month, year(date)
     *
     * @param day
     * @param month
     * @param year
     */
    public void selectDate(String title, String day, String month, String year) {
        By xpathDate = getByXpathDynamic(BaseConst.DYNAMIC_DIV_CLASS_FORM, title);
        clickElement(xpathDate, "Date");

        selectMonth(month);
        selectYear(year);
        selectDay(day);
    }

    public HomePage inputInfoCustomField(CustomFieldModel customFieldModel) {

        clickButton("Add");
        inputText(getByXpathDynamic(BaseConst.DYNAMIC_LABEL_TEXT_INPUT_FORM, "Field Name"), "Field Name", customFieldModel.getName());
        clickElementCombo("Screen", customFieldModel.getScreen());
        clickElementCombo("Type", customFieldModel.getType());
        if (customFieldModel.getType().equals("Drop Down")) {
            inputText(getByXpathDynamic(BaseConst.DYNAMIC_LABEL_TEXT_INPUT_FORM, "Select Options"), "Select Options", customFieldModel.getSelect());
        }
        clickButton("Save");
        checkSaveSuccess("Save success");
        verifyDataInputCustomFileModel(customFieldModel);
        clickDelete(BaseConst.XPATH_ICON_DELETE,customFieldModel.getName());
        return new HomePage(webDriver);
    }

//    public List<CustomFields> getCustomFieldsList() {
//        List<CustomFields> customFieldsList = new ArrayList<>();
//        int totalRow = getListWebElement(getByXpathDynamic("//div[@class='oxd-table-body']/div")).size();
//        for (int i = 1; i <= totalRow; i++) {
//            String fieldsName = getCellByColumnNameAndRowNumber("Custom Field Name", String.valueOf(i));
//            String screen = getCellByColumnNameAndRowNumber("Screen", String.valueOf(i));
//            String type = getCellByColumnNameAndRowNumber("Field Type", String.valueOf(i));
//            CustomFields customFields = CustomFields.builder().fieldsName(fieldsName).screen(screen).type(type).build();
//            customFieldsList.add(customFields);
//        }
//        return customFieldsList;
//    }
//    public void verifyCustomFields(CustomFields customFields) {
//        waitForElementVisible(getByXpathDynamic("//div[normalize-space()='Custom Field Name']"));
//        List<CustomFields> customFieldsList = getCustomFieldsList();
//        List<String> customFieldsListName = new ArrayList<>();
//        customFieldsList.forEach(customFields1 ->customFieldsListName.add(customFields1.getFieldsName()));
//        for (String c:
//                customFieldsListName) {
//            if (c.equals(customFields.getFieldsName())) assertTrueCondition(null,c.equals(customFields.getFieldsName()), FailureHandling.CONTINUE_ON_FAILURE, "Verify fields result");
//        }
//        clickDelete(customFields.getFieldsName());
//    }

    public HomePage createEmployee(EmployeeModel employeeModel) {
        clickTitle("Add Employee");
        inputText(getByXpathDynamic(BaseConst.DYNAMIC_INPUT_TYPE_FORM, "First Name"), "First Name", employeeModel.getFirstName());
        inputText(getByXpathDynamic(BaseConst.DYNAMIC_INPUT_TYPE_FORM, "Middle Name"), "Middle Name", employeeModel.getMiddleName());
        inputText(getByXpathDynamic(BaseConst.DYNAMIC_INPUT_TYPE_FORM, "Last Name"), "Last Name", employeeModel.getLastName());
        inputText(getByXpathDynamic(BaseConst.DYNAMIC_LABEL_TEXT_INPUT_FORM, "Employee Id"), "Employee Id", employeeModel.getEmployeeId());
        clickButton("Save");
        sleep(2);
        inputText(getByXpathDynamic(BaseConst.DYNAMIC_LABEL_TEXT_INPUT_FORM, "Nickname"), "Nickname", employeeModel.getNickName());
        inputText(getByXpathDynamic(BaseConst.DYNAMIC_LABEL_TEXT_INPUT_FORM, "Other Id"), "Other Id", employeeModel.getOtherId());
        inputText(getByXpathDynamic(BaseConst.DYNAMIC_LABEL_TEXT_INPUT_FORM, "Driver"), "Driver's License Number", employeeModel.getLicenseNumber());
        selectDate("License Expiry", employeeModel.getDayLicense(), employeeModel.getMonthLicense(), employeeModel.getYearLicense());
        inputText(getByXpathDynamic(BaseConst.DYNAMIC_LABEL_TEXT_INPUT_FORM, "SSN Number"), "SSN Number", employeeModel.getSsnNumber());
        inputText(getByXpathDynamic(BaseConst.DYNAMIC_LABEL_TEXT_INPUT_FORM, "SIN Number"), "SIN Number", employeeModel.getSinNumber());
        clickElementCombo("Nationality", employeeModel.getNationality());
        clickElementCombo("Marital Status", employeeModel.getStatus());
        selectDate("Birth", employeeModel.getDayOfBirth(), employeeModel.getMonthOfBirth(), employeeModel.getYearOfBirth());
        clickElement(getByXpathDynamic(BaseConst.DYNAMIC_LABEL_TEXT_FORM, employeeModel.getGender()), employeeModel.getGender());
        clickButton("Save");
        return new HomePage(webDriver);
    }

    public HomePage searchEmployee(EmployeeModel employeeModel) {
        clickTitle("Employee List");

        inputText(getByXpathDynamic(BaseConst.DYNAMIC_LABEL_TEXT_INPUT_FORM, "Employee Id"), "Employee Id", employeeModel.getEmployeeId());
        clickButton("Search");
        verifyValue(getByXpathDynamic(BaseConst.DYNAMIC_DIV_FORM, employeeModel.getEmployeeId()), employeeModel.getEmployeeId());
        clickDelete(BaseConst.XPATH_ICON_DELETE,employeeModel.getEmployeeId());
        return new HomePage(webDriver);
    }

    public HomePage reportEmployee() {
        clickTitle("Reports");
        clickButton("Add");
        inputText(getByXpathDynamic(BaseConst.DYNAMIC_LABEL_TEXT_INPUT_FORM, "Report Name"), "Report Name", "khanh");
        clickElementCombo("Selection Criteria", "Education");
        clickElementCombo("Include", "Past Employees Only");
        clickElementCombo("Field Group", "Personal");
//        clickElementCombo("Display Field", "Employee Id");
        clickElement(getByLocator(xpathButtonPlush), "Button Plush");
        clickButton("Save");
        return new HomePage(webDriver);
    }
    String xpathButtonPlush = "(//button[@type='button'])[3]";

}
