package com.epam.cdp.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.epam.cdp.util.Constants.LOGIN;

public class LoginPage extends AbstractPage{
    private String GOOGLE_POST_URL = "https://mail.google.com";

    @FindBy(id = "identifierId")
    private WebElement loginInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(id = "identifierNext")
    private WebElement nextButtonAfterLogin;

    @FindBy(id = "passwordNext")
    private WebElement nextButtonAfterPassword;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        driver.manage().window().maximize();
        driver.get(GOOGLE_POST_URL);
        return this;
    }

    public LoginPage populateLoginInput(){
        waitForElementVisible(loginInput);
        loginInput.sendKeys(LOGIN);
        return this;
    }

    public LoginPage populatePasswordInput(){
        waitForElementVisible(passwordInput);
        passwordInput.sendKeys("qwerty1*Q");
        return this;
    }

    public LoginPage clickNextButtonAfterLogin(){
        nextButtonAfterLogin.click();
        return this;
    }

    public LoginPage clickNextButtonAfterPassword(){
        waitForElementClickable(nextButtonAfterPassword);
        nextButtonAfterPassword.click();
        return this;
    }

}
