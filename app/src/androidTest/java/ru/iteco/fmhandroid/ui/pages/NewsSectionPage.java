package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.not;
import ru.iteco.fmhandroid.R;

public class NewsSectionPage extends BasePage {
    private final int sortBtnId = R.id.sort_news_material_button;
    private final int newsDateId = R.id.news_item_date_text_view;
    private final int filterBtnId = R.id.filter_news_material_button;
    private final int editBtnId = R.id.edit_news_material_button;
    private final int controlPanelNewsListId = R.id.news_list_recycler_view;
    private final int newsRefreshBtnId = R.id.news_retry_material_button;
    private final int newsTitleId = R.id.news_item_title_text_view;
    private final int expandBtnId = R.id.view_news_item_image_view;
    private final int descriptionId = R.id.news_item_description_text_view;
    private final int newsContainerId = R.id.all_news_cards_block_constraint_layout;
    private final int retryBtnId = R.id.news_retry_material_button;

    public String getFirstNewsDate() {

        return getTextFromView(withId(newsDateId), 0);
    }

    public void clickSort() {

        onView(withId(sortBtnId)).perform(click());
    }

    public String getFirstNewsTitle() {
        return getTextFromView(withId(newsTitleId), 0);
    }

    public void checkFirstNewsTitleChanged(String oldTitle) {
        onView(isRoot()).perform(waitTextChange(withIndex(withId(newsTitleId), 0), oldTitle, DEFAULT_TIMEOUT));
        onView(withIndex(withId(newsTitleId), 0)).check(matches(not(withText(oldTitle))));
    }

    public void checkFirstNewsTitle(String expectedTitle) {
        onView(withIndex(withId(newsTitleId), 0))
                .check(matches(withText(expectedTitle)));
    }

    public void checkFirstNewsDate(String expectedDate) {
        onView(withIndex(withId(newsDateId), 0))
                .check(matches(withText(expectedDate)));
    }

    public void checkFirstNewsDateChanged(String oldDate) {
        onView(withIndex(withId(newsDateId), 0))
                .check(matches(not(withText(oldDate))));
    }

    public void clickFilter() {
        onView(isRoot()).perform(waitDisplayed(filterBtnId, SHORT_TIMEOUT));
        onView(withId(filterBtnId)).perform(click());
    }


    public void clickEdit() {
        onView(isRoot()).perform(waitDisplayed(editBtnId, SHORT_TIMEOUT));
        onView(withId(editBtnId)).perform(click());
    }

    public void checkControlPanelIsLoaded() {
        onView(isRoot()).perform(waitDisplayed(controlPanelNewsListId, SHORT_TIMEOUT));
        onView(withText("Control panel")).check(matches(isDisplayed()));
        onView(withId(controlPanelNewsListId)).check(matches(isDisplayed()));
    }

    public void checkEmptyListAndRefreshButtonVisible() {
        onView(isRoot()).perform(waitDisplayed(newsRefreshBtnId, DEFAULT_TIMEOUT));
        onView(withId(newsRefreshBtnId)).check(matches(isDisplayed()));
    }

    public void clickRefresh() {
        onView(withId(newsRefreshBtnId)).perform(click());
    }

    public void expandFirstNews() {
        onView(isRoot()).perform(waitDisplayed(expandBtnId, DEFAULT_TIMEOUT));
        onView(withIndex(allOf(withId(expandBtnId), isDisplayed()), 0))
                .perform(click());
    }

    public void checkDescriptionVisible() {
        onView(withIndex(withId(descriptionId), 0))
                .check(matches(isDisplayed()));
    }

    public void checkFilterResultVisible() {
        onView(isRoot()).perform(waitDisplayed(newsContainerId, SHORT_TIMEOUT));
        onView(withIndex(anyOf(
                allOf(withId(newsContainerId), isDisplayed()),
                allOf(withId(retryBtnId), isDisplayed())
        ), 0)).check(matches(isDisplayed()));
    }
}
