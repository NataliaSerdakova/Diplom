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
import ru.iteco.fmhandroid.ui.pages.NewsFilterPage;
import ru.iteco.fmhandroid.ui.pages.NewsSectionPage;

@RunWith(AndroidJUnit4.class)
public class NewsSectionTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private final LoginPage loginPage = new LoginPage();
    private final NavigationPage navigationPage = new NavigationPage();
    private final NewsSectionPage newsSectionPage = new NewsSectionPage();
    private final NewsFilterPage newsFilterPage = new NewsFilterPage();

    @Before
    public void setUp() {
        loginPage.ensureLoggedIn("login2", "password2");
        navigationPage.openNavigationMenu();
        navigationPage.clickNews();
        navigationPage.checkIsNewsPage();
    }

    @Test
    public void sortButtonInNews_31() {
        String initialTitle = newsSectionPage.getFirstNewsTitle();
        newsSectionPage.clickSort();
        newsSectionPage.checkFirstNewsTitleChanged(initialTitle);
        newsSectionPage.clickSort();
        newsSectionPage.checkFirstNewsTitle(initialTitle);
    }

    @Test
    public void filterButtonInNews_32() {
        newsSectionPage.clickFilter();
        newsFilterPage.checkFilterFormIsLoaded();
    }

    @Test
    public void editButtonInNews_33() {
        newsSectionPage.clickEdit();
        newsSectionPage.checkControlPanelIsLoaded();
    }

    @Test
    public void disclosureOfTheNewsDescriptionInNews_30() {
        newsSectionPage.expandFirstNews();
        newsSectionPage.checkDescriptionVisible();
    }

    @Test
    public void refreshButtonInNews_34() {
        newsSectionPage.clickFilter();
        newsFilterPage.enterStartDate("01.01.2020");
        newsFilterPage.enterEndDate("02.01.2020");
        newsFilterPage.clickFilterSubmit();
        newsSectionPage.checkEmptyListAndRefreshButtonVisible();
        newsSectionPage.clickRefresh();
        newsSectionPage.checkEmptyListAndRefreshButtonVisible();
    }
}
