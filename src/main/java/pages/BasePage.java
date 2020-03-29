package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage extends PageGenerator {

    //*********Constructor*********/
    public BasePage(WebDriver driver) {
        super(driver);
    }

    public void click(WebElement element){
        waitelementToBeClickable(element);
        element.click();
    }

    public void clickWithMouseActions(WebElement element){
        waitelementToBeVisible(element);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build();
        waitelementToBeClickable(element);
        element.click();
    }

    public void setText(WebElement element,String text){
        waitelementToBeClickable(element);
        element.click();
        element.sendKeys(text);
    }

    public void selectByVisibleText(WebElement webElement,String value){
        Select select = new Select(webElement);
        select.selectByVisibleText(value);
    }

    public String getText(WebElement element){
        waitelementToBeVisible(element);
        return element.getText();
    }

    public boolean isElementDisplayed(WebElement element){

        return element.isDisplayed();
    }

    public boolean containsURL(String url){

        return driver.getCurrentUrl().contains(url);
    }


    /**
     * Utility function to switch to recent Open Window
     */
    public void switchToWindow() {
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }

    /**
     * Utility Function to switch between Windows
     *
     * @param windowName
     */
    public void switchToWindow(String windowName) {
        driver.switchTo().window(windowName);

    }

    /**
     * Explicit Wait Utility function to wait for particular element to be clickable
     *
     * @param element
     */
    public void waitelementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 45);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }


    /**
     *  Explicit Wait Utility function to wait for particular element to be visible
     * @param element
     */
    public void waitelementToBeVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 45);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Function to get URL of the current page
     * @return
     */
    public String getUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Function to get Xpath of Dynamic Element
     * @param xpathValue
     * @param substitutionValue
     * @return
     */
    public WebElement prepareWebElementWithDynamicXpath(String xpathValue, String substitutionValue) {
        return driver.findElement(By.xpath(xpathValue.replaceAll("xxxxx", substitutionValue)));
    }

}