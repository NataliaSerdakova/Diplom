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
import ru.iteco.fmhandroid.ui.pages.ControlPanelPage;
import ru.iteco.fmhandroid.ui.pages.LoginPage;
import ru.iteco.fmhandroid.ui.pages.NavigationPage;
import ru.iteco.fmhandroid.ui.pages.NewsEditorPage;
import ru.iteco.fmhandroid.ui.pages.NewsSectionPage;
import ru.iteco.fmhandroid.ui.pages.TimePage;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CreateNewsTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private final LoginPage loginPage = new LoginPage();
    private final NavigationPage navigationPage = new NavigationPage();
    private final NewsSectionPage newsSectionPage = new NewsSectionPage();
    private final ControlPanelPage controlPanelPage = new ControlPanelPage();
    private final NewsEditorPage newsEditorPage = new NewsEditorPage();
    private final CalendarPage calendarPage = new CalendarPage();
    private final TimePage timePage = new TimePage();

    @Before
    public void setUp() {
        loginPage.ensureLoggedIn("login2", "password2");
        navigationPage.openNavigationMenu();
        navigationPage.clickNews();
        newsSectionPage.clickEdit();
        controlPanelPage.checkControlPanelLoaded();
    }

    @Test
    public void newsCreation_98() throws Exception {
        controlPanelPage.clickAddNews();
        newsEditorPage.checkAddNewsFormIsLoaded();
        String title = DataHelper.generateTitle();
        LocalDate targetDate = LocalDate.now().plusDays(1);
        String hour = DataHelper.getRandomHour();
        String minute = DataHelper.getRandomMinuteForPicker();
        newsEditorPage.selectCategory(DataHelper.getRandomCategory());
        newsEditorPage.enterTitle(title);
        newsEditorPage.selectDateFromCalendar(targetDate);
        newsEditorPage.selectTimeFromPicker(hour, minute);
        newsEditorPage.enterDescription("Description for " + title);
        newsEditorPage.checkActiveSwitcherStatus();
        newsEditorPage.clickSave();
        controlPanelPage.checkControlPanelLoaded();
        controlPanelPage.checkNewsWithTitleExists(title);
    }

    @Test
    public void cancelingNewsCreation_99() {
        controlPanelPage.clickAddNews();
        newsEditorPage.checkAddNewsFormIsLoaded();
        newsEditorPage.selectCategory(DataHelper.getRandomCategory());
        newsEditorPage.clickCancel();
        newsEditorPage.checkCancelDialogText();
        newsEditorPage.confirmCancelDialog();
        controlPanelPage.checkControlPanelLoaded();
    }

    @Test
    public void creatingNewsItemWithCategoryOnly_100() {
        controlPanelPage.clickAddNews();
        newsEditorPage.checkAddNewsFormIsLoaded();
        newsEditorPage.selectCategory(DataHelper.getRandomCategory());
        newsEditorPage.enterTitle("");
        newsEditorPage.enterDate("");
        newsEditorPage.enterTime("");
        newsEditorPage.enterDescription("");
        newsEditorPage.clickSave();
        newsEditorPage.checkSaveButtonIsDisplayed();
        newsEditorPage.checkEmptyFieldsToast();
        newsEditorPage.checkTitleFieldEmptyError();
        newsEditorPage.checkDescriptionFieldEmptyError();
    }

    @Test
    public void anotherValueInTheCategoryField_101() throws Exception {
        controlPanelPage.clickAddNews();
        newsEditorPage.checkAddNewsFormIsLoaded();
        newsEditorPage.enterCustomCategory("Заявление");
        newsEditorPage.enterTitle(DataHelper.generateTitle());
        newsEditorPage.selectDateFromCalendar(LocalDate.now().plusDays(1));
        newsEditorPage.selectTimeFromPicker(DataHelper.getRandomHour(), DataHelper.getRandomMinuteForPicker());
        newsEditorPage.enterDescription(DataHelper.getRandomString(20));
        newsEditorPage.clickSave();
        newsEditorPage.checkSavingFailedToast();
    }

    @Test
    public void lastDateInTheCreation_109() throws Exception {
        controlPanelPage.clickAddNews();
        newsEditorPage.checkAddNewsFormIsLoaded();
        newsEditorPage.openCalendar();
        String yesterday = DataHelper.getYesterdayDay();
        calendarPage.selectDay(yesterday);
        calendarPage.clickOk();
        String today = DataHelper.getDate(0);
        newsEditorPage.checkDateText(today);
    }

    @Test
    public void cancelingTheDateSelection_110() throws Exception {
        controlPanelPage.clickAddNews();
        newsEditorPage.checkAddNewsFormIsLoaded();
        newsEditorPage.openCalendar();
        calendarPage.cancelDate(LocalDate.now().plusDays(2));
        newsEditorPage.checkDateText("");
    }

    @Test
    public void cancelingTheTimeSelection_113() throws Exception {
        controlPanelPage.clickAddNews();
        newsEditorPage.checkAddNewsFormIsLoaded();
        newsEditorPage.openTimePicker();
        timePage.selectTime("10", "45");
        timePage.clickCancel();
        newsEditorPage.checkTimeText("");
    }

    @Test
    public void creatingTheTimeFieldViaTheKeyboard_114() throws Exception {
        controlPanelPage.clickAddNews();
        newsEditorPage.checkAddNewsFormIsLoaded();
        newsEditorPage.openTimePicker();
        timePage.switchToKeyboardMode();
        timePage.typeTime("15", "10");
        timePage.clickOk();
        newsEditorPage.checkTimeText("15:10");
    }

    @Test
    public void cancelingTheTimeSelectionViaTheKeyboard_115() throws Exception {
        controlPanelPage.clickAddNews();
        newsEditorPage.checkAddNewsFormIsLoaded();
        newsEditorPage.openTimePicker();
        timePage.switchToKeyboardMode();
        timePage.typeTime("23", "55");
        timePage.clickCancel();
        newsEditorPage.checkTimeText("");
    }

    @Test
    public void emptyTimeFields_116() throws Exception {
        controlPanelPage.clickAddNews();
        newsEditorPage.checkAddNewsFormIsLoaded();
        newsEditorPage.openTimePicker();
        timePage.switchToKeyboardMode();
        timePage.typeTime("", "");
        timePage.clickOk();
        timePage.checkValidTimeError();
        timePage.checkTimePickerIsStillOpen();
    }

    @Test
    public void boundaryValueInClockField_119() throws Exception {
        controlPanelPage.clickAddNews();
        newsEditorPage.checkAddNewsFormIsLoaded();
        newsEditorPage.openTimePicker();
        timePage.switchToKeyboardMode();
        timePage.typeTime("24", "15");
        timePage.clickOk();
        timePage.checkValidTimeError();
        timePage.checkTimePickerIsStillOpen();
    }

    @Test
    public void switchingTheTimeInputMethod_122() throws Exception {
        controlPanelPage.clickAddNews();
        newsEditorPage.checkAddNewsFormIsLoaded();
        newsEditorPage.openTimePicker();
        timePage.switchToKeyboardMode();
        timePage.switchToClockMode();
        timePage.checkTimePickerIsStillOpen();
    }
}

