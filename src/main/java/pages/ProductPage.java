package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {

    //*********Constructor*********/
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    //*********Web Elements By Using Page Factory*********/

    @FindBy(xpath = "*//*[@name='Submit']")
    @CacheLookup
    private WebElement addToCartBtn;

    @FindBy(xpath = "//a[@class and @title='Proceed to checkout']")
    @CacheLookup
    private WebElement proceedToCheckout;

    @Step("Click Add To Cart")
    public void clickAddToCart() {
        clickWithMouseActions(addToCartBtn);
    }

    @Step("Click Proceed To Checkout")
    public void clickProceedToCheckout() {
        click(proceedToCheckout);
    }

    @Step("Add Product to Cart")
    public CheckOutPage addproduct(String productName) throws Exception {
        String xpath = "(//a[@title='xxxxx'])[2]";
        WebElement element = prepareWebElementWithDynamicXpath(xpath, productName);
        click(element);
        clickAddToCart();
        clickProceedToCheckout();
        return new PageGenerator(driver).GetInstance(CheckOutPage.class);
    }

}
