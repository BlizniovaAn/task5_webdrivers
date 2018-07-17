package com.epam.cdp.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SentFolderPage extends MainPage {

    @FindBy(linkText = "Отправленные")
    private WebElement sentFolderLink;

    @FindBy(css = "div[role='main'] table.F.cf.zt tr:first-child")
    private WebElement sentLetter;

    @FindBy(css = "div[role='main'] table.F.cf.zt tr:first-child div.oZ-jc.T-Jo.J-J5-Ji")
    private WebElement checkboxOfLastSentLetter;

    @FindBy(css = "div[title='Удалить'][role='button']")
    private WebElement removeLetterButton;

    @FindBy(xpath = "//div[@class = 'Kj-JD'][@role = 'alertdialog']")
    private WebElement alertDialog;

    @FindBy(name = "ok")
    private WebElement okButton;

    public SentFolderPage(WebDriver driver) {
        super(driver);
    }

    public SentFolderPage openPage(){
        waitForElementEnabled(sentFolderLink);
        sentFolderLink.click();
        return this;
    }

    public WebElement getSentFolderLink() {
        return sentFolderLink;
    }

    public WebElement getSentLetter() {
        waitForElementVisible(sentLetter);
        return sentLetter;
    }

    public SentFolderPage openLastSentLetter(){
        waitForElementVisible(sentLetter);
        sentLetter.click();
        return this;
    }

    public SentFolderPage checkCheckboxOfLastSentLetter(){
        waitForElementVisible(checkboxOfLastSentLetter);
        checkboxOfLastSentLetter.click();
        return this;
    }

    public SentFolderPage clickRemoveLetterButton() throws InterruptedException {
        Thread.sleep(8000);
        //waitForElementVisible(removeLetterButton);
        removeLetterButton.click();
        return this;
    }

    public WebElement getAlertDialog(){
        waitForElementVisible(alertDialog);
        return alertDialog;
    }

    public void clickOkButton(){
        okButton.click();
    }

}
