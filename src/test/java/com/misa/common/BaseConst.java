package com.misa.common;

public class BaseConst {

    //region DYNAMIC BASE LOCATOR

    //region ComboBox
    public static String DYNAMIC_INPUT_TYPE_FORM = "//input[contains(@placeholder,'%s')]";
    public static String DYNAMIC_LABEL_TEXT_INPUT_FORM = "//label[contains(text(),'%s')]/following::input[1]";
    public static String DYNAMIC_BUTTON_TYPE_FORM = "//button[normalize-space()='%s']";
    public static String DYNAMIC_BUTTON_TEXT_FORM="//button[text()='%s']";
    public static String DYNAMIC_SPAN_TYPE_FORM = "//span[text()='%s']";
    public static String DYNAMIC_DIV_LABEL_FORM = "//label[contains(text(),'%s')]/following::div[@class='oxd-select-wrapper'][1]";
    public static String DYNAMIC_DIV_TEXT_FORM = "//div[text()='%s']";
    public static String DYNAMIC_LI_ClASS_FORM = "//li[contains(@class,'%s')]";
    public static String DYNAMIC_LI_TEXT_FORM = "//li[contains(text(),'%s')]";
    public static String DYNAMIC_DIV_CLASS_FORM="//label[contains(text(),'%s')]/following::div[contains(@class,'date-input')][1]";
    public static String DYNAMIC_LABEL_TEXT_FORM="//label[text()='%s']";
    public static String DYNAMIC_A_TEXT_FORM="//a[text()='%s']";
    public static String DYNAMIC_DIV_FORM="//div[text()='Id']//following::div[text()='%s']";
    public static String DYNAMIC_UL_A_FORM="//ul[@role='menu']//a[text()='%s']";
    public static String DYNAMIC_DIV_SPAN_TEXT_FORM="//div[text()='Job Title']//span[text()='%s']";
    public static String XPATH_ICON_DELETE="(//div[text()='%s']/following::div/button)[1]";
    // endregion

    //redergin bai kiem tra 2
    public static String DYNAMIC_LABEL_TEXT_INPUT_FORM_2 = "//label[contains(text(),'%s')]/following::input[1]";
    public static String DYNAMIC_DIV_CLASS_FORM_2="//div[contains(@class,'%s')]";
    public static String DYNAMIC_A_TEXT_FORM_2="//a[contains(text(),'%s')]";
    public static String DYNAMIC_A_CLASS_FORM_2="(//a[contains(@class,'add')])[%s]";
    public static String DYNAMIC_BUTTON_TYPE_FORM_2= "//button[normalize-space()='%s']";
    public static String DYNAMIC_SPAN_TEXT_FORM_2 = "//span[text()='%s']";

    public enum FilterOption {
        EQUAL("opt_equal"), NOT_EQUAL("opt_not_equal"), CONTAIN("opt_contain"), NOT_CONTAIN("opt_not_contain"), EMPTY("opt_empty"), FILLED("opt_filled"), SMALLER("opt_smaller"), SMALLER_EQUAL("opt_smaller_equal"), GREATER("opt_greater"), GREATER_EQUAL("opt_greater_equal"), START_WITH("opt_start_with"), END_WITH("opt_end_with");

        private String name;
        private FilterOption(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }
}
