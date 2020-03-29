package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import tests.base.BaseTest;
import utils.Log;
import utils.PropertyManager;

import java.lang.reflect.Method;

public class HFTests extends BaseTest {

    @Severity(SeverityLevel.BLOCKER)
    @Feature("SignIn")
    @Story("Test Verify create acccount")
    @Test(priority = 1, description = "Create Account")
    public void signInTest(Method method) throws Exception {

        Log.startTestCase(method.getName());

        Log.info("========" + method.getName() + "Test is starting========");

        //Fetching Test Data using UserDetails ,which generates data using "Faker Library"
        UserDetails userDetails = new UserDetails();

        String name = userDetails.getFirstName();
        String surname = userDetails.getLastName();

        //Initialize elements by using PageFactory, Chain of Invocation
        page.GetInstance(HomePage.class).signInTestValidation(name, surname,
                userDetails.getPassword(), "1", "1", "2020", userDetails.getCompanyData(),
                userDetails.getCompanyAddress11(), userDetails.getCompanyAddress12(), userDetails.getCity(), userDetails.getState(),
                userDetails.getPostalCode(), userDetails.getOtherInfo(), userDetails.getPhoneNumber(), userDetails.getMobileNumber(), userDetails.getAlias());

        //*************ASSERTIONS***********************/
        String actualHeading = page.GetInstance(HomePage.class).getHeadingText();
        Assert.assertEquals("MY ACCOUNT", actualHeading, "Pls Check My Account Heading is wrong");

        String actualAccountHeading = page.GetInstance(HomePage.class).getAccountText();
        Assert.assertEquals(name + " " + surname, actualAccountHeading, "Pls Check Username  wrong");

        String actualInfoAccountHeading = page.GetInstance(HomePage.class).getInfoAccountText();
        Assert.assertTrue(actualInfoAccountHeading.contains("Welcome to your account."), "Pls Check InfoAccount   is wrong");

        Assert.assertTrue(page.GetInstance(HomePage.class).isLogoutDisplayed(), "Pls Check Logout button is not getting displayed");
        Assert.assertTrue(page.GetInstance(HomePage.class).containsExpectedURL("controller=my-account"), "Pls Check URL of My Account is not correct");

        Log.endTestCase(method.getName());
    }

    @Feature("SignIn")
    @Story("Test Verify Login using Existing Account")
    @Test(priority = 2, description = "Login using Existing Account")
    public void logInTest(Method method) throws Exception {

        Log.startTestCase(method.getName());

        Log.info("========" + method.getName() + "Test is starting========");
        String fullName = "Joe Black";

        PropertyManager propertyManager = new PropertyManager();
        String existingUserEmail = propertyManager.getProperty("username");
        String existingUserPassword = propertyManager.getProperty("password");

        //Chain of Invocation (Go to Login Page and then logInTest)
        String actualHeading = page.GetInstance(HomePage.class).clickSignIn().
                logInTest(existingUserEmail, existingUserPassword).getMyAccountHeading();

        //*************ASSERTIONS***********************/
        Assert.assertEquals("MY ACCOUNT", actualHeading, "Page is not routed to My Account Page after Login, Pls Check");

        String actualAccountText = page.GetInstance(MyAccount.class).getFullName();
        Assert.assertEquals(fullName, actualAccountText, "Pls Check, Looks like the username is incorrect");

        String actualinfoAccountText = page.GetInstance(MyAccount.class).getinfoAccount();
        Assert.assertEquals("Welcome to your account. Here you can manage all of your personal information and orders.", actualinfoAccountText, "Pls Check, Looks like the info Account Text is incorrect");

        Assert.assertTrue(page.GetInstance(MyAccount.class).isLogoutDisplayed(), "Pls Check, Logout link is not Displayed on My Account Page");
        Assert.assertTrue(page.GetInstance(BasePage.class).getUrl().contains("controller=my-account"), "Pls check, URL of the My Account page is incorrect");
        Log.endTestCase(method.getName());
    }

    @Feature("Checkout")
    @Story("Test Verify Checkout Flow")
    @Test(priority = 3, description = "Check Out Test case")
    public void checkoutTest(Method method) throws Exception {

        Log.startTestCase(method.getName());

        Log.info("========" + method.getName() + "Test is starting========");
        PropertyManager propertyManager = new PropertyManager();
        String existingUserEmail = propertyManager.getProperty("username");
        String existingUserPassword = propertyManager.getProperty("password");

        String productToShop = "Faded Short Sleeve T-shirts";

        //Chain of Invocation (Go to Login Page and then Clicking Women Link and then Adding Product in cart)
        page.GetInstance(HomePage.class).clickSignIn().
                logInTest(existingUserEmail, existingUserPassword).clickWomenLink().addproduct(productToShop);

        //*************ASSERTIONS***********************/
        String actualOrderConfirmatonText = page.GetInstance(CheckOutPage.class).getConfirmOrderHeaderText();
        Assert.assertTrue(actualOrderConfirmatonText.contains("SHOPPING-CART SUMMARY"), "Pls check looks like Order Confirmation Page is not loaded");

        Assert.assertTrue(page.GetInstance(CheckOutPage.class).isShippingStepTitleDisplayed(), "Pls Check Shipping Title is not getting displayed");
        Assert.assertTrue(page.GetInstance(CheckOutPage.class).isPaymentStepTitleDisplayed(), "Pls Check Payment Step Title is not getting displayed");

        String actualOrderCompletionLabelText = page.GetInstance(CheckOutPage.class).clickProceedToCheckout().clickProceedToCheckoutAfterAddressSelection().clickTnC().
                clickProceedToCheckoutAfterCarrierSelection().clickBankwirePaymentMode().clickConfirmOrder().getOrderCompletionLabelText();
        Assert.assertEquals("Your order on My Store is complete.", actualOrderCompletionLabelText, "Pls Check Order Completion Label is not correct");

        Assert.assertTrue(page.GetInstance(BasePage.class).getUrl().contains("controller=order-confirmation"), "Pls check , looks like Order Confirmation Url is correct or Order is not placed successfully");
        Log.endTestCase(method.getName());
    }
}
