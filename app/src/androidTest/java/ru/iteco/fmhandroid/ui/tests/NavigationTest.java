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
import ru.iteco.fmhandroid.ui.pages.NewsSectionPage;

@RunWith(AndroidJUnit4.class)
public class NavigationTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private final NavigationPage navigationPage = new NavigationPage();
    private final LoginPage loginPage = new LoginPage();
    private final NewsSectionPage newsSectionPage = new NewsSectionPage();

    @Before
    public void setUp() {
        loginPage.ensureLoggedIn("login2", "password2");
    }

    @Test
    public void goToTheNewsSectionFromTheMain_19() {
        navigationPage.openNavigationMenu();
        navigationPage.clickNews();
        navigationPage.checkIsNewsPage();
    }

    @Test
    public void goToTheAboutSectionFromTheMain_20() {
        navigationPage.openNavigationMenu();
        navigationPage.clickAbout();
        navigationPage.checkIsAboutPage();
    }

    @Test
    public void returnFromTheSectionAbout_21() {
        navigationPage.openNavigationMenu();
        navigationPage.clickAbout();
        navigationPage.checkIsAboutPage();
        navigationPage.clickBack();
        navigationPage.checkIsNewsPage();
    }

    @Test
    public void goToTheMainSectionFromTheNews_22() {
        navigationPage.openNavigationMenu();
        navigationPage.clickNews();
        navigationPage.checkIsNewsPage();
        navigationPage.openNavigationMenu();
        navigationPage.checkMenuElementsDisplayed();
        navigationPage.clickMain();
        navigationPage.checkIsMainPage();
    }

    @Test
    public void goToTheAboutSectionFromTheNews_23() {
        navigationPage.openNavigationMenu();
        navigationPage.clickNews();
        navigationPage.checkIsNewsPage();
        navigationPage.openNavigationMenu();
        navigationPage.checkMenuElementsDisplayed();
        navigationPage.clickAbout();
        navigationPage.checkIsAboutPage();
    }

    @Test
    public void goToTheQuotesSection_24() {
        navigationPage.clickButterfly();
        navigationPage.checkIsQuotesPage();
    }

    @Test
    public void disclosureOfTheNewsDescriptionInMain_28() {
        newsSectionPage.expandFirstNews();
        newsSectionPage.checkDescriptionVisible();
    }

    @Test
    public void goToTheNewsSectionOnAllNews_29() {
        navigationPage.clickAllNewsLink();
        navigationPage.checkIsNewsPage();
    }
}


