package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.*;
import android.view.View;
import android.widget.CompoundButton;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import org.hamcrest.Matcher;
import java.time.LocalDate;
import ru.iteco.fmhandroid.R;

public class NewsEditorPage extends BasePage {

    private final CalendarPage calendarPage = new CalendarPage();
    private final TimePage timePage = new TimePage();
    private final int categoryFieldId = R.id.news_item_category_text_auto_complete_text_view;
    private final int titleFieldId = R.id.news_item_title_text_input_edit_text;
    private final int dateFieldId = R.id.news_item_publish_date_text_input_edit_text;
    private final int timeFieldId = R.id.news_item_publish_time_text_input_edit_text;
    private final int descriptionFieldId = R.id.news_item_description_text_input_edit_text;
    private final int saveBtnId = R.id.save_button;
    private final int cancelBtnId = R.id.cancel_button;
    private final int switcherId = R.id.switcher;
    private final int titleInputLayoutId = R.id.news_item_title_text_input_layout;
    private final int descriptionLayoutId = R.id.news_item_description_text_input_layout;
    public static final String EMPTY_FIELDS_ERROR = "Fill empty fields";



    public void checkAddNewsFormIsLoaded() {
        onView(isRoot()).perform(waitDisplayed(titleFieldId, SHORT_TIMEOUT));
        onView(withText("Creating")).check(matches(isDisplayed()));
        onView(withId(categoryFieldId)).check(matches(isDisplayed()));
        onView(withId(titleFieldId)).check(matches(isDisplayed()));
        onView(withId(dateFieldId)).check(matches(isDisplayed()));
        onView(withId(timeFieldId)).check(matches(isDisplayed()));
        onView(withId(descriptionFieldId)).check(matches(isDisplayed()));
        onView(withId(switcherId)).check(matches(allOf(isDisplayed(), isChecked(), not(isEnabled()))));
        onView(withId(saveBtnId)).check(matches(isDisplayed()));
        onView(withId(cancelBtnId)).check(matches(isDisplayed()));
    }

    public void checkEditNewsFormIsLoaded() {
        onView(isRoot()).perform(waitDisplayed(titleFieldId, SHORT_TIMEOUT));
        onView(withText("Editing")).check(matches(isDisplayed()));
        onView(withId(categoryFieldId)).check(matches(isDisplayed()));
        onView(withId(titleFieldId)).check(matches(isDisplayed()));
        onView(withId(dateFieldId)).check(matches(isDisplayed()));
        onView(withId(timeFieldId)).check(matches(isDisplayed()));
        onView(withId(descriptionFieldId)).check(matches(isDisplayed()));
        onView(withId(switcherId)).check(matches(isDisplayed()));
        onView(withId(saveBtnId)).check(matches(isDisplayed()));
        onView(withId(cancelBtnId)).check(matches(isDisplayed()));
    }

    public void checkEditFormLoaded() {
        onView(isRoot()).perform(waitDisplayed(titleFieldId, SHORT_TIMEOUT));
    }

    public void selectCategory(String category) {
        onView(withId(categoryFieldId)).perform(replaceText(""));
        onView(withId(categoryFieldId)).perform(click(), closeSoftKeyboard());
        onView(withText(category))
                .inRoot(isPlatformPopup())
                .perform(click());
    }

    public void enterTitle(String title) {
        onView(withId(titleFieldId)).perform(replaceText(title), closeSoftKeyboard());
    }

    public void enterDate(String date) {
        onView(withId(dateFieldId)).perform(replaceText(date), closeSoftKeyboard());
    }

    public void enterTime(String time) {
        onView(withId(timeFieldId)).perform(replaceText(time), closeSoftKeyboard());
    }

    public void enterDescription(String description) {
        onView(withId(descriptionFieldId)).perform(scrollTo(), replaceText(description), closeSoftKeyboard());
    }

    public void checkActiveSwitcherStatus() {
        onView(withId(switcherId)).check(matches(isChecked()));
    }

    public void clickSave() {
        onView(withId(saveBtnId)).perform(click());
    }

    public void openTimePicker() {
        onView(withId(timeFieldId)).perform(click());
    }

    public String getCategoryText() {
        return getTextFromView(withId(categoryFieldId), 0);
    }

    public String getTitleText() {
        return getTextFromView(withId(titleFieldId), 0);
    }

    public String getDateText() {
        return getTextFromView(withId(dateFieldId), 0);
    }

    public String getTimeText() {
        return getTextFromView(withId(timeFieldId), 0);
    }

    public void checkCategoryText(String expectedText) {
        onView(withId(categoryFieldId)).check(matches(withText(expectedText)));
    }

    public void checkTitleText(String expectedText) {
        onView(withId(titleFieldId)).check(matches(withText(expectedText)));
    }

    public void checkDateText(String date) {
        onView(withId(dateFieldId)).check(matches(withText(date)));
    }

    public void checkNewsDate(String date) {
        onView(withText(date)).check(matches(isDisplayed()));
    }

    public void checkTimeText(String expectedTime) {
        onView(withId(timeFieldId)).check(matches(withText(expectedTime)));
    }

    public void checkSaveButtonIsDisplayed() {
        onView(withId(saveBtnId)).check(matches(isDisplayed()));
    }

    public void checkEmptyFieldsToast() {
        onView(withText(EMPTY_FIELDS_ERROR))
                .inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    public void checkTitleFieldEmptyError() {
        onView(withId(titleInputLayoutId))
                .perform(scrollTo())
                .check(matches(hasDescendant(withId(com.google.android.material.R.id.text_input_end_icon))));
    }

    public void checkDescriptionFieldEmptyError() {
        onView(withId(descriptionLayoutId))
                .perform(scrollTo())
                .check(matches(hasDescendant(withId(com.google.android.material.R.id.text_input_end_icon))));
    }

    public void selectDateFromCalendar(LocalDate date) throws Exception {
        calendarPage.openCalendarForField(dateFieldId);
        calendarPage.setDate(date);
    }

    public void selectTimeFromPicker(String hours, String minutes) throws Exception {
        onView(withId(timeFieldId)).perform(click());
        timePage.selectTime(hours, minutes);
        timePage.clickOk();
    }

    public void clickCancel() {
        onView(withId(cancelBtnId)).perform(scrollTo(), click());
    }

    public void checkCancelDialogText() {
        String dialogText = "The changes won't be saved, do you really want to log out?";
        onView(withText(dialogText)).check(matches(isDisplayed()));
    }

    public void confirmCancelDialog() {
        onView(withId(android.R.id.button1)).perform(click());
    }

    public void checkDateIsNotYesterday(String yesterday) {
        onView(withId(dateFieldId)).check(matches(not(withText(containsString(yesterday)))));
    }

    public void enterCustomCategory(String text) {
        onView(withId(categoryFieldId)).perform(replaceText(text), closeSoftKeyboard());
    }

    public void checkSavingFailedToast() {
        onView(withText("Saving failed. Try again later."))
                .inRoot(new BasePage.ToastMatcher())
                .check(matches(isDisplayed()));
    }

    public void openCalendar() {
        onView(withId(dateFieldId)).perform(click());
    }

    public void toggleActiveStatus(boolean shouldBeActive) {
        onView(withId(switcherId)).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(CompoundButton.class);
            }
            @Override
            public String getDescription() {
                return "установка статуса активен/неактивен";
            }
            @Override
            public void perform(UiController uiController, View view) {
                CompoundButton switchView = (CompoundButton) view;
                if (switchView.isChecked() != shouldBeActive) {
                    switchView.performClick();
                }
            }
        });
    }
}
