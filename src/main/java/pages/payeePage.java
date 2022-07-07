package pages;

public class payeePage {
    private String menuButton_xpath = "//*[@id=\"left\"]/div[1]/div/button";
    private String payeeButton_xpath = "//*[@id=\"left\"]/div[1]/div/div[3]/section/div[2]/nav[1]/ul/li[3]/a/span";
    private String addButtonMain_class = "js-add-payee";
    private String payeeHeader_css = "header[aria-label='Payees page. Attention access key one is available to close the Payees page.']";
    private String searchPayee_css = "input[placeholder='Search payees']";
    private String nameColumn_class = "js-payee-name-column";
    private String lastPaidColumn_class = "js-payee-last-paid-column";
    private String payeeName_id = "ComboboxInput-apm-name";
    private String payeeBankNumber_id = "apm-bank";
    private String payeeBranchNumber_id = "apm-branch";
    private String payeeAccountNumber_id = "apm-account";
    private String payeeSuffixNumber_id = "apm-suffix";
    private String payeeNameHover_id = "ComboboxList-apm-name";
    private String payeeAddButton_class = "js-submit";
    private String addPayeeSuccessMsg_class = "message";
    private String addedPayeeName_class = "js-payee-name";
    private String addedPayeeAccountNumber_class = "js-payee-account";
    private String validationError_class = "error-header";

    public String getMenuButton_xpath() {
        return menuButton_xpath;
    }

    public String getPayeeButton_xpath() {
        return payeeButton_xpath;
    }

    public String getAddButtonMain_class() {
        return addButtonMain_class;
    }

    public String getPayeeHeader_css() {
        return payeeHeader_css;
    }

    public String getSearchPayee_css() {
        return searchPayee_css;
    }

    public String getNameColumn_class() {
        return nameColumn_class;
    }

    public String getLastPaidColumn_class() {
        return lastPaidColumn_class;
    }

    public String getPayeeName_id() {
        return payeeName_id;
    }

    public String getPayeeBankNumber_id() {
        return payeeBankNumber_id;
    }

    public String getPayeeBranchNumber_id() {
        return payeeBranchNumber_id;
    }

    public String getPayeeAccountNumber_id() {
        return payeeAccountNumber_id;
    }

    public String getPayeeSuffixNumber_id() {
        return payeeSuffixNumber_id;
    }

    public String getPayeeNameHover_id() {
        return payeeNameHover_id;
    }

    public String getPayeeAddButton_class() {
        return payeeAddButton_class;
    }

    public String getAddPayeeSuccessMsg_class() {
        return addPayeeSuccessMsg_class;
    }

    public String getAddedPayeeName_class() {
        return addedPayeeName_class;
    }

    public String getAddedPayeeAccountNumber_class() {
        return addedPayeeAccountNumber_class;
    }

    public String getValidationError_class() {
        return validationError_class;
    }
}
