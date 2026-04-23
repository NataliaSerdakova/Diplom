package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.containsString;
import ru.iteco.fmhandroid.R;

public class NewsFilterPage extends BasePage {
    private final int filterTitleId = R.id.filter_news_title_text_view;
    private final int categoryFieldId = R.id.news_item_category_text_auto_complete_text_view;
    private final int dateStartId = R.id.news_item_publish_date_start_text_input_edit_text;
    private final int dateEndId = R.id.news_item_publish_date_end_text_input_edit_text;
    private final int filterActionBtnId = R.id.filter_button;
    private final int cancelBtnId = R.id.cancel_button;

    public void checkFilterFormIsLoaded() {
        onView(isRoot()).perform(waitDisplayed(filterTitleId, SHORT_TIMEOUT));
        onView(withId(filterTitleId)).check(matches(withText("Filter news")));
        onView(withId(categoryFieldId)).check(matches(isDisplayed()));
        onView(withId(dateStartId)).check(matches(isDisplayed()));
        onView(withId(dateEndId)).check(matches(isDisplayed()));
        onView(withId(filterActionBtnId)).check(matches(isDisplayed()));
        onView(withId(cancelBtnId)).check(matches(isDisplayed()));
    }

    public void selectCategory(String category) {
        onView(withId(categoryFieldId)).perform(click());
        onView(isRoot()).perform(closeSoftKeyboard());
        onView(withText(category)).inRoot(isPlatformPopup()).perform(click());
    }

    public void enterStartDate(String date) {
        onView(withId(dateStartId)).perform(replaceText(date), closeSoftKeyboard());
        onView(withId(dateStartId)).check(matches(withText(date)));
    }

    public void enterEndDate(String date) {
        onView(withId(dateEndId)).perform(replaceText(date), closeSoftKeyboard());
        onView(withId(dateEndId)).check(matches(withText(date)));
    }

    public void clickFilterSubmit() {
        onView(withId(filterActionBtnId)).perform(click());
    }

    public void clickCancel() {
        onView(withId(cancelBtnId)).perform(click());
    }

    public void checkWrongPeriodError() {
        onView(withText("Wrong period")).check(matches(isDisplayed()));
        onView(withId(android.R.id.button1)).perform(click());
    }

    public void openStartDateCalendar() {
        onView(isRoot()).perform(waitDisplayed(dateStartId, SHORT_TIMEOUT));
        onView(withId(dateStartId)).perform(click());
    }

    public void checkStartDateFieldIsEmpty() {
        onView(withId(dateStartId)).check(matches(withText("")));
    }

    public void checkStartDateFieldHasText(String expectedText) {
        onView(withId(dateStartId)).check(matches(withText(containsString(expectedText))));
    }

    public void enterCustomCategory(String text) {
        onView(withId(categoryFieldId)).perform(replaceText(text), closeSoftKeyboard());
    }
}
