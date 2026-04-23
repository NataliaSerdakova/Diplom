package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pages.CalendarPage;
import ru.iteco.fmhandroid.ui.pages.LoginPage;
import ru.iteco.fmhandroid.ui.pages.NavigationPage;
import ru.iteco.fmhandroid.ui.pages.NewsSectionPage;

@RunWith(AndroidJUnit4.class)
public class CalendarTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private LoginPage loginPage = new LoginPage();
    private NavigationPage navigationPage = new NavigationPage();
    private NewsSectionPage newsSectionPage = new NewsSectionPage();
    private CalendarPage calendarPage = new CalendarPage();


    private final int startField = R.id.news_item_publish_date_start_text_input_edit_text;
    private final int endField = R.id.news_item_publish_date_end_text_input_edit_text;

    @Before
    public void setUp() {
        loginPage.ensureLoggedIn("login2", "password2");
        navigationPage.openNavigationMenu();
        navigationPage.clickNews();
        newsSectionPage.clickFilter();
    }

    @Test
    public void testFullCalendarCycleForBothFields() throws Exception {
        calendarPage.openCalendarForField(startField);
        calendarPage.changeYear("2025");
        calendarPage.clickNextMonth();
        calendarPage.selectDay("10");
        calendarPage.clickOk();
        calendarPage.checkDateInField(startField, "10");
        calendarPage.checkDateInField(startField, "2025");

        calendarPage.openCalendarForField(endField);
        calendarPage.changeYear("2027");
        calendarPage.clickNextMonth();
        calendarPage.selectDay("25");
        calendarPage.clickOk();
        calendarPage.checkDateInField(endField, "25");
        calendarPage.checkDateInField(endField, "2027");
        calendarPage.checkDateInField(startField, "2025");
    }

    @Test
    public void testCancelCalendarSelection() throws Exception {
        calendarPage.openCalendarForField(startField);
        calendarPage.selectDay("15");
        calendarPage.clickCancel();
        calendarPage.checkFieldIsEmpty(startField);
    }
}