package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pages.LoginPage;
import ru.iteco.fmhandroid.ui.pages.NavigationPage;
import ru.iteco.fmhandroid.ui.pages.QuotesPage;

@RunWith(AndroidJUnit4.class)
public class QuotesTest {
    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule = new ActivityScenarioRule<>(AppActivity.class);

    private final LoginPage loginPage = new LoginPage();
    private final NavigationPage navigationPage = new NavigationPage();
    private final QuotesPage quotesPage = new QuotesPage();

    @Before
    public void setUp() {
        loginPage.ensureLoggedIn("login2", "password2");
        navigationPage.clickButterfly();
        navigationPage.checkIsQuotesPage();
    }

    @Test
    public void disclosureOfTheQuoteDescription_25() {
        quotesPage.expandFirstQuote();
        quotesPage.checkDescriptionVisible();
    }
}