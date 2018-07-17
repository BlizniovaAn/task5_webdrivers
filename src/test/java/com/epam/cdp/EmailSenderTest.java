package com.epam.cdp;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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

    @Test
    public void sendEmailTest() throws InterruptedException {
        //loginPage.open();
        loginPage.populatePasswordInput();
        loginPage.clickNextButtonAfterPassword();

        //Check login was successfull
        WebElement webDriverIcon = mainPage.getWebDriverIcon();
        Assert.assertTrue(webDriverIcon.isDisplayed());

        mainPage.clickWriteButton();

        String toEmail = "blizniova.an@gmail.com";
        newLetterPage.populateToInput(toEmail);

        String subject = "Web driver task " + random.nextInt(1000);
        newLetterPage.populateSubjectbox(subject);

        String text = "I am working on new Web driver task!";
        newLetterPage.populateBody(text);

        newLetterPage.closeLetter();

        draftFolderPage.openDraftFolder();

        WebElement draftLink1 = mainPage.getDraftLink1();
        Assert.assertEquals(draftLink1.getText(), DRAFT_LINK_TEXT_1);

        //Verify draft letter
        draftFolderPage.selectDraftLetter();
        Assert.assertEquals(HANNA_BLIZNIOVA, draftLetterPage.getToInput().getText());
        Assert.assertEquals(subject, draftLetterPage.getSubjectbox().getAttribute(VALUE_ATTRIBUTE));
        Assert.assertEquals(text, draftLetterPage.getBodyInput().getText());

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
