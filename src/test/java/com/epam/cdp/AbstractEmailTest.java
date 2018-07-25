package com.epam.cdp;

import com.epam.cdp.page.DraftFolderPage;
import com.epam.cdp.page.DraftLetterPage;
import com.epam.cdp.page.ErrorPopupPage;
import com.epam.cdp.page.LoginPage;
import com.epam.cdp.page.MainPage;
import com.epam.cdp.page.NewLetterPage;
import com.epam.cdp.page.SentFolderPage;
import com.epam.cdp.page.SentLetterPage;
import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
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
    public void setUp() throws InterruptedException, MalformedURLException {
        //System.setProperty("webdriver.chrome.driver", "D://programms//chromedriver//chromedriver_win32//chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        //driver = new ChromeDriver(chromeOptions);
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.chrome());
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
        logout();
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
        login();
        if(!draftFolderPage.getDraftLink().getText().equals(DRAFT)){
            draftFolderPage.openDraftFolder();
            draftFolderPage.checkSelectAllCheckbox();
            draftFolderPage.clickRemoveAllbutton();
        }
        logout();
    }

    protected Map<String, String> populateNewLetterWithData(){
        String toEmail = "blizniova.an@gmail.com";
        newLetterPage.populateToInput(toEmail);

        String subject = "Web driver task " + random.nextInt(1000);
        newLetterPage.populateSubjectbox(subject);

        String text = "I am working on new Web driver task!";
        newLetterPage.populateBody(text);

        return ImmutableMap.of("email", toEmail, "subject", subject, "text", text);
    }

}
