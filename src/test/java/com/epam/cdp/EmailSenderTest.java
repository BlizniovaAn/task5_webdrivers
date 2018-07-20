package com.epam.cdp;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

import static com.epam.cdp.util.Constants.DRAFT_LINK_TEXT_1;
import static com.epam.cdp.util.Constants.HANNA_BLIZNIOVA;
import static com.epam.cdp.util.Constants.VALUE_ATTRIBUTE;

/**
 * Test for sending emails
 */
public class EmailSenderTest extends AbstractEmailTest{

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws InterruptedException {
        super.setUp();
        login();
        cleanDraftFolderBeforeTest();
        logout();
    }

    @Test(groups = "P0", testName = "sentLetterIsPresentInSentFolderTest", description = "Verify set letter is present in Sent folder after mailing")
    public void sentLetterIsAbsentInSentFolderAfterRemovingTest(){
        loginPage.populatePasswordInput();
        loginPage.clickNextButtonAfterPassword();

        mainPage.clickWriteButton();
        Map<String, String> info = populateNewLetterWithData();
        newLetterPage.clickSendButton();

        sentFolderPage.openPage();

        //Check that Sent folder is open
        WebElement sentLetter = sentFolderPage.getSentLetter();
        Assert.assertTrue(sentLetter.isDisplayed());

        sentFolderPage.checkCheckboxOfLastSentLetter().clickRemoveLetterButton();
        Assert.assertTrue(sentFolderPage.getAlertDialog().isDisplayed());
        sentFolderPage.clickOkButton();

        //Check that Sent folder is open
        WebElement removedSentLetter = sentFolderPage.getSentLetter();
        Assert.assertFalse(removedSentLetter.getText().contains(info.get("subject")), "Verify sent letter was removed.");

        logout();
    }

    @Test(groups = "P0", testName = "sendEmptyEmailTest", description = "Verify sending empty email is forbidden")
    public void sendEmptyEmailTest() {
        loginPage.populatePasswordInput();
        loginPage.clickNextButtonAfterPassword();

        mainPage.clickWriteButton();
        newLetterPage.clickSendButton();
        Assert.assertTrue(errorPopupPage.getAlertDialog().isDisplayed());
        Assert.assertEquals(errorPopupPage.getErrorSpanText(), "Ошибка");
        Assert.assertEquals(errorPopupPage.getErrorMessage(), "Укажите как минимум одного получателя.");
        errorPopupPage.clickOKButton();

        newLetterPage.closeLetter();

        logout();
    }

    @Test(groups = "P0",  testName = "sendEmailTest", description = "Verify sending a valid email")
    public void sendEmailTest(){
        //loginPage.open();
        loginPage.populatePasswordInput();
        loginPage.clickNextButtonAfterPassword();

        //Check login was successfull
        WebElement webDriverIcon = mainPage.getWebDriverIcon();
        Assert.assertTrue(webDriverIcon.isDisplayed());

        mainPage.clickWriteButton();

        Map<String, String> info = populateNewLetterWithData();

        newLetterPage.closeLetter();

        draftFolderPage.openDraftFolder();

        Assert.assertEquals(mainPage.getDraftLink1().getText(), DRAFT_LINK_TEXT_1);

        //Verify draft letter
        draftFolderPage.selectDraftLetter();
        Assert.assertEquals(HANNA_BLIZNIOVA, draftLetterPage.getToInput().getText());
        Assert.assertEquals(info.get("subject"), draftLetterPage.getSubjectbox().getAttribute(VALUE_ATTRIBUTE));
        Assert.assertEquals(info.get("text"), draftLetterPage.getBodyInput().getText());

        newLetterPage.clickSendButton();

        sentFolderPage.openPage();

        //Verify that sent letter is present is 'Sent' folder
        WebElement sentLetter = sentFolderPage.getSentLetter();
        Assert.assertTrue(sentLetter.isDisplayed());

        //Verify that 'Draft' folder is empty
        draftFolderPage.openDraftFolder();
        WebElement tableWithLetters = draftFolderPage.getTableWithLetters();
        Assert.assertTrue(tableWithLetters.getText().equals("Нет сохраненных черновиков.\n" +
                "Черновики позволяют хранить письма, еще не готовые к отправке."));

        logout();
    }

}
