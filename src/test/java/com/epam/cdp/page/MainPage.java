package com.epam.cdp.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    @FindBy(css = "div[class='ajl aib aZ6'] .n6")
    WebElement moreBox;

    @FindBy(xpath = "//div[@class = 'l-u-Ab-zb-j']//div[@class = 'l-u-Ab-zb l-u-Ab-ul']")
    WebElement pic;

    @FindBy(xpath = "//div[@class = 'h-sb-Ic h-R-d a-c-d'] [@data-tooltip = 'Удалить']")
    WebElement trash;

    @FindBy(css = ".n6>span.J-Ke.n4.ah9")
    WebElement chats;

    @FindBy(css = ".TN.bzz.aHS-bnw .aio.UKr6le a")
    WebElement starred;

    public WebElement getStarred() {
        waitForElementVisible(starred);
        return starred;
    }

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
        waitForElementClickable(signOutLink);
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

    public WebElement getMoreBox() {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='ajl aib aZ6'] .n6")));
        return moreBox;
    }

    public WebElement getPic(){
        waitForElementVisible(pic);
        return pic;
    }

    public WebElement getTrash(){
        waitForElementVisible(trash);
        return trash;
    }

    public WebElement getchats() {
        waitForElementVisible(chats);
        return chats;
    }
}
