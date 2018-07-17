package com.epam.cdp.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DraftLetterPage extends NewLetterPage {

    @FindBy(xpath = "//div[@class='oL aDm az9']//span[@email='blizniova.an@gmail.com']")
    private WebElement toInput;

    public DraftLetterPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getToInput() {
        waitForElementVisible(toInput);
        return toInput;
    }

}
