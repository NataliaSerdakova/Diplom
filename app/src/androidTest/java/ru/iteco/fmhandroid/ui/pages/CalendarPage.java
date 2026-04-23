package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiScrollable;
import androidx.test.uiautomator.UiSelector;
import java.time.LocalDate;

import ru.iteco.fmhandroid.ui.data.DataHelper;

public class CalendarPage extends BasePage {
    private UiDevice device;

    public CalendarPage() {
        this.device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    public void openCalendarForField(int fieldId) {
        onView(withId(fieldId)).perform(click());
    }

    public void changeYear(String year) throws Exception {
        UiObject yearHeader = device.findObject(new UiSelector().textMatches("^\\d{4}$"));
        if (yearHeader.exists()) {
            yearHeader.click();
            UiScrollable yearList = new UiScrollable(new UiSelector().scrollable(true));
            yearList.scrollTextIntoView(year);
            device.findObject(new UiSelector().text(year)).click();
        }
    }

    public void clickNextMonth() throws Exception {
        UiObject next = device.findObject(new UiSelector().descriptionMatches("(?i)Next month|Следующий месяц"));
        if (next.exists()) next.click();
    }

    public void clickPrevMonth() throws Exception {
        UiObject prev = device.findObject(new UiSelector().descriptionMatches("(?i)Previous month|Предыдущий месяц"));
        if (prev.exists()) prev.click();
    }

    public void selectDay(String day) throws Exception {
        UiObject dayBtn = device.findObject(new UiSelector().text(day).className("android.view.View"));
        if (dayBtn.exists()) dayBtn.click();
    }

    public void clickOk() throws Exception {
        UiObject ok = device.findObject(new UiSelector().textMatches("(?i)OK|ОК|ГОТОВО"));
        if (ok.exists()) ok.click();
    }

    public void clickCancel() throws Exception {
        UiObject cancel = device.findObject(new UiSelector().textMatches("(?i)CANCEL|ОТМЕНА"));
        if (cancel.exists()) cancel.click();
    }

    public void checkDateInField(int fieldId, String expectedPart) {
        onView(withId(fieldId)).check(matches(withText(containsString(expectedPart))));
    }

    public void checkFieldIsEmpty(int fieldId) {
        onView(withId(fieldId)).check(matches(withText("")));
    }

    private void pickDay(LocalDate targetDate) throws Exception {
        LocalDate now = LocalDate.now();
        if (targetDate.isAfter(now) && targetDate.getMonthValue() != now.getMonthValue()) {
            clickNextMonth();
        }
        if (targetDate.isBefore(now) && targetDate.getMonthValue() != now.getMonthValue()) {
            clickPrevMonth();
        }
        selectDay(String.valueOf(targetDate.getDayOfMonth()));
    }

    public void setDate(LocalDate targetDate) throws Exception {
        pickDay(targetDate);
        clickOk();
    }

    public void cancelDate(LocalDate targetDate) throws Exception {
        pickDay(targetDate);
        clickCancel();
    }
}