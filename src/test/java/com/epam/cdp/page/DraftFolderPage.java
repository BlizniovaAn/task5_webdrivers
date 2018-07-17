package com.epam.cdp.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.epam.cdp.util.Constants.DRAFT;
import static com.epam.cdp.util.Constants.OPEN_DRAFT_FOLDER_URL;

public class DraftFolderPage extends MainPage{

    @FindBy(partialLinkText = DRAFT)
    private WebElement draftLink;

    @FindBy(xpath = "//div[@class='ae4 UI']//tr[@class='zA yO']")
    private WebElement draftLetter;

    @FindBy(css = "div[gh='tm'] div.J-J5-Ji.J-JN-M-I-Jm span.T-Jo[role='checkbox']")
    private WebElement selectAllCheckbox;

    @FindBy(css = "div.T-I.J-J5-Ji.aFh.T-I-ax7.W6eDmd[role='button']>div")
    private WebElement removeAlldraftsButton;

    @FindBy(css = "div[role='main'] table.cf.TB td:first-child")
    private WebElement tableWithLetters;

    public DraftFolderPage(WebDriver driver) {
        super(driver);
    }

    public DraftFolderPage openDraftFolder(){
        driver.get(OPEN_DRAFT_FOLDER_URL);
        return this;
    }

    public DraftFolderPage checkSelectAllCheckbox(){
        waitForElementVisible(selectAllCheckbox);
        selectAllCheckbox.click();
        return this;
    }

    public DraftFolderPage clickRemoveAllbutton(){
        waitForElementVisible(removeAlldraftsButton);
        removeAlldraftsButton.click();
        return this;
    }

     public DraftFolderPage selectDraftLetter(){
        waitForElementClickable(draftLetter);
        draftLetter.click();
        return this;
     }

    public WebElement getDraftLetter() {
        waitForElementClickable(draftLetter);
        return draftLetter;
    }

    public WebElement getDraftLink() {
        waitForElementVisible(draftLink);
        return draftLink;
    }

    public WebElement getSelectAllCheckbox() {
        return selectAllCheckbox;
    }

    public WebElement getRemoveAlldraftsButton() {
        return removeAlldraftsButton;
    }

    public WebElement getTableWithLetters() {
        waitForElementVisible(tableWithLetters);
        return tableWithLetters;
    }
}
