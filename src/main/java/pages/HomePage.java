package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.util.Date;

public class HomePage extends BasePage {

    //*********Constructor*********/
    public HomePage(WebDriver driver) {
        super(driver);
    }

    //*********Web Elements By Using Page Factory*********
    @FindBy(id = "email_create")
    @CacheLookup
    private WebElement emailCreate;

    @FindBy(id = "SubmitCreate")
    @CacheLookup
    private WebElement submitCreate;

    @FindBy(xpath = "//form[@id='account-creation_form']/descendant::label[@for='id_gender2']")
    @CacheLookup
    private WebElement Mrs;

    @FindBy(id = "customer_firstname")
    @CacheLookup
    private WebElement customerFirstname;

    @FindBy(id = "customer_lastname")
    @CacheLookup
    private WebElement customerLastname;

    @FindBy(id = "passwd")
    @CacheLookup
    private WebElement password;

    @FindBy(id = "company")
    @CacheLookup
    private WebElement company;

    @FindBy(id = "address1")
    @CacheLookup
    private WebElement address1;

    @FindBy(id = "address2")
    @CacheLookup
    private WebElement address2;

    @FindBy(id = "city")
    @CacheLookup
    private WebElement city;

    @FindBy(xpath = "//select[@name='id_state']")
    @CacheLookup
    private WebElement idState;

    @FindBy(id = "postcode")
    @CacheLookup
    private WebElement postcode;

    @FindBy(id = "other")
    @CacheLookup
    private WebElement other;

    @FindBy(id = "phone")
    @CacheLookup
    private WebElement phone;

    @FindBy(id = "phone_mobile")
    @CacheLookup
    private WebElement phoneMobile;

    @FindBy(id = "alias")
    @CacheLookup
    private WebElement alias;

    @FindBy(id = "submitAccount")
    @CacheLookup
    private WebElement submitAccount;

    @FindBy(id = "passwd")
    @CacheLookup
    private WebElement passwd;

    @FindBy(id = "email")
    @CacheLookup
    private WebElement email;

    @FindBy(css = "h1")
    @CacheLookup
    private WebElement heading;

    @FindBy(xpath = "//div[@id='header_logo']/a/img")
    @CacheLookup
    private WebElement headerLogo;

    @FindBy(className = "logout")
    @CacheLookup
    private WebElement logout;

    @FindBy(xpath = "//a[@class='login']")
    @CacheLookup
    private WebElement login;

    @FindBy(className = "account")
    @CacheLookup
    private WebElement account;

    @FindBy(className = "info-account")
    @CacheLookup
    private WebElement infoaccount;


   /* @Step("Verify signInTest with firstName-->{0} ,lastName-->{1},password-->{2},dayValue-->{3},monthValue-->{4},yearValue-->{5},companyData-->{6}" +
            "address11-->{7},address12-->{8},cityValue-->{9},stateValue-->{10},postalCode-->{11},otherInfo-->{12},homePhoneNumber-->{13}" +
            "mobilePhoneNumber-->{14},addressAlias-->{15}")*/
   @Step("Verify signInTest")
    public void signInTestValidation(String firstName, String lastName, String password, String dayValue,
                                     String monthValue, String yearValue, String companyData, String address11, String address12,
                                     String cityValue, String stateValue, String postalCode, String otherInfo, String homePhoneNumber,
                                     String mobilePhoneNumber, String addressAlias) {

        click(login);
        String timestamp = String.valueOf(new Date().getTime());

        String email = "hf_challenge_" + timestamp + "@hf" + timestamp.substring(7) + ".com";
        setText(emailCreate, email);
        click(submitCreate);
        click(Mrs);
        setText(customerFirstname, firstName);
        setText(customerLastname, lastName);
        setText(passwd, password);
        setText(company, companyData);
        setText(address1, address11);
        setText(address2, address12);
        setText(city, cityValue);

        selectByVisibleText(idState, stateValue);
        setText(postcode, postalCode);
        setText(other, otherInfo);
        setText(phone, homePhoneNumber);
        setText(phoneMobile, mobilePhoneNumber);
        setText(alias, addressAlias);
        click(submitAccount);
        waitelementToBeClickable(heading);
        waitelementToBeClickable(headerLogo);
        waitelementToBeClickable(logout);
    }

    @Step("Click SignIn")
    //To chain LoginPage's methods here I return LoginPage by initializing its elements
    public LoginPage clickSignIn() throws Exception {
        click(login);
        return new PageGenerator(driver).GetInstance(LoginPage.class);
    }

    public String getHeadingText(){
        String text=getText(heading);

        return text;
    }

    public String getAccountText(){
        String text=getText(account);
        return text;
    }

    public String getInfoAccountText(){
        String text=getText(infoaccount);

        return text;
    }

    public boolean isLogoutDisplayed(){
        return isElementDisplayed(logout);
    }

    public boolean containsExpectedURL(String expectedURL){
        return containsURL(expectedURL);
    }



}