package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiScrollable;
import androidx.test.uiautomator.UiSelector;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.pages.BasePage;
import ru.iteco.fmhandroid.ui.pages.CalendarPage;
import ru.iteco.fmhandroid.ui.pages.ControlPanelPage;
import ru.iteco.fmhandroid.ui.pages.LoginPage;
import ru.iteco.fmhandroid.ui.pages.NavigationPage;
import ru.iteco.fmhandroid.ui.pages.NewsEditorPage;
import ru.iteco.fmhandroid.ui.pages.NewsSectionPage;
import ru.iteco.fmhandroid.ui.pages.TimePage;

@RunWith(AndroidJUnit4.class)
public class EditNewsTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private final LoginPage loginPage = new LoginPage();
    private final NavigationPage navigationPage = new NavigationPage();
    private final NewsSectionPage newsSectionPage = new NewsSectionPage();
    private final ControlPanelPage controlPanelPage = new ControlPanelPage();
    private final CalendarPage calendarPage = new CalendarPage();
    private final TimePage timePage = new TimePage();
    private final NewsEditorPage newsEditorPage = new NewsEditorPage();

    @Before
    public void setUp() {
        loginPage.ensureLoggedIn("login2", "password2");
        navigationPage.openNavigationMenu();
        navigationPage.clickNews();
        newsSectionPage.clickEdit();
        newsSectionPage.checkControlPanelIsLoaded();
        controlPanelPage.getFirstNewsTitle();
    }

    @Test
    public void editingAllNewsFields_54() {
        controlPanelPage.clickEditNews(0);
        newsEditorPage.checkEditFormLoaded();
        String category = DataHelper.getRandomCategory();
        String newTitle = DataHelper.generateTitle();
        String newDate = DataHelper.getDate(1);
        String newTime = DataHelper.getCurrentTime();
        String newDescription = "Auto-Description: " + DataHelper.getRandomString(20);
        newsEditorPage.selectCategory(category);
        newsEditorPage.enterTitle(newTitle);
        newsEditorPage.enterDate(newDate);
        newsEditorPage.enterTime(newTime);
        newsEditorPage.enterDescription(newDescription);
        newsEditorPage.toggleActiveStatus(true);
        newsEditorPage.checkActiveSwitcherStatus();
        newsEditorPage.clickSave();
        controlPanelPage.checkControlPanelLoaded();
        controlPanelPage.checkNewsWithTitleExists(newTitle);
    }

    @Test
    public void editingTheCategoryField_55() {
        controlPanelPage.clickEditNews(0);
        newsEditorPage.checkEditFormLoaded();
        String newCategory = DataHelper.getRandomCategory();
        newsEditorPage.selectCategory(newCategory);
        newsEditorPage.clickSave();
        controlPanelPage.checkControlPanelLoaded();
        controlPanelPage.clickEditNews(0);
        newsEditorPage.checkEditFormLoaded();
        newsEditorPage.checkCategoryText(newCategory);
    }

    @Test
    public void anotherValueInTheCategoryField_56() {
        controlPanelPage.clickEditNews(0);
        newsEditorPage.checkEditNewsFormIsLoaded();
        newsEditorPage.enterCustomCategory("Заявленние");
        newsEditorPage.clickSave();
        newsEditorPage.checkSavingFailedToast();
    }

    @Test
    public void editingTheTitleField_57() {

        controlPanelPage.clickEditNews(0);
        newsEditorPage.checkEditFormLoaded();
        String newTitle = DataHelper.generateTitle();
        newsEditorPage.enterTitle(newTitle);
        newsEditorPage.clickSave();
        controlPanelPage.checkControlPanelLoaded();
        controlPanelPage.checkNewsWithTitleExists(newTitle);
    }

    @Test
    public void missingTitle_58() {
        controlPanelPage.clickEditNews(0);
        newsEditorPage.checkEditFormLoaded();
        newsEditorPage.enterTitle("");
        newsEditorPage.clickSave();
        newsEditorPage.checkEmptyFieldsToast();
        newsEditorPage.checkTitleFieldEmptyError();
        newsEditorPage.checkSaveButtonIsDisplayed();
    }

    @Test
    public void OneCharacterInTitleField_60() {
        controlPanelPage.clickEditNews(0);
        newsEditorPage.checkEditFormLoaded();
        String singleCharTitle = "A";
        String uniqueDescription = DataHelper.getRandomString(15);
        newsEditorPage.enterTitle(singleCharTitle);
        newsEditorPage.enterDescription(uniqueDescription);
        newsEditorPage.clickSave();
        controlPanelPage.checkControlPanelLoaded();
        controlPanelPage.checkNewsWithTitleExists(singleCharTitle);
        controlPanelPage.clickExpandNews(0);
        controlPanelPage.checkDescriptionText(0, uniqueDescription);
    }

    @Test
    public void editingThePublicationDateField_62() throws Exception {
        controlPanelPage.clickEditNews(0);
        newsEditorPage.openCalendar();
        calendarPage.setDate(LocalDate.now().plusDays(25));
        newsEditorPage.clickSave();
    }

    @Test
    public void lastDateInTheEdit_63() throws Exception {
        controlPanelPage.clickEditNews(0);
        newsEditorPage.checkEditFormLoaded();
        String today = DataHelper.getDate(0);
        newsEditorPage.enterDate(today);
        String initialDate = newsEditorPage.getDateText();
        newsEditorPage.openCalendar();
        String yesterday = DataHelper.getYesterdayDay();
        calendarPage.selectDay(yesterday);
        calendarPage.clickOk();
        newsEditorPage.checkDateText(initialDate);
    }

    @Test
    public void cancelingTheDateSelection_64() throws Exception {
        controlPanelPage.clickEditNews(0);
        String initialDate = newsEditorPage.getDateText();
        newsEditorPage.openCalendar();
        calendarPage.cancelDate(LocalDate.now().plusDays(2));
        newsEditorPage.checkDateText(initialDate);
    }

    @Test
    public void editingTheTimeField_65() throws Exception {
        controlPanelPage.clickEditNews(0);
        newsEditorPage.checkEditFormLoaded();
        String hour = DataHelper.getRandomHour();
        String minute = DataHelper.getRandomMinuteForPicker();
        String expectedTime = DataHelper.formatTime(hour, minute);
        newsEditorPage.selectTimeFromPicker(hour, minute);
        newsEditorPage.checkTimeText(expectedTime);
        newsEditorPage.clickSave();
        controlPanelPage.checkControlPanelLoaded();
        controlPanelPage.clickEditNews(0);
        newsEditorPage.checkEditFormLoaded();
        newsEditorPage.checkTimeText(expectedTime);
    }

    @Test
    public void cancelingTheTimeSelection_66() throws Exception {
        controlPanelPage.clickEditNews(0);
        newsEditorPage.checkEditFormLoaded();
        String initialTime = newsEditorPage.getTimeText();
        newsEditorPage.openTimePicker();
        timePage.selectTime("10", "45");
        timePage.clickCancel();
        newsEditorPage.checkTimeText(initialTime);
    }

    @Test
    public void editingTheTimeFieldViaTheKeyboard_67() throws Exception {
        controlPanelPage.clickEditNews(0);
        newsEditorPage.checkEditFormLoaded();
        newsEditorPage.openTimePicker();
        timePage.switchToKeyboardMode();
        timePage.typeTime("15", "10");
        timePage.clickOk();
        newsEditorPage.checkTimeText("15:10");
        newsEditorPage.clickSave();
        controlPanelPage.checkControlPanelLoaded();
        controlPanelPage.clickEditNews(0);
        newsEditorPage.checkTimeText("15:10");
    }

    @Test
    public void cancelingTheTimeSelectionViaTheKeyboard_68() throws Exception {
        controlPanelPage.clickEditNews(0);
        newsEditorPage.checkEditFormLoaded();
        String initialTime = newsEditorPage.getTimeText();
        newsEditorPage.openTimePicker();
        timePage.switchToKeyboardMode();
        timePage.typeTime("23", "55");
        timePage.clickCancel();
        newsEditorPage.checkTimeText(initialTime);
    }

    @Test
    public void emptyClockField_70() throws Exception {
        controlPanelPage.clickEditNews(0);
        newsEditorPage.checkEditFormLoaded();
        newsEditorPage.openTimePicker();
        timePage.switchToKeyboardMode();
        timePage.typeTime("", "26");
        timePage.clickOk();
        timePage.checkValidTimeError();
        timePage.checkTimePickerIsStillOpen();
    }

    @Test
    public void boundaryValueInClockField_72() throws Exception {
        controlPanelPage.clickEditNews(0);
        newsEditorPage.checkEditFormLoaded();
        newsEditorPage.openTimePicker();
        timePage.switchToKeyboardMode();
        timePage.typeTime("24", "15");
        timePage.clickOk();
        timePage.checkValidTimeError();
        timePage.checkTimePickerIsStillOpen();
    }

    @Test
    public void switchingTheTimeInputMethod_75() throws Exception {
        controlPanelPage.clickEditNews(0);
        newsEditorPage.checkEditFormLoaded();
        newsEditorPage.openTimePicker();
        timePage.switchToKeyboardMode();
        timePage.switchToClockMode();
        timePage.checkTimePickerIsStillOpen();
    }

    @Test
    public void editingTheDescriptionField_76 () {
        controlPanelPage.clickEditNews(0);
        newsEditorPage.checkEditNewsFormIsLoaded();
        String newDescription = "Unique Desc " + DataHelper.getRandomString(10);
        newsEditorPage.enterDescription(newDescription);
        newsEditorPage.clickSave();
        controlPanelPage.checkControlPanelLoaded();
        controlPanelPage.clickExpandNews(0);
        controlPanelPage.checkDescriptionVisible(0);
        controlPanelPage.checkDescriptionText(0, newDescription);
    }

    @Test
    public void missingDescription_77() {
        controlPanelPage.clickEditNews(0);
        newsEditorPage.checkEditNewsFormIsLoaded();
        newsEditorPage.enterDescription("");
        newsEditorPage.clickSave();
        newsEditorPage.checkEmptyFieldsToast();
        newsEditorPage.checkDescriptionFieldEmptyError();
        newsEditorPage.checkSaveButtonIsDisplayed();
    }

    @Test
    public void OneCharacterInDescriptionField_79() {
        controlPanelPage.clickEditNews(0);
        newsEditorPage.checkEditNewsFormIsLoaded();
        String uniqueTitle = DataHelper.generateTitle();
        String singleCharDesc = "Р";
        newsEditorPage.enterTitle(uniqueTitle);
        newsEditorPage.enterDescription(singleCharDesc);
        newsEditorPage.clickSave();
        controlPanelPage.checkControlPanelLoaded();
        controlPanelPage.checkNewsWithTitleExists(uniqueTitle);
        controlPanelPage.clickExpandNews(0);
        controlPanelPage.checkDescriptionText(0, singleCharDesc);
    }

    @Test
    public void changingTheNewsStatusToActive_81() {
        controlPanelPage.clickEditNews(0);
        newsEditorPage.checkEditNewsFormIsLoaded();
        newsEditorPage.toggleActiveStatus(false);
        newsEditorPage.clickSave();
        controlPanelPage.checkControlPanelLoaded();
        controlPanelPage.clickEditNews(0);
        newsEditorPage.checkEditNewsFormIsLoaded();
        newsEditorPage.toggleActiveStatus(true);
        newsEditorPage.checkActiveSwitcherStatus();
        newsEditorPage.clickSave();
        controlPanelPage.checkControlPanelLoaded();
        controlPanelPage.checkFirstNewsStatus("Active");
    }

    @Test
    public void changingTheNewsStatusToNotActive_82() {
        controlPanelPage.clickEditNews(0);
        newsEditorPage.checkEditNewsFormIsLoaded();
        String newCategory = DataHelper.getRandomCategory();
        newsEditorPage.selectCategory(newCategory);
        newsEditorPage.clickCancel();
        newsEditorPage.checkCancelDialogText();
        newsEditorPage.confirmCancelDialog();
        controlPanelPage.checkControlPanelLoaded();
    }

    @Test
    public void cancelEditingNews_83() {
        controlPanelPage.clickEditNews(0);
        newsEditorPage.checkEditNewsFormIsLoaded();
        String newCategory = DataHelper.getRandomCategory();
        newsEditorPage.selectCategory(newCategory);
        newsEditorPage.clickCancel();
        newsEditorPage.checkCancelDialogText();
        newsEditorPage.confirmCancelDialog();
        controlPanelPage.checkControlPanelLoaded();
    }
}
