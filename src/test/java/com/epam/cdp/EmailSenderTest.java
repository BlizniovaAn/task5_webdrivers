package com.epam.cdp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

import static com.epam.cdp.Constants.DRAFT;
import static com.epam.cdp.Constants.OPEN_DRAFT_FOLDER_URL;

/**
 * Unit test for simple App.
 */
public class EmailSenderTest {

    private Random random;
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "D://programms//chromedriver//chromedriver_win32//chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        wait = new WebDriverWait(driver, 90);
        random = new Random();

        login();
        cleanDraftFolderBeforeTest();
        logout();
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp(){
        //Close browser
        driver.quit();
    }

    @Test
    public void sendEmailTest(){
        //login
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        driver.findElement(By.name("password")).sendKeys("qwerty1*Q");

        wait.until(ExpectedConditions.elementToBeClickable(By.id("passwordNext")));
        WebElement nextButtonAfterPassword = driver.findElement(By.id("passwordNext"));
        nextButtonAfterPassword.click();

        //Check login was successfull
        waitForElement(By.cssSelector("a.gb_b.gb_db.gb_R"));
        WebElement webDriverIcon = driver.findElement(By.cssSelector("a.gb_b.gb_db.gb_R"));
        Assert.assertTrue(webDriverIcon.isDisplayed());

        //Click 'Write' button
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='z0']//div[@class='T-I J-J5-Ji T-I-KE L3']")));
        WebElement writeButton = driver.findElement(By.xpath("//div[@class='z0']//div[@class='T-I J-J5-Ji T-I-KE L3']"));
        writeButton.click();

        //Populate 'to' input field
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("to")));
        WebElement toInput = driver.findElement(By.name("to"));
        String toEmail = "blizniova.an@gmail.com";
        toInput.sendKeys(toEmail);

        //Populate 'Subject' input field
        WebElement subjectbox = driver.findElement(By.name("subjectbox"));
        String subject = "Web driver task " + random.nextInt(1000);
        subjectbox.sendKeys(subject);

        //Populate 'Text' input field
        WebElement bodyInput = driver.findElement(By.cssSelector("div.Am.Al.editable.LW-avf"));
        String text = "I am working on new Web driver task!";
        bodyInput.sendKeys(text);

        //Close popup with populated fields
        WebElement close = driver.findElement(By.cssSelector("img.Ha"));
        close.click();

        //Open 'Draft' folder
        waitForElement(By.partialLinkText(DRAFT));
        driver.get(OPEN_DRAFT_FOLDER_URL);

        waitForElement(By.linkText("Черновики (1)"));
        WebElement draft1 = driver.findElement(By.partialLinkText(DRAFT));
        Assert.assertEquals(draft1.getText(), "Черновики (1)");

        //Select last draft letter
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='ae4 UI']//tr[@class='zA yO']")));
        WebElement draftLetter = driver.findElement(By.xpath("//div[@class='ae4 UI']//tr[@class='zA yO']"));
        draftLetter.click();

        //Check 'To' input field
        waitForElement(By.xpath("//div[@class='oL aDm az9']//span[@email='blizniova.an@gmail.com']"));
        WebElement draftToElement = driver.findElement(By.xpath("//div[@class='oL aDm az9']//span[@email='blizniova.an@gmail.com']"));
        Assert.assertEquals("Анна Близнёва", draftToElement.getText());

        //Check 'Subject' input field
        WebElement draftSubjectboxElement = driver.findElement(By.name("subject"));
        Assert.assertEquals(subject, draftSubjectboxElement.getAttribute("value"));

        //Check 'Text' input field
        WebElement draftBodyInputElement = driver.findElement(By.cssSelector("div.Am.Al.editable.LW-avf"));
        Assert.assertEquals(text, draftBodyInputElement.getText());

        //Send a letter
        WebElement sendButton = driver.findElement(By.cssSelector("div.T-I.J-J5-Ji.aoO.T-I-atl.L3"));
        sendButton.click();

        //Go to 'Sent' folder
        waitForElement(By.cssSelector("a[href*='https://mail.google.com/mail/u/0/#sent']"));
        WebElement sentFolder = driver.findElement(By.linkText("Отправленные"));
        sentFolder.click();

        //Verify that sent letter is present is 'Sent' folder
        WebElement sentLetter = driver.findElement(By.cssSelector("div[role='main'] table.F.cf.zt tr:first-child"));
        Assert.assertTrue(sentLetter.isDisplayed());

        //Verify that 'Draft' folder is empty
        driver.navigate().to(OPEN_DRAFT_FOLDER_URL);
        waitForElement(By.cssSelector("div[role='main'] table.cf.TB td:first-child"));
        WebElement emptyDrafts = driver.findElement(By.cssSelector("div[role='main'] table.cf.TB td:first-child"));
        Assert.assertTrue(emptyDrafts.getText().equals("Нет сохраненных черновиков.\n" +
                "Черновики позволяют хранить письма, еще не готовые к отправке."));

        logout();

    }

    private WebElement waitForElement(final By by){
        WebElement webElement = (new WebDriverWait(driver, 10))
                .until(new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver d){
                        return d.findElement(by);
                    }
                });
        return webElement;
    }

    private void cleanDraftFolderBeforeTest(){
        waitForElement(By.partialLinkText(DRAFT));
        WebElement draft = driver.findElement(By.partialLinkText(DRAFT));
        if(!draft.getText().equals(DRAFT)){

            driver.get(OPEN_DRAFT_FOLDER_URL);
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[gh='tm'] div.J-J5-Ji.J-JN-M-I-Jm span.T-Jo[role='checkbox']")));
            WebElement selectAllButton = driver.findElement(By.cssSelector("div[gh='tm'] div.J-J5-Ji.J-JN-M-I-Jm span.T-Jo[role='checkbox']"));
            selectAllButton.click();

            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.T-I.J-J5-Ji.aFh.T-I-ax7.W6eDmd[role='button']>div")));
            WebElement removeAlldraftsButton = driver.findElement(By.cssSelector("div.T-I.J-J5-Ji.aFh.T-I-ax7.W6eDmd[role='button']>div"));
            removeAlldraftsButton.click();
        }

    }

    private void login(){
        driver.get("https://mail.google.com");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("identifierId")));
        WebElement login = driver.findElement(By.id("identifierId"));
        login.sendKeys("webdrivers5@gmail.com");

        WebElement nextButtonAfterLogin = driver.findElement(By.id("identifierNext"));
        nextButtonAfterLogin.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        driver.findElement(By.name("password")).sendKeys("qwerty1*Q");

        wait.until(ExpectedConditions.elementToBeClickable(By.id("passwordNext")));
        WebElement nextButtonAfterPassword = driver.findElement(By.id("passwordNext"));
        nextButtonAfterPassword.click();
    }

    private void logout(){
        //Navigate to 'signOut' icon
        WebElement signOutIcon = driver.findElement(By.cssSelector("a[href*='https://accounts.google.com/SignOutOptions']"));
        signOutIcon.click();

        //Sign out
        WebElement signOutLink = driver.findElement(By.cssSelector("a[href*='https://accounts.google.com/Logout']"));
        signOutLink.click();
    }

}
