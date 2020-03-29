package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    //*********Constructor*********/
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //*********Web Elements By Using Page Factory*********
    @FindBy(id = "SubmitLogin")
    @CacheLookup
    private WebElement signIn;

    @FindBy(id = "email_create")
    @CacheLookup
    private WebElement registerUserEmailTxtbox;

    @FindBy(id = "email")
    @CacheLookup
    private WebElement emailTxtbox;

    @FindBy(id = "passwd")
    @CacheLookup
    private WebElement passwordTxtbox;

    @FindBy(css = "div.alert.alert-danger")
    @CacheLookup
    private WebElement errorMessageBox;

    @FindBy(id = "SubmitCreate")
    @CacheLookup
    private WebElement createAnAccountBtn;

    @Step("Enter email")
    public LoginPage enterEmailId(String email) {
        setText(emailTxtbox, email);
        return this;
    }

    @Step("Enter pwd")
    public LoginPage enterPassword(String pwd) {
        setText(passwordTxtbox, pwd);
        return this;
    }

    @Step("Click SignIn")
    public LoginPage clickSignIn() {
        click(signIn);
        return this;
    }

    @Step("Verify logInTest ")
    public MyAccount logInTest(String existingUserEmail, String existingUserPassword) throws Exception {
        enterEmailId(existingUserEmail);
        enterPassword(existingUserPassword);
        clickSignIn();
        return new PageGenerator(driver).GetInstance(MyAccount.class);
    }
}
