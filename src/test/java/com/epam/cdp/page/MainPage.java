package com.epam.cdp.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.epam.cdp.util.Constants.DRAFT_LINK_TEXT_1;


public class MainPage extends AbstractPage {

    @FindBy(css = "a.gb_b.gb_db.gb_R")
    private WebElement webDriverIcon;

    @FindBy(xpath = "//div[@class='z0']//div[@class='T-I J-J5-Ji T-I-KE L3']")
    private WebElement writeButton;

    @FindBy(linkText = DRAFT_LINK_TEXT_1)
    private WebElement draftLink1;

    @FindBy(css = "a[href*='https://accounts.google.com/SignOutOptions']")
    private WebElement signOutIcon;

    @FindBy(css = "a[href*='https://accounts.google.com/Logout']")
    private WebElement signOutLink;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage clickWriteButton(){
        waitForElementClickable(writeButton);
        writeButton.click();
        return this;
    }

    public MainPage selectSignoutIcon(){
        waitForElementEnabled(signOutIcon);
        signOutIcon.click();
        return this;
    }

    public void signOut(){
        signOutLink.click();
    }

    public WebElement getWebDriverIcon() {
        waitForElementEnabled(webDriverIcon);
        return webDriverIcon;
    }

    public WebElement getWriteButton() {
        return writeButton;
    }

    public WebElement getDraftLink1() {
        waitForElementEnabled(draftLink1);
        return draftLink1;
    }

    public WebElement getSignOutIcon() {
        return signOutIcon;
    }

    public WebElement getSignOutLink() {
        return signOutLink;
    }

}
