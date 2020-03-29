package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class CheckOutPage extends BasePage {

    //*********Constructor*********/
    public CheckOutPage(WebDriver driver) {
        super(driver);
    }

    //*********Web Elements By Using Page Factory*********

    @FindBy(css = "a.standard-checkout[title='Proceed to checkout']")
    @CacheLookup
    private WebElement proceedToCheckout;

    @FindBy(xpath = "//a[@title='Proceed to checkout']")
    @CacheLookup
    private WebElement cartProceedToCheckout;

    @FindBy(name = "processAddress")
    @CacheLookup
    private WebElement cartProcssAddressProceedToCheckout;

    @FindBy(name = "processCarrier")
    @CacheLookup
    private WebElement cartProcessCarrierProceedToCheckout;

    @FindBy(id = "uniform-cgv")
    @CacheLookup
    private WebElement checkboxTnC;

    @FindBy(className = "bankwire")
    @CacheLookup
    private WebElement bankwirePaymentMode;

    @FindBy(xpath = "//*[@id='cart_navigation']/button")
    @CacheLookup
    private WebElement confirmOrderBtn;

    @FindBy(css = "h1")
    @CacheLookup
    private WebElement orderConfirmationHeader;

    @FindBy(xpath = "//li[@class='step_todo four']")
    @CacheLookup
    private WebElement shippingStepTitle;

    @FindBy(xpath = "//li[@id='step_end']")
    @CacheLookup
    private WebElement paymentStepTitle;

    @FindBy(xpath = "//*[@class='cheque-indent']/strong")
    @CacheLookup
    private WebElement orderCompletionLabel;

    @Step("Proceed To Checkout")
    public CheckOutPage clickProceedToCheckout() {
        click(proceedToCheckout);
        return this;
    }

    public CheckOutPage clickCartProceedToCheckout() {
        click(cartProceedToCheckout);
        return this;
    }

    @Step("ProceedToCheckout After AddressSelection")
    public CheckOutPage clickProceedToCheckoutAfterAddressSelection() {
        click(cartProcssAddressProceedToCheckout);
        return this;
    }

    @Step("ProceedToCheckout After CarrierSelection")
    public CheckOutPage clickProceedToCheckoutAfterCarrierSelection() {
        click(cartProcessCarrierProceedToCheckout);
        return this;
    }

    @Step("Click TnC")
    public CheckOutPage clickTnC() {
        click(checkboxTnC);
        return this;
    }

    @Step("Click Bankwire PaymentMode")
    public CheckOutPage clickBankwirePaymentMode() {
        click(bankwirePaymentMode);
        return this;
    }

    @Step("Click ConfirmOrder")
    public CheckOutPage clickConfirmOrder() {
        click(confirmOrderBtn);
        return this;
    }

    public String getConfirmOrderHeaderText() {
        waitelementToBeVisible(orderConfirmationHeader);
        return getText(orderConfirmationHeader);

    }

    public String getOrderCompletionLabelText() {

        return orderCompletionLabel.getText();

    }


    public boolean isShippingStepTitleDisplayed(){
        return isElementDisplayed(shippingStepTitle);
    }

    public boolean isPaymentStepTitleDisplayed(){
        return isElementDisplayed(paymentStepTitle);
    }
}
