package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;

import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.pages.CalendarPage;
import ru.iteco.fmhandroid.ui.pages.ControlPanelFilterPage;
import ru.iteco.fmhandroid.ui.pages.ControlPanelPage;
import ru.iteco.fmhandroid.ui.pages.LoginPage;
import ru.iteco.fmhandroid.ui.pages.NavigationPage;
import ru.iteco.fmhandroid.ui.pages.NewsSectionPage;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ControlPanelFilterTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private final LoginPage loginPage = new LoginPage();
    private final NavigationPage navigationPage = new NavigationPage();
    private final NewsSectionPage newsSectionPage = new NewsSectionPage();
    private final ControlPanelPage controlPanelPage = new ControlPanelPage();
    private final ControlPanelFilterPage filterPage = new ControlPanelFilterPage();
    private final CalendarPage calendarPage = new CalendarPage();

    @Before
    public void setUp() {
        loginPage.ensureLoggedIn("login2", "password2");
        navigationPage.openNavigationMenu();
        navigationPage.clickNews();
        newsSectionPage.clickEdit();
        controlPanelPage.checkControlPanelLoaded();
        controlPanelPage.clickFilter();
    }

    @Test
    public void filteringNewsByAllFieldsInNews_84() {
        filterPage.checkFilterFormIsLoaded();
        String category = DataHelper.getRandomCategory();
        String startDate = DataHelper.getDate(-1);
        String endDate = DataHelper.getDate(0);
        filterPage.selectCategory(category);
        filterPage.enterStartDate(startDate);
        filterPage.enterEndDate(endDate);
        filterPage.checkActiveCheckboxChecked();
        filterPage.checkNotActiveCheckboxChecked();
        filterPage.clickFilterSubmit();
        controlPanelPage.checkControlPanelLoaded();
        controlPanelPage.checkFilterResultVisible();
    }

    @Test
    public void cancelingNewsFilteringByAllFieldsInNews_85() {
        filterPage.checkFilterFormIsLoaded();
        filterPage.selectCategory(DataHelper.getRandomCategory());
        filterPage.clickCancel();
        controlPanelPage.checkControlPanelLoaded();
        controlPanelPage.checkFilterResultVisible();
    }


    @Test
    public void filteringNewsByCategoryInNews_86() {
        filterPage.checkFilterFormIsLoaded();
        filterPage.selectCategory(DataHelper.getRandomCategory());
        filterPage.clickFilterSubmit();
        controlPanelPage.checkControlPanelLoaded();
        controlPanelPage.checkFilterResultVisible();
    }

    @Test
    public void filteringNewsByAnotherCategoryInNews_87() {
        filterPage.enterCustomCategory("Заявление");
        filterPage.clickFilterSubmit();
        controlPanelPage.checkControlPanelLoaded();
        controlPanelPage.checkFilterResultVisible();
    }

    @Test
    public void filteringNewsByValidPeriodInNews_88() {
        filterPage.checkFilterFormIsLoaded();
        filterPage.enterStartDate(DataHelper.getDate(-7));
        filterPage.enterEndDate(DataHelper.getDate(0));
        filterPage.clickFilterSubmit();
        controlPanelPage.checkControlPanelLoaded();
        controlPanelPage.checkFilterResultVisible();
    }

    @Test
    public void filteringNewsByTheBeginningOfThePeriodInNews_89() {
        filterPage.checkFilterFormIsLoaded();
        filterPage.enterStartDate(DataHelper.getDate(0));
        filterPage.clickFilterSubmit();
        filterPage.checkWrongPeriodError();
    }

    @Test
    public void filteringNewsByInvalidPeriodInNews_91() {
        filterPage.checkFilterFormIsLoaded();
        String startDate = "06.03.2026";
        String endDate = "01.03.2026";
        filterPage.enterStartDate(startDate);
        filterPage.enterEndDate(endDate);
        filterPage.clickFilterSubmit();
        controlPanelPage.checkControlPanelLoaded();
        controlPanelPage.checkEmptyListAndRefreshButtonVisible();
    }

    @Test
    public void choiceStartDateSelectionInCalendar_92() throws Exception {
        filterPage.checkFilterFormIsLoaded();
        filterPage.openStartDateCalendar();
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        calendarPage.setDate(tomorrow);
        filterPage.checkStartDateFieldHasText(String.valueOf(tomorrow.getDayOfMonth()));
    }

    @Test
    public void cancelStartDateSelectionInCalendar_93() throws Exception {
        filterPage.checkFilterFormIsLoaded();
        filterPage.openStartDateCalendar();
        calendarPage.cancelDate(LocalDate.now().plusDays(2));
        filterPage.checkStartDateFieldIsEmpty();
    }

    @Test
    public void checkboxFilteringIsActive_96() {
        filterPage.checkFilterFormIsLoaded();
        filterPage.clickNotActiveCheckbox();
        filterPage.checkActiveCheckboxChecked();
        filterPage.clickFilterSubmit();
        controlPanelPage.checkControlPanelLoaded();
        controlPanelPage.checkFirstNewsStatusIfPresent("ACTIVE");
    }

    @Test
    public void checkboxFilteringIsNotActive_97() {
        filterPage.checkFilterFormIsLoaded();
        filterPage.clickActiveCheckbox();
        filterPage.checkNotActiveCheckboxChecked();
        filterPage.clickFilterSubmit();
        controlPanelPage.checkControlPanelLoaded();
        controlPanelPage.checkFirstNewsStatusIfPresent("NOT ACTIVE");
    }
}
