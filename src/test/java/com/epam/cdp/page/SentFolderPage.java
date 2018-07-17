package com.epam.cdp.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SentFolderPage extends MainPage {

    @FindBy(linkText = "Отправленные")
    WebElement sentFolderLink;

    @FindBy(css = "div[role='main'] table.F.cf.zt tr:first-child")
    WebElement sentLetter;

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
}
