package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;

import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pages.CalendarPage;
import ru.iteco.fmhandroid.ui.pages.LoginPage;
import ru.iteco.fmhandroid.ui.pages.NavigationPage;
import ru.iteco.fmhandroid.ui.pages.NewsSectionPage;
import ru.iteco.fmhandroid.ui.pages.NewsFilterPage;
import ru.iteco.fmhandroid.ui.data.DataHelper;

@RunWith(AndroidJUnit4.class)
public class NewsFilterTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private final LoginPage loginPage = new LoginPage();
    private final NavigationPage navigationPage = new NavigationPage();
    private final NewsSectionPage newsSectionPage = new NewsSectionPage();
    private final NewsFilterPage newsFilterPage = new NewsFilterPage();
    private final CalendarPage calendarPage = new CalendarPage();

    @Before
    public void setUp() {
        loginPage.ensureLoggedIn("login2", "password2");
        navigationPage.openNavigationMenu();
        navigationPage.clickNews();
        navigationPage.checkIsNewsPage();
    }

    @Test
    public void filteringNewsByAllFieldsInNews_35() {
        String startDate = DataHelper.getDate(-1);
        String endDate = DataHelper.getDate(0);
        String category = DataHelper.getRandomCategory();

        newsSectionPage.clickFilter();
        newsFilterPage.checkFilterFormIsLoaded();
        newsFilterPage.selectCategory(category);
        newsFilterPage.enterStartDate(startDate);
        newsFilterPage.enterEndDate(endDate);
        newsFilterPage.clickFilterSubmit();
        newsSectionPage.checkFilterResultVisible();
    }

    @Test
    public void cancelingNewsFilteringByAllFieldsInNews_36() {
        newsSectionPage.clickFilter();
        newsFilterPage.checkFilterFormIsLoaded();
        String category = DataHelper.getRandomCategory();
        newsFilterPage.selectCategory(category);
        newsFilterPage.clickCancel();
        newsSectionPage.checkFilterResultVisible();
    }

    @Test
    public void filteringNewsByCategoryInNews_37() {
        newsSectionPage.clickFilter();
        newsFilterPage.checkFilterFormIsLoaded();
        String category = DataHelper.getRandomCategory();
        newsFilterPage.selectCategory(category);
        newsFilterPage.clickFilterSubmit();
        newsSectionPage.checkFilterResultVisible();
    }

    @Test
    public void filteringNewsByAnotherCategoryInNews_38() {
        newsSectionPage.clickFilter();
        newsFilterPage.enterCustomCategory("Заявление");
        newsFilterPage.clickFilterSubmit();
        newsSectionPage.checkFilterResultVisible();
    }

    @Test
    public void filteringNewsByValidPeriodInNews_39() {
        String startDate = DataHelper.getDate(-7);
        String endDate = DataHelper.getDate(0);
        newsSectionPage.clickFilter();
        newsFilterPage.checkFilterFormIsLoaded();
        newsFilterPage.enterStartDate(startDate);
        newsFilterPage.enterEndDate(endDate);
        newsFilterPage.clickFilterSubmit();
        newsSectionPage.checkFilterResultVisible();
    }

    @Test
    public void filteringNewsByTheBeginningOfThePeriodInNews_40() {
        String startDate = DataHelper.getDate(0);
        newsSectionPage.clickFilter();
        newsFilterPage.checkFilterFormIsLoaded();
        newsFilterPage.enterStartDate(startDate);
        newsFilterPage.clickFilterSubmit();
        newsFilterPage.checkWrongPeriodError();
    }

    @Test
    public void filteringNewsByInvalidPeriodInNews_42() {
        String startDate = "06.03.2026";
        String endDate = "01.03.2026";
        newsSectionPage.clickFilter();
        newsFilterPage.checkFilterFormIsLoaded();
        newsFilterPage.enterStartDate(startDate);
        newsFilterPage.enterEndDate(endDate);
        newsFilterPage.clickFilterSubmit();
        newsSectionPage.checkFilterResultVisible();
    }

    @Test
    public void choiceStartDateSelectionInCalendar_43() throws Exception {
        newsSectionPage.clickFilter();
        newsFilterPage.openStartDateCalendar();
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        calendarPage.setDate(tomorrow);
        newsFilterPage.checkStartDateFieldHasText(String.valueOf(tomorrow.getDayOfMonth()));
    }

    @Test
    public void cancelStartDateSelectionInCalendar_44() throws Exception {
        newsSectionPage.clickFilter();
        newsFilterPage.openStartDateCalendar();
        LocalDate futureDate = LocalDate.now().plusDays(2);
        calendarPage.cancelDate(futureDate);
        newsFilterPage.checkStartDateFieldIsEmpty();
    }
}