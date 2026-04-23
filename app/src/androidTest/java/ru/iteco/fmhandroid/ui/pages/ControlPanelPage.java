package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.*;

import androidx.test.espresso.contrib.RecyclerViewActions;

import ru.iteco.fmhandroid.R;

public class ControlPanelPage extends BasePage {
    private final int sortBtnId = R.id.sort_news_material_button;
    private final int filterBtnId = R.id.filter_news_material_button;
    private final int addNewsBtnId = R.id.add_news_image_view;
    private final int editNewsItemBtnId = R.id.edit_news_item_image_view;
    private final int deleteBtnId = R.id.delete_news_item_image_view;
    private final int newsDateId = R.id.news_item_publication_date_text_view;
    private final int newsTitleId = R.id.news_item_title_text_view;
    private final int expandNewsBtnId = R.id.view_news_item_image_view;
    private final int newsDescriptionId = R.id.news_item_description_text_view;
    private final String deleteDialogText = "Are you sure you want to permanently delete the document? These changes cannot be reversed in the future.";
    private final int controlPanelRefreshBtnId = R.id.control_panel_news_retry_material_button;
    private final int controlPanelContainerId = R.id.container_custom_app_bar_include_on_fragment_news_control_panel;
    private final int newsListId = R.id.news_list_recycler_view;
    private final int newsStatusId = R.id.news_item_published_text_view;

    public void checkControlPanelLoaded() {
        onView(isRoot()).perform(waitTextDisplayed("Control panel", DEFAULT_TIMEOUT));
        onView(withText("Control panel")).check(matches(isDisplayed()));
    }

    public void clickSort() {
        onView(withId(sortBtnId)).perform(click());
    }

    public void clickFilter() {
        onView(withId(filterBtnId)).perform(click());
    }

    public void clickAddNews() {
        onView(isRoot()).perform(waitDisplayed(addNewsBtnId, SHORT_TIMEOUT));
        onView(withId(addNewsBtnId)).perform(click());
    }

    public void clickEditNews(int index) {
        onView(withIndex(withId(editNewsItemBtnId), index)).perform(click());
    }

    public void clickExpandNews(int index) {
        onView(withIndex(withId(expandNewsBtnId), index)).perform(click());
    }

    public String getFirstNewsTitle() {
        return getTextFromView(withId(newsTitleId), 0);
    }

    public void checkNewsWithTitleExists(String title) {
        onView(withId(newsListId))
                .perform(RecyclerViewActions.scrollTo(
                        hasDescendant(withText(title))
                ));
        onView(withText(title)).check(matches(isDisplayed()));
    }

    public void checkNewsWithTitleAndDescriptionExists(String title, String description) {
        // Ищем в списке карточку, которая содержит оба текста одновременно
        onView(withId(newsListId))
                .perform(RecyclerViewActions.scrollTo(
                        allOf(
                                hasDescendant(withText(title)),
                                hasDescendant(withText(description))
                        )
                ));

        // Проверяем, что заголовок виден
        onView(allOf(withText(title), isDescendantOfA(withId(newsListId))))
                .check(matches(isDisplayed()));
    }

    public void clickDeleteNews(int index) {
        onView(withIndex(withId(deleteBtnId), index)).perform(click());
    }

    public void checkDeleteDialogDisplayed() {
        onView(withText(deleteDialogText)).check(matches(isDisplayed()));
    }

    public void confirmDeletion() {
        onView(withId(android.R.id.button1)).perform(click());
    }

    public void cancelDeletion() {
        onView(withId(android.R.id.button2)).perform(click());
    }

    public String getFirstNewsDate() {
        return getTextFromView(withId(newsDateId), 0);
    }


    public void checkFirstNewsDateChanged(String oldDate) {
        onView(withIndex(withId(newsDateId), 0))
                .check(matches(not(withText(oldDate))));
    }

    public void checkFirstNewsDate(String expectedDate) {
        onView(withIndex(withId(newsDateId), 0))
                .check(matches(withText(expectedDate)));
    }

    public void checkDescriptionVisible(int index) {
        onView(withIndex(withId(newsDescriptionId), index)).check(matches(isDisplayed()));
    }

    public void checkDescriptionText(int index, String expectedText) {
        onView(withIndex(withId(newsDescriptionId), index))
                .check(matches(withText(expectedText)));
    }

    public void checkFirstNewsStatus(String expectedStatus) {
        onView(withIndex(withId(newsStatusId), 0))
                .check(matches(withText(expectedStatus)));
    }

    public void checkEmptyListAndRefreshButtonVisible() {
        onView(isRoot()).perform(waitDisplayed(controlPanelRefreshBtnId, DEFAULT_TIMEOUT));
        onView(withId(controlPanelRefreshBtnId)).check(matches(isDisplayed()));
    }

    public void checkFilterResultVisible() {
        onView(isRoot()).perform(waitDisplayed(controlPanelContainerId, SHORT_TIMEOUT));
        onView(anyOf(
                allOf(withId(newsListId), isDisplayed()),
                allOf(withId(controlPanelRefreshBtnId), isDisplayed())
        )).check(matches(isDisplayed()));
    }

    public void checkFirstNewsStatusIfPresent(String expectedStatus) {
        try {
            onView(withIndex(withId(newsStatusId), 0))
                    .check(matches(isDisplayed()));
            checkFirstNewsStatus(expectedStatus);
        } catch (AssertionError | Exception e) {
            checkEmptyListAndRefreshButtonVisible();
        }
    }

    public void checkNewsCountDecreased(String title, int initialCount) {
        waitForIdle(SHORT_TIMEOUT);
        int currentCount = getNewsCount(title);
        if (currentCount != initialCount - 1) {
            throw new AssertionError("Количество новостей не уменьшилось! Было: " + initialCount + ", стало: " + currentCount);
        }
    }

    public int getNewsCount(String title) {
        return getCount(allOf(withId(newsTitleId), withText(title)));
    }

    public void checkDescriptionByTitle(String title, String description) {
        // 1. Скроллим к нужной новости по связке заголовок+описание
        onView(withId(newsListId))
                .perform(RecyclerViewActions.scrollTo(
                        allOf(
                                hasDescendant(withText(title)),
                                hasDescendant(withText(description))
                        )
                ));

        // 2. Кликаем "развернуть" именно у той новости, где лежит наше описание
        // Это решит проблему, если заголовков "Л" несколько
        onView(allOf(withId(expandNewsBtnId), hasSibling(withText(title)),
                hasSibling(withText(description))))
                .perform(click());

        // 3. Финальная проверка, что описание отобразилось
        onView(withText(description)).check(matches(isDisplayed()));
    }
}
