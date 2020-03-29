package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class MyAccount extends BasePage {

    //*********Constructor*********/
    public MyAccount(WebDriver driver) {
        super(driver);
    }

    //*********Web Elements By Using Page Factory*********
    @FindBy(className ="page-heading")
    @CacheLookup
    private WebElement myAccountHeading;

    @FindBy(className = "account")
    @CacheLookup
    private WebElement fullName;

    @FindBy(className = "info-account")
    @CacheLookup
    private WebElement infoAccount;

    @FindBy(className = "logout")
    @CacheLookup
    private WebElement logout;

    @FindBy(linkText = "Women")
    @CacheLookup
    private WebElement womenLink;


    public String getMyAccountHeading() {
        return getText(myAccountHeading);
    }

    @Step("Clicked Women Link")
    public ProductPage clickWomenLink() throws Exception {
            click(womenLink);
            return new PageGenerator(driver).GetInstance(ProductPage.class);
    }

    public String getFullName() {
        return getText(fullName);
    }

    public String getinfoAccount() {
        return getText(infoAccount);
    }

    public boolean isLogoutDisplayed() {
        return isElementDisplayed(logout);
    }
}
