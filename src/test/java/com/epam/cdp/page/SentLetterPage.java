package com.epam.cdp.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SentLetterPage extends AbstractPage {

    @FindBy(css = "div.nH.V8djrc.byY h2.hP")
    private WebElement subject;

    public SentLetterPage(WebDriver driver) {
        super(driver);
    }

    public String getSubjectOfSentLetter(){
        waitForElementVisible(subject);
        return subject.getText();
    }
}
