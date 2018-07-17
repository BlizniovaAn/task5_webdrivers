package com.epam.cdp.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewLetterPage extends MainPage {
    @FindBy(name = "to")
    WebElement toInput;

    @FindBy(name = "subjectbox")
    WebElement subjectbox;

    @FindBy(css = "div.Am.Al.editable.LW-avf")
    WebElement bodyInput;

    @FindBy(css = "img.Ha")
    WebElement cross;

    @FindBy(css = "div.T-I.J-J5-Ji.aoO.T-I-atl.L3")
    WebElement sendButton;

    public NewLetterPage(WebDriver driver) {
        super(driver);
    }

    public NewLetterPage populateToInput(String to){
        waitForElementVisible(toInput);
        toInput.sendKeys(to);
        return this;
    }

    public NewLetterPage populateSubjectbox(String subject){
        waitForElementVisible(subjectbox);
        subjectbox.sendKeys(subject);
        return this;
    }

    public NewLetterPage populateBody(String body){
        waitForElementVisible(bodyInput);
        bodyInput.sendKeys(body);
        return this;
    }

    public NewLetterPage closeLetter(){
        cross.click();
        return this;
    }

    public NewLetterPage clickSendButton(){
        sendButton.click();
        return this;
    }

    public WebElement getToInput() {
        return toInput;
    }

    public WebElement getSubjectbox() {
        return subjectbox;
    }

    public WebElement getBodyInput() {
        return bodyInput;
    }

    public WebElement getCross() {
        return cross;
    }

    public WebElement getSendButton() {
        return sendButton;
    }
}