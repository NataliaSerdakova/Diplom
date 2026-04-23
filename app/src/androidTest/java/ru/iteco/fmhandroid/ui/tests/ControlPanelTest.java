package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pages.ControlPanelPage;
import ru.iteco.fmhandroid.ui.pages.LoginPage;
import ru.iteco.fmhandroid.ui.pages.NavigationPage;
import ru.iteco.fmhandroid.ui.pages.NewsEditorPage;
import ru.iteco.fmhandroid.ui.pages.NewsFilterPage;
import ru.iteco.fmhandroid.ui.pages.NewsSectionPage;

@RunWith(AndroidJUnit4.class)
public class ControlPanelTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private final LoginPage loginPage = new LoginPage();
    private final NavigationPage navigationPage = new NavigationPage();
    private final NewsSectionPage newsSectionPage = new NewsSectionPage();
    private final ControlPanelPage controlPanelPage = new ControlPanelPage();
    private final NewsFilterPage newsFilterPage = new NewsFilterPage();
    private final NewsEditorPage newsEditorPage = new NewsEditorPage();

    @Before
    public void setUp() {
        loginPage.ensureLoggedIn("login2", "password2");
        navigationPage.openNavigationMenu();
        navigationPage.clickNews();
        newsSectionPage.clickEdit();
        controlPanelPage.checkControlPanelLoaded();
    }

    @Test
    public void sortButtonInControlPanel_47() {
        String initialDate = controlPanelPage.getFirstNewsDate();
        controlPanelPage.clickSort();
        controlPanelPage.checkFirstNewsDateChanged(initialDate);
        controlPanelPage.clickSort();
        controlPanelPage.checkFirstNewsDate(initialDate);
    }

    @Test
    public void filterButtonInControlPanel_48() {
        controlPanelPage.clickFilter();
        newsFilterPage.checkFilterFormIsLoaded();
    }

    @Test
    public void addingButonInControlPanel_49() {
        controlPanelPage.clickAddNews();
        newsEditorPage.checkAddNewsFormIsLoaded();
    }

    @Test
    public void deleteButtonInControlPanel_50() {
        String title = controlPanelPage.getFirstNewsTitle();
        int countBefore = controlPanelPage.getNewsCount(title);
        controlPanelPage.clickDeleteNews(0);
        controlPanelPage.confirmDeletion();
        controlPanelPage.checkNewsCountDecreased(title, countBefore);
    }

    @Test
    public void cancelingDeletionInControlPanel_51() {
        String titleToKeep = controlPanelPage.getFirstNewsTitle();
        controlPanelPage.clickDeleteNews(0);
        controlPanelPage.checkDeleteDialogDisplayed();
        controlPanelPage.cancelDeletion();
        controlPanelPage.checkNewsWithTitleExists(titleToKeep);
    }

    @Test
    public void editButonInControlPanel_52() {
        controlPanelPage.clickEditNews(0);
        newsEditorPage.checkEditNewsFormIsLoaded();
    }

    @Test
    public void disclosureOfTheNewsDescriptionInControlPanel_53() {
        controlPanelPage.clickExpandNews(0);
        controlPanelPage.checkDescriptionVisible(0);
    }
}