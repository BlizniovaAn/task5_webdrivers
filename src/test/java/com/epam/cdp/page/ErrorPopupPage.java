package com.epam.cdp.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ErrorPopupPage extends AbstractPage {

    @FindBy(xpath = "//div[@role = 'alertdialog']")
    protected WebElement alertDialog;

    @FindBy(xpath = "//span[@role = 'heading']")
    protected WebElement errorSpan;

    @FindBy(xpath = "//div[@class = 'Kj-JD-Jz']")
    protected WebElement errorMessage;

    @FindBy(name = "ok")
    protected WebElement okButton;

    public ErrorPopupPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getAlertDialog(){
        waitForElementVisible(alertDialog);
        return alertDialog;
    }

    public String getErrorSpanText(){
        return errorSpan.getText();
    }

    public String getErrorMessage(){
        return errorMessage.getText();
    }

    public void clickOKButton(){
        okButton.click();
    }

}
