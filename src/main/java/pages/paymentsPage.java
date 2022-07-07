package pages;

public class paymentsPage {
    private String payOrTransferButton_class = "js-main-menu-paytransfer";
    private String fromAccount_css = "button[data-monitoring-label='Transfer Form From Chooser']";
    private String toAccount_css = "button[data-monitoring-label='Transfer Form To Chooser']";
    private String toAccountSearchBox_class = "searchContainer-1-1-75";
    private String amountTextBox_id = "field-bnz-web-ui-toolkit-9";
    private String transferButton_css = "button[data-monitoring-label='Transfer Form Submit']";
    private String paymentSuccessfulMessage_class = "message";
    private String everydayAccountBalance_xpath = "//*[@id=\"account-ACC-1\"]/div[2]/span[3]";
    private String billsAccountBalance_xpath = "//*[@id=\"account-ACC-5\"]/div[2]/span[3]";

    public String getPayOrTransferButton_class() {
        return payOrTransferButton_class;
    }

    public String getFromAccount_css() {
        return fromAccount_css;
    }

    public String getToAccount_css() {
        return toAccount_css;
    }

    public String getToAccountSearchBox_class() {
        return toAccountSearchBox_class;
    }

    public String getAmountTextBox_id() {
        return amountTextBox_id;
    }

    public String getTransferButton_css() {
        return transferButton_css;
    }

    public String getPaymentSuccessfulMessage_class() {
        return paymentSuccessfulMessage_class;
    }

    public String getEverydayAccountBalance_xpath() {
        return everydayAccountBalance_xpath;
    }

    public String getBillsAccountBalance_xpath() {
        return billsAccountBalance_xpath;
    }
}
