package com.epam.cdp;

import com.epam.cdp.page.DraftFolderPage;
import com.epam.cdp.page.DraftLetterPage;
import com.epam.cdp.page.ErrorPopupPage;
import com.epam.cdp.page.LoginPage;
import com.epam.cdp.page.MainPage;
import com.epam.cdp.page.NewLetterPage;
import com.epam.cdp.page.SentFolderPage;
import com.epam.cdp.page.SentLetterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.Random;

import static com.epam.cdp.util.Constants.DRAFT;

public class AbstractEmailTest {
    protected Random random;
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected LoginPage loginPage;
    protected DraftFolderPage draftFolderPage;
    protected MainPage mainPage;
    protected NewLetterPage newLetterPage;
    protected SentFolderPage sentFolderPage;
    protected DraftLetterPage draftLetterPage;
    protected ErrorPopupPage errorPopupPage;
    protected SentLetterPage sentLetterPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D://programms//chromedriver//chromedriver_win32//chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        wait = new WebDriverWait(driver, 90);
        random = new Random();
        loginPage = new LoginPage(driver);
        draftFolderPage = new DraftFolderPage(driver);
        mainPage = new MainPage(driver);
        newLetterPage = new NewLetterPage(driver);
        sentFolderPage = new SentFolderPage(driver);
        draftLetterPage = new DraftLetterPage(driver);
        errorPopupPage = new ErrorPopupPage(driver);
        sentLetterPage = new SentLetterPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp(){
        driver.quit();
    }

    protected void login(){
        loginPage.open();
        driver.manage().window().maximize();
        loginPage.populateLoginInput().clickNextButtonAfterLogin();
        loginPage.populatePasswordInput().clickNextButtonAfterPassword();
    }

    protected void logout(){
        mainPage.selectSignoutIcon().signOut();
    }

    protected void cleanDraftFolderBeforeTest() throws InterruptedException {
        if(!draftFolderPage.getDraftLink().getText().equals(DRAFT)){
            draftFolderPage.openDraftFolder();
            Thread.sleep(4000);
            draftFolderPage.checkSelectAllCheckbox();
            draftFolderPage.clickRemoveAllbutton();
            Thread.sleep(4000);
        }
    }

}
